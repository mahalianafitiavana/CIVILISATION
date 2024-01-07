package batiment;

import java.util.Random;
import java.util.Vector;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import peuple.Population;

public class Hopital extends Batiment {
    int soins;
    int capacite;
    Vector<Population> malades = new Vector<>();
    public Hopital() {
    }
    public Hopital(int soins, int capacite) {
        this.soins = soins;
        this.capacite = capacite;
    }
    public int getSoins() {
        return soins;
    }
    public void setSoins(int soins) {
        this.soins = soins;
    }
    public int getCapacite() {
        return capacite;
    }
    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
    public Vector<Population> getMalades() {
        return malades;
    }
    public void setMalades(Vector<Population> malades) {
        this.malades = malades;
    }
    public void addMalade (Population p){
        if (getMalades().size() < this.getCapacite() ) {
            getMalades().add(p) ;
        }
    }
    public void soin (){
        Vector<Population> malades = getMalades();
        for (Population population : malades) {
            if (population.getVie() < population.getVie_init()) {
                population.setVie(population.getVie()+this.getSoins());
                if (population.getVie_init() < population.getVie()) {
                    population.setVie(population.getVie_init());
                }
            }
        }
    }
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.red);
        super.paint(g);
    }
    public Point getPosition() {
        Polygon polygon = this.getAire();
        Random rand = new Random();
        int xMin = polygon.getBounds().x;
        int xMax = xMin + polygon.getBounds().width;
        int yMin = polygon.getBounds().y;
        int yMax = yMin + polygon.getBounds().height;

        while (true) {
            int randomX = rand.nextInt(xMax - xMin) + xMin;
            int randomY = rand.nextInt(yMax - yMin) + yMin;

            if (polygon.contains(randomX, randomY)) {
                return new Point(randomX, randomY);
            }
        }
    }
    
    
    
}
