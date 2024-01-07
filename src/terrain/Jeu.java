package terrain;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import java.util.Vector;
import javax.swing.JPanel;
import batiment.Batiment;
import batiment.Hopital;
import civilisation.Civilisation;
import peuple.Militaire;
import peuple.Population;
import peuple.Professeur;
import ressources.Ressource;
import util.Cercle;

public class Jeu extends JPanel implements  Serializable {
    Vector<Civilisation> civilisations = new Vector<>();
    Vector<Ressource> ressources = new Vector<>();
    public Vector<Ressource> getRessources() {
        return ressources;
    }
    public void setRessources(Vector<Ressource> ressources) {
        this.ressources = ressources;
    }
    public Vector<Civilisation> getCivilisations() {
        return civilisations;
    }
    public void setCivilisations(Vector<Civilisation> civilisations) {
        this.civilisations = civilisations;
    }
    public Vector<Population> all_people (){
        Vector<Population> p = new Vector<>(this.getCivilisations().get(0).getPopulation());
        for (int i = 1; i < this.getCivilisations().size(); i++) {
            p.addAll(this.getCivilisations().get(i).getPopulation());
        }        
        return p;
    }
    public Vector<Militaire> all_militaries (){
        Vector<Militaire> p = new Vector<>(this.getCivilisations().get(0).getMilitaires());
        for (int i = 1; i < getCivilisations().size(); i++) {
            p.addAll(getCivilisations().get(i).getMilitaires());
        }
        return p;
    }

    /* tous les batiments */
    public Vector<Batiment> all_batiments (){
        Vector<Batiment> p = new Vector<>(this.getCivilisations().get(0).getBatiments());
        for (int i = 1; i < getCivilisations().size(); i++) {
            p.addAll(getCivilisations().get(i).getBatiments());
        }
        return p;
    }  
    /* tous les hopitaux */
    public Vector<Hopital> all_hopital (){
        Vector<Hopital> p = new Vector<>();
        for (int i = 0; i < getCivilisations().size(); i++) {
            p.add(getCivilisations().get(i).getHopital());
        }
        return p;
    }
    /* */
    public Hopital isInHopital (Point p ){
        Vector<Hopital> liste = all_hopital();
        Hopital h = null;
        for (Hopital hopital : liste) {
            if (hopital.getAire().contains(p)) {
                return hopital;
            }
        }
        return h;
    }
    /* tous les profs */
    public Vector<Professeur> all_profs (){
        Vector<Professeur> p = new Vector<>(this.getCivilisations().get(0).getProfs());
        for (int i = 1; i < getCivilisations().size(); i++) {
            p.addAll(getCivilisations().get(i).getProfs());
        }
        return p;
    }
    /* si la position x et y  sont dans un champ null si ce n est pas dans un batiment */
    public Object[] clickedBatiment(int x, int y){
        Object[] val = new Object[2];
        Point init = new Point(x, y);
        for (Civilisation c : civilisations) {
            Batiment b=c.getTheBatiment(init);
            if(b!=null){
                val[0] = b;     // le batiment pour creer la population
                val[1] = c;     // civilisation pour ajouter la population
                return val;
            }
        }
        return null;
    }
    /* soin */
    public  void soin (){
        Vector<Hopital> h = all_hopital();
        for (Hopital hopital : h) {
            hopital.soin();
        }
    }
    public void paint (Graphics g){
        super.paint(g);
        Jeu terrain = this;
        terrain.setBackground(Color.decode("#c69e77"));
        Vector<Civilisation> civilisations = terrain.getCivilisations();
        for (Batiment batiment : all_batiments()) {
            batiment.paint(g);
        }
        for (Ressource ressource :  getRessources()) {
            ressource.paint(g);
        }
        for (Hopital h : all_hopital()) {
            h.paint(g);
        }
        for (int i = 0; i < civilisations.size(); i++) {
            for (Population population   : civilisations.get(i).getPopulation()) {
                population.paint(g, i);
            }
        }
    }
    public int getPeople (Point i){
        int t = -1;
        Cercle reel = new Cercle();
        Vector<Population> population = this.all_people();
        for (int j = 0; j < population.size(); j++) {
            reel = new Cercle(new Point(((int) population.get(j).getPosition().getX() +10), ((int) population.get(j).getPosition().getY()+10)),10*2);
            if (reel.contains(i)) {
                t = j;
                break;
            }
        }
        return t;
    }
    /* tous les populayion different de l indice de la civilisation */
    public Vector<Population> others ( int i){
        Vector<Population> result = new Vector<>();
        for (int j = 0; j < this.getCivilisations().size(); j++) {
            if (j != i) {
                result.addAll(this.getCivilisations().get(j).getPopulation());
            }
        }
        return result;
    }
    /* soinnn */

    public void work () {

        for (int i = 0; i < all_people().size(); i++) {
         
            all_people().get(i).escapefrom(all_people().get(i).warning(this,20), i);;
            all_people().get(i).run();
          //  all_people().get(i).work(this, 20);
     //       System.out.println( all_people().get(i).getMvt());

            // if (all_people().get(i).getVie() <= 0 ) {
            //     Civilisation origine = all_people().get(i).getOrigine(this);
            //     Hopital h = origine.getHopital();
            //     /* ra mbola tsy feno */
            //     if (h.getMalades().size() < h.getCapacite()) {
            //         Point p = h.getPosition();
            //         all_people().get(i).setPosition(p);
            //         all_people().get(i).setDestination(p);
            //         h.getMalades().add(all_people().get(i));
            //     }
            //     /* ra efa feno de maty */
            //     else{
            //         origine.getPopulation().remove(all_people().get(i));
            //     }
            // }
        }

    }
    public void gard () {
        Civilisation origine = new Civilisation();
        Vector liste = new Vector<>();
        for (int i = 0; i < all_militaries().size(); i++) {
            origine = all_militaries().get(i).getOrigine(this);
            liste = others(getCivilisations().indexOf(origine));
            all_militaries().get(i).work(liste);
        }
    }
    public void play (){
            
            work( );
            gard();
            soin();
    } 

}
