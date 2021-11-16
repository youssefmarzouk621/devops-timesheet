package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Contrat;


public interface IContratService {
	
	
	public List<Contrat> getAllContrats();
	public Contrat ajouterContrat(Contrat contrat); 
	public boolean affecterContratAEmploye(int contratId, int employeId); 
	public boolean deleteContratById(int contratId); 
	public void deleteAllContratJPQL(); 



	
	
	

	
}
