package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
<<<<<<< HEAD
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
import app.repository.PacienteRepository;
=======
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.entity.Paciente;
import app.service.PacienteService;
>>>>>>> refs/remotes/origin/master

@SpringBootTest
public class PacienteControllerTest {
	@Autowired
	PacienteController pacienteController;
	
	@MockBean
	PacienteRepository pacienteRepository;
	
	@BeforeEach
	void setup() {
		Paciente paciente = new Paciente();
		
		when(this.pacienteRepository.save(paciente)).thenReturn(paciente);
	}
	
	@Test
	@DisplayName("Teste de integração com o método save retornando sucesso")
	void testSave() {
		Paciente paciente = new Paciente(1, "Machu Picchu", "gato", LocalDate.of(2021, 1, 25), "SRD", null, null);
		
		ResponseEntity<String>response = pacienteController.save(paciente);
		assertTrue(response.getStatusCode() == HttpStatus.CREATED);
	}
	
	//TESTE PEGANDO A VALIDAÇÃO DE DATA NO FUTURO - ANNOTATION @PastOrPresent)
	@Test
	@DisplayName("Teste de integração com o método save retornando assertThrows")
	void testSaveData() {
		Paciente paciente = new Paciente(1, "Machu Picchu", "gato", LocalDate.of(2025, 1, 25), "SRD", null, null);

		assertThrows(Exception.class,()->{
			ResponseEntity<String>response = pacienteController.save(paciente);
		});
	}
	
	//TESTE PEGANDO A VALIDAÇÃO DE MINIMO DE CARACTERES PARA A ESPECIE - ANNOTATION @Size(min = 3)
	@Test
	@DisplayName("Teste de integração com o método save retornando assertThrows")
	void testSaveEspecie() {
		Paciente paciente = new Paciente(1, "Machu Picchu", "ga", LocalDate.of(2021, 1, 25), "SRD", null, null);

		assertThrows(Exception.class,()->{
			ResponseEntity<String>response = pacienteController.save(paciente);
		});
	}
	
	@Test
	@DisplayName("Teste de integração com método save retornando uma exception")
	void testSaveException() {
		Paciente paciente = new Paciente();
		
		ResponseEntity<String>response = pacienteController.save(null);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	@DisplayName("Teste de integração com o método update retornando sucesso")
	void testUpdate() {
		Paciente paciente = new Paciente(3, "Malu", "gato", LocalDate.of(2014, 12, 29), "SRD", null, null);
		long id = 0;
		
		ResponseEntity<String>response = pacienteController.update(paciente, id);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}
	
	//TESTE PEGANDO A VALIDAÇÃO DE MINIMO DE CARACTERES PARA A RACA - ANNOTATION @Size(min = 3)
	@Test
	@DisplayName("Teste de integração com o método update retornando assertThrows")
	void testUpdateRaca() {
		Paciente paciente = new Paciente(3, "Malu", "gato", LocalDate.of(2014, 12, 29), "SR", null, null);
		long id = 0;
		
		assertThrows(Exception.class,()->{
			ResponseEntity<String>response = pacienteController.update(paciente, id);
		});
	}

    @Mock
    private PacienteService pacienteServiceMock;

    @InjectMocks
    private PacienteController pacienteController;

    @Test
    public void testFindById_Normal() {
        // Mock do objeto Paciente retornado pelo serviço
        Paciente mockPaciente = new Paciente();
        mockPaciente.setId(1L);
        mockPaciente.setNome("Fido");
        mockPaciente.setEspecie("Cachorro");
        mockPaciente.setDataNascimento("01/01/2022");
        mockPaciente.setRaca("Golden Retriever");

        // Configura o comportamento do mock do serviço para retornar o mock do paciente quando findById é chamado
        when(pacienteServiceMock.findById(1L)).thenReturn(mockPaciente);

        // Chama o método findById do controlador
        ResponseEntity<Paciente> response = pacienteController.findById(1L);

        // Verifica se a resposta HTTP é OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verifica se o resultado retornado é igual ao mock do paciente
        assertEquals(mockPaciente, response.getBody());
    }

    @Test
    public void testFindById_Exception() {
        // Configura o comportamento do mock do serviço para lançar uma exceção quando findById é chamado
        when(pacienteServiceMock.findById(1L)).thenThrow(new RuntimeException("Paciente não encontrado"));

        // Chama o método findById do controlador
        ResponseEntity<Paciente> response = pacienteController.findById(1L);

        // Verifica se a resposta HTTP é BAD REQUEST (400)
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        // Verifica se o corpo da resposta é nulo
        assertEquals(null, response.getBody());
    }

    @Test
    public void testListAll_Normal() {
        // Mock da lista de pacientes retornada pelo serviço
        List<Paciente> mockPacientes = new ArrayList<>();
        mockPacientes.add(new Paciente(1L, "Fido", "Cachorro", "01/01/2022", "Golden Retriever"));
        mockPacientes.add(new Paciente(2L, "Whiskers", "Gato", "02/02/2022", "Siamese"));

        // Configura o comportamento do mock do serviço para retornar a lista de pacientes quando listAll é chamado
        when(pacienteServiceMock.listAll()).thenReturn(mockPacientes);

        // Chama o método listAll do controlador
        ResponseEntity<List<Paciente>> response = pacienteController.listAll();

        // Verifica se a resposta HTTP é OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verifica se o resultado retornado é igual à lista mockada
        assertEquals(mockPacientes, response.getBody());
    }

    @Test
    public void testListAll_Exception() {
        // Configura o comportamento do mock do serviço para lançar uma exceção quando listAll é chamado
        when(pacienteServiceMock.listAll()).thenThrow(new RuntimeException("Erro ao listar pacientes"));

        // Chama o método listAll do controlador
        ResponseEntity<List<Paciente>> response = pacienteController.listAll();

        // Verifica se a resposta HTTP é BAD REQUEST (400)
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        // Verifica se o corpo da resposta é nulo
        assertEquals(null, response.getBody());
    }
}
