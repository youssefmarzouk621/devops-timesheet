package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	public int ajouterEntreprise(Entreprise entreprise) {
		entrepriseRepoistory.save(entreprise);
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		deptRepoistory.save(dep);
		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
				Optional<Entreprise> entrepriseManagedEntity1 = entrepriseRepoistory.findById(entrepriseId);
				Entreprise entrepriseManagedEntity = new Entreprise();;
				if (entrepriseManagedEntity1.isPresent()) {
					entrepriseManagedEntity = entrepriseManagedEntity1.get();
				}
				Departement depManagedEntity = deptRepoistory.findById(depId).get();
				
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
		Optional<Departement> DepartementeManagedEntity1 = deptRepoistory.findById(depId);
		Departement DepartementeManagedEntity = new Departement();
		if (DepartementeManagedEntity1.isPresent()) {
			DepartementeManagedEntity = DepartementeManagedEntity1.get();
		}
		deptRepoistory.delete(DepartementeManagedEntity);	
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		Optional<Entreprise> entrepriseManagedEntity1 = entrepriseRepoistory.findById(entrepriseId);
		Entreprise entrepriseManagedEntity = new Entreprise();
		if (entrepriseManagedEntity1.isPresent()) {
			entrepriseManagedEntity = entrepriseManagedEntity1.get();
		}
		return entrepriseManagedEntity;	
	}

}
