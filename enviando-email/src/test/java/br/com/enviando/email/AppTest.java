package br.com.enviando.email;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

	
	
	@Test
	public void testeEmail() throws Exception {

		OjbetoEnviaEmail enviaEmail = new OjbetoEnviaEmail("iiigor315@gmail.com, minhacontadetestes.developer@gmail.com", "Igor Santos 😎 - Aluno do curso Jdev Treinamento", 
														   "Chegou o e-mail enviado com Java 😀", "Olá programador você acabou de receber um E-mail enviado com Java, do curso Formação Java Web do Alex");
		
		enviaEmail.enviarEmail();		
	}
}
