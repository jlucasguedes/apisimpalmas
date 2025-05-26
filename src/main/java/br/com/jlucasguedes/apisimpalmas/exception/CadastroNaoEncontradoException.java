package br.com.jlucasguedes.apisimpalmas.exception;

public class CadastroNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8186733101098096414L;

	public CadastroNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public CadastroNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
