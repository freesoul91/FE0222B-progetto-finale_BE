package it.epicode.be.energy.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.be.energy.model.Fattura;

public interface FatturaRepository extends JpaRepository<Fattura, Long> {

	@Query("Select f from Fattura f where f.stato= :stato")
	public Page<Fattura> findAllByStato(String stato, Pageable pageable);

	public Page<Fattura> findAllByData(Date data, Pageable pageable);

	@Query("Select f from Fattura f where f.cliente.id=:id")
	public Page<Fattura> findFatturaByIdCliente(Long id, Pageable pageable);

	public Page<Fattura> findAllByAnno(int anno, Pageable pageable);

	@Query("Select f from Fattura f where f.importo between :min and :max")
	public Page<Fattura> findByRange(BigDecimal min, BigDecimal max, Pageable pageable);

	public Page<Fattura> findAll(Pageable pageable);
	
	public List<Fattura> findByClienteId(Long id);

}
