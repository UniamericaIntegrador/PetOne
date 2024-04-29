package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	public List<Paciente> findByRaca(String raca);

	public List<Paciente> findByEspecie(String especie);

	@Query("SELECT p FROM Paciente p WHERE YEAR (p.dataNascimento) > :ano")
	public List<Paciente> findByAcimaAno(int ano);

<<<<<<< HEAD
	@Query("FROM Paciente p WHERE p.nome LIKE %:part% ")
	public List<Paciente> findByPart( @Param("part") String part);
=======
	@Query("FROM Paciente p WHERE p.nome LIKE '%:nome%' ")
	public List<Paciente> findByPart(String nome);
>>>>>>> branch 'master' of https://github.com/UniamericaIntegrador/PetOne.git
}
