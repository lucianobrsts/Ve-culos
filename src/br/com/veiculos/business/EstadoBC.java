package br.com.veiculos.business;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.NotFoundException;

import br.com.veiculos.dao.Repositorio;
import br.com.veiculos.model.Estado;

@ApplicationScoped
public class EstadoBC {

	@Inject
	private Repositorio repositorio;
	
	@PostConstruct
	public void inicializar() {
		
	Estado est = new Estado();
	est.setData( new Date());
	est.setNome("Maranhão");
	est.setPib(4);
	est.setPopulacao(735);
	est.setSigla("MA");
	repositorio.inserir(est);
	
	Estado est2 = new Estado();
	est2.setData( new Date());
	est2.setNome("Ceará");
	est2.setPib(7);
	est2.setPopulacao(500);
	est2.setSigla("CE");
	repositorio.inserir(est2);
	
	}
	
	public List<Estado> selecionar() {
		return repositorio.selecionar(Estado.class);
	}

	public Estado selecionar(Long id) throws UsuarioNaoEncontradoException {
		Estado estado = repositorio.selecionar(Estado.class, id);
		if (estado == null) {
			throw new UsuarioNaoEncontradoException();
		}
		return estado;
	}

	public Long inserir(Estado estado) throws ValidacaoException {
		validar(estado);
		return repositorio.inserir(estado);
	}

	public void atualizar(Estado estado) throws ValidacaoException {
		validar(estado);
		if (!repositorio.atualizar(estado)) {
			throw new NotFoundException();
		}
	}

	public Estado excluir(Long id) throws UsuarioNaoEncontradoException {
		Estado estado = repositorio.excluir(Estado.class, id);
		if (estado == null) {
			throw new UsuarioNaoEncontradoException();
		}
		return estado;
	}
	
	private void validar(Estado estado) throws ValidacaoException {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Estado>> violations = validator.validate(estado);
		if (!violations.isEmpty()) {
			ValidacaoException validacaoException = new ValidacaoException();
			for (ConstraintViolation<Estado> violation : violations) {
				String entidade = violation.getRootBeanClass().getSimpleName();
				String propriedade = violation.getPropertyPath().toString();
				String mensagem = violation.getMessage();

				validacaoException.adicionar(entidade, propriedade, mensagem);
			}
			throw validacaoException;
		}
	}

}
