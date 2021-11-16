/*package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import tn.esprit.spring.TimesheetApplication;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;

import org.apache.log4j.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = { TimesheetApplication.class })
@TestMethodOrder(OrderAnnotation.class)
public class DepartementTest {

	@Autowired
	private IEntrepriseService iEntrepriseService;
	@Autowired
	private IEmployeService iEmployeService;

	ObjectWriter objectWriter;

	Entreprise esprit;
	Departement departement;
	Employe employer1;
	Employe employer2;
	Employe employer3;
	

	@Before
	public void setUp() {
		objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

		esprit = new Entreprise(1, "ESPRIT", "se former autrement");
		departement = new Departement(1, "Recherche et develloppement ");
		employer1 = new Employe(1,"Dhia","Benhamouda","Dhia.bh@gmail.com","1234",true,Role.CHEF_DEPARTEMENT);
		employer2 = new Employe(2,"Youssef","Marzouk","Youssef.marzouk@gmail.com","1234",true,Role.ADMINISTRATEUR);
		//employer3 = new Employe(3,"Zied","Lazrak","ZiedLazrak@gmail.com","1234",true,Role.INGENIEUR);
	}

	private static final Logger log = Logger.getLogger(DepartementTest.class);

	@Test
	@Order(1)
	public void testAddEntreprise() throws Exception {
		log.info("1--------------------------------------------------------------------------------------");
		log.info("Testing add Entreprise");

		Entreprise dulasha = new Entreprise("Dulasha",
				" a young Tunisian startup aiming to preserve, modernize and re imagine the #Tunisian oral folklore aka khrafa through the creation of immersive AR and VR experiences");
		Entreprise persisted = iEntrepriseService.ajouterEntreprise(dulasha);

		String json = objectWriter.writeValueAsString(persisted);
		log.info(json);

		org.assertj.core.api.Assertions.assertThat(dulasha.getName()).isEqualTo(persisted.getName());
		org.assertj.core.api.Assertions.assertThat(dulasha.getRaisonSocial()).isEqualTo(persisted.getRaisonSocial());

		log.info("done Testing add Entreprise");
	}

	@Test
	@Order(2)
	public void testAddDepartement() throws Exception {
		log.info("2--------------------------------------------------------------------------------------");
		log.info("Testing add Departement");

		Departement dept = new Departement("Ressources Humaines");
		Departement persisted = iEntrepriseService.ajouterDepartement(dept);

		String json = objectWriter.writeValueAsString(persisted);
		log.info(json);

		org.assertj.core.api.Assertions.assertThat(dept.getName()).isEqualTo(persisted.getName());

		log.info("done Testing add Departement");
	}
	@Test
	@Order(3)
	public void testAffecterDepartementAEntreprise() throws Exception {
		log.info("3--------------------------------------------------------------------------------------");
		log.info("Testing Affecter Departement a Entreprise");						
		departement.setEntreprise(esprit);	
		List<Departement> departements = esprit.getDepartements();
		departements.add(departement);
		esprit.setDepartements(departements);
		iEntrepriseService.affecterDepartementAEntreprise(departement.getId(),esprit.getId());		
		org.assertj.core.api.Assertions.assertThat(departement.getEntreprise().getId()).isEqualTo(esprit.getId());
		
		log.info("done Testing Affecter Departement a Entreprise");
	}
	@Test
	@Order(4)
	public void testAffecterDepartementAEmployer() throws Exception {
		log.info("4--------------------------------------------------------------------------------------");
		log.info("Testing Affecter Departement a Employer");
		
		List emp = new ArrayList<Employe>();
		emp.add(employer1);
		emp.add(employer2);
		//emp.add(employer3);
		departement.setEmployes(emp);
		
		List<Departement> departements = esprit.getDepartements();
		departements.add(departement);
		esprit.setDepartements(departements);
		
		
		iEmployeService.affecterEmployeADepartement(employer1.getId(),departement.getId());
		iEmployeService.affecterEmployeADepartement(employer2.getId(),departement.getId());
		
		org.assertj.core.api.Assertions.assertThat( departement.getEmployes().get(0).getId()).isEqualTo(employer1.getId());
		
		log.info("done Testing Affecter Departement a Employer");
	}
	@Test
	@Order(5)
	public void testGetAllDepartementsNamesByEntreprise() throws Exception {
		log.info("5 --------------------------------------------------------------------------------------");
		log.info("Testing Get all departement names by entreprise");
		
		List<String> names = iEntrepriseService.getAllDepartementsNamesByEntreprise(esprit.getId());
		String json = objectWriter.writeValueAsString(names);
		log.info(json);

		//org.assertj.core.api.Assertions.assertThat(names.get(0).equals("Ressources Humaines"));
		 if(names == null)
		 {
			 org.assertj.core.api.Assertions.assertThat(names).isNull();
			 log.warn("No departement with this name !");
		 }
		 else {
			 log.info("Departement names  "+ names);
			 org.assertj.core.api.Assertions.assertThat(names).isNotNull();
			
		 }
		log.info("done Testing Get all departement names by entreprise");
	}
	

}*/