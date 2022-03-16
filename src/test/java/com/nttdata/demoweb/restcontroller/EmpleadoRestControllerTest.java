package com.nttdata.demoweb.restcontroller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import com.nttdata.demoweb.repository.EmpleadoRepoJPA;
import com.nttdata.demoweb.repository.entity.Empleado;
import com.nttdata.demoweb.service.EmpleadoService;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
class EmpleadoRestControllerTest {
	private Empleado e1, e2;
	
	@Autowired
	EmpleadoService service;
	
	@Autowired
	EmpleadoRepoJPA repo;
	
	@Autowired
	EmpleadoRestController controller;
	
	@Mock // --> Simular
	EmpleadoService serviceMock;
	
	@InjectMocks
	EmpleadoRestController controllerMock;
	
	
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
	void testListarEmpleados() {
		// GIVEN
		// Existen 2 empleados

	
		// WHEN
		List<Empleado> le = controller.listarEmpleados();
	
		// THEN
		assertEquals(2, le.size(), "hay 2 empleados en la BDD");
	}

	@Test
	void testInsertarEmpleado_v3() {
		// GIVEN
		assertEquals(2,service.listar().size(),"2 empleados en BDD");
		
		// WHEN
		Empleado e3 = new Empleado();
		e3.setNombre("Luis");
		e3.setApellidos("Torres");
		ResponseEntity<Empleado> re = controller.insertarEmpleado_v3(e3);
		
		// THEN
		assertAll (
				() -> assertEquals(HttpStatus.CREATED, re.getStatusCode(), "Codigo 201: Inserta empleado"),
				() -> assertEquals(3,service.listar().size(),"3 empleados en BDD")
		);
	}
	
	@Test
	void testInsertarEmpleado_v3_exception() throws Exception {
		// GIVEN
		when ( serviceMock.inserta(e1)).thenThrow (new Exception());
		
		// WHEN
		ResponseEntity<Empleado> re = controllerMock.insertarEmpleado_v3(e1);
		
		// THEN
		assertEquals(HttpStatus.NOT_ACCEPTABLE, re.getStatusCode(), "Excepción");
		
	}
	
	@Test
	void testInsertarEmpleado_v3_idIsNotNull() {
		// GIVEN
		
		// WHEN
		Empleado e3 = new Empleado();
		e3.setId(47);
		e3.setNombre("Nombre");
		e3.setApellidos("Apellidos");
		ResponseEntity<Empleado> re = controller.insertarEmpleado_v3(e3);
		
		// THEN
		assertEquals(HttpStatus.NOT_ACCEPTABLE, re.getStatusCode(), "Error 406 id is not null");
		
	}
	
	@Test
	void testInsertarEmpleado_v3_NombreIsNull() {
		// GIVEN
		
		// WHEN
		Empleado e3 = new Empleado();
		e3.setApellidos("Apellidos");
		ResponseEntity<Empleado> re = controller.insertarEmpleado_v3(e3);
		
		// THEN
		assertEquals(HttpStatus.NOT_ACCEPTABLE, re.getStatusCode(), "Error 406 nombre is null");
		
	}
	

	@Test
	void testModificarEmpleado() {
		// GIVEN
		// Existen 2 empleados
		assertEquals(2,service.listar().size(),"2 empleados en BDD");
				
		// WHEN
		String nuevoNombre = "Mariano";
		e2.setNombre(nuevoNombre);
		controller.modificarEmpleado(e2);
				
		// THEN
		assertEquals(2, service.listar().size(), " hay 2 empleados en BDD");
		assertEquals(nuevoNombre, service.getById(e2.getId()).getNombre(), " mod nombre");
	}

	@Test
	void testEliminarEmpleado() {
		// GIVEN
			// Existen 2 empleados
		assertEquals(2,service.listar().size(),"2 empleados en BDD");
			
		// WHEN
			controller.eliminarEmpleado(e2.getId());
			
		// THEN
			assertEquals(1, service.listar().size(), "hay 1 empleado en la BDD");
	}

	@Test
	void testDevuelveEmpleado() {
		// GIVEN
			// Existen 2 empleados
		assertEquals(2,service.listar().size(),"2 empleados en BDD");
		
	// WHEN
		ResponseEntity<Empleado> re = controller.devuelveEmpleado(e1.getId());
		
	// THEN
		assertEquals(e1, re.getBody(), "Empleado E1");
		assertThat (re.getStatusCodeValue()).isEqualTo(200);
		assertThat (re.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

}
