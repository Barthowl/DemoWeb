package com.nttdata.demoweb.repository.impl;

import org.springframework.stereotype.Repository;

import com.nttdata.demoweb.repository.EmpleadoRepo;
import org.slf4j.Logger;

@Repository
public class EmpleadoRepoImpl implements EmpleadoRepo {
	
	private static Logger LOG = org.slf4j.LoggerFactory.getLogger(EmpleadoRepoImpl.class);
		
	@Override
	public void Registrar(String nombre) {
		LOG.info("se ha saludado al empleado: " + nombre);
		
	}

}
