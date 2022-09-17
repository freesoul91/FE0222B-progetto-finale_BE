package it.epicode.be.energy.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Fattura extends BaseEntity {
	@ManyToOne
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private Cliente cliente;
	private int anno;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data;
	private BigDecimal importo;
	private int numeroFattura;
	private String stato;

	public void setData() {
		this.data = new Date();
	}

	public void setAnno() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.data);
		var year = cal.get(Calendar.YEAR);
		this.anno = year;
	}

}
