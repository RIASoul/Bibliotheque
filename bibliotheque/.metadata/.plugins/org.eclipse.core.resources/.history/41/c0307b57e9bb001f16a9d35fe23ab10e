package model;

import java.util.List;

import exception.CSVOperationException;
import exception.LivreNotFoundException;

public interface LivreModelInterface {

	public void AjouterLivre(Livre livre);
	public void SupprimerLivre(Livre livre) throws LivreNotFoundException;
	public void ModifierLivre(int idLivre, String nouvTitre) throws LivreNotFoundException;
	public void AfficherLivre();
	public List<Livre> RechercherLivre(String motCle);
	public List<Livre> FiltrerParGenre(String genre);
	public List<Livre> FiltrerParPrix(double prixMin, double prixMax);
	public void chargerLivresDepuisCSV() throws CSVOperationException;
	public void sauvegarderLivresDansCSV() throws CSVOperationException;
	
}
