package tn.esprit.spring.services;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import tn.esprit.spring.TimesheetApplication;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;

import org.apache.log4j.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.MOCK, classes={ TimesheetApplication.class })
@TestMethodOrder(OrderAnnotation.class)
public class TimesheetTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private ITimesheetService iTimesheetService;
	
	@Autowired
	private TimesheetServiceImpl timesheetServiceImpl;
	

	@Autowired
	private IEntrepriseService iEntrepriseService;



	
	ObjectWriter objectWriter;

	Entreprise esprit;
	Departement departement;
	@Before
	public void setUp() {
		 this.mockMvc = webAppContextSetup(webApplicationContext).build();
		 objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		 
		esprit = new Entreprise(1,"ESPRIT","se former autrement");
		departement = new Departement(1,"Recherche et develloppement ");
	}
	
	private static final Logger log = Logger.getLogger(TimesheetTest.class);
	
	
	@Test
	@Order(1)
	public void testAddEntreprise() throws Exception {
		log.info("Testing add Entreprise");
		
		Entreprise vermeg = new Entreprise("Vermeg","VERMEG est un groupe de logiciels international opérant sur plusieurs lignes de services B2B");
		Entreprise persisted = iEntrepriseService.ajouterEntreprise(vermeg);
		
		String json = objectWriter.writeValueAsString(persisted);
		log.info(json);
		
		org.assertj.core.api.Assertions.assertThat(vermeg.getName()).isEqualTo(persisted.getName());
		org.assertj.core.api.Assertions.assertThat(vermeg.getRaisonSocial()).isEqualTo(persisted.getRaisonSocial());
		

		log.info("done Testing add Entreprise");
	}
	
	@Test
	@Order(2)
	public void testAddDepartement() throws Exception {
		log.info("Testing add Departement");
		
		Departement dept = new Departement("Ressources Humaines ");
		Departement persisted = iEntrepriseService.ajouterDepartement(dept);
		
		String json = objectWriter.writeValueAsString(persisted);
		log.info(json);
		
		org.assertj.core.api.Assertions.assertThat(dept.getName()).isEqualTo(persisted.getName());
		
		log.info("done Testing add Departement");
	}
	
	
	@Test
	@Order(3)
	public void testAffecterDepartementAEntreprise() throws Exception {
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
		log.info("Testing add Mission");
		Mission mission = new Mission("Mission", "Description");
		Mission persisted = timesheetServiceImpl.ajouterMission(mission);
		org.assertj.core.api.Assertions.assertThat(persisted.getId()).isEqualTo(mission.getId());
		log.info("done Testing add Mission");
		
		
		
		log.info("Testing affecter mission a departement");
		boolean isPersisted = timesheetServiceImpl.affecterMissionADepartement(persisted.getId(), departement.getId());
		
		org.assertj.core.api.Assertions.assertThat(isPersisted).isTrue();
		log.info("done Testing affecter mission a departement");
	}
	
	
	
	
	
	

	
	
}
