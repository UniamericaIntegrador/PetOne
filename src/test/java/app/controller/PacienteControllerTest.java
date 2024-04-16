package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

@SpringBootTest
public class PacienteControllerTest {

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
