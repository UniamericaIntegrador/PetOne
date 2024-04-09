package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Veterinario;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long>{

}
