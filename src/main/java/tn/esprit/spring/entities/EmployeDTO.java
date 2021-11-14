package tn.esprit.spring.entities;



public class EmployeDTO {

	private int idDTO;
	
	private String prenomDTO;
	
	private String nomDTO;
	
	private String emailDTO;

	private String passwordDTO;
	
	private boolean actifDTO;
	
	private String roleDTO;

	public EmployeDTO(int idDTO, String prenomDTO, String nomDTO, String emailDTO, String passwordDTO, boolean actifDTO,
			String roleDTO) {
		this.idDTO = idDTO;
		this.prenomDTO = prenomDTO;
		this.nomDTO = nomDTO;
		this.emailDTO = emailDTO;
		this.passwordDTO = passwordDTO;
		this.actifDTO = actifDTO;
		this.roleDTO = roleDTO;
	}

	public int getIdDTO() {
		return idDTO;
	}

	public void setIdDTO(int idDTO) {
		this.idDTO = idDTO;
	}

	public String getPrenomDTO() {
		return prenomDTO;
	}

	public void setPrenomDTO(String prenomDTO) {
		this.prenomDTO = prenomDTO;
	}

	public String getNomDTO() {
		return nomDTO;
	}

	public void setNomDTO(String nomDTO) {
		this.nomDTO = nomDTO;
	}

	public String getEmailDTO() {
		return emailDTO;
	}

	public void setEmailDTO(String emailDTO) {
		this.emailDTO = emailDTO;
	}

	public String getPasswordDTO() {
		return passwordDTO;
	}

	public void setPasswordDTO(String passwordDTO) {
		this.passwordDTO = passwordDTO;
	}

	public boolean isActifDTO() {
		return actifDTO;
	}

	public void setActifDTO(boolean actifDTO) {
		this.actifDTO = actifDTO;
	}

	public String getRoleDTO() {
		return roleDTO;
	}

	public void setRoleDTO(String roleDTO) {
		this.roleDTO = roleDTO;
	}

	@Override
	public String toString() {
		return "EmployeDTO [idDTO=" + idDTO + ", prenomDTO=" + prenomDTO + ", nomDTO=" + nomDTO + ", emailDTO="
				+ emailDTO + ", passwordDTO=" + passwordDTO + ", actifDTO=" + actifDTO + ", roleDTO=" + roleDTO + "]";
	}
	
	

	
}
