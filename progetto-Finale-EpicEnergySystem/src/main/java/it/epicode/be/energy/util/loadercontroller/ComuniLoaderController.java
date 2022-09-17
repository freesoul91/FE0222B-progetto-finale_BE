package it.epicode.be.energy.util.loadercontroller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.epicode.be.energy.util.serviceloader.ComuniLoaderCsv;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller per popolare il Db di Province e Comuni accessibile ed
 * utilizzabile dall'"admin" tramite Postman/Swagger-UI
 * 
 * @author danie
 *
 */
@Slf4j
@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class ComuniLoaderController {

	@Autowired
	ComuniLoaderCsv comune;

	@PostMapping(path = "/popolacomuni")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Popola il DB con Province e Comuni (NECESSARIA AUTENTICAZIONE ED AUTORIZZAZIONE 'ADMIN'!)")
	public void popola() throws FileNotFoundException, IOException {
		// log.info(comune.popola());
		log.info("Start 'popolaComuni()'");
		log.info(comune.popolaComuni());
	}

}
