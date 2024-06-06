package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.entity.Veterinario;
import app.repository.VeterinarioRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class VeterinarioControllerTest {
	@Autowired
	VeterinarioController veterinarioController;

	@MockBean
	VeterinarioRepository veterinarioRepository;

	@BeforeEach
	void setup() {
		List<Veterinario>listaVeterinario = new ArrayList<>();
		//listaVeterinario.add(new Veterinario(1, "Zé da Roça", "7207", "Sitio, Vila Abobrinha", null));
		//listaVeterinario.add(new Veterinario(2, "Dona Marocas", "74927", "Sitio, Vila Abobrinha", null));
		//listaVeterinario.add(new Veterinario(3, "Nhô Lau", "45893", "Sitio, Vila Abobrinha", null));
		listaVeterinario.add(null);
		
		Veterinario veterinario = new Veterinario();
		String endereco = "Japão";

		when(this.veterinarioRepository.save(veterinario)).thenReturn(veterinario);
		when(this.veterinarioRepository.findAll()).thenReturn(listaVeterinario);
		//when(this.veterinarioRepository.findByEndereco(endereco)).thenThrow(IllegalArgumentException.class);
		doNothing().when(this.veterinarioRepository).deleteById(Mockito.anyLong());
	}

	/*
	@Test
	@DisplayName("Teste de integração com o método save retornando sucesso")
	void testSave() {
		Veterinario veterinario = new Veterinario(7, "Chico Bento", "6390", "Sitio, Vila Abobrinha", null);

		ResponseEntity<String> response = veterinarioController.save(veterinario);
		assertTrue(response.getStatusCode() == HttpStatus.CREATED);
	}
	*/

	// TESTE PEGANDO A VALIDAÇÃO DE CRMV QUE NÃO PODE SER NULO - ANNOTATION @NotBlank)
	/*
	@Test
	@DisplayName("Teste de integração com o método save retornando assertThrows")
	void testSaveCrmv() {
		Veterinario veterinario = new Veterinario(8, "Zé Lelé", "", "Sitio, Vila Abobrinha", null);

		assertThrows(Exception.class, () -> {
			ResponseEntity<String> response = veterinarioController.save(veterinario);
		});
	}
	*/

	/*
	@Test
	@DisplayName("Teste de integração com o método update retornando sucesso")
	void testUpdate() {
		Veterinario veterinario = new Veterinario(2, "Mauricio de Sousa", "72910", "Rua do Limão, Vila do Limoeiro", null);
		long id = 0;
		ResponseEntity<String> response = veterinarioController.update(veterinario, id);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	*/

	// TESTE PEGANDO A VALIDAÇÃO DE QUANTIDADE DE CARACTERES PERMITIDO NO NOME - ANNOTATION @PATTERN(regexp)
	/*
	@Test
	@DisplayName("Teste de integração com o método update retornando assertThrows")
	void testUpdateNome() {
		Veterinario veterinario = new Veterinario(2, "Ma", "72910", "Rua 7, Vila do Limoeiro", null);
		long id = 0;

		assertThrows(Exception.class, () -> {
			ResponseEntity<String> response = veterinarioController.update(veterinario, id);
		});
	}
	*/
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método delete")
	void testDelete() {
		long id = 0;
		ResponseEntity<String>response = veterinarioController.delete(id);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método delete com exception")
	void testDeleteException() {
		long id = -1;
		ResponseEntity<String>response = veterinarioController.delete(id);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());	
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método findAll")
	void testFindAll() {
		ResponseEntity<List<Veterinario>>response = this.veterinarioController.listAll();
		List<Veterinario>listaVeterinario = response.getBody();
		
		assertEquals(4, listaVeterinario.size());
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método findById com exception")
	void testFindByIdException() {
		long id = 0;
		ResponseEntity<Veterinario>response = veterinarioController.findById(id);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByCrmv")
	void testFindByCrmv() {
		String crmv = null;
		ResponseEntity<Veterinario> response = veterinarioController.findByCrmv(crmv);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	/*
	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByEndereco")
	void testFindByEndereco() {
		String endereco = "Brasil";
		ResponseEntity<List<Veterinario>> response = veterinarioController.findByEndereco(endereco);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	*/
	
	/*
	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByEndereco com exception")
	void testFindByEnderecoException() {
		String endereco = "Japão";
		ResponseEntity<List<Veterinario>> response = veterinarioController.findByEndereco(endereco);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	*/
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByNome")
	void testFindByNome() {
		String nome = "Nhô Lau";

		ResponseEntity<List<Veterinario>> response = veterinarioController.findByNome(nome);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	
}
