package com.nttdata.demoweb.service;
import java.util.List;

import com.nttdata.demoweb.repository.entity.Empleado;

public interface EmpleadoService {
	public void registrar(String nombre);
	public List<Empleado> listar();
	public List<Empleado> listarFiltroNombre(String cad);
	public List<Empleado> listarConJPA(Integer id, String nombre);
	public List<Empleado> listarFiltroNombreES(String nombre);
	Empleado inserta(Empleado e);
    Empleado modificar(Empleado e);
	void eliminar(Integer id);
	Empleado getById(Integer id);
	
}
