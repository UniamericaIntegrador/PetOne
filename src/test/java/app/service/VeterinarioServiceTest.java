package app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import app.entity.Veterinario;

@SpringBootTest
public class VeterinarioServiceTest {
	@Autowired
	VeterinarioService veterinarioService;

    @Test
    @DisplayName("Teste unitário para endereço inválido que contem Japão")
    void testEnderecoInvalido() {
        Veterinario veterinario = new Veterinario(1, "Rosinha Rodrigues", "7429", "Sitio, Vila Abobrinha, Japão", null);

        assertThrows(IllegalArgumentException.class, () -> {
            veterinarioService.verificarEndereco(veterinario);
        });
    }

    @Test
    @DisplayName("Teste unitário para endereço válido que não contem Japão")
    void testEnderecoValido() {
        Veterinario veterinario = new Veterinario(1, "Rosinha Rodrigues", "7429", "Sitio, Vila Abobrinha, Brasil", null);
        Veterinario resultado = veterinarioService.verificarEndereco(veterinario);

        assertEquals(veterinario.getEndereco(), resultado.getEndereco());
    }

}
