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

import app.entity.Procedimento;
import app.repository.ProcedimentoRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ProcedimentoControllerTest {
	@Autowired
	ProcedimentoController procedimentoController;
	
	@MockBean
	ProcedimentoRepository procedimentoRepository;

	@BeforeEach
	void setup() {
		List<Procedimento>listaProcedimento = new ArrayList<>();
		listaProcedimento.add(new Procedimento(1, "Castração", LocalDate.of(2023, 07, 19), null, null, null));
		listaProcedimento.add(new Procedimento(2, "Vacina", LocalDate.of(2023, 07, 19), null, null, null));
		listaProcedimento.add(new Procedimento(3, "Raio-x", LocalDate.of(2023, 07, 19), null, null, null));
		listaProcedimento.add(null);
		
		
		Procedimento procedimento = new Procedimento();

		when(this.procedimentoRepository.save(procedimento)).thenReturn(procedimento);
		when(this.procedimentoRepository.findAll()).thenReturn(listaProcedimento);
		when(this.procedimentoRepository.findAllById(null)).thenReturn(listaProcedimento);
	}

	
	@Test
	@DisplayName("Teste de integração com o método save retornando sucesso")
	void testSave() {
		Procedimento procedimento = new Procedimento(1, "Castração", LocalDate.of(2023, 07, 19), null, null, null);

		ResponseEntity<String> response = procedimentoController.save(procedimento);
		assertTrue(response.getStatusCode() == HttpStatus.CREATED);
	}

	// TESTE PEGANDO A VALIDAÇÃO DE MINIMO DE CARACTÉRES PARA O NOME DO PROCEDIMENTO - ANNOTATION @Size(min = 3)
	@Test
	@DisplayName("Teste de integração com o método save retornando assertThrows")
	void testSaveNomeProcedimento() {
		Procedimento procedimento = new Procedimento(1, "Ca", LocalDate.of(2023, 07, 19), null, null, null);

		assertThrows(Exception.class, () -> {
			ResponseEntity<String> response = procedimentoController.save(procedimento);
		});
	}

	@Test
	@DisplayName("Teste de integração com método save retornando uma exception")
	void testSaveException() {
		Procedimento procedimento = new Procedimento();

		ResponseEntity<String> response = procedimentoController.save(null);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração com o método update retornando sucesso")
	void testUpdate() {
		Procedimento procedimento = new Procedimento(2, "Vacina", LocalDate.of(2023, 07, 19), null, null, null);
		long id = 0;

		ResponseEntity<String> response = procedimentoController.update(procedimento, id);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	// TESTE PEGANDO A VALIDAÇÃO DE MINIMO DE CARACTERES PARA O DIAGNOSTICO - ANNOTATION @Size(min = 7)
	@Test
	@DisplayName("Teste de integração com o método update retornando assertThrows")
	void testUpdateDiagnostico() {
		Procedimento procedimento = new Procedimento(2, "Vacina", LocalDate.of(2023, 07, 19), null, "ABC", null);
		long id = 0;

		assertThrows(Exception.class, () -> {
			ResponseEntity<String> response = procedimentoController.update(procedimento, id);
		});
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método delete")
	void testDelete() {
		long id = 0;
		ResponseEntity<String>response = procedimentoController.delete(id);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método delete com exception")
	void testDeleteException() {
		long id = -1;
		ResponseEntity<String>response = procedimentoController.delete(id);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());	
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método findAll")
	void testFindAll() {
		ResponseEntity<List<Procedimento>>response = this.procedimentoController.listAll();
		List<Procedimento>listaProcedimento = response.getBody();
		
		assertEquals(4, listaProcedimento.size());
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método findById com exception")
	void testFindByIdException() {
		long id = 0;
		ResponseEntity<Procedimento>response = procedimentoController.findById(id);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
}
