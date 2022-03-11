package com.nttdata.demoweb.repository;

import java.util.List;

import com.nttdata.demoweb.repository.entity.Empleado;

public interface EmpleadoRepo {
	public void Registrar(String nombre);
	List<Empleado> listarCuyoNombreContiene(String texto_nombre);
}
