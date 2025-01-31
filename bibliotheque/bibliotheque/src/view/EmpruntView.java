package view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

public class EmpruntView extends JPanel {

    private JComboBox<String> utilisateurComboBox; // Liste déroulante pour les utilisateurs
    private JList<String> livresList; // Liste avec sélection multiple pour les livres
    private JTextField dateEmpruntField;
    private JTextField dateRetourField;
    private JButton enregistrerButton;
    private JButton afficherButton;
    private JButton consulterHistoriqueButton;
    private JButton prolongerButton;

    public EmpruntView() {
        // Initialisation des composants
        utilisateurComboBox = new JComboBox<>(); // Liste déroulante pour les utilisateurs
        livresList = new JList<>(); // Liste avec sélection multiple pour les livres
        livresList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // Permet plusieurs sélections
        dateEmpruntField = new JTextField(20);
        dateRetourField = new JTextField(20);
        enregistrerButton = new JButton("Enregistrer Emprunt");
        afficherButton = new JButton("Afficher Emprunts");
        consulterHistoriqueButton = new JButton("Consulter Historique");
        prolongerButton = new JButton("Prolonger Emprunt");

        // Mise en page et ajout des composants
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new JLabel("Veuillez sélectionner un utilisateur :"));
        add(utilisateurComboBox);

        add(new JLabel("Veuillez sélectionner les livres :"));
        add(new JScrollPane(livresList)); // Ajout dans un JScrollPane pour gérer les grandes listes

        add(new JLabel("Date Emprunt (YYYY-MM-DD)"));
        add(dateEmpruntField);

        add(new JLabel("Date Retour (YYYY-MM-DD)"));
        add(dateRetourField);

        add(enregistrerButton);
        add(afficherButton);
        add(consulterHistoriqueButton);
        add(prolongerButton);

        setSize(400, 600);
        setVisible(true);
    }

    // Méthodes pour mettre à jour les données dans les listes
    public void setUtilisateurs(List<String> utilisateurs) {
        utilisateurComboBox.removeAllItems();
        for (String utilisateur : utilisateurs) {
            utilisateurComboBox.addItem(utilisateur);
        }
    }

    public void setLivres(List<String> livres) {
        livresList.setListData(livres.toArray(new String[0]));
    }

    // Méthodes pour récupérer les données sélectionnées
    public String getUtilisateurSelectionne() {
        return (String) utilisateurComboBox.getSelectedItem();
    }

    public List<String> getLivresSelectionnes() {
        return livresList.getSelectedValuesList();
    }

    public String getDateEmprunt() {
        return dateEmpruntField.getText();
    }

    public String getDateRetour() {
        return dateRetourField.getText();
    }

    // Méthodes pour ajouter des listeners aux boutons
    public void addEnregistrerListener(ActionListener listener) {
        enregistrerButton.addActionListener(listener);
    }

    public void addAfficherListener(ActionListener listener) {
        afficherButton.addActionListener(listener);
    }

    public void addConsulterHistoriqueListener(ActionListener listener) {
        consulterHistoriqueButton.addActionListener(listener);
    }

    public void addProlongerListener(ActionListener listener) {
        prolongerButton.addActionListener(listener);
    }
}