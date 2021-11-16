package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService{

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	
	@Autowired
	DepartementRepository deptRepoistory;
	
	
	public Entreprise ajouterEntreprise(Entreprise entreprise) {
		return entrepriseRepoistory.save(entreprise);
	}

	public Departement ajouterDepartement(Departement dep) {
		return deptRepoistory.save(dep);
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
				Optional<Entreprise> entrepriseManagedEntity1 = entrepriseRepoistory.findById(entrepriseId);
				Entreprise entrepriseManagedEntity = new Entreprise();
				if (entrepriseManagedEntity1.isPresent()) {
					entrepriseManagedEntity = entrepriseManagedEntity1.get();
				}
				
				Optional<Departement> departementemanagedentity1 = deptRepoistory.findById(depId);
				
				Departement depManagedEntity = new Departement();
				if (departementemanagedentity1.isPresent()) {
					depManagedEntity = departementemanagedentity1.get();
				}
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);
		
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		Optional<Entreprise> entrepriseManagedEntity1 = entrepriseRepoistory.findById(entrepriseId);
		Entreprise entrepriseManagedEntity = new Entreprise();
		if (entrepriseManagedEntity1.isPresent()) {
			entrepriseManagedEntity = entrepriseManagedEntity1.get();
		}
		List<String> depNames = new ArrayList<>();
		for(Departement dep : entrepriseManagedEntity.getDepartements()){
			depNames.add(dep.getName());
		}
		
		return depNames;
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		Optional<Entreprise> entrepriseManagedEntity1 = entrepriseRepoistory.findById(entrepriseId);
		Entreprise entrepriseManagedEntity = new Entreprise();
		if (entrepriseManagedEntity1.isPresent()) {
			entrepriseManagedEntity = entrepriseManagedEntity1.get();
		}
		entrepriseRepoistory.delete(entrepriseManagedEntity);	
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		Optional<Departement> departementemanagedentity1 = deptRepoistory.findById(depId);
		Departement departementemanagedentity = new Departement();
		if (departementemanagedentity1.isPresent()) {
			departementemanagedentity = departementemanagedentity1.get();
		}
		deptRepoistory.delete(departementemanagedentity);	
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		Optional<Entreprise> entrepriseManagedEntity1 = entrepriseRepoistory.findById(entrepriseId);
		Entreprise entrepriseManagedEntity = new Entreprise();
		if (entrepriseManagedEntity1.isPresent()) {
			entrepriseManagedEntity = entrepriseManagedEntity1.get();
		}
		return entrepriseManagedEntity;	
	}


	public Entreprise getLastEntreprise() {
		List<Entreprise> entreprises = (List<Entreprise>) entrepriseRepoistory.findAll();
		if(entreprises.isEmpty()) {
			return null;
		}
		
		return entreprises.get(entreprises.size()-1);
	}

}
