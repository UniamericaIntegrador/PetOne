package app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import app.entity.Tutor;

@SpringBootTest
public class TutorServiceTest {
	@Autowired
	TutorService tutorService;
	
	/*
	@Test
	@DisplayName("TESTE UNITÁRIO PARA VERIFICAR IDADE MÍNIMA")
	void verificaIdadeTutor1() {
		Tutor tutor = new Tutor(1, "Fulano Silva", "08870809877", 25, "VilaA", null);
		Tutor retorno = this.tutorService.verificaIdadeTutor(tutor);
		
		assertEquals(25, retorno.getIdade());
	}
	*/
	
	
	/*
	@Test
	@DisplayName("TESTE UNITÁRIO PARA VERIFICAR IDADE MÍNIMA(retorna exception)")
	void verificaIdadeTutor2() {
		Tutor tutor = new Tutor(1, "Fulano Silva", "08870809877", 15, "VilaA", null);
		
		assertThrows(RuntimeException.class, () -> {
            tutorService.verificaIdadeTutor(tutor);
        });
	}
	*/
}
