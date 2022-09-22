package br.com.enviando.email;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

	
	
	@Test
	public void testeEmail() throws Exception {

		StringBuilder stringBuilderTextoEmail = new StringBuilder();
		
		stringBuilderTextoEmail.append("Olá programador! <br/><br/>");
		stringBuilderTextoEmail.append("Aprenda Java e programe em qualquer plataforma: <br/><br/> ");
		stringBuilderTextoEmail.append("Uma das grandes vantagens do Java é que ele além de ser uma linguagem é uma plataforma de desenvolvimento. Com ele é possível desenvolver aplicações para desktop, celular, cartão, web, televisão digital, etc.<br/><br/>");
		stringBuilderTextoEmail.append("Para saber mais acesse o link abaixo.<br/><br/>");
		
		stringBuilderTextoEmail.append("<b>Login: @teste</b><br/>");
		stringBuilderTextoEmail.append("<b>Senha: prx123</b><br/>");
		
		stringBuilderTextoEmail.append("<a target=\"_blank\" href=\"https://projetojavaweb.com/certificado-aluno/login\" "
				+ "					   style=\"color:#2525a7; padding: 14px 25px; text-aling: center; text-decoration: none; display: inline-block; border-radius:30x; font-size:20px; font-family:courier; border:3px solid green; background-color:#99DA39;\">Acessar Portal</a><br/><br/>");
		
		stringBuilderTextoEmail.append("<span style=\"font-size:8px\" >Ass.: Igor Santos😎 aluno do curso Jdev Treinamento</span>");
		
		OjbetoEnviaEmail enviaEmail = new OjbetoEnviaEmail("iiigor315@gmail.com, minhacontadetestes.developer@gmail.com", "Igor Santos 😎 - Aluno do curso Jdev Treinamento", 
														   "Porque estudar Java? 😀", stringBuilderTextoEmail.toString());
		
		enviaEmail.enviarEmailAnexo(true);		
	}
}
