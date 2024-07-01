package app.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Tutor;
import app.entity.Veterinario;


public interface LoginRepository extends JpaRepository<Tutor, Long>{

	public Optional<Tutor> findByEmail(String login);
	
	
}