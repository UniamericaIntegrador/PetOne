package app.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@CrossOrigin("*")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
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

	@PostMapping("/cadastro")
	public ResponseEntity<String> cadastro(@RequestBody Usuario usuario) {
		try {
			String token = loginService.cadastro(usuario);
			return ResponseEntity.ok(token);
		} catch (AuthenticationException ex) {
			System.out.println(ex.getMessage());
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/firstLetter")
    public ResponseEntity<Usuario> getUsuarioLogado(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Busca o usuário pelo username (email) que está logado
        Usuario usuario = loginService.findByEmail(userDetails.getUsername());
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }
}
