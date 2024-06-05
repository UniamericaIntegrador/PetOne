package app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import app.entity.Paciente;

@SpringBootTest
public class PacienteServiceTest {
	@Autowired
	PacienteService pacienteService;

	@Test
	@DisplayName("Teste unitário retornando especie não permitida")
	void testEspecie() {
		//Paciente paciente = new Paciente(1, "Giselda", "Ave", LocalDate.of(2021, 1, 25), "Galinha", null, null);

		assertThrows(IllegalArgumentException.class, () -> {
			//pacienteService.verificarEspecie(paciente);
		});
	}
	
	@Test
	@DisplayName("Teste unitário retornando especie permitida")
	void testEspeciePermitida() {
	   // Paciente paciente = new Paciente(2, "Torresmo", "Porco", LocalDate.of(2021, 1, 25), "Porco", null, null);

	    //assertEquals("Porco", pacienteService.verificarEspecie(paciente).getEspecie());
	}

}
