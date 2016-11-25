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
import br.com.fws.certificado_digital.models.customer.Address;
import br.com.fws.certificado_digital.models.customer.Customer;

public class VelocityServiceTest {

	private static VelocityEngine velocityEngine;
	private static Customer customer;


	@BeforeClass
	public static void preTest(){
		URL templateURL = VelocityEngine.class.getResource("/template_velocity/");
		
		velocityEngine = new VelocityEngine();
		velocityEngine.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, templateURL.getPath());
		velocityEngine.init();
		
		Address address = new Address();//("Rua da Empresa", 1000, "18º andar", "São Paulo", Estado.SP, "11111-111");
		address.setStreet("Rua da Empresa");
		address.setNumber(1000);
		address.setComplement("18º andar");
		address.setCity("São Paulo");
		address.setState(Estado.SP);
		address.setZipCode("11111-111");
		
		customer = new Customer(); 
		customer.setId(1L);
		customer.setCompanyName("Nome da Empresa");
		customer.setCompanyRegistration("11.111.111/1111-11");
		customer.setStateRegistration("2.222.222-2");
		customer.setMunicipalRegistration("1.111.111-1");
		customer.setAddress(address);
		customer.setContact("Contato da Empresa");
		customer.setEmail("email@empresa.com.br");		
		customer.setTelephoneNumber("11 2323-2323");
		customer.setCellPhoneNumber("11 9 9999-9999");
		
	}
	
	@Test
	public void shouldBeLoadEmailTemplate(){
						
		
		
		VelocityService velocityService = new VelocityService(velocityEngine);
		
		String content = velocityService
							.loadTemplate("customer.vm")
							.getContent();
		
		assertThat(content, containsString("<strong>Empresa:</strong>"));
		assertThat(content, containsString("<strong>CNPJ:</strong>"));
		
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void shouldFillOutTheTemplateEmailWithACustomer(){
		VelocityService velocityService = new VelocityService(velocityEngine);
		
		
		
		
		String content = velocityService
							.loadTemplate("customer.vm")
								.set("customer", customer)
								.getContent();
		
		
		assertThat(content, allOf(		containsString(customer.getCompanyName()),
										containsString(customer.getCompanyRegistration()),
										containsString(customer.getMunicipalRegistration()),
										containsString(customer.getStateRegistration()),
										containsString(customer.getContact()),
										containsString(customer.getEmail()),
										containsString(customer.getTelephoneNumber()),
										containsString(customer.getCellPhoneNumber()),
										containsString(customer.getAddress().getStreet()),
										containsString(customer.getAddress().getNumber().toString()),										
										containsString(customer.getAddress().getComplement()),										
										containsString(customer.getAddress().getZipCode()),										
										containsString(customer.getAddress().getCity()),										
										containsString(customer.getAddress().getState().name())
										
										
									)
				);
		

	}
	
}
