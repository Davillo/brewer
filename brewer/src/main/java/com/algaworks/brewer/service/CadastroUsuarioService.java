package com.algaworks.brewer.service;

import java.util.Optional;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.algaworks.brewer.model.Usuario;
import com.algaworks.brewer.repository.Usuarios;
import com.algaworks.brewer.service.exception.EmailUsuarioJaCadastradoException;
import com.algaworks.brewer.service.exception.SenhaObrigatoriaUsuarioException;

@Service
public class CadastroUsuarioService {
	@Autowired
	private Usuarios usuarios;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public void salvar(Usuario usuario){
		 Optional<Usuario> emailUsuarioExistente = usuarios.findByEmail(usuario.getEmail());
		 if(emailUsuarioExistente.isPresent()){
			 throw new EmailUsuarioJaCadastradoException("E-mail de usuário já cadastrado!");
		 }
		 
		 if(usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())){
			 throw new SenhaObrigatoriaUsuarioException("Senha é obrigatória para novo usuário!");
		 }
		 
		 if(usuario.isNovo()){
			 usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
			 usuario.setConfirmacaoSenha(usuario.getSenha());
		 }
		usuarios.save(usuario);
	}
	
}
