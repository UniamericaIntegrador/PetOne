package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.entity.Tutor;
import app.service.TutorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.entity.Tutor;
import app.repository.TutorRepository;

@SpringBootTest
public class TutorControllerTest {
	@Autowired
	@InjectMocks
	TutorController tutorController;

	@MockBean
	TutorRepository tutorRepository;

	@Mock
	private TutorService tutorServiceMock;

	@BeforeEach
	void setup() {
		Tutor tutor = new Tutor();

		when(this.tutorRepository.save(tutor)).thenReturn(tutor);
	}

	// ------- POR CAUSA DA VALIDAÇÀO @CPF, O CPF DEVE SER UM DOCUMENTO QUE REALMENTE EXISTA. NA HORA DE TESTAR, SUBSTITUIR O QUE ESTÁ COM X -------
	@Test
	@DisplayName("Teste de integração com o método save retornando sucesso")
	void testSave() {
		Tutor tutor = new Tutor(1, "Cebolinha Silva", "XXX.XXX.XXX-XX", "Rua do Limão, Bairro do Limoeiro", null);

		ResponseEntity<String> response = tutorController.save(tutor);
		assertTrue(response.getStatusCode() == HttpStatus.CREATED);
	}

	// TESTE PEGANDO A VALIDAÇÃO DE CPF INEXISTENTE- ANNOTATION @Cpf)
	@Test
	@DisplayName("Teste de integração com o método save retornando assertThrows")
	void testSaveCpf() {
		Tutor tutor = new Tutor(2, "Monica de Sousa", "000.000.000-00", "Rua do Limão, Bairro do Limoeiro", null);

		assertThrows(Exception.class, () -> {
			ResponseEntity<String> response = tutorController.save(tutor);
		});
	}

	@Test
	@DisplayName("Teste de integração com método save retornando uma exception")
	void testSaveException() {
		Tutor tutor = new Tutor();

		ResponseEntity<String> response = tutorController.save(null);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	// ------- POR CAUSA DA VALIDAÇÀO @CPF, O CPF DEVE SER UM DOCUMENTO QUE
	// REALMENTE EXISTA. NA HORA DE TESTAR, SUBSTITUIR O QUE ESTÁ COM X -------
	@Test
	@DisplayName("Teste de integração com o método update retornando sucesso")
	void testUpdate() {
		Tutor tutor = new Tutor(3, "Cascão Araujo", "XXX.XXX.XXX-XX", "Rua do Limão, Bairro do Limoeiro", null);
		long id = 0;

		ResponseEntity<String> response = tutorController.update(tutor, id);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	// ------- POR CAUSA DA VALIDAÇÀO @CPF, O CPF DEVE SER UM DOCUMENTO QUE
	// REALMENTE EXISTA. NA HORA DE TESTAR, SUBSTITUIR O QUE ESTÁ COM X -------
	// TESTE PEGANDO A VALIDAÇÃO DE QUANTIDADE DE CARACTERES PERMITIDO NO NOME -
	// ANNOTATION @PATTERN(regexp)
	@Test
	@DisplayName("Teste de integração com o método update retornando assertThrows")
	void testUpdateNome() {
		Tutor tutor = new Tutor(3, "C", "XXX.XXX.XXX-XX", "Rua do Limão, Bairro do Limoeiro", null);
		long id = 0;

		assertThrows(Exception.class, () -> {
			ResponseEntity<String> response = tutorController.update(tutor, id);
		});
	}

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindById_Normal() {
		// Mock do objeto Tutor retornado pelo serviço
		Tutor mockTutor = new Tutor();
		mockTutor.setId(1L);
		mockTutor.setNome("João da Silva");

		// Configura o comportamento do mock do serviço para retornar o mock do tutor
		// quando findById é chamado
		when(tutorServiceMock.findById(1L)).thenReturn(mockTutor);

		// Chama o método findById do controlador
		ResponseEntity<Tutor> response = tutorController.findById(1L);

		// Verifica se a resposta HTTP é OK (200)
		assertEquals(HttpStatus.OK, response.getStatusCode());

		// Verifica se o resultado retornado é igual ao mock do tutor
		assertEquals(mockTutor, response.getBody());
	}

	@Test
	public void testFindById_Exception() {
		// Configura o comportamento do mock do serviço para lançar uma exceção quando
		// findById é chamado
		when(tutorServiceMock.findById(1L)).thenThrow(new RuntimeException("Tutor não encontrado"));

		// Chama o método findById do controlador
		ResponseEntity<Tutor> response = tutorController.findById(1L);

		// Verifica se a resposta HTTP é BAD REQUEST (400)
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

		// Verifica se o corpo da resposta é nulo
		assertNull(response.getBody());
	}

	@Test
	public void testListAll_Normal() {
		// Mock da lista de tutores retornada pelo serviço
		List<Tutor> mockTutors = new ArrayList<>();
		mockTutors.add(new Tutor(1L, "João da Silva", "123.456.789-00", "Endereço João"));
		mockTutors.add(new Tutor(2L, "Maria Oliveira", "987.654.321-00", "Endereço Maria"));

		// Configura o comportamento do mock do serviço para retornar a lista de tutores
		// quando listAll é chamado
		when(tutorServiceMock.listAll()).thenReturn(mockTutors);

		// Chama o método listAll do controlador
		ResponseEntity<List<Tutor>> response = tutorController.listAll();

		// Verifica se a resposta HTTP é OK (200)
		assertEquals(HttpStatus.OK, response.getStatusCode());

		// Verifica se o resultado retornado é igual à lista mockada
		assertEquals(mockTutors, response.getBody());
	}

	@Test
	public void testListAll_Exception() {
		// Configura o comportamento do mock do serviço para lançar uma exceção quando
		// listAll é chamado
		when(tutorServiceMock.listAll()).thenThrow(new RuntimeException("Erro ao listar tutores"));

		// Chama o método listAll do controlador
		ResponseEntity<List<Tutor>> response = tutorController.listAll();

		// Verifica se a resposta HTTP é BAD REQUEST (400)
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

		// Verifica se o corpo da resposta é nulo
		assertNull(response.getBody());
	}
}
