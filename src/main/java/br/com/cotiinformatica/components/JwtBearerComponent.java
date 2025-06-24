package br.com.cotiinformatica.components;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

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
		//Data atual + o tempo de expiração em milisegundos
		var dataAtual = new Date();
		return new Date(dataAtual.getTime() + Integer.parseInt(jwtExpiration));
	}
	
	/*
	 * Método para gerar e retornar o Token do usuário autenticado
	 */
	public String getToken(String emailUsuario) {
		
		return Jwts.builder()
				.setSubject(emailUsuario) //identificação do usuário do token
				.setIssuedAt(new Date()) //data de geração do token
				.setExpiration(getExpiration()) //data de expiração do token
				.signWith(SignatureAlgorithm.HS256, jwtSecret) //chave de assinatura
				.compact(); //finaliza e retorna o token gerado
	}	
}
