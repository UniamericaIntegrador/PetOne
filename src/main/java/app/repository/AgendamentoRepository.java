package app.repository;

import app.entity.Agendamento;
import app.entity.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{
	public List<Agendamento> findAllByDataBetween(LocalDate dataStart, LocalDate dataEnd);

	public List<Agendamento> findByResultado(String resultado);

	public List<Agendamento> findByDiagnostico(String diagnostico);

	public List<Agendamento> findByVeterinario(Veterinario veterinario);
	
	public List<Agendamento> findByVeterinarioNome(String nome);
	
	public List<Agendamento> findByVeterinarioCrmv(String crmv);

	List<Agendamento> findByPacienteId(Long id);

	public List<Agendamento> findByVeterinarioId(Long id);
	
	//JPQL:
	
	@Query("SELECT p FROM Agendamento p WHERE p.nome LIKE CONCAT ('%', :nomeAgendamento, '%')")
	public List<Agendamento> buscarPorNomeAgendamento(@Param("nomeAgendamento") String nomeAgendamento);
	
	@Query("SELECT COUNT(p) FROM Agendamento p")
	public long count();
}
