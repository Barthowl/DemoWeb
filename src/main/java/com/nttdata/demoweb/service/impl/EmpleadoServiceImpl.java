package com.nttdata.demoweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.demoweb.repository.EmpleadoRepoJPA;
import com.nttdata.demoweb.repository.entity.Empleado;
import com.nttdata.demoweb.service.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired 
	EmpleadoRepoJPA empleadoRepo;
	
	
	@Override
	public void registrar(String nombre) {
		empleadoRepo.Registrar(nombre);
		
	}

	// tipos:
	
	
	// hereda de JPA (Testeadas y no fallan) (1)
	@Override
	public List<Empleado> listar() {
		// TODO Auto-generated method stub
		return empleadoRepo.findAll();
	}

	// Escrito en otro repoImpl (4)
	@Override
	public List<Empleado> listarFiltroNombre(String cad) {
		// TODO Auto-generated method stub
		return empleadoRepo.listarCuyoNombreContiene(cad);
	}

	// JPA (2)
	@Override
	public List<Empleado> listarConJPA(Integer id, String nombre) {
		// TODO Auto-generated method stub
		return empleadoRepo.findByIdGreaterThanAndNombreLike(id, nombre);
	}

	// Query personalizada en JPA y llamarla (3)
	@Override
	public List<Empleado> listarFiltroNombreES(String nombre) {
		// TODO Auto-generated method stub
		return empleadoRepo.listarCuyoNombreEs(nombre);
	}

}
