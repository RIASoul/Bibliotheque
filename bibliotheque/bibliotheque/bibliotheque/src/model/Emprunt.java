package model;
import java.time.LocalDate;

public class Emprunt {

	private int id;
    private int utilisateurId;
    private int livreId;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    
    
	public Emprunt(int id, int utilisateurId, int livreId, LocalDate dateEmprunt, LocalDate dateRetourPrevue) {
		super();
		this.id = id;
		this.utilisateurId = utilisateurId;
		this.livreId = livreId;
		this.dateEmprunt = dateEmprunt;
		this.dateRetourPrevue = dateRetourPrevue;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUtilisateurId() {
		return utilisateurId;
	}


	public void setUtilisateurId(int utilisateurId) {
		this.utilisateurId = utilisateurId;
	}


	public int getLivreId() {
		return livreId;
	}


	public void setLivreId(int livreId) {
		this.livreId = livreId;
	}


	public LocalDate getDateEmprunt() {
		return dateEmprunt;
	}


	public void setDateEmprunt(LocalDate dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}


	public LocalDate getDateRetourPrevue() {
		return dateRetourPrevue;
	}


	public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
		this.dateRetourPrevue = dateRetourPrevue;
	}
	
	
	
	
	
}
