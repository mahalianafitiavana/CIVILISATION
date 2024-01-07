package peuple;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;
import util.Cercle;

public class Militaire extends Population {
    double force = 1.0;
    double rayon ;

    public void setForce(double force) {
        this.force = force;
    }
    public double getForce() {
        return force;
    }
    public void setRayon(double rayon) {
        this.rayon = rayon;
    }
    public double getRayon() {
        return rayon;
    }
    public Militaire(){
        super.setVie(this.getVie_init());
        setRayon(30.0);
    }
    public Militaire(Population p){
        super.setVie(20.0);
        super.setPosition(p.getPosition());
        setRayon(30.0);
    }
    /* reduce life of all the person arround */
    public void work (Vector<Population> peuple){
        int i = this.around(peuple).size();
        double reduction = this.getForce()/i;
        for (Population p : this.around(peuple)) {
            p.setVie(p.getVie()-reduction);
            System.out.println("vie -"+p.getVie());
        }
    }
    /* retourn la liste populatin arround */
    public Vector<Population> around (Vector<Population> peuple){
        Cercle cercle = new Cercle(this.getPosition(), this.getRayon());
        Vector<Population> in =  new Vector<>();
        for (Population p : peuple) {
            if ( !(p instanceof Militaire) && cercle.contains(p.getPosition())) {
                in.add(p);
            }
        }
        return in;
    }
    @Override
    public void paint(Graphics g, int i) {
        g.setColor(Color.black);
        super.paint(g, i);
        Cercle champ = new Cercle(this.getPosition(), getRayon());
        Cercle teta = new Cercle(this.getPosition(), getRayon()+20);
        champ.paint(g, -1); teta.paint(g, -1);
    }
}
