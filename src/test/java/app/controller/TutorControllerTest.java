package app.controller;

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

public class TutorControllerTest {

    @Mock
    private TutorService tutorServiceMock;

    @InjectMocks
    private TutorController tutorController;

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

        // Configura o comportamento do mock do serviço para retornar o mock do tutor quando findById é chamado
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
        // Configura o comportamento do mock do serviço para lançar uma exceção quando findById é chamado
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

        // Configura o comportamento do mock do serviço para retornar a lista de tutores quando listAll é chamado
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
        // Configura o comportamento do mock do serviço para lançar uma exceção quando listAll é chamado
        when(tutorServiceMock.listAll()).thenThrow(new RuntimeException("Erro ao listar tutores"));

        // Chama o método listAll do controlador
        ResponseEntity<List<Tutor>> response = tutorController.listAll();

        // Verifica se a resposta HTTP é BAD REQUEST (400)
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        // Verifica se o corpo da resposta é nulo
        assertNull(response.getBody());
    }
}
