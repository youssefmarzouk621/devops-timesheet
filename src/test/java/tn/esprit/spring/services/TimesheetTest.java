package tn.esprit.spring.services;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;



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
	@Before
	public void setUp() {
		 this.mockMvc = webAppContextSetup(webApplicationContext).build();
		 objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
	}
	
	private static final Logger log = Logger.getLogger(TimesheetServiceImpl.class);
	
	
	@Test
	@Order(1)
	public void testAddEntreprise() throws Exception {
		Entreprise u = new Entreprise("ESPRIT","se former autrement");
		int id = iEntrepriseService.ajouterEntreprise(u);
		Assertions.assertEquals(u.getName(),"ESPRIT");
	}
	
	@Test
	@Order(2)
	public void testGetEntreprise() throws Exception {
		Entreprise entreprise = iEntrepriseService.getEntrepriseById(6);
		org.assertj.core.api.Assertions.assertThat(entreprise.getName()).isEqualTo("ESPRIT");
	}
	
	@Test
	@Order(3)
	public void testAddMission() throws Exception {
		Mission mission = new Mission("Mission", "Description");
		Mission persisted = timesheetServiceImpl.ajouterMission(mission);
		org.assertj.core.api.Assertions.assertThat(persisted.getName()).isEqualTo(mission.getName());
	}
	
	
	

	
	
}
