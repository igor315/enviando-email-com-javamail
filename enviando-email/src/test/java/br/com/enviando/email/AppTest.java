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

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

	private String userName = "minhacontadetestes.developer@gmail.com";
	private String senha = "izydsmcgxbaqnaqf";
	
	@Test
	public void testeEmail() {

		/* Olhe as configurações do smtp do seu email */
		/*Email do gmail criado: minhacontadetestes.developer@gmail.com - senha: 01030506 */
		/*senha sisop izydsmcgxbaqnaqf */

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
			
			Address[] toUser = InternetAddress.parse("minhacontadetestes.developer@gmail.com, iiigor315@gmail.com");
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userName, "Igor Santos 😎 - Aluno do curso Jdev Treinamento"));/*Quem esta enviando*/
			message.setRecipients(Message.RecipientType.TO, toUser);/*E-mail de destino*/
			message.setSubject("Chegou o e-mail enviado com Java 😀");/*Assunto do E-mail*/
			message.setText("Olá programador você acabou de receber um E-mail enviado com Java, do curso Formação Java Web do Alex");/*Corpo do E-mail*/
			
			Transport.send(message);
			
			/*Caso o email não esteja sendo enviado entao
			 * coloque um tempo de espera mais isso só pode 
			 * ser usado para testes
			 * Thread.sleep(5000);
			 */
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
