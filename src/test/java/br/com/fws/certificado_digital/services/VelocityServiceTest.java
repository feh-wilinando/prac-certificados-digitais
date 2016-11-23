package br.com.fws.certificado_digital.services;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.stella.type.Estado;
import br.com.fws.certificado_digital.models.Address;
import br.com.fws.certificado_digital.models.Customer;
import br.com.fws.certificado_digital.models.users.User;

public class VelocityServiceTest {

	private static VelocityEngine velocityEngine;
	private static Customer customer;


	@BeforeClass
	public static void preTest(){
		URL templateURL = VelocityEngine.class.getResource("/template_velocity/");
		
		velocityEngine = new VelocityEngine();
		velocityEngine.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, templateURL.getPath());
		velocityEngine.init();
		
		Address address = new Address("Rua da Empresa", 1000, "18º andar", "São Paulo", Estado.SP, "11111-111");		
		User user = new User("contato@empresa.com.br", "123");
		
		customer = new Customer(1L, "Nome da Empresa", "11.111.111/1111-11", "", "1.111.111-1", address, user, "");
	}
	
	@Test
	public void shouldBeLoadEmailTemplate(){
						
		
		
		VelocityService velocityService = new VelocityService(velocityEngine);
		
		String content = velocityService
							.loadTemplate("email.vm")
							.getContent();
		
		assertThat(content, containsString("<strong>Empresa:</strong>"));
		assertThat(content, containsString("<strong>CNPJ:</strong>"));
		
		
	}
	
	@Test
	public void shouldFillOutTheTemplateEmailWithACustomer(){
		VelocityService velocityService = new VelocityService(velocityEngine);
		
		
		
		
		String content = velocityService
							.loadTemplate("email.vm")
								.set("customer", customer)
								.getContent();
		
		assertThat(content, allOf(		containsString(customer.getCompanyName()),
										containsString(customer.getCompanyRegistration()),
										containsString(customer.getMunicipalRegistration()),
										containsString(customer.getUser().getEmail())
										
									)
				);
		

	}
	
}
