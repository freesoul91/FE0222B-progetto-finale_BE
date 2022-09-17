package it.epicode.be.energy.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.epicode.be.energy.model.Provincia;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {

	public Optional<Provincia> findBySigla(String sigla);

	public Page<Provincia> findByRegione(String regione, Pageable pageable);

	public Optional<Provincia> findByNome(String nome);

}
