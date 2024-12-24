package model;
import java.util.ArrayList;

public class UtilisateurModel implements UtilisateurModelInterface{
	
	private ArrayList<Utilisateur> utilisateurs = new ArrayList<>();


	@Override
	public void modifierUser(String email) {
		// TODO Auto-generated method stub
		for (Utilisateur u : utilisateurs) {
            if (u.getEmail().equals(email)) {
                u.setEmail(email);
	}
		}  }          

	
	
	@Override
	public void supprimerUser(int id) {
		// TODO Auto-generated method stub
		utilisateurs.removeIf(u -> u.getId() == id);
	}

	@Override
	public boolean rechercherUser(int id) {
		// TODO Auto-generated method stub
		for (Utilisateur u : utilisateurs) {
            if (u.getId() == id) {
                return true;
            }
        }
        return false;
	}

	@Override
	public void afficherUser() {
		// TODO Auto-generated method stub
		 for (Utilisateur u : utilisateurs) {
	            System.out.println(u);
	        }
	}

	@Override
	public void ajouterUser(Utilisateur u) {
		// TODO Auto-generated method stub
		utilisateurs.add(u);
	}

}
