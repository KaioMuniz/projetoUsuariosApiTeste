package br.com.cotiinformatica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.AutenticarUsuarioRequest;
import br.com.cotiinformatica.dtos.AutenticarUsuarioResponse;
import br.com.cotiinformatica.dtos.CriarUsuarioRequest;
import br.com.cotiinformatica.dtos.CriarUsuarioResponse;
import br.com.cotiinformatica.services.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

	//injeção de dependência
	@Autowired UsuarioService usuarioService;
	
	@PostMapping("criar")
	public CriarUsuarioResponse criar(@RequestBody @Valid CriarUsuarioRequest request) {
		return usuarioService.criarUsuario(request);
	}
	
	@PostMapping("autenticar")
	public AutenticarUsuarioResponse autenticar(@RequestBody @Valid AutenticarUsuarioRequest request) {
		return usuarioService.autenticarUsuario(request);
	}
}
