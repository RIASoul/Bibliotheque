package controller;

import model.Livre;
import model.LivreModel;

import java.util.Scanner;

public class LivreController {

    private LivreModel livreModel;

    public LivreController(LivreModel livreModel) {
        this.livreModel = livreModel;
    }

    public void afficherLivres() {
        livreModel.AfficherLivre();
    }

 
  //Ajouter
  //Supprimer  
    
    
    
    
    public void modifierLivre(int id, String nouveauTitre) {
        livreModel.ModifierLivre(id, nouveauTitre);
        System.out.println("Livre avec l'ID " + id + " modifié.");
    }

    public void rechercherLivre(String nom) {
        if (livreModel.RechercherLivre(nom)) {
            System.out.println("Livre trouvé : " + nom);
        } else {
            System.out.println("Livre non trouvé : " + nom);
        }
    }

    public static void main(String[] args) {
        LivreModel livreModel = new LivreModel();
        LivreController controller = new LivreController(livreModel);

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Menu :");
            System.out.println("1. Ajouter Livre");
            System.out.println("2. Supprimer Livre");
            System.out.println("3. Modifier Livre");
            System.out.println("4. Afficher Livres");
            System.out.println("5. Rechercher Livre");
            System.out.println("6. Quitter");
            System.out.print("Votre choix : ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consommer le '\n'

            switch (choice) {
                case 1:
                    System.out.print("Entrez l'ID du livre : ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consommer le '\n'
                    System.out.print("Entrez le titre du livre : ");
                    String titre = scanner.nextLine();
                    //controller.ajouterLivre(id, titre);
                    break;

                case 2:
                    System.out.print("Entrez l'ID du livre à supprimer : ");
                    id = scanner.nextInt();
                    //controller.supprimerLivre(id);
                    break;

                case 3:
                    System.out.print("Entrez l'ID du livre à modifier : ");
                    id = scanner.nextInt();
                    scanner.nextLine(); // Consommer le '\n'
                    System.out.print("Entrez le nouveau titre du livre : ");
                    titre = scanner.nextLine();
                    controller.modifierLivre(id, titre);
                    break;

                case 4:
                    controller.afficherLivres();
                    break;

                case 5:
                    System.out.print("Entrez le nom du livre à rechercher : ");
                    String nom = scanner.nextLine();
                    controller.rechercherLivre(nom);
                    break;

                case 6:
                    System.out.println("Au revoir!");
                    break;

                default:
                    System.out.println("Choix invalide.");
            }
        } while (choice != 6);

        scanner.close();
    }
}
