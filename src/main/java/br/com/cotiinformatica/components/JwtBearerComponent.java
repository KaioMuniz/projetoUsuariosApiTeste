package br.com.cotiinformatica.components;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtBearerComponent {

	/*
	 * Ler a chave de configuração 'jwt.secretkey'
	 * criada no arquivo /application.properties
	 */
	@Value("${jwt.secretkey}")
	private String jwtSecret;
	
	/*
	 * Ler a chave de configuração 'jwt.expiration'
	 * criada no arquivo /application.properties
	 */
	@Value("${jwt.expiration}")
	private String jwtExpiration;
	
	/*
	 * Método para calcular e retornar a data de expiração do token
	 */
	public Date getExpiration() {
		return null; //TODO
	}
	
	/*
	 * Método para gerar e retornar o Token do usuário autenticado
	 */
	public String getToken(String emailUsuario) {
		return null; //TODO
	}
	
}
