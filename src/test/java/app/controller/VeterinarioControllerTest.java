package app.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class VeterinarioControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc;

    @Test
    public void testDelete() throws Exception {
        // Execute a solicitação DELETE e espera o status HTTP 200 (OK)
        mockMvc.perform(delete("/api/veterinario/delete/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testDeleteBadRequest() throws Exception {
        // Tenta excluir um paciente com ID inválido (por exemplo, -1)
        mockMvc.perform(delete("/api/veterinario/delete/{id}", -1L) // ID inválido
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()); // Espera-se um Bad Request (400)
    }

}
