package br.com.enviando.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class OjbetoEnviaEmail {

	private String userName = "minhacontadetestes.developer@gmail.com";
	private String senha = "izydsmcgxbaqnaqf";

	private String listaDestinatarios = "";
	private String nomeRemetente = "";
	private String assuntoEmail = "";
	private String textoEmail = "";

	public OjbetoEnviaEmail(String listaDestinatarios, String nomeRemetente, String assuntoEmail, String textoEmail) {
		this.listaDestinatarios = listaDestinatarios;
		this.nomeRemetente = nomeRemetente;
		this.assuntoEmail = assuntoEmail;
		this.textoEmail = textoEmail;
	}

	public void enviarEmail(boolean envioHtml) {

		/* Olhe as configurações do smtp do seu email */
		/*
		 * Email do gmail criado: minhacontadetestes.developer@gmail.com - senha:
		 * 01030506
		 */
		/* senha usada para autenticar aqui izydsmcgxbaqnaqf */

		try {

			Properties properties = new Properties();
			/*
			 * no java mail tutorial encontro as configuracoes necessarias para usar o smtp
			 */
			properties.put("mail.smtp.ssl.trust", "*");
			properties.put("mail.smtp.auth", "true");/* Autorizacao */
			properties.put("mail.smtp.starttls", "true");/* Autenticacao */
			properties.put("mail.smtp.host", "smtp.gmail.com");/* Servidor gmail Google */
			properties.put("mail.smtp.port", "465");/* Porta do servidor */
			properties.put("mail.smtp.socketFactory.port", "465");/* Expecifica a porta a ser conectada pelo socket */
			properties.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");/* classe socket de conexao ao SMTP */

			/* inicia uma sessao com as configuracoes acima do nosso properties */
			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, senha);
				}
			});

			Address[] toUser = InternetAddress.parse(listaDestinatarios);

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userName, nomeRemetente));/* Quem esta enviando */
			message.setRecipients(Message.RecipientType.TO, toUser);/* E-mail de destino */
			message.setSubject(assuntoEmail);/* Assunto do E-mail */

			if (envioHtml) {
				message.setContent(textoEmail, "text/html; charset=utf-8");
			} else {
				message.setText(textoEmail);/* Corpo do E-mail */
			}
			
			
			Transport.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	
	/*Envia em com anexo*/
	public void enviarEmailAnexo(boolean envioHtml) {

		/* Olhe as configurações do smtp do seu email */
		/*
		 * Email do gmail criado: minhacontadetestes.developer@gmail.com - senha:
		 * 01030506
		 */
		/* senha usada para autenticar aqui izydsmcgxbaqnaqf */

		try {

			Properties properties = new Properties();
			/*
			 * no java mail tutorial encontro as configuracoes necessarias para usar o smtp
			 */
			properties.put("mail.smtp.ssl.trust", "*");
			properties.put("mail.smtp.auth", "true");/* Autorizacao */
			properties.put("mail.smtp.starttls", "true");/* Autenticacao */
			properties.put("mail.smtp.host", "smtp.gmail.com");/* Servidor gmail Google */
			properties.put("mail.smtp.port", "465");/* Porta do servidor */
			properties.put("mail.smtp.socketFactory.port", "465");/* Expecifica a porta a ser conectada pelo socket */
			properties.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");/* classe socket de conexao ao SMTP */

			/* inicia uma sessao com as configuracoes acima do nosso properties */
			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, senha);
				}
			});

			Address[] toUser = InternetAddress.parse(listaDestinatarios);

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userName, nomeRemetente));/* Quem esta enviando */
			message.setRecipients(Message.RecipientType.TO, toUser);/* E-mail de destino */
			message.setSubject(assuntoEmail);/* Assunto do E-mail */
			
			
			/*Parte 1 do e-mail e o texto e a descricao do email*/
			MimeBodyPart corpoEmail = new MimeBodyPart();
			
			
			if (envioHtml) {
				corpoEmail.setContent(textoEmail, "text/html; charset=utf-8");
			} else {
				corpoEmail.setText(textoEmail);/* Corpo do E-mail */
			}

			
			/*Parte 2 do e-mail que sao os anexos em PDF*/
			MimeBodyPart anexoEmail = new MimeBodyPart();
			
			/*Onde é passado o simuladorDePDF voce passa o seu arquivo gravado no banco de dados */
			anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(simuladordePDF(), "application/pdf")));
			anexoEmail.setFileName("anexoemail.pdf");
			
			/*Junta as duas partes do e-mail*/
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(corpoEmail);
			multipart.addBodyPart(anexoEmail);
			
			message.setContent(multipart);

			Transport.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	/*
	 * Esse metodo simula o PDF ou arquivos que podem ser enviador por anexo voce
	 * pode pegar arquivos no seu banco de dados, base64, byte[], Stream de aqruivos
	 * Pode estar em um banco de dados ou em uma pasta
	 * 
	 * Retorna um PDF em branco com o texto do paragrafo de exemplo
	 */
	private FileInputStream simuladordePDF() throws Exception {
		Document document = new Document();
		File file = new File("fileanexo.pdf");
		file.createNewFile();
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		document.add(new Paragraph("Conteudo do PDF anexo com Java Mail, esse texto é do PDF"));
		document.close();

		return new FileInputStream(file);
	}

}
