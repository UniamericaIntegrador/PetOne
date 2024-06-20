package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.entity.Especie;
import app.entity.Paciente;
import app.entity.Raca;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	//public List<Paciente> findByRaca(String raca);

	//public List<Paciente> findByEspecie(String especie);
	
	//public List<Paciente>findByRacaEspecie(Raca raca);
	
	public List<Paciente> findByRaca(Raca raca);
	
	//public List<Paciente>findByEspecie(Especie especie);

	@Query("SELECT p FROM Paciente p WHERE YEAR (p.dataNascimento) > :ano")
	public List<Paciente> findByAcimaAno(int ano);

	@Query("FROM Paciente p WHERE p.nome LIKE %:part% ")
	public List<Paciente> findByPart( @Param("part") String part);
	
	@Query("SELECT COUNT(p) FROM Paciente p")
	public long counter();
}
