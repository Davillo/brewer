package com.algaworks.brewer.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.brewer.model.Usuario;

public interface Usuarios extends JpaRepository<Usuario, Long> {

}
