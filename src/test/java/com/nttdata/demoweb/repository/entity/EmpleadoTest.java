package com.nttdata.demoweb.repository.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmpleadoTest {

	@Test
	void test() {
		
		// comprobar constructor
		Empleado e1 = new Empleado();
		
		// setter getter 1 a 1
		e1.setId(1);
		assertEquals(1,e1.getId(), "mismo ID");
		
		String nombre = "prueba";
		e1.setNombre(nombre);
		assertEquals(nombre,e1.getNombre(), "Mismo nombre");
		
		String apellidos = "apellidos";
		e1.setApellidos(apellidos);
		assertEquals(apellidos,e1.getApellidos(), "Mismos apellidos");
		
		
		// equals (id -> Empleado.java)
		Empleado e2 = new Empleado();
		e2.setId(1);
		e2.setNombre(nombre);
		e2.setApellidos(apellidos);
		
		// pasar por los ifs de equals
		assertEquals(e1, e2, "Mismo empleado");
		assertEquals(e1, e1, "Mismo objeto");
		assertNotEquals(e1, nombre, "Distinto objeto");
		
		// hashcode (id -> Empleado.java)
		assertEquals(e1.hashCode(), e2.hashCode(), "Mismo hash code");
		
	}

}
