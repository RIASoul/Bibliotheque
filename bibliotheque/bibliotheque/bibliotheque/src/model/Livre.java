package model;

public class Livre {

	private int id;
    private String titre;
    private String auteur;
    private int anneePublication;
    private String genre;
    private boolean disponible;
    
    
	public Livre(int id, String titre, String auteur, int anneePublication, String genre, boolean disponible) {
		super();
		this.id = id;
		this.titre = titre;
		this.auteur = auteur;
		this.anneePublication = anneePublication;
		this.genre = genre;
		this.disponible = disponible;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public String getAuteur() {
		return auteur;
	}


	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}


	public int getAnneePublication() {
		return anneePublication;
	}


	public void setAnneePublication(int anneePublication) {
		this.anneePublication = anneePublication;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public boolean isDisponible() {
		return disponible;
	}


	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}


	@Override
	public String toString() {
		return "Livre [id=" + id + ", titre=" + titre + ", auteur=" + auteur + ", anneePublication=" + anneePublication
				+ ", genre=" + genre + ", disponible=" + disponible + "]";
	}
    
    
    
	
	
}
