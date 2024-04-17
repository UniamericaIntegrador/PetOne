package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.entity.Paciente;
import app.entity.Veterinario;
import app.repository.PacienteRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PacienteControllerTest {
	@Autowired
	PacienteController pacienteController;

	@MockBean
	PacienteRepository pacienteRepository;

	@BeforeEach
	void setup() {
		List<Paciente>listaPaciente = new ArrayList<>();
		listaPaciente.add(new Paciente(1, "Mingau", "gato", LocalDate.of(2021, 1, 25), "Angorá", null,null));
		listaPaciente.add(new Paciente(2, "Floquinho", "cachorro", LocalDate.of(2021, 1, 25), "SRD", null,null));
		listaPaciente.add(new Paciente(3, "Chovinista", "porco", LocalDate.of(2021, 1, 25), "SRD", null,null));
		listaPaciente.add(null);
		
		Paciente paciente = new Paciente();
		String especie = "Ave";

		when(this.pacienteRepository.save(paciente)).thenReturn(paciente);
		when(this.pacienteRepository.findAll()).thenReturn(listaPaciente);
		when(this.pacienteRepository.findAllById(null)).thenReturn(listaPaciente);
		when(this.pacienteRepository.findByEspecie(especie)).thenThrow(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("Teste de integração com o método save retornando sucesso")
	void testSave() {
		Paciente paciente = new Paciente(1, "Machu Picchu", "gato", LocalDate.of(2021, 1, 25), "SRD", null,null);

		ResponseEntity<String> response = pacienteController.save(paciente);
		assertTrue(response.getStatusCode() == HttpStatus.CREATED);
	}

	// TESTE PEGANDO A VALIDAÇÃO DE DATA NO FUTURO - ANNOTATION @PastOrPresent)
	@Test
	@DisplayName("Teste de integração com o método save retornando assertThrows")
	void testSaveData() {
		Paciente paciente = new Paciente(1, "Machu Picchu", "gato", LocalDate.of(2025, 1, 25), "SRD", null,null);

		assertThrows(Exception.class, () -> {
			ResponseEntity<String> response = pacienteController.save(paciente);
		});
	}

	// TESTE PEGANDO A VALIDAÇÃO DE MINIMO DE CARACTERES PARA A ESPECIE - ANNOTATION @Size(min = 3)
	@Test
	@DisplayName("Teste de integração com o método save retornando assertThrows")
	void testSaveEspecie() {
		Paciente paciente = new Paciente(1, "Machu Picchu", "ga", LocalDate.of(2021, 1, 25), "SRD", null,null);

		assertThrows(Exception.class, () -> {
			ResponseEntity<String> response = pacienteController.save(paciente);
		});
	}

	@Test
	@DisplayName("Teste de integração com método save retornando uma exception")
	void testSaveException() {
		Paciente paciente = new Paciente();

		ResponseEntity<String> response = pacienteController.save(null);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração com o método update retornando sucesso")
	void testUpdate() {
		Paciente paciente = new Paciente(3, "Malu", "gato", LocalDate.of(2014, 12, 29), "SRD", null,null);
		long id = 0;

		ResponseEntity<String> response = pacienteController.update(paciente, id);
		assertEquals(HttpStatus.OK, response.getStatusCode());

	}

	// TESTE PEGANDO A VALIDAÇÃO DE MINIMO DE CARACTERES PARA A RACA - ANNOTATION @Size(min = 3)
	@Test
	@DisplayName("Teste de integração com o método update retornando assertThrows")
	void testUpdateRaca() {
		Paciente paciente = new Paciente(3, "Malu", "gato", LocalDate.of(2014, 12, 29), "SR", null,null);
		long id = 0;

		assertThrows(Exception.class, () -> {
			ResponseEntity<String> response = pacienteController.update(paciente, id);
		});
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método delete")
	void testDelete() {
		long id = 0;
		ResponseEntity<String>response = pacienteController.delete(id);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método delete com exception")
	void testDeleteException() {
		long id = -1;
		ResponseEntity<String>response = pacienteController.delete(id);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());	
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método findAll")
	void testFindAll() {
		ResponseEntity<List<Paciente>>response = this.pacienteController.listAll();
		List<Paciente>listaPaciente = response.getBody();
		
		assertEquals(4, listaPaciente.size());
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método findById com exception")
	void testFindByIdException() {
		long id = 0;
		ResponseEntity<Paciente>response = pacienteController.findById(id);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByEndereco")
	void testFindByEpecie() {
		String especie = null;
		ResponseEntity<List<Paciente>> response = pacienteController.findByEspecie(especie);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByEndereco com exception")
	void testFindByEnderecoException() {
		String especie = "Ave";
		ResponseEntity<List<Paciente>> response = pacienteController.findByEspecie(especie);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
}
