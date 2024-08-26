package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Raca;

public interface RacaRepository extends JpaRepository<Raca, Long> {

    public Raca findByNome(String nome);
}
