package br.com.cotiinformatica.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class AutenticarUsuarioResponse {

	private UUID id;
	private String nome;
	private String email;
	private LocalDateTime dataHoraAcesso;
	private LocalDateTime dataHoraExpiracao;
	private String token;
}
