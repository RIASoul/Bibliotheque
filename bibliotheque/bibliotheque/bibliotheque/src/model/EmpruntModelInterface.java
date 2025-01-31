package model;
import java.time.LocalDate;
import java.util.List;

public interface EmpruntModelInterface {
	
	void enregistrerEmprunt(Emprunt emprunt);
    List<Emprunt> consulterHistoriqueParUtilisateur(int utilisateurId);
    void prolongerEmprunt(int empruntId, LocalDate dateRetour);

}
