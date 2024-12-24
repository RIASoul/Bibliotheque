package model;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import exception.EmpruntNotFoundException;
import exception.LivreNotFoundException;
import exception.UtilisateurNotFoundException;

public interface EmpruntModelInterface {
	
	public void enregistrerEmprunt(Emprunt emprunt) throws IOException;
	void afficherEmprunt();
	public List<Emprunt> consulterHistoriqueParUtilisateur(int utilisateurId) throws UtilisateurNotFoundException;
	public void prolongerEmprunt(int empruntId, LocalDate dateRetour) throws EmpruntNotFoundException, IOException;
	public void chargerEmpruntsDepuisCSV() throws IOException, UtilisateurNotFoundException, LivreNotFoundException;
	public void sauvegarderEmpruntsDansCSV() throws IOException;

}
