package app.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.entity.Veterinario;
import app.service.VeterinarioService;

@SpringBootTest
public class VeterinarioControllerTest {

    @Mock
    private VeterinarioService veterinarioServiceMock;

    @InjectMocks
    private VeterinarioController veterinarioController;

    @Test
    public void testFindById_Normal() {
        // Mock do objeto Veterinario retornado pelo serviço
        Veterinario mockVeterinario = new Veterinario();
        mockVeterinario.setId(1L);
        mockVeterinario.setNome("João da Silva");

        // Configura o comportamento do mock do serviço para retornar o mock do veterinário quando findById é chamado
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
        // Configura o comportamento do mock do serviço para lançar uma exceção quando findById é chamado
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

        // Configura o comportamento do mock do serviço para retornar a lista de veterinários quando listAll é chamado
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
        // Configura o comportamento do mock do serviço para lançar uma exceção quando listAll é chamado
        when(veterinarioServiceMock.listAll()).thenThrow(new RuntimeException("Erro ao listar veterinários"));

        // Chama o método listAll do controlador
        ResponseEntity<List<Veterinario>> response = veterinarioController.listAll();

        // Verifica se a resposta HTTP é BAD REQUEST (400)
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        // Verifica se o corpo da resposta é nulo
        assertNull(response.getBody());
    }
}
