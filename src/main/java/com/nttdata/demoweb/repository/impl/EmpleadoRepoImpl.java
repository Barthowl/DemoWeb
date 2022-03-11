package com.nttdata.demoweb.repository.impl;

import com.nttdata.demoweb.repository.EmpleadoRepo;
import com.nttdata.demoweb.repository.entity.Empleado;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;

//@Repository
public class EmpleadoRepoImpl implements EmpleadoRepo {
	
	private static Logger LOG = org.slf4j.LoggerFactory.getLogger(EmpleadoRepoImpl.class);
		
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void Registrar(String nombre) {
		LOG.info("se ha saludado al empleado: " + nombre);
		
	}

	 @Override
	    public List<Empleado> listarCuyoNombreContiene(String texto_nombre) {
	        javax.persistence.Query query = entityManager.createNativeQuery("SELECT * FROM empleado " +
	                "WHERE nombre LIKE ?", Empleado.class);
	        query.setParameter(1, "%" + texto_nombre + "%");
	        return query.getResultList();
		}

}
