package com.nttdata.demoweb.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.nttdata.demoweb.repository.EmpleadoRepoJPA;
import com.nttdata.demoweb.repository.entity.Empleado;
import com.nttdata.demoweb.service.EmpleadoService;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
class EmpleadoServiceImplTest {
	
	private Empleado e1, e2;
	
	@Autowired
	EmpleadoService service;
	
	@Autowired
	EmpleadoRepoJPA repo;

	@BeforeEach
	void setUp() throws Exception {
		repo.deleteAll();
		
		e1 = new Empleado();
		e1.setNombre("Manuel");
		e1.setApellidos("Muñoz");
		e1 = repo.save(e1);
		
		e2 = new Empleado();
		e2.setNombre("Ana");
		e2.setApellidos("Torres");
		e2 = repo.save(e2);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		repo.deleteAll();
	}

	@Test
	void testRegistrar() {
		service.registrar("Test");
	}

	@Test
	void testInserta() throws Exception {
		// GIVEN
		// Existen 2 empleados
		assertEquals(2,service.listar().size(),"2 empleados en BDD");
		
		// WHEN
		
		Empleado e3 = new Empleado();
		e3.setNombre("N3");
		e3.setApellidos("AP3");
		e3 = service.inserta(e3);
		
		// THEN
		assertEquals(3, service.listar().size(), "Hay 3 empleados");
	}

	@Test
	void testListar() {
		// GIVEN
			// Existen 2 empleados
			assertEquals(2,service.listar().size(),"2 empleados en BDD");
		
		// WHEN
			List<Empleado> le = service.listar();
			assertEquals(2, le.size(), "hay 2 empleados en la BDD");
		
		// THEN
			/*int encontrados = 0;
			for(Empleado e: le) {
				if(e==e1 || e==e2)
					encontrados++;
			}
			assertEquals(2, encontrados, "2 empleados encontrados");*/
	}

	@Test
	void testListarFiltroNombre() {
		// GIVEN
			// Existen 2 empleados
			assertEquals(2,service.listar().size(),"2 empleados en BDD");
	
	    // WHEN
			List<Empleado> le = service.listarFiltroNombre("u");
	
		// THEN
			assertEquals(1, le.size(), "Solo está Manuel con 1 'u' en el nombre ");
			assertEquals(e1, le.get(0), "Manuel contiene una 'u'");
	}

	@Test
	void testListarConJPA() {
		// GIVEN
		// Existen 2 empleados
		assertEquals(2,service.listar().size(),"2 empleados en BDD");

		// WHEN
		List<Empleado> le = service.listarFiltroNombre("%a%");

		// THEN
		assertEquals(2, le.size(), "Solo está Anaa con 1 'a' en el nombre ");
	}

	@Test
	void testListarFiltroNombreES() {
		// GIVEN
		// Existen 2 empleados
		assertEquals(2,service.listar().size(),"2 empleados en BDD");

		// WHEN
		List<Empleado> le1 = service.listarFiltroNombreES("Ana");
		List<Empleado> le2 = service.listarFiltroNombreES("u");

		// THEN
		
		assertAll (
				() -> assertEquals(1, le1.size(), "Hay 1 empleado Ana "),
				() -> assertEquals(0, le2.size(), "Ningún empleado que se llamen 'u'")
		);

	}

	@Test
	void testModificar() {
		// GIVEN
		// Existen 2 empleados
		assertEquals(2,service.listar().size(),"2 empleados en BDD");
		
		// WHEN
		String nuevoNombre = "Mariano";
		e2.setNombre(nuevoNombre);
		service.modificar(e2);
		
		// THEN
		assertEquals(2, service.listar().size(), " hay 2 empleados en BDD");
		assertEquals(nuevoNombre, service.getById(e2.getId()).getNombre(), " mod nombre");
	}

	@Test
	void testEliminar() {
		// GIVEN
		// Existen 2 empleados
		assertEquals(2,service.listar().size(),"2 empleados en BDD");
		
		// WHEN
		service.eliminar(e1.getId());
		
		// THEN
		assertEquals(1, service.listar().size(), " hay 1 empleado BDD");
	}

	@Test
	void testGetById() {
		// GIVEN
		// Existen 2 empleados
		assertEquals(2,service.listar().size(),"2 empleados en BDD");
		
		// WHEN
		Empleado e3 = service.getById(e1.getId());
		
		// THEN
		assertEquals(e1.getId(), e3.getId(), " Mismo ID");
		assertNotNull(e3, "empleado válido");
	}

}
