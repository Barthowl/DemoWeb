package com.nttdata.demoweb.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Empleado {
// JAVAX
	
	@Id
	@Column
	private Integer id;
	
	@Column(nullable = false, length=30)
	private String nombre;
	
	// tamaño por defecto: 255
	@Column
	private String apellidos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	// nos genera un h2-console y accedemos desde el localhost/app/h2-console
	
}
