package tn.esprit.spring;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.MissionDTO;
import tn.esprit.spring.services.ITimesheetService;
import tn.esprit.spring.services.TimesheetServiceImpl;

import org.apache.log4j.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.MOCK, classes={ TimesheetApplication.class })
public class TimesheetTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private ITimesheetService iTimesheetService;

	@MockBean 
	private ITimesheetService timesheetServiceMock;
	
	ObjectWriter objectWriter;
	@Before
	public void setUp() {
		 this.mockMvc = webAppContextSetup(webApplicationContext).build();
		 objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
	}
	
	private static final Logger log = Logger.getLogger(TimesheetServiceImpl.class);
	
	@Test
	public void TestAjouterMission() throws Exception {
		log.info("Performing TestAjouterMission");
		
		//when(timesheetServiceMock.ajouterMission(any(Mission.class))).thenReturn(10);

	    
	    Mission mission = new Mission(1,"Conception et Développement d'une application mobile",
	    		"• Le test de l’application et le traitement des dysfonctionnements éventuels \n"
	    		+ "• La mise en place des bonnes pratiques d’utilisation et de développement");
	    
	    String json = objectWriter.writeValueAsString(mission);
		log.info("body :"+json);
		
		int returnedValue = iTimesheetService.ajouterMission(mission);
		log.info("returneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeed Value :"+returnedValue);
		
		/*mockMvc.perform(post("/ajouterMission")
			   .contentType(MediaType.APPLICATION_JSON)
			   .content(json)						
			   .accept(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk());*/
		
	
		log.info("Done Performing TestAjouterMission");
	}
	
	
}
