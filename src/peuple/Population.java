package peuple;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Vector;
import civilisation.Civilisation;
import ressources.Ressource;
import terrain.Jeu;
import util.Cercle;
public class Population implements Serializable{
    double vie_init = 10;
    Point position ;  
    Point destination = null ;
    double vie = vie_init;
    String mvt = "null";
    
    public String getMvt() {
        return mvt;
    }
    public void setMvt(String mvt) {
        this.mvt = mvt;
    }
    public void setVie_init(double vie_init) {
        this.vie_init = vie_init;
    }
    public  double getVie_init() {
        return vie_init;
    }
    public Point getDestination() {
        return destination;
    }
    public void setDestination(Point destination) {
        this.destination = destination;
    }
    public double getVie() {
        return vie;
    }
    public void setVie(double d) {
        this.vie = d;
    }  
    public Point getPosition() {
        return position;
    }
    public void setPosition(Point position) {
        this.position = position;
    } 
    /* get civilisation origine */
    public Civilisation getOrigine (Jeu terrain){
        Civilisation result = new Civilisation();
        Vector<Civilisation> civilisations = terrain.getCivilisations();
        for (int i = 0; i < civilisations.size(); i++) {
            for (int j = 0; j < civilisations.get(i).getPopulation().size(); j++) {
                Population p = civilisations.get(i).getPopulation().get(j);
                if (p.equals(this)) {
                    result = civilisations.get(i);
                    break;
                }
            }
        }
        return result;
    }
  
    /* make the calcul if inside a ressource */  
    public void work (Jeu terrain,long thread){
        Civilisation origine = this.getOrigine(terrain);
        Vector<Ressource> r = terrain.getRessources();
        Ressource res = where(r);
        DecimalFormat d = new DecimalFormat("#.##");
        if (res != null) {
            double a = res.getApport()*thread /1000;
            String f = d.format(a);
            f = f.replace(",", ".");
            a = Double.parseDouble(f);
            double ajour = (origine.getRessource()+ origine.getValeur_ajouter()+a); 
            //  System.out.println("ressource avant:   "+origine.getRessource()+ " valeur ajouter  "+ origine.getValeur_ajouter()+"   "); 
            origine.setRessource(ajour); 
                                  // apport efa tao           // valeur ajouter       //valeur del aressource
        }
    }
     /* get the ressource where the people is */
    public Ressource where (Vector<Ressource> ressources){
        for (Ressource ressource : ressources) {
            if (ressource.getAire().contains(this.getPosition())) {
                return ressource;
            }
        }
        return null;
    }
    public void run (){
        String mvt = this.getMvt();
        switch (mvt) {
            case "right":
                right();
                break;
            case "left":
                left();
                break;
            case "up":
                up();
                break;
            case "down":
                down();                  
        }   
        moveOnce();            
    }
   /*liste of all the cops arround */
    public Militaire warning (Jeu jeu,double teta ){
        Vector<Population> people = jeu.others(jeu.getCivilisations().indexOf(this.getOrigine(jeu)));
        Militaire m = null;
        for (Population p : people) {
            if ( p instanceof Militaire  ) {
                Cercle pra_champ = new Cercle(p.getPosition(),( (Militaire) p).getRayon()+ teta);
                Cercle mon_champ = new Cercle(this.getPosition(), 10);
                if (pra_champ.contains(this.getPosition())) {
                    m = (Militaire) p;
                    break;
                }                
            }
        }
        return m;
    }
    /* position anle polisy par rapoort anle olona */
    public String pra_position (Militaire m  ){
        String p = new String();
        Integer delatX = (int) (this.getPosition().getX() - m.getPosition().getX() ) ;
        Integer delatY = (int) (this.getPosition().getY() - m.getPosition().getY()  );
        Integer fabsX = Math.abs(delatX    );
        Integer fabsY = Math.abs(delatY    );
        int i = fabsX.compareTo(fabsY); /* ecart entre direction x et y */
        
        if (i == 1) {
            if (0 < delatX ) {
                p = "left";
            }
            if ( delatX < 0 ) {
                p = "right";
            }
        }
        if (i == -1) {
            if (0 < delatY) {
                p = "up";
            }
            if (  delatY < 0 ) {
                p = "down";
            }
        }
        return p;
    }
    public void escapefrom (Militaire m,double teta){
        if (m != null) {
            String mvt = pra_position(m); 
            /* on execute le contraire du mvt */
             switch (mvt) {
                case "right":
                    this.setDestination(new Point( (int) this.getPosition().getX()-20, (int) this.getPosition().getY()));
                    break;                          
                case "left":
                    this.setDestination(new Point( (int) this.getPosition().getX()+20, (int) this.getPosition().getY()));
                    break;
                case "up":
                    this.setDestination(new Point( (int) this.getPosition().getX(), (int) this.getPosition().getY()+20));
                    break;
                case "down":
                    this.setDestination(new Point( (int) this.getPosition().getX(), (int) this.getPosition().getY()-20));
                    break;
            }    
            System.out.println("attack "+this.getPosition());
        }
    }
    public static String getRandomDirection() {
        String[] directions = {"up", "down", "left", "right"};
        Random random = new Random();
        int randomIndex = random.nextInt(directions.length);
        return directions[randomIndex];
    }
    
    public void moveOnce() {
        Point destination = this.getDestination();
        if (destination == null) {
            destination = getPosition();
        }
        int x = (int) destination.getX();
        int y = (int) destination.getY();
        if (position.getX() ==  x && position.getY() == y) {
            this.setMvt("null");
        }
        if (position.getX() !=  x || position.getY() != y) {
            if (x < position.getX() ) {
                this.setMvt("left");
            }
            if ( position.getX() < x ) {
                this.setMvt("right");
            }
            if (y < position.getY() ) {
                this.setMvt("up");
            }
            if ( position.getY() < y ) {
                this.setMvt("down");
            }
        }
    }
    public void left () {
        Point p = this.getPosition();
        p.setLocation(p.getX()-1,p.getY());
        setPosition(p);
    }
    public void right () {
        Point p = this.getPosition();
        p.setLocation(p.getX()+1,p.getY());
        setPosition(p);
    }
    public void up () {
        Point p = this.getPosition();
        p.setLocation(p.getX(),p.getY()-1);
        setPosition(p);
    }
    public void down () {
        Point p = this.getPosition();
        p.setLocation(p.getX(),p.getY()+1);
        setPosition(p);
    }
    public void paint (Graphics g,  int i){
        Cercle cercle = new Cercle(this.getPosition(), 10);
        if (!(this instanceof Militaire) && !(this instanceof Professeur)) {
            g.setColor(Color.decode("#4fc0db"));
        }
        cercle.paint(g, i);
    }
}
