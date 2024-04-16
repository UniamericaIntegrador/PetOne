package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
	TutorController tutorController;
	
	@MockBean
	TutorRepository tutorRepository;
	
	@BeforeEach
	void setup() {
		Tutor tutor = new Tutor();
		
		when(this.tutorRepository.save(tutor)).thenReturn(tutor);
	}
	
	// ------- POR CAUSA DA VALIDAÇÀO @CPF, O CPF DEVE SER UM DOCUMENTO QUE REALMENTE EXISTA. NA HORA DE TESTAR, SUBSTITUIR O QUE ESTÁ COM X -------
	@Test
	@DisplayName("Teste de integração com o método save retornando sucesso")
	void testSave() {
		Tutor tutor = new Tutor(1, "Cebolinha Silva", "XXX.XXX.XXX-XX", "Rua do Limão, Bairro do Limoeiro",null);
		
		ResponseEntity<String>response = tutorController.save(tutor);
		assertTrue(response.getStatusCode() == HttpStatus.CREATED);
	}
	
	//TESTE PEGANDO A VALIDAÇÃO DE CPF INEXISTENTE- ANNOTATION @Cpf)
	@Test
	@DisplayName("Teste de integração com o método save retornando assertThrows")
	void testSaveCpf() {
		Tutor tutor = new Tutor(2, "Monica de Sousa", "000.000.000-00", "Rua do Limão, Bairro do Limoeiro",null);
		
		assertThrows(Exception.class,()->{
			ResponseEntity<String>response = tutorController.save(tutor);
		});
	}
	
	@Test
	@DisplayName("Teste de integração com método save retornando uma exception")
	void testSaveException() {
		Tutor tutor = new Tutor();
		
		ResponseEntity<String>response = tutorController.save(null);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	// ------- POR CAUSA DA VALIDAÇÀO @CPF, O CPF DEVE SER UM DOCUMENTO QUE REALMENTE EXISTA. NA HORA DE TESTAR, SUBSTITUIR O QUE ESTÁ COM X -------
	@Test
	@DisplayName("Teste de integração com o método update retornando sucesso")
	void testUpdate() {
		Tutor tutor = new Tutor(3, "Cascão Araujo", "XXX.XXX.XXX-XX", "Rua do Limão, Bairro do Limoeiro",null);
		long id = 0;
		
		ResponseEntity<String>response = tutorController.update(tutor, id);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	// ------- POR CAUSA DA VALIDAÇÀO @CPF, O CPF DEVE SER UM DOCUMENTO QUE REALMENTE EXISTA. NA HORA DE TESTAR, SUBSTITUIR O QUE ESTÁ COM X -------
	//TESTE PEGANDO A VALIDAÇÃO DE QUANTIDADE DE CARACTERES PERMITIDO NO NOME - ANNOTATION @PATTERN(regexp)
	@Test
	@DisplayName("Teste de integração com o método update retornando assertThrows")
	void testUpdateNome() {
		Tutor tutor = new Tutor(3, "C", "XXX.XXX.XXX-XX", "Rua do Limão, Bairro do Limoeiro",null);
		long id = 0;
		
		assertThrows(Exception.class,()->{
			ResponseEntity<String>response = tutorController.update(tutor,id);
		});
	}
}




