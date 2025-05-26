package br.com.jlucasguedes.apisimpalmas.exception;

public class ClassificacaoNaoLiberadaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5996327614372953766L;

	public ClassificacaoNaoLiberadaException(String mensagem) {
		super(mensagem);
	}

	public ClassificacaoNaoLiberadaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
