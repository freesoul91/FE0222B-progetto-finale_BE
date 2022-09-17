package it.epicode.be.energy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.energy.exceptions.EnergyException;
import it.epicode.be.energy.model.Indirizzo;
import it.epicode.be.energy.repository.IndirizzoRepository;

@Service
public class IndirizzoService {

	@Autowired
	IndirizzoRepository indirizzoRepo;

	public Page<Indirizzo> findAll(Pageable pageable) {
		return indirizzoRepo.findAll(pageable);
	}

	public List<Indirizzo> findAll() {
		return indirizzoRepo.findAll();
	}

	public Optional<Indirizzo> findById(Long id) {
		return indirizzoRepo.findById(id);
	}

	public Indirizzo save(Indirizzo indirizzo) {
		Indirizzo i = new Indirizzo();
		i.setVia(indirizzo.getVia());
		i.setCivico(indirizzo.getCivico());
		i.setCap(indirizzo.getCap());
		i.setComune(indirizzo.getComune());
		i.setLocalita(indirizzo.getLocalita());
		return indirizzoRepo.save(i);
	}

	public void delete(Long id) {
		if (indirizzoRepo.findById(id).isPresent()) {
			Indirizzo i = indirizzoRepo.findById(id).get();
			i.setComune(null);
			indirizzoRepo.delete(i);
		}

	}

	public Indirizzo update(Long id, Indirizzo indirizzo) {
		Optional<Indirizzo> result = indirizzoRepo.findById(id);
		if (result.isPresent()) {
			Indirizzo update = result.get();
			update.setCap(indirizzo.getCap());
			update.setCivico(indirizzo.getCivico());
			update.setComune(indirizzo.getComune());
			update.setVia(indirizzo.getVia());
			update.setLocalita(indirizzo.getLocalita());
			return indirizzoRepo.save(update);
		} else {
			throw new EnergyException("Indirizzo non trovato/aggiornato!");
		}

	}

}
