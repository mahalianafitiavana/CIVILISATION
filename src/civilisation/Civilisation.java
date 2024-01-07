package civilisation;
import java.awt.Point;
import java.awt.Polygon;
import java.io.Serializable;
import java.util.Vector;
import batiment.Batiment;
import batiment.Hopital;
import peuple.Militaire;
import peuple.Population;
import peuple.Professeur;
public class Civilisation implements Serializable {
    Vector<Population> population = new Vector<>();
    Vector<Batiment> batiments = new Vector<>() ;
    double ressource = 100.0;
    double valeur_ajouter = 0.0;
    Hopital Hopital;
    
    public Hopital getHopital() {
        return Hopital;
    }
    public void setHopital(Hopital hopital) {
        Hopital = hopital;
    }
    public double getValeur_ajouter() {
        return valeur_ajouter;
    }
    public void setValeur_ajouter(double valeur_ajouter) {
        this.valeur_ajouter = valeur_ajouter;
    }
    public double getRessource() {
        return ressource;
    }
    public void setRessource(double ressource) {
        this.ressource = ressource;
    }
    public Vector<Batiment> getBatiments() {
        return batiments;
    }
    public void setBatiments(Vector<Batiment> batiments) {
        this.batiments = batiments;
    }
    public Vector<Population> getPopulation() {
        return population;
    }
    public void setPopulation(Vector<Population> population) {
        this.population = population;
    }
    public Vector<Militaire> getMilitaires() {
        Vector<Militaire> m = new Vector<>();
        for (Population population : getPopulation()) {
            if (population instanceof Militaire) {
                m.add( (Militaire) population);
            }
        }
        return m;
    }
    public Vector<Professeur> getProfs() {
        Vector<Professeur> m = new Vector<>();
        for (Population population : getPopulation()) {
            if (population instanceof Professeur) {
                m.add( (Professeur) population);
            }
        }
        return m;
    }    
    public Batiment getTheBatiment (Point i){
        Batiment result = new Batiment();
        Vector<Batiment> batiments = this.getBatiments();
        Polygon aire = new Polygon();
        for (int j = 0; j < batiments.size(); j++) {
            aire = batiments.get(j).getAire();
            if(  aire.contains(i) ) {
                result = batiments.get(j);
                return result;
            }
        }
        return null;
    }
}