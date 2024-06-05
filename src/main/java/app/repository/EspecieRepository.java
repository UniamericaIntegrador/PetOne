package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Especie;

public interface EspecieRepository extends JpaRepository<Especie, Long> {

}
