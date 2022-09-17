package it.epicode.be.energy.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Classe principale del progetto che identifica un cliente
 * 
 * @author danie
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder(setterPrefix = "with")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Cliente extends BaseEntity {
	private String ragioneSociale;
	private String iva;
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataInserimento;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataUltimoContatto;
	private BigDecimal fatturatoAnnuale;
	private String pec;
	private String telefono;
	@Enumerated(EnumType.STRING)
	private TipoCliente tipoCliente;
	private String emailContatto;
	private String nomeContatto;
	private String cognomeContatto;
	private String telefonoContatto;

	@OneToOne(cascade = CascadeType.ALL)
	private Indirizzo sedeLegale;

	@OneToOne(cascade = CascadeType.ALL)
	private Indirizzo sedeOperativa;

	public final void setDataInserimento() {
		this.dataInserimento = new Date();
	}
}
