package app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import app.entity.Veterinario;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long>{

    // Métodos automáticos CRUD

    // Método para encontrar veterinários pelo nome
    public List<Veterinario> findByNome(String nome);

    // Método para encontrar veterinários pelo CRMV
   public  Veterinario findByCrmv(String crmv);

    // Método para encontrar veterinários pelo endereço
    //List<Veterinario> findByEndereco(String endereco);
    
	public Optional<Veterinario> findByEmail(String email);


    // JPQL para encontrar veterinários com nome iniciando com uma determinada letra
    @Query("SELECT v FROM Veterinario v WHERE v.nome LIKE CONCAT(:letra, '%')")
    public List<Veterinario> findByNomeStartingWith(@Param("letra") String letra);

    // JPQL para atualizar o endereço do veterinário pelo ID
    @Transactional
    @Modifying
    @Query("UPDATE Veterinario v SET v.endereco = :novoEndereco WHERE v.id = :id")
    public void updateEnderecoById(@Param("id") long id, @Param("novoEndereco") String novoEndereco);
    
    @Query("SELECT COUNT(p) FROM Veterinario p")
    public long count();
}