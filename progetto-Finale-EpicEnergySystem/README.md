# BE-progetto-Finale-EpicEnergySystem
Progetto Finale del corso Epicode BE "Epic Energy System"

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


# Di seguito un tracciato di esempio in formato JSON per l'inserimento di un nuovo cliente tramite servizio REST da Swagger-UI/Postman:

      {
        
        "ragioneSociale": "Epicode",
        "iva": "12345678910",
        "email": "epicode@epicode.it",
        "dataInserimento": "2021-04-15T14:03:05.276Z",
        "dataUltimoContatto": "2022-02-15T14:03:05.276Z",
        "fatturatoAnnuale": 12900000,
        "pec": "epicode@pec.it",
        "telefono": "06567891011",
        "tipoCliente": "SRL",
        "emailContatto": "m.rossi@epicode.it",
        "nomeContatto": "Mario",
        "cognomeContatto": "Rossi",
        "telefonoContatto": "3348318694",
        "sedeLegale": {
        
        "via": "via Verdi",
        "civico": 85,
        "localita": "Torino",
        "cap": 128,
        "comune": {
        "id": 97
        }
    },
    "sedeOperativa": {
    
    "via": "via Neri",
    "civico": 86,
    "localita": "Torino",
    "cap": 128,
    "comune": {
      "id": 97
      
        }
      }
    }
    
Il cliente sarà quindi inserito nel Db e sarà possibile associare delle fatture al cliente appena inserito seguendo il tracciato di esempio:

# Esempio tracciato JSON di fattura (alla quale associamo il cliente corrispondente all'id 1 ad esempio):

    {
      "cliente": {
       "id": 1     
    }
    },
      "anno": 2022,
      "data": "2022-03-15T14:12:24.484Z",
      "importo": 12000,
      "numeroFattura": 1234,
      "stato": "PAGATA"
    }

# View e Services REST

È inoltre possibile inserire clenti e fatture oppure consultare gli elenchi di fattura/indirizzo/cliente/comune/provincia accedendo alla Home (realizzata con Thymeleaf) previa autenticazione con username e password:


![HomePage](https://user-images.githubusercontent.com/98736232/158781469-c1745760-1ce1-4133-bbb4-8712fbf8fa25.JPG)

Home Page della parte "view" del progetto.

![AuthBase](https://user-images.githubusercontent.com/98736232/158781509-ac5d8e1b-86a9-43b3-98a8-bf620904ed45.JPG)

L'autenticazione sarà richiesta ogni volta si cerca di accedere ad una delle funzioni elencate.

![tableProv](https://user-images.githubusercontent.com/98736232/158784388-d72bbb6e-b639-4e87-bea9-4ccd5adbc0e2.JPG)

Tabella delle province presenti nel sistema accedendo ad "Elenco province" dalla Home (richiesta autenticazione).

Accedendo come "admin" (username: admin, password: admin) sarà possibile avere accesso a TUTTE le funzioni mentre accedendo come "user" (username: user, password: user) sarà possibile avere accesso solo ed unicamente alle funzioni di retrieve dei dati inseriti.

# Swagger-UI

Accedendo ai servizi REST documentati su Swagger sarà richiesto un JWT (JSON Web Token) in cui sono contenute le informazioni e soprattutto le autorizzazioni concesse ad un determinato user, per completezza si consiglia di effettuare il login con username: admin e password: admin


![auth login](https://user-images.githubusercontent.com/98736232/158784317-a5a15c24-49fb-429e-a17d-e881ba2838b9.jpg)

Schermata di risposta dell'endpoint http://localhost:8080/auth/login.

![SwaggerUI](https://user-images.githubusercontent.com/98736232/158784373-fb0db0d1-8679-47ae-9508-9f0b06e732d0.JPG)

Cliccando sul bottone "Authorize" ed inserendo il JWT dopo aver effettuato il login si avrà accesso ai servizi esposti.



Autore: Daniele Cerulli.
