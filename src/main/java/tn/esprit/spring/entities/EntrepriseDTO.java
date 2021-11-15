
package tn.esprit.spring.entities;

import java.util.ArrayList;
import java.util.List;



public class EntrepriseDTO{

	private int idDTO;
	
	private String nameDTO;
	
	
	private String raisonSocialDTO;
	

	private List<Departement> departements = new ArrayList<>();


	public int getIdDTO() {
		return idDTO;
	}


	public void setIdDTO(int idDTO) {
		this.idDTO = idDTO;
	}


	public String getNameDTO() {
		return nameDTO;
	}


	public void setNameDTO(String nameDTO) {
		this.nameDTO = nameDTO;
	}


	public String getRaisonSocialDTO() {
		return raisonSocialDTO;
	}


	public void setRaisonSocialDTO(String raisonSocialDTO) {
		this.raisonSocialDTO = raisonSocialDTO;
	}


	public List<Departement> getDepartements() {
		return departements;
	}


	public void setDepartements(List<Departement> departements) {
		this.departements = departements;
	}
	

}
