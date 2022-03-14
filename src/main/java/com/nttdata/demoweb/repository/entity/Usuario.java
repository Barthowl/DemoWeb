package com.nttdata.demoweb.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// application.properties: line 12 > ddl-auto= update 

@Entity
@Table
public class Usuario {
	
	@Id
	@Column
	private String username;
	
	@Column(name="nombre", nullable=false, length=50)
	private String nombreYapellidos;
	
	@Column(nullable=false)
	private String password;
	
	@OneToOne (optional=false) // 1 usuario relacionado a 1 rol y que 1 rol está relacionado a 1 usuario y que es obligatorio
	private Rol rol;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNombreYapellidos() {
		return nombreYapellidos;
	}

	public void setNombreYapellidos(String nombreYapellidos) {
		this.nombreYapellidos = nombreYapellidos;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	

}
