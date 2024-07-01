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
import app.entity.Endereco;
import app.entity.Tutor;
import app.entity.Veterinario;
import app.repository.VeterinarioRepository;
import app.service.EnderecoService;

@Service
public class LoginService {

    @Autowired
    private LoginRepository repository;
    
    @Autowired
    private JwtServiceGenerator jwtService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private EnderecoService enderecoService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    public String logar(Login login) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(),
                        login.getPassword()
                )
        );
        Tutor user = repository.findByEmail(login.getEmail()).get();
        String jwtToken = jwtService.generateToken(user);

        return jwtToken;
    }

    public String cadastrarTutor(Tutor tutor) throws Exception {
        Optional<Tutor> existingUser = repository.findByEmail(tutor.getEmail());
        if (existingUser.isPresent()) {
            throw new Exception("Usuário já cadastrado com este email");
        }

        // Salvando o endereço primeiro
        Endereco enderecoSalvo = enderecoService.save(tutor.getEndereco());

        // Associando o endereço salvo ao tutor
        tutor.setEndereco(enderecoSalvo);

        // Configurando dados adicionais do tutor
        tutor.setUsername(tutor.getEmail());
        String rawPassword = tutor.getPassword(); // Guardar a senha original
        tutor.setPassword(passwordEncoder.encode(rawPassword));
        tutor.setRole("USER");

        // Salvando o tutor no banco de dados
        repository.save(tutor);

        // Logando o usuário após cadastro
        Login login = new Login();
        login.setEmail(tutor.getEmail());
        login.setPassword(rawPassword); // Usar a senha original para o login

        return logar(login);
    }
    
    
    public String cadastrarVeterinario(Veterinario veterinario) throws Exception {
        Optional<Veterinario> existingUser = veterinarioRepository.findByEmail(veterinario.getEmail());
        if (existingUser.isPresent()) {
            throw new Exception("Usuário já cadastrado com este email");
        }

        // Salvando o endereço primeiro
        Endereco enderecoSalvo = enderecoService.save(veterinario.getEndereco());

        // Associando o endereço salvo ao tutor
        veterinario.setEndereco(enderecoSalvo);

        // Configurando dados adicionais do tutor
        veterinario.setUsername(veterinario.getEmail());
        String rawPassword = veterinario.getPassword(); // Guardar a senha original
        veterinario.setPassword(passwordEncoder.encode(rawPassword));
        veterinario.setRole("USERVET");

        // Salvando o tutor no banco de dados
        veterinarioRepository.save(veterinario);

        // Logando o usuário após cadastro
        Login login = new Login();
        login.setEmail(veterinario.getEmail());
        login.setPassword(rawPassword); // Usar a senha original para o login

        return logar(login);
    }
    
    
    
    public Veterinario findByEmailVet(String email) {
        return veterinarioRepository.findByEmail(email).orElse(null);
    }
    
    

    public Tutor findByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }
}