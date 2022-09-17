package it.epicode.be.energy.service.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.epicode.be.energy.model.Fattura;
import it.epicode.be.energy.model.web.Paged;
import it.epicode.be.energy.model.web.Paging;
import it.epicode.be.energy.repository.FatturaRepository;

@Service
public class FatturaServiceWeb {

	private final FatturaRepository fatturaRepo;

	@Autowired
	public FatturaServiceWeb(FatturaRepository fatturaRepo) {
		this.fatturaRepo = fatturaRepo;
	}

	public Paged<Fattura> getPage(int pageNumber, int size) {
		PageRequest request = PageRequest.of(pageNumber - 1, size, Sort.by(Sort.Direction.ASC, "id"));
		Page<Fattura> postPage = fatturaRepo.findAll(request);
		return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), pageNumber, size));
	}
}
