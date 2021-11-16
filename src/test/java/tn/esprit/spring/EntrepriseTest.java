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

import com.fasterxml.jackson.databind.ObjectWriter;

import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEntrepriseService;

import org.apache.log4j.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.MOCK, classes={ TimesheetApplication.class })
@TestMethodOrder(OrderAnnotation.class)
public class EntrepriseTest {

	 @Autowired
	 private IEntrepriseService iEntrepriseService;
	 

	 private static final Logger log = Logger.getLogger(EntrepriseTest.class);
	 
	 @Test
	 @Order(1)
	 public void testAddEntreprise() throws Exception {
	 log.info("-------------------------------------------------");
	 log.info("Testing add Entreprise");

	 Entreprise vermeg = new Entreprise("Vermeg","VERMEG est un groupe de logiciels international opérant sur plusieurs lignes de services B2B");
	 Entreprise persisted = iEntrepriseService.ajouterEntreprise(vermeg);

	 log.info(persisted);

	 org.assertj.core.api.Assertions.assertThat(vermeg.getName()).isEqualTo(persisted.getName());
	 org.assertj.core.api.Assertions.assertThat(vermeg.getRaisonSocial()).isEqualTo(persisted.getRaisonSocial());


	 log.info("Done Testing Add Entreprise");
	 log.info("-------------------------------------------------");
	 }
	 
	 
	 @Test
	 @Order(2)
	 public void testAffichageEntreprise() throws Exception {
	 log.info("-------------------------------------------------");
	 log.info("Testing Affichage Entreprise");

	 
	 Entreprise entreprise = iEntrepriseService.getEntrepriseById(1);

	 log.info("Entreprise affiché "+ entreprise);
	 
	 org.assertj.core.api.Assertions.assertThat(entreprise.getId()).isEqualTo(1);
	 org.assertj.core.api.Assertions.assertThat(entreprise.getName()).isEqualTo("Vermeg");


	 log.info("Done Testing Affichage Entreprise");
	 log.info("-------------------------------------------------");
	 }
	 
	 @Test
	 @Order(3)
	 public void testDeleteEntreprise() throws Exception {
	 log.info("-------------------------------------------------");
	 log.info("Testing Delete Entreprise");

	 
	 Entreprise entreprise = iEntrepriseService.getLastEntreprise();

	 
	 
	 if(entreprise == null)
	 {
		 org.assertj.core.api.Assertions.assertThat(entreprise).isNull();
		 log.warn("No Entreprise to delete !");
	 }
	 else {
		 log.info("Entreprise to delete "+ entreprise);
		 org.assertj.core.api.Assertions.assertThat(entreprise).isNotNull();
		 iEntrepriseService.deleteEntrepriseById(entreprise.getId());
	 }
	 
	 
	 


	 log.info("Done Testing Delete Entreprise");
	 log.info("-------------------------------------------------");
	 }
	
	
}
