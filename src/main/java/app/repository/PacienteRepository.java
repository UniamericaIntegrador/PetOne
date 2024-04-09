package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{

}
