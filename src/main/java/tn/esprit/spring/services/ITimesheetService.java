package tn.esprit.spring.services;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;



public interface ITimesheetService {
	
	public Mission ajouterMission(Mission mission);
	public boolean affecterMissionADepartement(int missionId, int depId);
	public Timesheet ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin);
	public boolean validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId);
	public List<Mission> findAllMissionByEmployeJPQL(int employeId);
	public List<Mission> findAllMissions();
	
	public List<Employe> getAllEmployeByMission(int missionId);
}
