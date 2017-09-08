package br.com.veiculos.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.veiculos.business.UsuarioBC;
import br.com.veiculos.business.UsuarioInvalidoException;
import br.com.veiculos.model.Usuario;
import br.com.veiculos.security.JWTUtil;

@Path("login")
public class LoginRS {
	
	@Inject
	private UsuarioBC usuarioBC;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response login(Usuario usuario) {
		try {
			String cpf = usuario.getCpf();
			String senha = usuario.getSenha();
			usuarioBC.autenticarUsuario(cpf, senha);
			return Response.status(Response.Status.OK)
					.entity("Usu�rio autorizado")
					.header("jwt", JWTUtil.criar(cpf))
					.build();
		} catch (UsuarioInvalidoException e) {
			return Response.status(Response.Status.UNAUTHORIZED)
				.entity("Usu�rio ou senha inv�lidos")
				.build();
		}
	}

}