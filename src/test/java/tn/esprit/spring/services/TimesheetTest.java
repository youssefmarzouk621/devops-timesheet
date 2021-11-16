package tn.esprit.spring.services;


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
@SpringBootTest(webEnvironment=WebEnvironment.MOCK, classes={ TimesheetApplication.class })
@TestMethodOrder(OrderAnnotation.class)
public class TimesheetTest {
	
	@Autowired
	private TimesheetServiceImpl timesheetServiceImpl;
	

	@Autowired
	private IEntrepriseService iEntrepriseService;


	
	ObjectWriter objectWriter;

	Entreprise esprit;
	Departement departement;
	
	@Before
	public void setUp() {
		 objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		 
		esprit = new Entreprise(1,"ESPRIT","se former autrement");
		departement = new Departement(1,"Recherche et develloppement ");
	}
	
	private static final Logger log = Logger.getLogger(TimesheetTest.class);
	
	
	@Test
	@Order(1)
	public void testAddEntreprise() throws Exception {
		log.info("1--------------------------------------------------------------------------------------");
		log.info("Testing add Entreprise");
		
		Entreprise dulasha = new Entreprise("Dulasha"," a young Tunisian startup aiming to preserve, modernize and re imagine the #Tunisian oral folklore aka khrafa through the creation of immersive AR and VR experiences");
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
	public void testAddMission() throws Exception {
		log.info("4--------------------------------------------------------------------------------------");
		log.info("Testing add Mission");
		Mission mission = new Mission("Mission", "Description");
		Mission persisted = timesheetServiceImpl.ajouterMission(mission);
		org.assertj.core.api.Assertions.assertThat(persisted.getId()).isEqualTo(mission.getId());
		log.info("done Testing add Mission");
		
		
		log.info("--------------------------------------------------------------------------------------");
		log.info("Testing affecter mission a departement");
		boolean isPersisted = timesheetServiceImpl.affecterMissionADepartement(persisted.getId(), departement.getId());
		
		org.assertj.core.api.Assertions.assertThat(isPersisted).isTrue();
		log.info("done Testing affecter mission a departement");
	}
	
	@Test
	@Order(5)
	public void testAjouterTimesheet() throws Exception {
		log.info("5--------------------------------------------------------------------------------------");
		log.info("Testing Ajouter Timesheet");
		
		Mission mission = new Mission(1,"Mission", "implement React Dev Extreme forms, integrate with Rest API.");
		
		Employe chef = new Employe(1, "Youssef", "Marzouk", "youssef.marzouk@esprit.tn", "1234", true, Role.CHEF_DEPARTEMENT);
		Employe ingenieur = new Employe(2, "Dhia", "Ben Hamouda", "dhia.benhamouda@esprit.tn", "1234", true, Role.INGENIEUR);
		
		Date dateDebut = new Date(121, 11, 14);
		Date dateFin = new Date(121, 11, 15);
		
		Timesheet persisted = timesheetServiceImpl.ajouterTimesheet(mission.getId(), ingenieur.getId(), dateDebut, dateFin);
		
		String json = objectWriter.writeValueAsString(persisted);
		log.info(json);
		
		org.assertj.core.api.Assertions.assertThat(persisted.getTimesheetPK().getIdMission()).isEqualTo(mission.getId());
		org.assertj.core.api.Assertions.assertThat(persisted.getTimesheetPK().getIdEmploye()).isEqualTo(ingenieur.getId());
		

		log.info("done Testing add Timesheet");
	
		
		
		log.info("--------------------------------------------------------------------------------------");
		log.info("Testing valider Timesheet");
		

		
		boolean valid = timesheetServiceImpl.validerTimesheet(mission.getId(), ingenieur.getId(), dateDebut, dateFin, chef.getId());
		
		
		log.info("status validation duu timesheet :"+valid);
		
		org.assertj.core.api.Assertions.assertThat(valid).isTrue();

		

		log.info("done Testing add Timesheet");
		
	}
	
	
	
	
	
	
	
	
	

	
	
}
