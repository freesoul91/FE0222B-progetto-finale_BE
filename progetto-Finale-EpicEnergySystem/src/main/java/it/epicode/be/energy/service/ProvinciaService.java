package it.epicode.be.energy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.energy.exceptions.EnergyException;
import it.epicode.be.energy.model.Provincia;
import it.epicode.be.energy.repository.ProvinciaRepository;

@Service
public class ProvinciaService {

	@Autowired
	ProvinciaRepository provinciaRepo;

	public Provincia save(Provincia provincia) {
		return provinciaRepo.save(provincia);
	}

	public Optional<Provincia> findBySigla(String sigla) {
		return provinciaRepo.findBySigla(sigla);
	}

	public Optional<Provincia> findByNome(String nome) {
		return provinciaRepo.findByNome(nome);
	}

	public Page<Provincia> findAll(Pageable pageable) {
		return provinciaRepo.findAll(pageable);
	}

	public List<Provincia> findAll() {
		return provinciaRepo.findAll();
	}

	public Optional<Provincia> findById(Long id) {
		return provinciaRepo.findById(id);
	}

	public void delete(Long id) {
		if (provinciaRepo.findById(id).isPresent()) {

			provinciaRepo.delete(provinciaRepo.findById(id).get());
		}

	}

	public Provincia update(Long id, Provincia provincia) {
		Optional<Provincia> provinciaResult = provinciaRepo.findById(id);
		if (provinciaResult.isPresent()) {
			Provincia update = provinciaResult.get();
			update.setNome(provincia.getNome());
			update.setSigla(provincia.getSigla());
			update.setRegione(provincia.getRegione());
			return provinciaRepo.save(update);
		} else {
			throw new EnergyException("Provincia non aggiornata/trovata");
		}

	}
}
