package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ContratServiceImpl implements IContratService {
	Logger logger = LoggerFactory.getLogger(ContratServiceImpl.class);

	@Autowired
	ContratRepository contratRepository;
	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;

	public List<Contrat> getAllContrats() {
		List<Contrat> contrat = new ArrayList<>();

		try {
			logger.info("getting Contrats");
			logger.debug("Je vais afficher les contrats.");
			contrat = (List<Contrat>) contratRepository.findAll();
			logger.debug("Je viens de finir l'opération X.");
			logger.info("Out getAllContrarts() without errors.");

		} catch (Exception e) {
			logger.error(e.toString());
		}

		return contrat;

	}

	public Contrat ajouterContrat(Contrat contrat) {
		logger.info("Adding Contract");
		if (contrat != null) {
			try {
				logger.info("add Contrat");
				logger.debug("Je vais ajouter un contrat.");
				Contrat persisted = contratRepository.save(contrat);
				logger.info("Contract Added Succefully");
				return persisted;

			} catch (Exception e) {
				logger.error(e.toString());
			}
		} else {
			logger.warn("Contract is Empty ! ");
		}
		return null;
	}

	public boolean affecterContratAEmploye(int contratId, int employeId) {

		if (employeId==0) {
			logger.warn("No Employe Assigned !");
			return false;
		} else if (contratId==0) {
			logger.warn("No Contract to Assign to the Employee !");
			return false;
		} else {

			try {

				Optional<Contrat> contratOpt = contratRepository.findById(contratId);
				Optional<Employe> employeOpt = employeRepository.findById(employeId);
				
				
				
				logger.info("Affecting Contract ");
				if (contratOpt.isPresent() && employeOpt.isPresent()) {
					Contrat contrat = contratOpt.get();
					Employe employe = employeOpt.get();
					contrat.setEmploye(employe);
					
					contratRepository.save(contrat);
					logger.info("Contract Binded Succefully ! ");
					return true;
				}else {
					return false;
				}
				
			} catch (Exception e) {
				logger.error(e.toString());
				return false;
			}
		}

	}

	public boolean deleteContratById(int contratId) {
		
		if(contratId==0) {
			logger.warn("Contract not found");
			return false;
		}
		
		logger.info("Deleting Contract");
		
		Optional<Contrat> contratOpt = contratRepository.findById(contratId);
		if(contratOpt.isPresent()) { 
			Contrat contrat = contratOpt.get();
			contratRepository.delete(contrat);
			logger.info("Contract Deleted Succefully ! ");
			return true;
		}
		
		return false;

	}

	public void deleteAllContratJPQL() {
		try {
			logger.info("delete Contrat");
			logger.debug("Je vais supprimer un contrat.");
			employeRepository.deleteAllContratJPQL();
			logger.debug("delete finish");
			logger.info("Contracts deleted Succefully ! ");
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}
	
	

    

}