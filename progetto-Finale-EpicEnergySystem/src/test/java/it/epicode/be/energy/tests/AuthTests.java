package it.epicode.be.energy.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Date;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.epicode.be.energy.model.Cliente;
import it.epicode.be.energy.model.Comune;
import it.epicode.be.energy.model.Indirizzo;
import it.epicode.be.energy.model.Provincia;
import it.epicode.be.energy.model.TipoCliente;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	Comune c1;
	Comune c2;
	Provincia p1;
	Provincia p2;
	Indirizzo iSedLeg1;
	Indirizzo iSedOpe1;
	Cliente cl1;
	
	@Test
	@WithAnonymousUser
	public void listaComuniWhenUtenteIsAnonymous() throws Exception {
		this.mockMvc.perform(get("/api/comune")).andExpect(status().isUnauthorized());
	}


	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "USER")
	public void listaComuniWhenUtenteMockIsAuthenticated() throws Exception {
		this.mockMvc.perform(get("/api/comune")).andExpect(status().isOk());
	}



	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "USER")
	public void deleteComuneByUser() throws Exception {
		this.mockMvc.perform(delete("/api/comune/9"))
			.andExpect(status().isForbidden());
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void deleteComuneByAdmin() throws Exception {
		this.mockMvc.perform(delete("/api/comune/97"))
			.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void addNuovoCliente() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(cl1);

		MvcResult result = mockMvc.perform(post("/api/cliente").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated()).andReturn();
			
		JSONObject json_obj=new JSONObject(result.getResponse().getContentAsString());
		assertTrue(json_obj.has("ragioneSociale"));
		assertTrue(json_obj.has("email"));
		
		assertTrue(json_obj.getString("email").contains("testspa@testspa.it"));

	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void addNuovoComune() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(c1);

		MvcResult result = mockMvc.perform(post("/api/comune").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();
			
		JSONObject json_obj=new JSONObject(result.getResponse().getContentAsString());
		assertTrue(json_obj.has("nome"));		
		
		assertTrue(json_obj.getString("nome").contains("Test Comune"));

	}

	@BeforeEach
	public void initContext() {
		cl1 = new Cliente();
		cl1.setRagioneSociale("Test");
		cl1.setTipoCliente(TipoCliente.SPA);
		cl1.setIva("02412630465");
		cl1.setEmail("testspa@testspa.it");
		cl1.setPec("testspa@pec.it");
		Date dIns = new Date();
		dIns.setDate(2022-03-13);
		Date dUltimoCont = new Date();
		dUltimoCont.setDate(2022-02-10);
		cl1.setDataInserimento(dIns);
		cl1.setDataUltimoContatto(dUltimoCont);
		BigDecimal fattAnn = new BigDecimal(12900000);
		cl1.setFatturatoAnnuale(fattAnn);
		cl1.setTelefono("0685387653");
		cl1.setNomeContatto("Mario");
		cl1.setCognomeContatto("Rossi");
		cl1.setTelefonoContatto("3348318694");
		cl1.setEmailContatto("m.rossi@gmail.com");
		
		p1 = new Provincia();
		p1.setNome("Torino");
		p1.setRegione("Piemonte");
		p1.setSigla("TO");
		
		p2 = new Provincia();
		p2.setNome("Torino");
		p2.setRegione("Piemonte");
		p2.setSigla("TO");
		
		c1 = new Comune();
		c1.setNome("Agliè");
		c1.setProvincia(p1);
		
		c2 = new Comune();
		c2.setNome("Agliò");
		c2.setProvincia(p2);
		
		iSedLeg1 = new Indirizzo();
		iSedLeg1.setCap(110L);
		iSedLeg1.setCivico(85);
		iSedLeg1.setComune(c1);
		iSedLeg1.setLocalita("Agliè-bassa");
		iSedLeg1.setVia("via Test");
		
		iSedOpe1 = new Indirizzo();
		iSedOpe1.setCap(110L);
		iSedOpe1.setCivico(789);
		iSedOpe1.setComune(c2);
		iSedOpe1.setLocalita("Agliè-alta");
		iSedOpe1.setVia("via Test2");
		
		cl1.setSedeLegale(iSedLeg1);
		cl1.setSedeOperativa(iSedOpe1);	
		System.out.println("Ciao");
	}
	
	@BeforeEach
	public void initContext2() {
		c1 = new Comune();
		p1 = new Provincia();
		p1.setNome("TEST");
		p1.setRegione("Regione test");
		p1.setSigla("TT");
		c1.setNome("Test Comune");
		c1.setProvincia(p1);
	}

}
