package it.epicode.be.energy.service.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.epicode.be.energy.model.Provincia;
import it.epicode.be.energy.model.web.Paged;
import it.epicode.be.energy.model.web.Paging;
import it.epicode.be.energy.repository.ProvinciaRepository;

@Service
public class ProvinciaServiceWeb {

	private final ProvinciaRepository provinciaRepo;

	@Autowired
	public ProvinciaServiceWeb(ProvinciaRepository provinciaRepo) {
		this.provinciaRepo = provinciaRepo;
	}

	public Paged<Provincia> getPage(int pageNumber, int size) {
		PageRequest request = PageRequest.of(pageNumber - 1, size, Sort.by(Sort.Direction.ASC, "id"));
		Page<Provincia> postPage = provinciaRepo.findAll(request);
		return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), pageNumber, size));
	}

}
