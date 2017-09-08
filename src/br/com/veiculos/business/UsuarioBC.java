package br.com.veiculos.business;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import br.com.veiculos.dao.Repositorio;
import br.com.veiculos.model.Cep;
import br.com.veiculos.model.Usuario;

@ApplicationScoped
public class UsuarioBC {

	@Inject
	private Repositorio repositorio;

	@PostConstruct
	public void inicializar() {

		Calendar data = Calendar.getInstance();
		Cep cep = new Cep();
		cep.setRegiao("00000");
		cep.setSufixo("000");
		Usuario usuario = new Usuario();
		usuario.setNome("Maria José");
		usuario.setEmail("maria.jose@gmail.com");
		usuario.setSenha("123456");
		usuario.setCpf("11111111111");
		usuario.setCep(cep);
		data.set(1975, 7, 20);
		usuario.setData(data.getTime());
		repositorio.inserir(usuario);

		usuario = new Usuario();
		usuario.setNome("Paulo Roberto");
		usuario.setEmail("paulo.roberto@gmail.com");
		usuario.setSenha("123456");
		usuario.setCpf("22222222222");
		data.set(1873, 8, 12);
		usuario.setCep(cep);
		usuario.setData(data.getTime());
		repositorio.inserir(usuario);

		usuario = new Usuario();
		usuario.setNome("Isabel Felix");
		usuario.setEmail("maria.isabel@gmail.com");
		usuario.setSenha("123456");
		usuario.setCpf("33333333333");
		data.set(1900, 4, 17);
		usuario.setCep(cep);
		usuario.setData(data.getTime());
		repositorio.inserir(usuario);
	}

	public Usuario autenticarUsuario(String cpf, String senha) throws UsuarioInvalidoException {
		List<Usuario> usuarios = selecionar();
		for (Usuario usuario : usuarios) {
			if (usuario.getCpf().equals(cpf) && usuario.getSenha().equals(senha)) {
				return usuario;
			}
		}
		throw new UsuarioInvalidoException();
	}

	public List<Usuario> selecionar() {
		return repositorio.selecionar(Usuario.class);
	}

	public Usuario selecionar(Long id) throws UsuarioNaoEncontradoException {
		Usuario usuario = repositorio.selecionar(Usuario.class, id);
		if (usuario == null) {
			throw new UsuarioNaoEncontradoException();
		}
		return usuario;
	}

	public Long inserir(Usuario usuario) throws ValidacaoException {
		validar(usuario);
		return repositorio.inserir(usuario);
	}

	public void atualizar(Usuario usuario) throws UsuarioNaoEncontradoException, ValidacaoException {
		validar(usuario);
		if (!repositorio.atualizar(usuario)) {
			throw new UsuarioNaoEncontradoException();
		}
	}

	public Usuario excluir(Long id) throws UsuarioNaoEncontradoException {
		Usuario usuario = repositorio.excluir(Usuario.class, id);
		if (usuario == null) {
			throw new UsuarioNaoEncontradoException();
		}
		return usuario;
	}

	private void validar(Usuario usuario) throws ValidacaoException {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		if (!violations.isEmpty()) {
			ValidacaoException validacaoException = new ValidacaoException();
			for (ConstraintViolation<Usuario> violation : violations) {
				String entidade = violation.getRootBeanClass().getSimpleName();
				String propriedade = violation.getPropertyPath().toString();
				String mensagem = violation.getMessage();

				validacaoException.adicionar(entidade, propriedade, mensagem);
			}
			throw validacaoException;
		}
	}
}