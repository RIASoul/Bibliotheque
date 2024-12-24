package model;
import java.time.LocalDate;

public class Retour {

	private int empruntId;
    private LocalDate dateRetourEffective;
    private double penalite;
    
    
	public Retour(int empruntId, LocalDate dateRetourEffective, double penalite) {
		super();
		this.empruntId = empruntId;
		this.dateRetourEffective = dateRetourEffective;
		this.penalite = penalite;
	}


	public int getEmpruntId() {
		return empruntId;
	}


	public void setEmpruntId(int empruntId) {
		this.empruntId = empruntId;
	}


	public LocalDate getDateRetourEffective() {
		return dateRetourEffective;
	}


	public void setDateRetourEffective(LocalDate dateRetourEffective) {
		this.dateRetourEffective = dateRetourEffective;
	}


	public double getPenalite() {
		return penalite;
	}


	public void setPenalite(double penalite) {
		this.penalite = penalite;
	}
    
    
	
    
	
}
