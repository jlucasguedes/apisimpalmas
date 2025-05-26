package br.com.jlucasguedes.apisimpalmas.exception;

public class SerieNaoEncontradaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6034721116126185541L;

	public SerieNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public SerieNaoEncontradaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
