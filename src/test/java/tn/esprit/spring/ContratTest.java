package tn.esprit.spring;

/*import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import tn.esprit.spring.entities.Employe;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.services.IContratService;

import org.apache.log4j.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = { TimesheetApplication.class })
@TestMethodOrder(Alphanumeric.class)
public class ContratTest {

	ObjectWriter objectWriter;

	@Autowired
	IContratService contratService;
	int idEmp = 0;
	int idCont = 0;

	@Before
	public void setUp() {
		objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
	}

	private static final Logger log = Logger.getLogger(ContratTest.class);

	@Test
	public void AtestAddContrat() throws Exception {
		log.info("5--------------------------------------------------------------");
		log.info("Testing delete Contrat By Id");

		List<Contrat> contrats = new ArrayList<Contrat>();

		contrats = contratService.getAllContrats();
		if (contrats.isEmpty()) {
			org.assertj.core.api.Assertions.assertThat(contrats).isEmpty();
			log.warn("list is empty !");

		} else {
			Contrat contrat = contrats.get(contrats.size() - 1);
			String contratString = objectWriter.writeValueAsString(contrat);
			log.info("Contrat a supprimer :" + contratString);
			boolean isDeleted = contratService.deleteContratById(contrat.getReference());

			if (isDeleted) {
				log.info("done deleting last contrat");
				org.assertj.core.api.Assertions.assertThat(isDeleted).isTrue();
			} else {
				log.warn("something went wrong");
				org.assertj.core.api.Assertions.assertThat(isDeleted).isFalse();
			}

		}
	}

	
	@Test
	public void BtestAddNULLContrat() throws Exception {
		log.info("6--------------------------------------------------------------");
		log.info("Testing delete All Contrat");
		List<Contrat> contrats = new ArrayList<Contrat>();
		contrats = contratService.getAllContrats();
		if (contrats.isEmpty()) {
			org.assertj.core.api.Assertions.assertThat(contrats).isEmpty();
			log.warn("list is empty !");
			
		} else {
			contratService.deleteAllContratJPQL();
			log.info("list deleted successfully");
		}
	}
	

	@Test
	public void CtestGetAllContrats() throws Exception {
		log.info("4--------------------------------------------------------------");
		log.info("Testing affecter Contrat A Employe");

		Date currentDate = new Date(System.currentTimeMillis());
		Contrat contrat = new Contrat(currentDate, "CDD", 4000);
		Contrat persisted = contratService.ajouterContrat(contrat);
		Employe moetez = new Employe(2, "Moetez", "Hechmi", "moetez.hechmi", "1234", true, Role.INGENIEUR);
		
		
		log.info("moetez :"+moetez);
		log.info("persisted :"+persisted);
		
		
		boolean isAssigned = contratService.affecterContratAEmploye(persisted.getReference(), moetez.getId());

		if (isAssigned) {
			org.assertj.core.api.Assertions.assertThat(isAssigned).isTrue();
		} else {
			log.warn("something went wrong");
			org.assertj.core.api.Assertions.assertThat(isAssigned).isFalse();
		}
	}

	@Test
	public void DaffecterContratAEmploye() throws Exception {
		log.info("2--------------------------------------------------------------");
		log.info("Testing add null Contrat");

		Contrat contrat = null;
		Contrat persisted = contratService.ajouterContrat(contrat);

		if (persisted == null) {
			org.assertj.core.api.Assertions.assertThat(persisted).isNull();
			log.warn("contrat is null !");
		} else {
			String json = objectWriter.writeValueAsString(persisted);
			log.info(json);

			org.assertj.core.api.Assertions.assertThat(contrat.getTypeContrat()).isEqualTo(persisted.getTypeContrat());
			org.assertj.core.api.Assertions.assertThat(contrat.getSalaire()).isEqualTo(persisted.getSalaire());

		}
		log.info("done Testing add Contrat");
	}

	@Test
	public void EdeleteContratById() throws Exception {
		log.info("1--------------------------------------------------------------");

		log.info("Testing add Contrat");

		Date currentDate = new Date(System.currentTimeMillis());
		Contrat contrat = new Contrat(currentDate, "CDI", 1000);
		Contrat persisted = contratService.ajouterContrat(contrat);

		if (persisted == null) {
			org.assertj.core.api.Assertions.assertThat(persisted).isNull();
			log.warn("contrat is null !");
		} else {
			String json = objectWriter.writeValueAsString(persisted);
			log.info(json);

			org.assertj.core.api.Assertions.assertThat(contrat.getTypeContrat()).isEqualTo(persisted.getTypeContrat());
			org.assertj.core.api.Assertions.assertThat(contrat.getSalaire()).isEqualTo(persisted.getSalaire());

		}
		log.info("done Testing add Contrat");

	}

	@Test
	public void FtestdeleteAllContratJPQL() throws Exception {
		log.info("3--------------------------------------------------------------");
		log.info("Testing Get All Contrats");

		List<Contrat> contrats = new ArrayList<Contrat>();

		contrats = contratService.getAllContrats();

		if (contrats.isEmpty()) {
			org.assertj.core.api.Assertions.assertThat(contrats).isEmpty();
			log.warn("list is empty !");
		} else {

			String json = objectWriter.writeValueAsString(contrats);
			log.info(json);

			org.assertj.core.api.Assertions.assertThat(contrats).isNotEmpty();

		}
		log.info("done Testing Get All Contrats");

	}

}*/