package tn.esprit.spring;


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

import tn.esprit.spring.entities.Entreprise;

import org.apache.log4j.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.MOCK, classes={ TimesheetApplication.class })
@TestMethodOrder(OrderAnnotation.class)
public class EntrepriseTest {
	 ObjectWriter objectWriter;
	 @Autowired
	 private IEntrepriseService iEntrepriseService;
	 
	 @Before
	 public void setUp() {
	 objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
	 }

	 private static final Logger log = Logger.getLogger(EntrepriseTest.class);
	 
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
}
