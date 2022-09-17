package it.epicode.be.energy.util.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.epicode.be.energy.model.Comune;
import it.epicode.be.energy.service.ComuneService;

@Component
public class ComuneConverter implements Converter<Long, Comune> {

	@Autowired
	ComuneService comuneServ;

	@Override
	public Comune convert(Long id) {
		return comuneServ.findById(id).get();
	}

}
