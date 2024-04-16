package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.entity.Veterinario;
import app.repository.VeterinarioRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import app.service.VeterinarioService;

@SpringBootTest
public class VeterinarioControllerTest {
	@Autowired
	@InjectMocks
	VeterinarioController veterinarioController;

	@MockBean
	VeterinarioRepository veterinarioRepository;

	@BeforeEach
	void setup() {
		Veterinario veterinario = new Veterinario();

		when(this.veterinarioRepository.save(veterinario)).thenReturn(veterinario);
	}

	@Mock
	private VeterinarioService veterinarioServiceMock;

	@Test
	@DisplayName("Teste de integração com o método save retornando sucesso")
	void testSave() {
		Veterinario veterinario = new Veterinario(7, "Chico Bento", "6390", "Sitio, Vila Abobrinha", null);

		ResponseEntity<String> response = veterinarioController.save(veterinario);
		assertTrue(response.getStatusCode() == HttpStatus.CREATED);
	}

	// TESTE PEGANDO A VALIDAÇÃO DE CRMV QUE NÃO PODE SER NULO - ANNOTATION
	// @NotBlank)
	@Test
	@DisplayName("Teste de integração com o método save retornando assertThrows")
	void testSaveCrmv() {
		Veterinario veterinario = new Veterinario(8, "Zé Lelé", "", "Sitio, Vila Abobrinha", null);

		assertThrows(Exception.class, () -> {
			ResponseEntity<String> response = veterinarioController.save(veterinario);
		});
	}

	@Test
	@DisplayName("Teste de integração com método save retornando uma exception")
	void testSaveException() {
		Veterinario veterinario = new Veterinario();

		ResponseEntity<String> response = veterinarioController.save(null);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração com o método update retornando sucesso")
	void testUpdate() {
		Veterinario veterinario = new Veterinario(2, "Mauricio de Sousa", "72910", "Rua do Limão, Vila do Limoeiro",
				null);
		long id = 0;

		ResponseEntity<String> response = veterinarioController.update(veterinario, id);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	// TESTE PEGANDO A VALIDAÇÃO DE QUANTIDADE DE CARACTERES PERMITIDO NO NOME -
	// ANNOTATION @PATTERN(regexp)
	@Test
	@DisplayName("Teste de integração com o método update retornando assertThrows")
	void testUpdateNome() {
		Veterinario veterinario = new Veterinario(2, "Ma", "72910", "Rua 7, Vila do Limoeiro", null);
		long id = 0;

		assertThrows(Exception.class, () -> {
			ResponseEntity<String> response = veterinarioController.update(veterinario, id);
		});
	}

	@Test
	public void testFindById_Normal() {
		// Mock do objeto Veterinario retornado pelo serviço
		Veterinario mockVeterinario = new Veterinario();
		mockVeterinario.setId(1L);
		mockVeterinario.setNome("João da Silva");

		// Configura o comportamento do mock do serviço para retornar o mock do
		// veterinário quando findById é chamado
		when(veterinarioServiceMock.findById(1L)).thenReturn(mockVeterinario);

		// Chama o método findById do controlador
		ResponseEntity<Veterinario> response = veterinarioController.findById(1L);

		// Verifica se a resposta HTTP é OK (200)
		assertEquals(HttpStatus.OK, response.getStatusCode());

		// Verifica se o resultado retornado é igual ao mock do veterinário
		assertEquals(mockVeterinario, response.getBody());
	}

	@Test
	public void testFindById_Exception() {
		// Configura o comportamento do mock do serviço para lançar uma exceção quando
		// findById é chamado
		when(veterinarioServiceMock.findById(1L)).thenThrow(new RuntimeException("Veterinário não encontrado"));

		// Chama o método findById do controlador
		ResponseEntity<Veterinario> response = veterinarioController.findById(1L);

		// Verifica se a resposta HTTP é BAD REQUEST (400)
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

		// Verifica se o corpo da resposta é nulo
		assertNull(response.getBody());
	}

	@Test
	public void testListAll_Normal() {
		// Mock da lista de veterinários retornada pelo serviço
		List<Veterinario> mockVeterinarios = new ArrayList<>();
		mockVeterinarios.add(new Veterinario(1L, "João da Silva", "CRMV1234", "Endereço João"));
		mockVeterinarios.add(new Veterinario(2L, "Maria Oliveira", "CRMV5678", "Endereço Maria"));

		// Configura o comportamento do mock do serviço para retornar a lista de
		// veterinários quando listAll é chamado
		when(veterinarioServiceMock.listAll()).thenReturn(mockVeterinarios);

		// Chama o método listAll do controlador
		ResponseEntity<List<Veterinario>> response = veterinarioController.listAll();

		// Verifica se a resposta HTTP é OK (200)
		assertEquals(HttpStatus.OK, response.getStatusCode());

		// Verifica se o resultado retornado é igual à lista mockada
		assertEquals(mockVeterinarios, response.getBody());
	}

	@Test
	public void testListAll_Exception() {
		// Configura o comportamento do mock do serviço para lançar uma exceção quando
		// listAll é chamado
		when(veterinarioServiceMock.listAll()).thenThrow(new RuntimeException("Erro ao listar veterinários"));

		// Chama o método listAll do controlador
		ResponseEntity<List<Veterinario>> response = veterinarioController.listAll();

		// Verifica se a resposta HTTP é BAD REQUEST (400)
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

		// Verifica se o corpo da resposta é nulo
		assertNull(response.getBody());
	}
}
