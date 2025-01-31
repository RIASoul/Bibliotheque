package model;
import java.util.ArrayList;

public class LivreModel implements LivreModelInterface{
	
	private ArrayList<Livre> livres = new ArrayList<Livre>();

	@Override
	public void AjouterLivre(Livre livre) {
		// TODO Auto-generated method stub
		livres.add(livre);
		
	}

	@Override
	public void SupprimerLivre(Livre livre) {
		// TODO Auto-generated method stub
		livres.remove(livre);
		
	}

	@Override
	public void ModifierLivre(int idLivre, String nouvTitre) {
		// TODO Auto-generated method stub
		 System.out.println("Modification du titre ");
	        for (int i = 0; i < livres.size(); i++) {
	            if (livres.get(i).getId() == idLivre) {
	                livres.get(i).setTitre(nouvTitre);
			}
		}
		
	}

	@Override
	public void AfficherLivre() {
		// TODO Auto-generated method stub
		for (Livre livre : livres) {
            System.out.println(livre);  
        }
		
	}

	@Override
	public Boolean RechercherLivre(String nom) {
		// TODO Auto-generated method stub
		for (Livre livre : livres) {
            if (livre.getTitre()==nom) {
                return true;
            }
        }
        return false;
    }
		
	}


