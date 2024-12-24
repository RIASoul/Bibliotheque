package view;

import javax.swing.*;
import java.awt.*;

import controller.LivreController;
import controller.EmpruntController;
import controller.UtilisateurController;
import controller.RetourController;

import model.LivreModel;
import model.EmpruntModel;
import model.UtilisateurModel;
import model.RetourModel;

public class MenuView extends JFrame {
    private JTabbedPane tabbedPane;  // Onglets principaux
    private JPanel livrePanel;
    private JPanel empruntPanel;
    private JPanel utilisateurPanel;
    private JPanel retourPanel;

    private LivreController livreController;
    private EmpruntController empruntController;
    private UtilisateurController utilisateurController;
    private RetourController retourController;

    public MenuView() {
        setTitle("Menu Principal");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeUI();
    }

    private void initializeUI() {
        // Création du JTabbedPane pour gérer les onglets
        tabbedPane = new JTabbedPane();

        // Création des modèles
        LivreModel livreModel = new LivreModel();
        EmpruntModel empruntModel = new EmpruntModel();
        UtilisateurModel utilisateurModel = new UtilisateurModel();
        RetourModel retourModel = new RetourModel();

        // Création des vues
        LivreView livreView = new LivreView();
        EmpruntView empruntView = new EmpruntView();
        UtilisateurView utilisateurView = new UtilisateurView();
        RetourView retourView = new RetourView();

        // Création des contrôleurs
        livreController = new LivreController(livreModel, livreView);
        empruntController = new EmpruntController(empruntModel, empruntView);
        utilisateurController = new UtilisateurController(utilisateurModel, utilisateurView);
        retourController = new RetourController(retourModel, retourView);

        // Initialisation des vues avec leurs contrôleurs respectifs
        livrePanel = livreView;
        empruntPanel = empruntView;
        utilisateurPanel = utilisateurView;
        retourPanel = retourView;

        // Ajout des panneaux aux onglets
        tabbedPane.addTab("Gestion des Livres", livrePanel);
        tabbedPane.addTab("Gestion des Emprunts", empruntPanel);
        tabbedPane.addTab("Gestion des Utilisateurs", utilisateurPanel);
        tabbedPane.addTab("Gestion des Retours", retourPanel);

        // Ajouter le JTabbedPane au contenu principal de la fenêtre
        add(tabbedPane, BorderLayout.CENTER);
    }
}
