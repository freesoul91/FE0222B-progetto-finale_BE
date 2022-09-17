package it.epicode.be.energy.util.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.epicode.be.energy.model.Indirizzo;
import it.epicode.be.energy.service.IndirizzoService;

@Component
public class IndirizzoConverter implements Converter<Long, Indirizzo> {

	@Autowired
	IndirizzoService indirizzoServ;

	@Override
	public Indirizzo convert(Long id) {
		return indirizzoServ.findById(id).get();
	}

}
