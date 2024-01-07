package terrain;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import file.Writer;
import listener.Mouvement;
import util.Info;

public class MaFenetre extends JFrame {
    Jeu terrain;

    public Jeu getTerrain() {
        return terrain;
    }

    public void setTerrain(Jeu terrain) {
        this.terrain = terrain;
    }
    public MaFenetre (Jeu terrain){
        Writer w = new Writer();
        try {
            Vector liste = w.readObject("jeu");
            if (liste.lastElement() != null) {
                terrain = (Jeu) liste.lastElement();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        this.setTitle("CIVILISATION");
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setVisible(true); 
        this.add(terrain);
        this.setTerrain(terrain);
        this.addMouseListener(new Mouvement(this));
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(
                    MaFenetre.this, // Parent component
                    "Voulez-vous sauvegarder avant de quitter ?", // Message
                    "Sauvegarde", // Titre
                    JOptionPane.YES_NO_OPTION // Options de la boîte de dialogue
                    );
                    try {
                        
                        if (option == JOptionPane.YES_OPTION) {
                            w.writeObject("jeu", getTerrain());
                            
                        }if (option == JOptionPane.NO_OPTION) {
                            w.writeObject("jeu", null);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    dispose(); // Fermer la fenêtre
                }
            });
            Info i = new Info(this.getTerrain());
            MaFenetre fenetre = this;
            Thread th = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        fenetre.getTerrain().play();
                     //   System.out.println(" resource actuelle"+ fenetre.getTerrain().getCivilisations().get(0).getRessource());
                        fenetre.getTerrain().repaint();
                        i.updateTables();
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            th.start();
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }   
    }
    