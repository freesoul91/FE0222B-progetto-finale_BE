package it.epicode.be.energy.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

/**
 * Superclasse che estende tutti i model del progetto in modo da evitare di
 * inserire in ogni model l'attributo Long id
 * 
 * @author danie
 *
 */
@MappedSuperclass
@Data
public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}
