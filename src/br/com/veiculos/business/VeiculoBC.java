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
import br.com.veiculos.model.Veiculo;

@ApplicationScoped
public class VeiculoBC {

	@Inject
	private Repositorio repositorio;
	
	@PostConstruct
	public void inicializar() {
		
		Veiculo vei = new Veiculo();
		vei.setDataEmplacamento(new Date());
		vei.setPlaca("oro4631");
		vei.setProprietario("Luciano Brito");
		vei.setValorEmplacamento(200.00);
		repositorio.inserir(vei);
		
		Veiculo vei2 = new Veiculo();
		vei2.setDataEmplacamento(new Date());
		vei2.setPlaca("xxt4145");
		vei2.setProprietario("Felipe Amancio");
		vei2.setValorEmplacamento(200.00);
		repositorio.inserir(vei2);	
	}
	
	public List<Veiculo> selecionar() {
		return repositorio.selecionar(Veiculo.class);
	}

	public Veiculo selecionar(Long id) throws UsuarioNaoEncontradoException {
		Veiculo veiculo = repositorio.selecionar(Veiculo.class, id);
		if (veiculo == null) {
			throw new UsuarioNaoEncontradoException();
		}
		return veiculo;
	}

	public Long inserir(Veiculo veiculo) throws ValidacaoException {
		validar(veiculo);
		return repositorio.inserir(veiculo);
	}

	public void atualizar(Veiculo veiculo) throws ValidacaoException {
		validar(veiculo);
		if (!repositorio.atualizar(veiculo)) {
			throw new NotFoundException();
		}
	}

	public Veiculo excluir(Long id) throws UsuarioNaoEncontradoException {
		Veiculo veiculo = repositorio.excluir(Veiculo.class, id);
		if (veiculo == null) {
			throw new UsuarioNaoEncontradoException();
		}
		return veiculo;
	}
	
	private void validar(Veiculo veiculo) throws ValidacaoException {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Veiculo>> violations = validator.validate(veiculo);
		if (!violations.isEmpty()) {
			ValidacaoException validacaoException = new ValidacaoException();
			for (ConstraintViolation<Veiculo> violation : violations) {
				String entidade = violation.getRootBeanClass().getSimpleName();
				String propriedade = violation.getPropertyPath().toString();
				String mensagem = violation.getMessage();

				validacaoException.adicionar(entidade, propriedade, mensagem);
			}
			throw validacaoException;
		}
	}

}
