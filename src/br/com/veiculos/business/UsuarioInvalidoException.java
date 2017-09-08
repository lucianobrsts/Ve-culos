package br.com.veiculos.business;

/**
 * Classe de excecao disparada pela camada de negocio.
 * @author Fabio Barros
 */
public class UsuarioInvalidoException extends Exception {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -1071896537277884578L;

	public UsuarioInvalidoException() {
		super("Login ou senha invalidos!");
	}
}
