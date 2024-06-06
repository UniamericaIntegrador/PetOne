package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.entity.Paciente;
import app.entity.Tutor;

public interface TutorRepository extends JpaRepository<Tutor, Long>{
	
	public List<Tutor> findByNome (String nome);
	
	public List<Tutor> findByCpf (String cpf);
	
	//public List<Tutor> findByEndereco (String endereco);
	
	public List<Tutor> findByPacienteNome (String nome);
	
	// -----JPQL-----
	
	@Query("SELECT t FROM Tutor t WHERE t.nome LIKE CONCAT ('%', :nome, '%')")
	public List<Tutor> findByTrechoNome(@Param("nome") String nome);
	
	@Query("SELECT t FROM Tutor t WHERE t.cpf LIKE CONCAT ('%', :cpf, '%')")
	public List<Tutor> findByTrechoCpf(@Param("cpf") String cpf);
	
	@Query("SELECT COUNT(p) FROM Tutor p")
	public long count();
}
