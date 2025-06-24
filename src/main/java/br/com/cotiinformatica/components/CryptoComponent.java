package br.com.cotiinformatica.components;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class CryptoComponent {

	/*
	 * Método para criptografar um valor no padrão SHA-256
	 */
	public String getSHA256(String value) {
		try {
			// Instancia o algoritmo de hash
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			// Aplica o hash à string convertida em bytes
			byte[] hashBytes = digest.digest(value.getBytes(StandardCharsets.UTF_8));

			// Converte os bytes para hexadecimal
			StringBuilder hexString = new StringBuilder();
			for (byte b : hashBytes) {
				hexString.append(String.format("%02x", b));
			}

			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			// Em caso de erro, relança como RuntimeException
			throw new RuntimeException("Erro ao gerar hash SHA-256", e);
		}
	}
}
