package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.entity.Tutor;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import app.repository.TutorRepository;

@SpringBootTest
public class TutorControllerTest {
	@Autowired
	TutorController tutorController;

	@MockBean
	TutorRepository tutorRepository;

	@BeforeEach
	void setup() {
		List<Tutor>listaTutor = new ArrayList<>();
		listaTutor.add(new Tutor(5, "Magali Lima", "077.271.590-42", 7,"Rua do Limão, Bairro do Limoeiro", null));
		listaTutor.add(new Tutor(6, "Nimbus Hiromashi", "800.951.440-30", 7,"Rua do Limão, Bairro do Limoeiro", null));
		listaTutor.add(new Tutor(7, "Do Contra", "884.678.200-39", 7,"Rua do Limão, Bairro do Limoeiro", null));
		listaTutor.add(null);
		
		Tutor tutor = new Tutor();

		when(this.tutorRepository.save(tutor)).thenReturn(tutor);
		when(this.tutorRepository.findAll()).thenReturn(listaTutor);
	}

	// ------- POR CAUSA DA VALIDAÇÀO @CPF, O CPF DEVE SER UM DOCUMENTO QUE REALMENTE EXISTA. NA HORA DE TESTAR, SUBSTITUIR O QUE ESTÁ COM X -------
	@Test
	@DisplayName("Teste de integração com o método save retornando sucesso")
	void testSave() {
		Tutor tutor = new Tutor(1, "Cebolinha Silva", "246.683.220-83", 18, "Rua do Limão, Bairro do Limoeiro", null);

		ResponseEntity<String> response = tutorController.save(tutor);
		assertTrue(response.getStatusCode() == HttpStatus.CREATED);
	}

	// TESTE PEGANDO A VALIDAÇÃO DE CPF INEXISTENTE- ANNOTATION @Cpf)
	@Test
	@DisplayName("Teste de integração com o método save retornando assertThrows")
	void testSaveCpf() {
		Tutor tutor = new Tutor(2, "Monica de Sousa", "000.000.000-00", 7, "Rua do Limão, Bairro do Limoeiro", null);

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
		Tutor tutor = new Tutor(3, "Cascão Araujo", "893.933.920-72", 18, "Rua do Limão, Bairro do Limoeiro", null);
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
		Tutor tutor = new Tutor(3, "C", "592.076.420-18", 7, "Rua do Limão, Bairro do Limoeiro", null);
		long id = 0;

		assertThrows(Exception.class, () -> {
			ResponseEntity<String> response = tutorController.update(tutor, id);
		});
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método delete")
	void testDelete() {
		long id = 0;
		ResponseEntity<String>response = tutorController.delete(id);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método delete com exception")
	void testDeleteException() {
		long id = -1;
		ResponseEntity<String>response = tutorController.delete(id);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());	
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método findAll")
	void testFindAll() {
		ResponseEntity<List<Tutor>>response = this.tutorController.listAll();
		List<Tutor>listaTutor = response.getBody();
		
		assertEquals(4, listaTutor.size());
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método findById com exception")
	void testFindByIdException() {
		long id = 0;
		ResponseEntity<Tutor>response = tutorController.findById(id);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByCpf")
	void testFindByCpf() {
		String cpf = "887.683.890-27";
		ResponseEntity<List<Tutor>> response = tutorController.findByCpf(cpf);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByNome")
	void testFindByNome() {
		String nome = "Maria Cebolinha";
		ResponseEntity<List<Tutor>> response = tutorController.findByNome(nome);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByEndereco")
	void testFindByEndereco() {
		String endereco = "Rua do Limão, Bairro do Limoeiro";
		ResponseEntity<List<Tutor>> response = tutorController.findByEndereco(endereco);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByPacienteNome")
	void testFindByPacienteNome() {
		String nome = "Chovinista";
		ResponseEntity<List<Tutor>> response = tutorController.findByPacienteNome(nome);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	
	
	
	
	
}
