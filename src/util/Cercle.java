package util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class Cercle extends Ellipse2D.Double {
    double rayon;
    
    public double getRayon() {
        return rayon;
    }
    public void setRayon(double rayon) {
        this.rayon = rayon;
    }
    public Cercle(Point p, double rayon) {
        super(p.getX() - rayon, p.getY() - rayon, 2 * rayon, 2 * rayon);
        this.setRayon(rayon);
    } 
    public Cercle (){}
    public void paint (Graphics g, int i){
        if (i == -1) {
            g.setColor(Color.black);
            g.drawOval( (int) (this.getX()),(int) (this.getY()),(int) this.getRayon()*2, (int) this.getRayon()*2);
        }   
        else{
            g.fillOval( (int) (this.getX()),(int) (this.getY()),(int) this.getRayon()*2, (int) this.getRayon()*2);
            // Coordonnées pour centrer le texte dans le cercle
           int xText = (int) this.getCenterX() - (int) this.getRayon() +8; // Ajustez la valeur 10 selon vos besoins
           int yText = (int) this.getCenterY() + 5; // Ajustez la valeur 5 selon vos besoins
           g.setColor(Color.white); // Couleur du texte
           g.drawString(Integer.toString(i+1), xText, yText);
        }
        
    }
    public boolean contains(Point p) {
        // Vérifie si la distance du point (x, y) au centre du cercle est inférieure ou égale au rayon
        double distance = Math.sqrt(Math.pow((getCenterX() - p.getX()), 2) + Math.pow((getCenterY() - p.getY()), 2));
        return distance <= rayon;
    }
    public boolean contains (Cercle autreCercle) {
        double distanceCentres = this.getCenterX() - autreCercle.getCenterX();
        double differenceRayons = this.getWidth() / 2 - autreCercle.getWidth() / 2;

        return Math.sqrt(distanceCentres * distanceCentres) <= differenceRayons;
    }

    
}
