package it.epicode.be.energy.tests;


import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import it.epicode.be.energy.model.Fattura;
import it.epicode.be.energy.service.FatturaService;


@WebMvcTest
public class FatturaTest {
	
	@Autowired
	private MockMvc mvc;
	@MockBean
	private FatturaService service;
	
	Fattura test= Fattura.builder()
			.withImporto(new BigDecimal(345.00))
			.build();
	
}
