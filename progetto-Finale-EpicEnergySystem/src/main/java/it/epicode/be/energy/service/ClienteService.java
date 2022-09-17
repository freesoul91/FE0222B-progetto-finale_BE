package it.epicode.be.energy.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.energy.exceptions.EnergyException;
import it.epicode.be.energy.model.Cliente;
import it.epicode.be.energy.model.Fattura;
import it.epicode.be.energy.model.Indirizzo;
import it.epicode.be.energy.repository.ClienteRepository;
import it.epicode.be.energy.repository.FatturaRepository;
import it.epicode.be.energy.repository.IndirizzoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepo;

	@Autowired
	FatturaRepository fatturaRepo;

	@Autowired
	IndirizzoRepository indirizzoRepo;

	public Page<Cliente> findByParteRagioneSociale(String part, Pageable pageable) {
		try {
			Page<Cliente> clienti = clienteRepo.findByRagioneSocialeContaining(part, pageable);
			if (clienti.hasContent()) {
				return clienti;
			}
			log.error("Nessun cliente trovato");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}

	public Page<Cliente> findByDataInserimento(Date data, Pageable pageable) {
		try {

			return clienteRepo.findAllByDataInserimento(data, pageable);
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}

	public Page<Cliente> findByDataUltimoContatto(Date data, Pageable pageable) {
		try {

			return clienteRepo.findAllByDataUltimoContatto(data, pageable);
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}

	public Page<Cliente> findAllSortedByFatturatoAnnuale(BigDecimal fattAnn, Pageable pageable) {
		try {
			return clienteRepo.findAllSortedByFatturatoAnnuale(fattAnn, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}

	public Page<Fattura> findFatturaByIdCliente(Long id, Pageable pageable) {
		try {
			return fatturaRepo.findFatturaByIdCliente(id, pageable);
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}

	public Cliente save(Cliente cliente) {
		return clienteRepo.save(cliente);
	}

	public void delete(Long id) {
		if (clienteRepo.findById(id).isPresent()) {
			Cliente delete = clienteRepo.findById(id).get();
			Indirizzo sedLeg = indirizzoRepo.findById(delete.getSedeLegale().getId()).get();
			Indirizzo sedOpe = indirizzoRepo.findById(delete.getSedeOperativa().getId()).get();
			sedLeg.setComune(null);
			sedOpe.setComune(null);
			List<Fattura> fattureCliente = fatturaRepo.findByClienteId(id);
			for (Fattura f : fattureCliente) {
				fatturaRepo.delete(f);
			}		
			clienteRepo.deleteById(id);
		} else {
			throw new EnergyException("Cliente non cancellato/trovato!");
		}

	}

	public Cliente update(Long id, Cliente cliente) {
		Optional<Cliente> clienteResult = clienteRepo.findById(id);
		if (clienteResult.isPresent()) {
			Cliente update = clienteResult.get();
			update.setRagioneSociale(cliente.getRagioneSociale());
			update.setDataInserimento(cliente.getDataInserimento());
			update.setDataUltimoContatto(cliente.getDataUltimoContatto());
			update.setIva(cliente.getIva());
			update.setNomeContatto(cliente.getNomeContatto());
			update.setCognomeContatto(cliente.getCognomeContatto());
			update.setEmailContatto(cliente.getEmailContatto());
			update.setTelefonoContatto(cliente.getTelefonoContatto());
			update.setFatturatoAnnuale(cliente.getFatturatoAnnuale());
			update.setPec(cliente.getPec());
			update.setEmail(cliente.getEmail());
			update.setSedeLegale(cliente.getSedeLegale());
			update.setSedeOperativa(cliente.getSedeOperativa());
			update.setTelefono(cliente.getTelefono());
			update.setTipoCliente(cliente.getTipoCliente());
			return clienteRepo.save(update);
		}
		return null; // TODO implementare eccezione relativa.
	}

	public Page<Cliente> findAllByProvinciaSigla(String siglaProvincia, Pageable pageable) {
		try {
			Page<Cliente> clientiProv = clienteRepo.findAllBySedeLegaleComuneProvinciaSigla(siglaProvincia, pageable);
			if (clientiProv.hasContent()) {
				return clientiProv;
			}
			log.error("Nessun cliente trovato in questa provincia!");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}

	public Page<Cliente> findByOrderByNomeContattoAsc(Pageable pageable) {
		try {
			Page<Cliente> clientiNomeAsc = clienteRepo.findByOrderByNomeContattoAsc(pageable);
			if (clientiNomeAsc.hasContent()) {
				return clientiNomeAsc;
			}
			log.error("Nessun cliente trovato!");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}

	public Page<Cliente> findByOrderByFatturatoAnnualeDesc(Pageable pageable) {
		try {
			Page<Cliente> clienti = clienteRepo.findByOrderByFatturatoAnnualeDesc(pageable);
			if (clienti.hasContent()) {
				return clienti;
			}
			log.error("Nessun cliente trovato!");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}

	public Page<Cliente> findAll(Pageable pageable) {
		return clienteRepo.findAll(pageable);
	}

	public Optional<Cliente> findById(Long id) {
		return clienteRepo.findById(id);
	}

	public List<Cliente> findAll() {
		return clienteRepo.findAll();
	}

}
