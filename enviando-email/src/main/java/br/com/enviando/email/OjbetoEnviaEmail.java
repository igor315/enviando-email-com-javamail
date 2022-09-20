package br.com.enviando.email;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class OjbetoEnviaEmail {

	private String userName = "minhacontadetestes.developer@gmail.com";
	private String senha = "izydsmcgxbaqnaqf";
	
	private String listaDestinatarios = "";
	private String nomeRemetente = "Igor Santos 😎 - Aluno do curso Jdev Treinamento";
	private String assuntoEmail = "Chegou o e-mail enviado com Java 😀";
	private String textoEmail = "Olá programador você acabou de receber um E-mail enviado com Java, do curso Formação Java Web do Alex";
	
	public OjbetoEnviaEmail(String listaDestinatarios, String nomeRemetente, String assuntoEmail, String textoEmail) {
		this.listaDestinatarios = listaDestinatarios;
		this.nomeRemetente = nomeRemetente;
		this.assuntoEmail = assuntoEmail;
		this.textoEmail = textoEmail;
	}
	
	
	public void enviarEmail() {
		
		/* Olhe as configurações do smtp do seu email */
		/*Email do gmail criado: minhacontadetestes.developer@gmail.com - senha: 01030506 */
		/*senha usada para autenticar aqui izydsmcgxbaqnaqf */

		try {
			
			Properties properties = new Properties();
			/*no java mail tutorial encontro as configuracoes necessarias para usar o smtp*/
			properties.put("mail.smtp.ssl.trust", "*");
			properties.put("mail.smtp.auth", "true");/*Autorizacao*/
			properties.put("mail.smtp.starttls", "true");/*Autenticacao*/
			properties.put("mail.smtp.host", "smtp.gmail.com");/*Servidor gmail Google*/
			properties.put("mail.smtp.port", "465");/*Porta do servidor*/
			properties.put("mail.smtp.socketFactory.port", "465");/*Expecifica a porta a ser conectada pelo socket*/
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");/*classe socket de conexao ao SMTP*/
			
			/*inicia uma sessao com as configuracoes acima do nosso properties*/
			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, senha);
				}
			});
			
			Address[] toUser = InternetAddress.parse(listaDestinatarios);
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userName, nomeRemetente));/*Quem esta enviando*/
			message.setRecipients(Message.RecipientType.TO, toUser);/*E-mail de destino*/
			message.setSubject(assuntoEmail);/*Assunto do E-mail*/
			message.setText(textoEmail);/*Corpo do E-mail*/
			
			Transport.send(message);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
