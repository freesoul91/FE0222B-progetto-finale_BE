package it.epicode.be.energy.service.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.epicode.be.energy.model.Indirizzo;
import it.epicode.be.energy.model.web.Paged;
import it.epicode.be.energy.model.web.Paging;
import it.epicode.be.energy.repository.IndirizzoRepository;

@Service
public class IndirizzoServiceWeb {

	private final IndirizzoRepository indirizzoRepo;

	@Autowired
	public IndirizzoServiceWeb(IndirizzoRepository indirizzoRepo) {
		this.indirizzoRepo = indirizzoRepo;
	}

	public Paged<Indirizzo> getPage(int pageNumber, int size) {
		PageRequest request = PageRequest.of(pageNumber - 1, size, Sort.by(Sort.Direction.ASC, "id"));
		Page<Indirizzo> postPage = indirizzoRepo.findAll(request);
		return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), pageNumber, size));
	}

}
