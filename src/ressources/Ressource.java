package ressources;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.io.Serializable;
import java.util.Vector;

public class Ressource implements Serializable {
    String nom;
    Vector points;
    double apport;
    public double getApport() {
        return apport;
    }

    public void setApport(double apport) {
        this.apport = apport;
    }

    public Vector getPoints() {
        return points;
    }

    public void setPoints(Vector points) {
        this.points = points;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public Polygon getAire (){
        Polygon p = new Polygon();
        for(Object o : this.getPoints()){
            Point point = (Point) o;
            p.addPoint( (int ) point.getX(), (int) point.getY());
        }
        return p;
    }
    public void paint (Graphics g){
        g.fillPolygon(getAire());
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        // calcul du centre du polygone
        int centerX = 0;
        int centerY = 0;
        for (int i = 0; i < getPoints().size(); i++) {
            centerX += ( (Point) this.getPoints().get(i)).getX();
            centerY += ( (Point) this.getPoints().get(i)).getY();
        }
        centerX /= getPoints().size();
        centerY /= getPoints().size();
        FontMetrics fontMetrics = g.getFontMetrics();
        int largeurTexte = fontMetrics.stringWidth(this.getNom());
        int hauteurTexte = fontMetrics.getHeight();
        // Calcul des coordonnées pour centrer le texte
        int x = centerX - (largeurTexte/2);
        int y = centerY - (hauteurTexte/2);

        g.drawString(this.getNom(), x, y);
    }
    
}
