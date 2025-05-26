package br.com.jlucasguedes.apisimpalmas.exception;

public class UnidadeNaoEncontradaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8186733101098096414L;

	public UnidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public UnidadeNaoEncontradaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
