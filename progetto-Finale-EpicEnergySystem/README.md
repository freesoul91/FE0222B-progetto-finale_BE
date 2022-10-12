# BE-progetto-Finale-EpicEnergySystem

Tecnologie utilizzate:

-SpringBoot Web starter

-SpringJPA (Hibernate)

-PostgreSQL Driver

-JWT Auth

-Spring Advanced Security Configuration

-Thymeleaf

-BootStrap

-OpenCSV

-Swagger Open Api Docs

Descrizione del progetto:

Con questo progetto ho tentato di realizzare il backend di un sistema CRM per un'azienda fornitrice di energia, denominata "EPIC ENERGY SERVICES", che vuole gestire i contatti 
con i propri clienti business.

Il sistema, basato su Web Service REST Spring Boot e database PostgreSQL, deve permettere di gestire un elenco dei clienti, che sono caratterizzati dai seguenti dati:
      -ragioneSociale
      
      -partitaIva
      
      -email
      
      -dataInserimento
      
      -dataUltimoContatto
      
      -fatturatoAnnuale
      
      -pec
      
      -telefono
      
      -emailContatto
      
      -nomeContatto
      
      -cognomeContatto
      
      -telefonoContatto

Ogni cliente può avere fino a due indirizzi, uno per la sede legale ed uno per la sede operativa.

Un indirizzo è composto da 
      -via
      
      -civico
      
      -località
      
      -cap
      
      -comune 

I comuni sono gestiti attraverso un'anagrafica centralizza e sono caratterizzati da un nome e da un riferimento ad una provincia, anch'essa gestita 
in anagrafica centralizzata e caratterizzata da un nome ed una sigla. 


I clienti possono essere di vario tipo:
      PA
      SAS
      SPA
      SRL

Associato ad ogni cliente c'è un insieme di fatture. Le fatture sono caratterizzate dai seguenti dati:
      anno : Integer
      data : Date
      importo : BigDecimal
      numero : Integer

Ogni fattura ha uno stato. Gli stati fattura possono essere dinamici, in quanto in base all'evoluzione del business possono essere inseriti nel sistema nuovi stati.

Inoltre è implementata la possibilità di autenticarsi e registrarsi sia come 'USER' che come 'ADMIN' in quanto progettualmente è possibile utilizzare tutti i servizi esposti 
(di tipo Create Retrieve Update Delete - CRUD ) SOLO dopo essersi autenticati come 'admin', 'admin'.

Sarà possibile utilizzare SwaggerOpenApi per testare i servizi ed accedere alla documentazione tramite l'endpoint : http://localhost:8080/swagger-ui.html

Qui sarà richiesta l'autenticazione per utilizzare la maggior parte dei servizi esposti come: http://localhost:8080/api/

mentre sarà sempre possibile accedere ai servizi di signup e login tramite endpoint: http://localhost:8080/auth/



