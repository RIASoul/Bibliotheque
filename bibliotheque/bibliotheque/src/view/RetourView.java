package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import model.Retour;

public class RetourView extends JPanel {

    private JTextField empruntIdField;
    private JTextField dateRetourEffectiveField;
    private JTextField dateRetourPrevueField;
    private JTextField tauxPenaliteField;
    private JButton enregistrerButton;
    private JButton rechercherButton;
    private JButton calculerPenaliteButton;
    private JTextArea messageArea;

    public RetourView() {
        //setTitle("Gestion des Retours");
        setSize(600, 400);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeUI();
    }

    private void initializeUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        // Zone de saisie
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.add(new JLabel("ID Emprunt:"));
        empruntIdField = new JTextField();
        inputPanel.add(empruntIdField);

        inputPanel.add(new JLabel("Date Retour Effective (AAAA-MM-JJ):"));
        dateRetourEffectiveField = new JTextField();
        inputPanel.add(dateRetourEffectiveField);

        inputPanel.add(new JLabel("Date Retour Prévue (AAAA-MM-JJ):"));
        dateRetourPrevueField = new JTextField();
        inputPanel.add(dateRetourPrevueField);

        inputPanel.add(new JLabel("Taux de Pénalité Par Jour:"));
        tauxPenaliteField = new JTextField();
        inputPanel.add(tauxPenaliteField);

        // Boutons d'action
        enregistrerButton = new JButton("Enregistrer Retour");
        rechercherButton = new JButton("Rechercher Retour");
        calculerPenaliteButton = new JButton("Calculer Pénalité");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.add(enregistrerButton);
        buttonPanel.add(rechercherButton);
        buttonPanel.add(calculerPenaliteButton);

        // Zone de message
        messageArea = new JTextArea(5, 30);
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);

        // Ajout des composants au panneau principal
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(scrollPane, BorderLayout.NORTH);

        add(mainPanel);
    }

    public String getEmpruntId() {
        return empruntIdField.getText().trim();
    }

    public String getDateRetourEffective() {
        return dateRetourEffectiveField.getText().trim();
    }

    public String getDateRetourPrevue() {
        return dateRetourPrevueField.getText().trim();
    }

    public String getTauxPenalite() {
        return tauxPenaliteField.getText().trim();
    }

    public void afficherMessage(String message) {
        messageArea.append(message + "\n");
    }

    public void addEnregistrerButtonListener(ActionListener listener) {
        enregistrerButton.addActionListener(listener);
    }

    public void addRechercherButtonListener(ActionListener listener) {
        rechercherButton.addActionListener(listener);
    }

    public void addCalculerPenaliteButtonListener(ActionListener listener) {
        calculerPenaliteButton.addActionListener(listener);
    }

    public void displayRetour(Retour retour) {
        afficherMessage("Emprunt ID : " + retour.getEmpruntId() + "\n" +
                "Date Retour Effective : " + retour.getDateRetourEffective() + "\n" +
                "Pénalité : " + retour.getPenalite() + "\n");
    }
}
