package br.com.wayon.globals;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.wayon.exceptions.ChaveDuplicadaException;
import br.com.wayon.exceptions.ContaCorrenteInexistenteException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ChaveDuplicadaException.class)
	public ResponseEntity<?> handleChaveDuplicadaException(ChaveDuplicadaException exception){
		return ResponseEntity.status(400).body(exception.getMessage());
	}
	
	@ExceptionHandler(ContaCorrenteInexistenteException.class)
	public ResponseEntity<?> handleContaCorrenteInexistenteException(ContaCorrenteInexistenteException exception){
		return ResponseEntity.status(400).body(exception.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleErroGenerico(Exception exception){
		return ResponseEntity.status(400).body(exception.getLocalizedMessage());
	}

}
