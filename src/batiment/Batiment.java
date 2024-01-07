package batiment;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.io.Serializable;
import java.util.Vector;
import civilisation.Civilisation;
import peuple.Population;

public class Batiment implements Serializable {
    String nom;
    Vector<Point> points = new Vector<>();
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public Vector<Point> getPoints() {
        return points;
    }
    public void setPoints(Vector<Point> points) {
        this.points = points;
    }
    public Population birth (Civilisation origine){
        Population p = new Population();
        Polygon aire = this.getAire();
        double x = aire.getBounds().getX()+aire.getBounds().getWidth();
        double y = aire.getBounds().getY()+aire.getBounds().getHeight();
        Point position = new Point( (int) x, (int) y);
        
        p.setPosition(position);
        System.out.println(p.getVie());
        origine.setRessource(origine.getRessource() - p.getVie());
        return p;
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
       // super.paint(g);
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
        java.awt.FontMetrics fontMetrics = g.getFontMetrics();
        int largeurTexte = fontMetrics.stringWidth(this.getNom());
        int hauteurTexte = fontMetrics.getHeight();
        // Calcul des coordonnées pour centrer le texte
        int x = centerX - (largeurTexte/2);
        int y = centerY - (hauteurTexte/2);

        g.drawString(this.getNom(), x, y);
    }
    
}
