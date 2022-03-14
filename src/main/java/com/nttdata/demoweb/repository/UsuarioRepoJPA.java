package com.nttdata.demoweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nttdata.demoweb.repository.entity.Usuario;
// string = PK de usuario
public interface UsuarioRepoJPA extends JpaRepository<Usuario, String>{

}
