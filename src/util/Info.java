package util;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import civilisation.Civilisation;
import peuple.Population;
import terrain.Jeu;
import java.awt.*;
import java.util.Vector;

public class Info extends JFrame {
    Jeu jeu;
    JTable civilisationsTable;
    DefaultTableModel civilisationsTableModel;
    JTable personnesTable;
    DefaultTableModel personnesTableModel;
    public Jeu getJeu() {
        return jeu;
    }
    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }
    public Info(Jeu jeu) {
        this.jeu = jeu;
        setTitle("EVOLUTION DE JEU");
        setSize(400, 300);
        setLayout(new GridLayout(2, 1));
        initCivilisationsTable();           
         initPersonnesTable();

        add(new JScrollPane(civilisationsTable));
         add(new JScrollPane(personnesTable));

        setVisible(true);
    }
    private void initCivilisationsTable() {
        Vector<String> civilisationsHeaders = new Vector<>();
        civilisationsHeaders.add("ID");
        civilisationsHeaders.add("Civilisations' Ressources");

        Vector<Vector<String>> civilisationsData = new Vector<>();
        for (int index = 0; index < getJeu().getCivilisations().size(); index++) {
            Vector<String> rowData = new Vector<>();
            rowData.add(String.valueOf(index +1));
            rowData.add(String.valueOf(getJeu().getCivilisations().get(index).getRessource()));
            civilisationsData.add(rowData);        
        }
        civilisationsTableModel = new DefaultTableModel(civilisationsData, civilisationsHeaders);
        civilisationsTable = new JTable(civilisationsTableModel);
    }
    public void initPersonnesTable() {
        Vector<String> personnesHeaders = new Vector<>();
        personnesHeaders.add("ID");
        personnesHeaders.add("Job");
        personnesHeaders.add("vie");
        Vector<Vector<String>> personnesData = new Vector<>();
        Civilisation origine = new Civilisation();
        for (Population p : getJeu().all_people()) {
            origine = p.getOrigine(getJeu());
            Vector<String> rowData = new Vector<>();
            rowData.add(String.valueOf(getJeu().getCivilisations().indexOf(origine) + 1));
            rowData.add(p.getClass().getSimpleName());
            rowData.add(String.valueOf(p.getVie()));
            personnesData.add(rowData);
        }

        personnesTableModel = new DefaultTableModel(personnesData, personnesHeaders);
        personnesTable = new JTable(personnesTableModel);
    }
    private void updateCivilisationsTable() {
        civilisationsTableModel.setRowCount(0); // Efface toutes les lignes actuelles
        for (int index = 0; index < getJeu().getCivilisations().size(); index++) {
            Vector<String> rowData = new Vector<>();
            rowData.add(String.valueOf(index+1));
            rowData.add(String.valueOf(getJeu().getCivilisations().get(index).getRessource()));
            civilisationsTableModel.addRow(rowData);
        }
    }
    public void updatePersonnesTable() {
            
            personnesTableModel.setRowCount(0); // Efface toutes les lignes actuelles
            Civilisation origine = new Civilisation();
            for (Population p : getJeu().all_people()) {
                origine = p.getOrigine(getJeu());
                Vector<String> rowData = new Vector<>();
                rowData.add(String.valueOf(getJeu().getCivilisations().indexOf(origine) + 1));
                rowData.add(p.getClass().getSimpleName());
                rowData.add(String.valueOf(p.getVie()));
                personnesTableModel.addRow(rowData);
            }
    }
    public void updateTables() {
        updateCivilisationsTable();
        updatePersonnesTable();
    }

}