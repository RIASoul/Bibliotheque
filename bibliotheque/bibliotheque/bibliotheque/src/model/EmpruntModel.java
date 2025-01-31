package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpruntModel implements EmpruntModelInterface {
    private List<Emprunt> emprunts = new ArrayList<>();

    @Override
    public void enregistrerEmprunt(Emprunt emprunt) {
        emprunts.add(emprunt);
        System.out.println("Emprunt enregistré : " + emprunt);
    }

    @Override
    public List<Emprunt> consulterHistoriqueParUtilisateur(int utilisateurId) {
        List<Emprunt> historique = new ArrayList<>();
        for (Emprunt emprunt : emprunts) {
            if (emprunt.getUtilisateurId() == utilisateurId) {
                historique.add(emprunt);
            }
        }
        return historique;
    }

    
	@Override
	public void prolongerEmprunt(int empruntId, LocalDate dateRetour) {
		// TODO Auto-generated method stub
		  for (Emprunt emprunt : emprunts) {
	            if (emprunt.getId() == empruntId) {
	                emprunt.setDateRetourPrevue(dateRetour);
	                System.out.println("Prolongation effectuée pour l'emprunt ID : " + empruntId);
	                return;
	            }
	}
  
}
}
