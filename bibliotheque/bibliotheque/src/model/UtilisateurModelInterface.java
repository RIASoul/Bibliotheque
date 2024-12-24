package model;

public interface UtilisateurModelInterface {
	
	public void modifierUser(String email);
	public void supprimerUser(int id);
	public boolean rechercherUser(int id);
	public void afficherUser();
	public void ajouterUser(Utilisateur u);
	public void chargerUtilisateursDepuisCSV();
	public void sauvegarderUtilisateursDansCSV();

}
