package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import controller.LivreController;

public class LivreView extends JPanel {

    private JTextField titreField;
    private JTextField auteurField;
    private JTextField anneeField;
    private JTextField genreField;
    private JTextField prixField;
    private JCheckBox disponibleCheckBox;
    private JButton addButton;
    private JButton deleteButton;
    private JButton editButton;
    private JButton listButton;
    private JTextField searchField;
    private JTextField genreFilterField;
    private JTextField prixFilterField;
    private JButton searchButton;
    public JList<String> livreList;
    private DefaultListModel<String> listModel;

    public LivreView(LivreController controller) {
        initializeUI();
       
    }
    
    
    
public LivreView() {
	
	initializeUI();
    }
  
    
    

    private void initializeUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        listModel = new DefaultListModel<>();
        livreList = new JList<>(listModel);
        JScrollPane listScrollPane = new JScrollPane(livreList);
        mainPanel.add(listScrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Détails du Livre"));

        inputPanel.add(new JLabel("Titre:"));
        titreField = new JTextField();
        inputPanel.add(titreField);

        inputPanel.add(new JLabel("Auteur:"));
        auteurField = new JTextField();
        inputPanel.add(auteurField);

        inputPanel.add(new JLabel("Année:"));
        anneeField = new JTextField();
        inputPanel.add(anneeField);

        inputPanel.add(new JLabel("Genre:"));
        genreField = new JTextField();
        inputPanel.add(genreField);

        inputPanel.add(new JLabel("Prix:"));
        prixField = new JTextField();
        inputPanel.add(prixField);

        inputPanel.add(new JLabel("Disponible:"));
        disponibleCheckBox = new JCheckBox();
        inputPanel.add(disponibleCheckBox);

        mainPanel.add(inputPanel, BorderLayout.EAST);

        JPanel filterPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        filterPanel.setBorder(BorderFactory.createTitledBorder("Recherche et Filtrage"));

        searchField = new JTextField();
        searchButton = new JButton("Rechercher");
        genreFilterField = new JTextField();
        prixFilterField = new JTextField();

        filterPanel.add(new JLabel("Titre:"));
        filterPanel.add(searchField);
        filterPanel.add(searchButton);

        filterPanel.add(new JLabel("Genre:"));
        filterPanel.add(genreFilterField);

        filterPanel.add(new JLabel("Prix Max:"));
        filterPanel.add(prixFilterField);

        mainPanel.add(filterPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        addButton = new JButton("Ajouter Livre");
        deleteButton = new JButton("Supprimer Livre");
        editButton = new JButton("Modifier Livre");
        listButton = new JButton("Rafraîchir Liste");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);
        buttonPanel.add(listButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public String getTitre() {
        return titreField.getText();
    }

    public String getAuteur() {
        return auteurField.getText();
    }

    public int getAnnee() {
        return Integer.parseInt(anneeField.getText());
    }

    public String getGenre() {
        return genreField.getText();
    }

    public double getPrix() {
        return Double.parseDouble(prixField.getText());
    }

    public boolean isDisponible() {
        return disponibleCheckBox.isSelected();
    }

    public String getSearchTitre() {
        return searchField.getText();
    }

    public String getFilterGenre() {
        return genreFilterField.getText();
    }

    public double getFilterPrix() {
        try {
            return Double.parseDouble(prixFilterField.getText());
        } catch (NumberFormatException e) {
            return Double.MAX_VALUE; // Prix infini si pas spécifié
        }
    }

    public void addAddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void addDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void addEditButtonListener(ActionListener listener) {
        editButton.addActionListener(listener);
    }

    public void addListButtonListener(ActionListener listener) {
        listButton.addActionListener(listener);
    }

    public void addSearchButtonListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }

    public void updateLivreList(List<String> livres) {
        listModel.clear();
        for (String livre : livres) {
            listModel.addElement(livre);
        }
    }
}
