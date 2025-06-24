package br.com.cotiinformatica.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.com.cotiinformatica.exceptions.AcessoNegadoException;
import br.com.cotiinformatica.exceptions.EmailJaCadastradoException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationException(
			MethodArgumentNotValidException exception,
			WebRequest request
			) {
		
		var erros = exception.getBindingResult()
					.getFieldErrors()
					.stream()
					.map(error -> "Campo: '" + error.getField() + "' : " + error.getDefaultMessage())
					.collect(Collectors.toList());
		
		var body = new HashMap<String, Object>();
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("erros", erros);
		
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmailJaCadastradoException.class)
	public ResponseEntity<Map<String, Object>> handleEmailJaCadastradoException(
			EmailJaCadastradoException exception,
			WebRequest request
			) {
					
		var body = new HashMap<String, Object>();
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("erro", exception.getMessage());
		
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AcessoNegadoException.class)
	public ResponseEntity<Map<String, Object>> handleAcessoNegadoException(
			AcessoNegadoException exception,
			WebRequest request
			) {
					
		var body = new HashMap<String, Object>();
		body.put("status", HttpStatus.UNAUTHORIZED.value());
		body.put("erro", exception.getMessage());
		
		return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
	}	
}
