package br.com.fws.certificado_digital.resources;

import java.net.URI;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.fws.certificado_digital.dao.CustomerDao;
import br.com.fws.certificado_digital.models.customer.Customer;

@Path("customer")
public class CustomerResources {

	@Inject
	private CustomerDao dao;

	@GET
	public String defaultGet() {
		return "Tá aí a parada ";
	}

	@Path("{hash:[a-zA-Z-0-9_+/=]+}")
	@GET
	@Transactional
	public Response activation(@PathParam("hash") String hash) {

		try {
			Customer customer = dao.loadByHash(hash);

			Response response;

			if (customer.isActive()) {
				URI location = URI.create("/customer/activation-already-done");
				response = Response.temporaryRedirect(location).build();
			} else {

				customer.setActive(true);

				dao.update(customer);

				URI location = URI.create("/customer/activation-success");
				response = Response.temporaryRedirect(location).build();
			}

			return response;

		} catch (NoResultException e) {
			return Response.status(Status.BAD_REQUEST).build();
		}

	}

}
