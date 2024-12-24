package model;

public interface LivreModelInterface {

	public void AjouterLivre(Livre livre);
	public void SupprimerLivre(Livre livre);
	public void ModifierLivre(int idLivre, String nouvTitre);
	public void AfficherLivre();
	public Boolean RechercherLivre(String nom);
	
}
