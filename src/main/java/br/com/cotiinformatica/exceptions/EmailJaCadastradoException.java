package br.com.cotiinformatica.exceptions;

@SuppressWarnings("serial")
public class EmailJaCadastradoException extends RuntimeException {

	/*
	 * Método para retornar a mensagem de erro
	 */
	@Override
	public String getMessage() {	
		return "O email informado já está cadastrado. Tente outro.";
	}
}
