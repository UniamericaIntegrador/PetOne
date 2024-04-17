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
import app.entity.Procedimento;
import app.repository.ProcedimentoRepository;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import app.service.ProcedimentoService;

@SpringBootTest
public class ProcedimentoControllerTest {
	@Autowired
	@InjectMocks
	ProcedimentoController procedimentoController;

	@MockBean
	ProcedimentoRepository procedimentoRepository;

	@Mock
	private ProcedimentoService procedimentoServiceMock;

	@BeforeEach
	void setup() {
		Procedimento procedimento = new Procedimento();

		when(this.procedimentoRepository.save(procedimento)).thenReturn(procedimento);
		MockitoAnnotations.initMocks(this);

		List<Procedimento> lista = new ArrayList<>();
		lista.add(new Procedimento(1, "Procedimento1", LocalDate.of(2024,04,14), null, null, null, null));
		lista.add(new Procedimento(2, "Procedimento2", LocalDate.of(2024,04,14), null, null, null, null));
		when(this.procedimentoRepository.findAllByDataBetween(LocalDate.of(2024,04,14))).thenReturn(lista);
	
		List<Procedimento> listaResultado = new ArrayList<>();
		listaResultado.add(new Procedimento(1, "Procedimento1", LocalDate.of(2024,04,14), "NÃO REALIZADO", "NÃO REALIZADO", null, null));
		listaResultado.add(new Procedimento(2, "Procedimento2", LocalDate.of(2024,04,14), "NÃO REALIZADO", "NÃO REALIZADO", null, null));
		when(this.procedimentoRepository.findByResultado("NÃO REALIZADO").thenReturn(listaResultado);

		List<Procedimento> listaDiagnostico = new ArrayList<>();
		listaDiagnostico.add(new Procedimento(1, "Procedimento1", LocalDate.of(2024,04,14), "NÃO REALIZADO", "NÃO REALIZADO", null, null));
		listaDiagnostico.add(new Procedimento(2, "Procedimento2", LocalDate.of(2024,04,14), "NÃO REALIZADO", "NÃO REALIZADO", null, null));
		when(this.procedimentoRepository.findByDiagnostico("NÃO REALIZADO").thenReturn(listaDiagnostico);

		List<Veterinario> listaVeterinarioId = new ArrayList<>();
		listaVeterinarioId.add(new Veterinario());
		when(this.procedimentoRepository.findByVetarinario(1).thenReturn(listaVeterinarioId));

		List<Veterinario> listaVeterinarioNome = new ArrayList<>();
		listaVeterinarioNome.add(new Veterinario());
		when(this.procedimentoRepository.findByVetarinarioNome("Teste").thenReturn(listaVeterinarioNome));

		List<Veterinario> listaVeterinarioCrmv = new ArrayList<>();
		listaVeterinarioCrmv.add(new Veterinario());
		when(this.procedimentoRepository.findByVetarinarioCrmv("CRMV01").thenReturn(listaVeterinarioCrmv));

		List<Veterinario> listaVeterinarioProcedimento = new ArrayList<>();
		listaVeterinarioProcedimento.add(new Veterinario());
		when(this.procedimentoRepository.findByNomeProcedimento("Raio X").thenReturn(listaVeterinarioProcedimento));
	}

	@Test
	@DisplayName("Teste de integração com o método save retornando sucesso")
	void testSave() {
		Procedimento procedimento = new Procedimento(1, "Castração", LocalDate.of(2023, 07, 19), null, null, null);

		ResponseEntity<String> response = procedimentoController.save(procedimento);
		assertTrue(response.getStatusCode() == HttpStatus.CREATED);
	}

	// TESTE PEGANDO A VALIDAÇÃO DE MINIMO DE CARACTÉRES PARA O NOME DO PROCEDIMENTO
	// - ANNOTATION @Size(min = 3)
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

	// TESTE PEGANDO A VALIDAÇÃO DE MINIMO DE CARACTERES PARA O DIAGNOSTICO -
	// ANNOTATION @Size(min = 7)
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
	public void testFindById_Normal() {
		// Mock do objeto Procedimento retornado pelo serviço
		Procedimento mockProcedimento = new Procedimento();
		mockProcedimento.setId(1L);
		mockProcedimento.setNomeProcedimento("Consulta");

		// Configura o comportamento do mock do serviço para retornar o mock do
		// procedimento quando findById é chamado
		when(procedimentoServiceMock.findById(1L)).thenReturn(mockProcedimento);

		// Chama o método findById do controlador
		ResponseEntity<Procedimento> response = procedimentoController.findById(1L);

		// Verifica se a resposta HTTP é OK (200)
		assertEquals(HttpStatus.OK, response.getStatusCode());

		// Verifica se o resultado retornado é igual ao mock do procedimento
		assertEquals(mockProcedimento, response.getBody());
	}

	@Test
	public void testListAll_Normal() {
		// Mock da lista de procedimentos retornada pelo serviço
		List<Procedimento> mockProcedimentos = new ArrayList<>();
		mockProcedimentos.add(new Procedimento(1L, "Consulta"));
		mockProcedimentos.add(new Procedimento(2L, "Cirurgia"));

		// Configura o comportamento do mock do serviço para retornar a lista de
		// procedimentos quando listAll é chamado
		when(procedimentoServiceMock.listAll()).thenReturn(mockProcedimentos);

		// Chama o método listAll do controlador
		ResponseEntity<List<Procedimento>> response = procedimentoController.listAll();

		// Verifica se a resposta HTTP é OK (200)
		assertEquals(HttpStatus.OK, response.getStatusCode());

		// Verifica se o resultado retornado é igual à lista mockada
		assertEquals(mockProcedimentos, response.getBody());
	}

	@Test
	public void testFindById_Exception() {
		// Configura o comportamento do mock do serviço para lançar uma exceção quando
		// findById é chamado
		when(procedimentoServiceMock.findById(1L)).thenThrow(new RuntimeException("Procedimento não encontrado"));

		// Chama o método findById do controlador
		ResponseEntity<Procedimento> response = procedimentoController.findById(1L);

		// Verifica se a resposta HTTP é BAD REQUEST (400)
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

		// Verifica se o corpo da resposta é nulo
		assertNull(response.getBody());
	}

	@Test
	public void testListAll_Exception() {
		// Configura o comportamento do mock do serviço para lançar uma exceção quando
		// listAll é chamado
		when(procedimentoServiceMock.listAll()).thenThrow(new RuntimeException("Erro ao listar procedimentos"));

		// Chama o método listAll do controlador
		ResponseEntity<List<Procedimento>> response = procedimentoController.listAll();

		// Verifica se a resposta HTTP é BAD REQUEST (400)
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

		// Verifica se o corpo da resposta é nulo
		assertNull(response.getBody());
	}

	@Test
	public void testFindByPeriodo(){
		ResponseEntity<List<Procedimento>> response = this.procedimentoController.findAllByPeriodoBetween("12/04/2024", "15/04/2024");
		List<Procedimento> lista = response.getBody();
		assertEquals(2, lista.size());
	}

	@Test
	public void testFindByDiagnostico(){
		ResponseEntity<List<Procedimento>> response = this.procedimentoController.findByDiagnostico("NÃO REALIZADO");
		List<Procedimento> lista = response.getBody();
		assertEquals(2, lista.size());
	}

	@Test
	public void testFindByResultado(){
		ResponseEntity<List<Procedimento>> response = this.procedimentoController.findByResultado("NÃO REALIZADO");
		List<Procedimento> lista = response.getBody();
		assertEquals(2, lista.size());
	}

	@Test
	public void testFindByVeterinario(){
		ResponseEntity<List<Procedimento>> response = this.procedimentoController.findByVetarinario(1);
		List<Procedimento> lista = response.getBody();
		assertEquals(1, lista.size());
	}

	@Test
	public void testFindByVeterinarioNome(){
		ResponseEntity<List<Procedimento>> response = this.procedimentoController.findByVetarinarioNome(1);
		List<Procedimento> lista = response.getBody();
		assertEquals(1, lista.size());
	}

	@Test
	public void testFindByVeterinarioCrmv(){
		ResponseEntity<List<Procedimento>> response = this.procedimentoController.findByVetarinarioCrmv("CRMV");
		List<Procedimento> lista = response.getBody();
		assertEquals(1, lista.size());
	}

	@Test
	public void testFindByVeterinarioNomeProcedimento(){
		ResponseEntity<List<Procedimento>> response = this.procedimentoController.findByNomeProcedimento("Raio X");
		List<Procedimento> lista = response.getBody();
		assertEquals(2, lista.size());
	}
}
