package br.com.cotiinformatica.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.components.CryptoComponent;
import br.com.cotiinformatica.dtos.AutenticarUsuarioRequest;
import br.com.cotiinformatica.dtos.AutenticarUsuarioResponse;
import br.com.cotiinformatica.dtos.CriarUsuarioRequest;
import br.com.cotiinformatica.dtos.CriarUsuarioResponse;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.exceptions.AcessoNegadoException;
import br.com.cotiinformatica.exceptions.EmailJaCadastradoException;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	//Injeção de dependência
	@Autowired UsuarioRepository usuarioRepository;
	@Autowired CryptoComponent cryptoComponent;
	
	/*
	 * Método para que possamos gravar 
	 * um usuário no banco de dados
	 */
	public CriarUsuarioResponse criarUsuario(CriarUsuarioRequest request) {
		
		//verificando se o email informado já existe no banco de dados
		if(usuarioRepository.existsByEmail(request.getEmail())) {
			throw new EmailJaCadastradoException();
		}
				
		var usuario = new Usuario(); //instanciando a entidade
		
		usuario.setNome(request.getNome()); //capturando nome
		usuario.setEmail(request.getEmail()); //capturando email
		usuario.setSenha(cryptoComponent.getSHA256(request.getSenha())); //capturando senha
		
		//gravar o usuário no banco de dados
		usuarioRepository.save(usuario);
		
		//retornar os dados de resposta
		var response = new CriarUsuarioResponse();
		response.setId(usuario.getId());
		response.setNome(usuario.getNome());
		response.setEmail(usuario.getEmail());
		response.setDataHoraCriacao(LocalDateTime.now());
		
		return response; //retornando e finalizando
	}
	
	/*
	 * Método para autenticar o usuário
	 */
	public AutenticarUsuarioResponse autenticarUsuario(AutenticarUsuarioRequest request) {
	
		//consultando o usuário no banco de dados
		var usuario = usuarioRepository.find(request.getEmail(), cryptoComponent.getSHA256(request.getSenha()));
		
		//verificando se o usuário não foi encontrado
		if(usuario == null) {
			throw new AcessoNegadoException();
		}
		
		//retornar os dados de resposta
		var response = new AutenticarUsuarioResponse();
		response.setId(usuario.getId());
		response.setNome(usuario.getNome());
		response.setEmail(usuario.getEmail());
		response.setDataHoraAcesso(LocalDateTime.now());
		response.setToken("<<TOKEN JWT>>"); //TODO Implementar a geraçao do TOKEN JWT
	
		return response;
	}	
}







