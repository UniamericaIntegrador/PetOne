package app.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import app.config.JwtServiceGenerator;

@Service
public class LoginService {

	@Autowired
	private LoginRepository repository;
	@Autowired
	private JwtServiceGenerator jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	public String logar(Login login) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						login.getEmail(),
						login.getPassword()
				)
		);
		Usuario user = repository.findByEmail(login.getEmail()).get();
		String jwtToken = jwtService.generateToken(user);

		return jwtToken;
	}

	public String cadastro(Usuario usuario) throws Exception {
		Optional<Usuario> existingUser = repository.findByEmail(usuario.getEmail());
		if (existingUser.isPresent()) {
			throw new Exception("Usuário já cadastrado com este email");
		}

		Usuario user = new Usuario();
		user.setUsername(usuario.getEmail());
		user.setPassword(passwordEncoder.encode(usuario.getPassword()));
		user.setNome(usuario.getNome());
		user.setEmail(usuario.getEmail());
		user.setCpf(usuario.getCpf());
		user.setRole("USER");

		repository.save(user);

		// Logar o usuário após cadastro
		Login login = new Login();
		login.setEmail(usuario.getEmail());
		login.setPassword(usuario.getPassword()); // Use a senha original aqui, não codificada

		return logar(login);
	}
	
	public Usuario findByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }
	
}
