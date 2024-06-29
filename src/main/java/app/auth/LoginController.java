package app.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import app.entity.Endereco;
import app.entity.Tutor;
import app.service.EnderecoService;

@RestController
@RequestMapping("/api/login")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private LoginService loginService;
    
    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<String> logar(@RequestBody Login login) {
        try {
            return ResponseEntity.ok(loginService.logar(login));
        } catch (AuthenticationException ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/cadastroTutor")
    public ResponseEntity<String> cadastro(@RequestBody Tutor tutor) {
        try {
            // Verifica se já existe um usuário com o mesmo e-mail
            Tutor existingTutor = loginService.findByEmail(tutor.getEmail());
            if (existingTutor != null) {
                return ResponseEntity.badRequest().body("Usuário já cadastrado com este e-mail");
            }

            // Salva o endereço primeiro
            Endereco enderecoSalvo = enderecoService.save(tutor.getEndereco());

            // Cria um novo tutor com os dados recebidos, incluindo o endereço salvo
            Tutor tutor1 = new Tutor();
            tutor1.setNome(tutor.getNome());
            tutor1.setEmail(tutor.getEmail());
            tutor1.setCpf(tutor.getCpf());
            tutor1.setIdade(tutor.getIdade());
            tutor1.setPassword(tutor.getPassword());
            tutor1.setEndereco(enderecoSalvo);
            tutor1.setRole("USER");

            // Salva o tutor no banco de dados
            String token = loginService.cadastrarTutor(tutor);

            return ResponseEntity.ok(token);
        } catch (AuthenticationException ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("Erro ao tentar cadastrar");
        }
    }

    @GetMapping("/firstLetter")
    public ResponseEntity<Tutor> getUsuarioLogado(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Busca o usuário pelo username (email) que está logado
        Tutor tutor = loginService.findByEmail(userDetails.getUsername());
        if (tutor == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(tutor);
    }
}
