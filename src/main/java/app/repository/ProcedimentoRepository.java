package app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entity.Procedimento;
import app.entity.Veterinario;

public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long> {

	public List<Procedimento> findAllByDataBetween(LocalDate dataStart, LocalDate dataEnd);

	public List<Procedimento> findByResultado(String resultado);

	public List<Procedimento> findByDiagnostico(String diagnostico);

	public List<Procedimento> findByVeterinario(Veterinario veterinario);
	
	public List<Procedimento> findByVeterinarioNome(String nome);
	
	public List<Procedimento> findByVeterinarioCrmv(String crmv);
	
	//JPQL:
	
	@Query("SELECT p FROM Procedimento p WHERE p.nomeProcedimento LIKE CONCAT ('%', :nomeProcedimento, '%')")
	public List<Procedimento> BuscarPorNomeProcedimento(String nomeProcedimento);
	
	

}
