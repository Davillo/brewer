package com.algaworks.brewer.repository.helper;

import java.util.Optional;

import com.algaworks.brewer.model.Usuario;

public interface UsuariosQueries {

	public Optional<Usuario> porEmailEAtivo(String email);

	
}
