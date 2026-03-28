package br.com.wayon.exceptions;

public class ValorInvalidoException extends RuntimeException{
	public ValorInvalidoException(String message) {
		super(message);
	}
}