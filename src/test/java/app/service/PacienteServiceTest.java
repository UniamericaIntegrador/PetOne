package app.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import app.entity.Paciente;
import app.repository.PacienteRepository;

public class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteService pacienteService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSavePaciente() {
    	// Preparação de um Paciente para teste
        Paciente paciente = new Paciente();
        paciente.setNome("Teste");
        paciente.setEspecie("Cão");

        // Configuração do comportamento do mock do repositório
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        // Execução do método do serviço que será testado
        String mensagem = pacienteService.save(paciente);

        // Verificação do resultado esperado
        assertEquals("Paciente Teste cadastrado com sucesso!", mensagem);
    }

    // Outros testes seguem a mesma lógica, verificando diferentes métodos do serviço.


    @Test
    public void testUpdatePaciente() {
        long id = 1;
        Paciente paciente = new Paciente();
        paciente.setId(id);
        paciente.setNome("Teste");
        paciente.setEspecie("Cão");

        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        String mensagem = pacienteService.update(id, paciente);

        assertEquals("Cadastro do paciente Teste alterado com sucesso!", mensagem);
    }

    @Test
    public void testDeletePaciente() {
        long id = 1;

        String mensagem = pacienteService.delete(id);

        assertEquals("Cadastro do paciente deletado com sucesso!", mensagem);
        verify(pacienteRepository, times(1)).deleteById(id);
    }

    @Test
    public void testListAllPacientes() {
        Paciente paciente1 = new Paciente();
        Paciente paciente2 = new Paciente();

        List<Paciente> pacientes = Arrays.asList(paciente1, paciente2);

        when(pacienteRepository.findAll()).thenReturn(pacientes);

        List<Paciente> result = pacienteService.listAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindById() {
        long id = 1;
        Paciente paciente = new Paciente();
        paciente.setId(id);

        when(pacienteRepository.findById(id)).thenReturn(Optional.of(paciente));

        Paciente result = pacienteService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }
    
    @Test
    public void testFindByPart() {
        String nome = "Teste";
        Paciente paciente1 = new Paciente();
        paciente1.setId(1);
        paciente1.setNome("NomeTeste");
        Paciente paciente2 = new Paciente();
        paciente2.setId(2);
        paciente2.setNome("Teste");

        List<Paciente> pacientes = Arrays.asList(paciente1, paciente2);

        when(pacienteRepository.findByPart(nome)).thenReturn(pacientes);

        List<Paciente> result = pacienteService.findByPart(nome);

        assertEquals(2, result.size());
    }

    @Test
    public void testFindByRaca() {
        String raca = "Labrador";
        Paciente paciente1 = new Paciente();
        paciente1.setId(1);
        paciente1.setRaca("Poodle");
        Paciente paciente2 = new Paciente();
        paciente2.setId(2);
        paciente2.setRaca("Labrador");

        List<Paciente> pacientes = Arrays.asList(paciente2);

        when(pacienteRepository.findByRaca(raca)).thenReturn(pacientes);

        List<Paciente> result = pacienteService.findByRaca(raca);

        assertEquals(1, result.size());
        assertEquals("Labrador", result.get(0).getRaca());
    }

    @Test
    public void testFindByEspecie() {
        String especie = "Cão";
        Paciente paciente1 = new Paciente();
        paciente1.setId(1);
        paciente1.setEspecie("Gato");
        Paciente paciente2 = new Paciente();
        paciente2.setId(2);
        paciente2.setEspecie("Cão");

        List<Paciente> pacientes = Arrays.asList(paciente2);

        when(pacienteRepository.findByEspecie(especie)).thenReturn(pacientes);

        List<Paciente> result = pacienteService.findByEspecie(especie);

        assertEquals(1, result.size());
        assertEquals("Cão", result.get(0).getEspecie());
    }

    @Test
    public void testFindByAcimaAno() {
        int ano = 2010;
        Paciente paciente1 = new Paciente();
        paciente1.setId(1);
        paciente1.setDataNascimento(Date.valueOf("2005-01-01"));
        Paciente paciente2 = new Paciente();
        paciente2.setId(2);
        paciente2.setDataNascimento(Date.valueOf("2015-01-01"));

        List<Paciente> pacientes = Arrays.asList(paciente2);

        when(pacienteRepository.findByAcimaAno(ano)).thenReturn(pacientes);

        List<Paciente> result = pacienteService.findByAcimaAno(ano);

        assertEquals(1, result.size());
        assertTrue(result.get(0).getDataNascimento().toLocalDate().getYear() > ano);
    }


}
