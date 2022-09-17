package it.epicode.be.energy.util.serviceloader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.opencsv.CSVReader;
import it.epicode.be.energy.model.Comune;
import it.epicode.be.energy.model.Provincia;
import it.epicode.be.energy.service.ComuneService;
import it.epicode.be.energy.service.ProvinciaService;
import lombok.extern.slf4j.Slf4j;

/**
 * Classe che permette di leggere e salvare le entries provenienti da file .csv
 * 
 * @author danie
 *
 */
@Slf4j
@Service
public class ComuniLoaderCsv {

	@Autowired
	ComuneService comuneService;

	@Autowired
	ProvinciaService provinciaService;

	/**
	 * Vecchio metodo che utilizzavo per salvare i comuni e le province da singolo
	 * file ISTAT ma che inseriva tanti record di provincia quanti sono i comuni sul
	 * file csv. Metodo "popola()" @Deprecated
	 * 
	 * @return
	 * @throws IOException
	 */

	@Deprecated
	public String popola() throws IOException {
		try (CSVReader csvReader = new CSVReader(new FileReader("Elenco-comuni-italiani-virgole.csv"));) {
			String[] values = null;
			csvReader.readNext(); // primo readNext per saltare la riga (intestazione)
			while ((values = csvReader.readNext()) != null) {

				String nomeComune = values[5];
				String regione = values[10];
				String nome = values[11];
				String sigla = values[14];

				Provincia p = new Provincia();
				p.setRegione(regione);
				p.setNome(nome);
				p.setSigla(sigla);
				provinciaService.save(p);
				comuneService.save(new Comune(nomeComune, p));

			}

		}
		return "DB popolato correttamente!";

	}

	/**
	 * Metodo che carica la lista delle province italiane da csv e le aggiunge ad
	 * una List<Provincia> province
	 * 
	 * @return List<Provincia> province
	 * @throws IOException
	 */

	public List<Provincia> popolaProvinceList() throws IOException {
		List<Provincia> province = new ArrayList<>();
		try (CSVReader csvReader = new CSVReader(new FileReader("province-italiane-virgole.csv"));) {
			String[] values = null;
			csvReader.readNext();
			while ((values = csvReader.readNext()) != null) {

				String regione = values[2];
				String nome = values[1];
				String sigla = values[0];

				Provincia p = new Provincia();
				p.setRegione(regione);
				p.setNome(nome);
				p.setSigla(sigla);
				province.add(p);

			}

		}
		return province;

	}

	/**
	 * Metodo che salva le province su Db e contestualmente controlla se al comune
	 * da salvare sul Db corrisponde la provincia e la setta al comune da salvare su
	 * Db (Per evitare inserimenti multipli delle province)
	 * 
	 * @return "DB popolato correttamente con i comuni e le province!";
	 * @throws IOException
	 */
	public String popolaComuni() throws IOException {

		List<Provincia> province = popolaProvinceList();
		for (Provincia p : province) {
			provinciaService.save(p);
		}
		log.info("Province salvate correttamente sul DB!");
		try (CSVReader csvReader = new CSVReader(new FileReader("comuni-italiani-virgole.csv"));) {
			String[] values = null;
			csvReader.readNext();
			while ((values = csvReader.readNext()) != null) {
				Comune c = new Comune();
				String nomeComune = values[2];
				String provinciaDelComune = values[3];

				for (Provincia p : province) {

					if (p.getNome().equals(provinciaDelComune) && !provinciaDelComune.isBlank()) {
						c.setProvincia(p);
						c.setNome(nomeComune);
						comuneService.save(c);
					}

				}

			}

		}
		return "DB popolato correttamente con i comuni e le province!";

	}

}
