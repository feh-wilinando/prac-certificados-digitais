package br.com.fws.certificado_digital.services;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class EncryptorServiceTest {

	@Test
	public void shouldConvertRawPasswordToSha256AndBase64(){
					
		EncryptorService service = new EncryptorService();
		String password = "jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=";
		
		String passwordGenerated = service.encrypt("123456");
		
		assertThat(passwordGenerated, equalTo(password));
		
	}
	
}
