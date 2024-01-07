package peuple;

import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;
import civilisation.Civilisation;
import ressources.Ressource;
import terrain.Jeu;

public class Professeur extends Population {
    double apport;
    public double getApport() {
        return apport;
    }
    public void setApport(double apport) {
        this.apport = apport;
    }
    public Professeur(){
        super.setVie(20.0);
    }
    public Professeur(Population p){
        super.setVie_init(20);
        super.setVie(20);
        super.setPosition(p.getPosition());
        this.setApport(5);
    }
    @Override
    public void work (Jeu terrain,long thread){
        Civilisation origine = this.getOrigine(terrain);
        Ressource ressource = this.where(terrain.getRessources());
        double ajout = this.getApport()*thread/1000;
        System.out.println(ajout);
        DecimalFormat format = new DecimalFormat("#.##");
        String s = format.format(ajout);
        s = s.replace(",", ".");
        ajout = Double.parseDouble(s);
        if (ressource != null) {
            origine.setValeur_ajouter(origine.getValeur_ajouter()+ajout); // add pour faire augmenter le rapport
            System.out.println("valeur "+ressource.getNom()+"  "+origine.getValeur_ajouter());
        }
    }
    @Override
    public void paint (Graphics g, int i){
        Color jaune = Color.decode("#031a47"); 
        g.setColor(jaune);
        super.paint(g, i);
    }

}
