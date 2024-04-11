package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.entity.Procedimento;

public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long>{
	
	public List<Procedimento> findByNomeProcedimento(String nomeProcedimento);

    public List<Procedimento> findByData(String data);

    public List<Procedimento> findByResultado(String resultado);

    public List<Procedimento> findByDiagnostico(String diagnostico);
    
    @Query("SELECT P.id, P.nomeProcedimento, P.data, P.resultado, P.diagnostico \n"
            + "FROM Procedimento P \n"
            + "LEFT JOIN P.veterinario V \n" 
            + "WHERE V.id = :veterinario")
    public List<Procedimento> buscarProcedimentosPorVetId(@Param("veterinario") int veterinarioId);
    
    @Query("SELECT P.id, P.nomeProcedimento, P.data, P.resultado, P.diagnostico \n"
            + "FROM Procedimento P \n"
            + "LEFT JOIN P.veterinario V \n" 
            + "WHERE V.nome = :veterinario")
    public List<Procedimento> buscarProcedimentosPorVetString(@Param("veterinario") String veterinarioId);

}
