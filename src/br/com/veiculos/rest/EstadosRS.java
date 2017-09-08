package br.com.veiculos.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.veiculos.business.EstadoBC;
import br.com.veiculos.business.ValidacaoException;
import br.com.veiculos.model.Estado;
import br.com.veiculos.security.JWTRequired;

@JWTRequired
@Path("estados")
public class EstadosRS {

	@Inject
	private EstadoBC estadoBC;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estado> selecionar() {
		return estadoBC.selecionar();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Estado selecionar(@PathParam("id") Long id) {
		try {
			return estadoBC.selecionar(id);
		} catch (Exception e) {
			throw new NotFoundException();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserir(Estado body) {
		try {
			Long id = estadoBC.inserir(body);
			String url = "/api/estados/" + id;
			return Response.status(Status.CREATED).header("Location", url).entity(id).build();
		} catch (ValidacaoException e) {
			return tratarValidacaoException(e);
		}
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizar(@PathParam("id") Long id, Estado estado) {
		try {
			estado.setId(id);
			estadoBC.atualizar(estado);
			return Response.status(Status.OK).entity(id).build();
		} catch (ValidacaoException e) {
			return tratarValidacaoException(e);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluir(@PathParam("id") Long id) {
		try {
			Estado estado = estadoBC.excluir(id);

			return Response.status(Status.OK).entity(estado).build();
		} catch (Exception e) {
			throw new NotFoundException();
		}
	}

	private Response tratarValidacaoException(ValidacaoException e) {
		return Response.status(Status.NOT_ACCEPTABLE).entity(e.getErros()).build();
	}
}
