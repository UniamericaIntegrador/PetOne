package app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.entity.Procedimento;
import app.entity.Veterinario;

public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long>{

	@Query("SELECT p FROM Procedimento p WHERE p.nomeProcedimento LIKE CONCAT ('%', :nomeProcedimento, '%')")
	public List<Procedimento> buscarPorNomeProcedimento(@Param("nomeProcedimento") String nomeProcedimento);
	
	@Query("SELECT COUNT(p) FROM Procedimento p")
	public long count();
}
