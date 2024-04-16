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

@SpringBootTest
public class VeterinarioControllerTest {
	@Autowired
	VeterinarioController veterinarioController;
	
	@MockBean
	VeterinarioRepository veterinarioRepository;
	
	@BeforeEach
	void setup() {
		Veterinario veterinario = new Veterinario();
		
		when(this.veterinarioRepository.save(veterinario)).thenReturn(veterinario);
	}
	
	@Test
	@DisplayName("Teste de integração com o método save retornando sucesso")
	void testSave() {
		Veterinario veterinario = new Veterinario(7, "Chico Bento", "6390", "Sitio, Vila Abobrinha",null);
		
		ResponseEntity<String>response = veterinarioController.save(veterinario);
		assertTrue(response.getStatusCode() == HttpStatus.CREATED);
	}
	
	//TESTE PEGANDO A VALIDAÇÃO DE CRMV QUE NÃO PODE SER NULO - ANNOTATION @NotBlank)
	@Test
	@DisplayName("Teste de integração com o método save retornando assertThrows")
	void testSaveCrmv() {
		Veterinario veterinario = new Veterinario(8, "Zé Lelé", "", "Sitio, Vila Abobrinha",null);
		
		assertThrows(Exception.class,()->{
			ResponseEntity<String>response = veterinarioController.save(veterinario);
		});
	}
	
	@Test
	@DisplayName("Teste de integração com método save retornando uma exception")
	void testSaveException() {
		Veterinario veterinario = new Veterinario();
		
		ResponseEntity<String>response = veterinarioController.save(null);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	@DisplayName("Teste de integração com o método update retornando sucesso")
	void testUpdate() {
		Veterinario veterinario = new Veterinario(2, "Mauricio de Sousa", "72910", "Rua do Limão, Vila do Limoeiro",null);
		long id = 0;
		
		ResponseEntity<String>response = veterinarioController.update(veterinario, id);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	//TESTE PEGANDO A VALIDAÇÃO DE QUANTIDADE DE CARACTERES PERMITIDO NO NOME - ANNOTATION @PATTERN(regexp)
	@Test
	@DisplayName("Teste de integração com o método update retornando assertThrows")
	void testUpdateNome() {
		Veterinario veterinario = new Veterinario(2, "Ma", "72910", "Rua 7, Vila do Limoeiro",null);
		long id = 0;
		
		assertThrows(Exception.class,()->{
			ResponseEntity<String>response = veterinarioController.update(veterinario,id);
		});
	}

}
