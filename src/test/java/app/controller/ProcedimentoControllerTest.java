package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.entity.Procedimento;
import app.service.ProcedimentoService;

public class ProcedimentoControllerTest {

    @Mock
    private ProcedimentoService procedimentoServiceMock;

    @InjectMocks
    private ProcedimentoController procedimentoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById_Normal() {
        // Mock do objeto Procedimento retornado pelo serviço
        Procedimento mockProcedimento = new Procedimento();
        mockProcedimento.setId(1L);
        mockProcedimento.setNomeProcedimento("Consulta");

        // Configura o comportamento do mock do serviço para retornar o mock do procedimento quando findById é chamado
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

        // Configura o comportamento do mock do serviço para retornar a lista de procedimentos quando listAll é chamado
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
        // Configura o comportamento do mock do serviço para lançar uma exceção quando findById é chamado
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
        // Configura o comportamento do mock do serviço para lançar uma exceção quando listAll é chamado
        when(procedimentoServiceMock.listAll()).thenThrow(new RuntimeException("Erro ao listar procedimentos"));

        // Chama o método listAll do controlador
        ResponseEntity<List<Procedimento>> response = procedimentoController.listAll();

        // Verifica se a resposta HTTP é BAD REQUEST (400)
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        // Verifica se o corpo da resposta é nulo
        assertNull(response.getBody());
    }
}
