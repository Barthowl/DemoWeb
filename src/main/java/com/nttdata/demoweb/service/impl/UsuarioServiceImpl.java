package com.nttdata.demoweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.demoweb.repository.UsuarioRepoJPA;
import com.nttdata.demoweb.repository.entity.Usuario;
import com.nttdata.demoweb.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	UsuarioRepoJPA usuarioRepoJPA;

	@Override
	public List<Usuario> listar() {
		return usuarioRepoJPA.findAll();
	}

	@Override
	public Usuario buscarPorUsername(String nombre) {
		Usuario us = usuarioRepoJPA.findById(nombre).get();
		return usuarioRepoJPA.findById(nombre).get();
	}

}
