import ressources.*;
import terrain.MaFenetre;
import util.Cercle;
import terrain.Jeu;
import java.awt.Point;
import java.util.Vector;
import batiment.Batiment;
import batiment.Camp;
import batiment.Hopital;
import batiment.Maison;
import batiment.University;
import civilisation.Civilisation;
public class Main {
    public static void main(String[] args) throws Exception {
        Civilisation deux = new Civilisation();
        /* Civilisation 1 */
        Vector<Ressource> ressources1 = new Vector();
        Civilisation un = new Civilisation();
        /*professeur */
        Jeu terrain = new Jeu();
        /* university */
        Batiment u1 = new University();
        u1.setNom("University 1");
        Vector u1_position = new Vector<>();
        u1_position.add(new Point(150,350));
        u1_position.add(new Point(200,325));
        u1_position.add(new Point(250,350));
        u1_position.add(new Point(250,450));
        u1_position.add(new Point(150,450));
        u1.setPoints(u1_position);
        un.getBatiments().add(u1);

        Batiment u2 = new University();
        u2.setNom("University 2");
        Vector u2_position = new Vector<>();
        u2_position.add(new Point(600,550));
        u2_position.add(new Point(650,500));
        u2_position.add(new Point(700,550));
        u2_position.add(new Point(700,600));
        u2_position.add(new Point(600,600));
        u2.setPoints(u2_position);
        deux.getBatiments().add(u2);
        /* terre */
        Ressource terre1 = new Terre();
        terre1.setApport(2);
        terre1.setNom("TERRE ");
        Vector terre1_position = new Vector<>();
        terre1_position.add(new Point(650,350));
        terre1_position.add(new Point(400,250));
        terre1_position.add(new Point(500,500));
        terre1_position.add(new Point(500,300));
        terre1_position.add(new Point(500,400));
        terre1.setPoints(terre1_position);
        ressources1.add(terre1);
        //--------------------------------
        /*Ressource terre2 = new Terre();
        terre2.setApport(200);
        terre2.setNom("TERRE");
         Vector terre2_position = new Vector<>();
        terre2_position.add(new Point(400,550));
        terre2_position.add(new Point(300,600));
        terre2_position.add(new Point(300,700));
        terre2_position.add(new Point(500,650));
        terre2_position.add(new Point(500,600));
        terre2.setPoints(terre2_position); */
        /* bois */
        Bois bois = new Bois();
        bois.setApport(3);
        bois.setNom("Bois");
        Vector bois_position = new Vector<>();
        bois_position.add(new Point(1200,20));
        bois_position.add(new Point(1050, 100));
        bois_position.add(new Point(1040, 100));
        bois_position.add(new Point(1100,150));
        bois_position.add(new Point(1300,150));
        bois.setPoints(bois_position);
        ressources1.add(bois);
        //---------------------------------
        /* Bois bois2 = new Bois();
        bois2.setApport(100);
        bois2.setNom("Bois 2");
        Vector bois2_position = new Vector<>();
        bois2_position.add(new Point(500,20));
        bois2_position.add(new Point(250, 50));
        bois2_position.add(new Point(350, 100));
        bois2_position.add(new Point(200,150));
        bois2_position.add(new Point(500,150));
        bois2.setPoints(bois2_position); */
        /* maison */
        Batiment maison = new Maison();
        maison.setNom("MAISON 1");
        Vector maison_position = new Vector<>();
        maison_position.add(new Point(700, 300));
        maison_position.add(new Point(750, 250));
        maison_position.add(new Point(800, 300));
        maison_position.add(new Point(800, 400));
        maison_position.add(new Point(700, 400));
        maison.setPoints(maison_position);
        un.getBatiments().add(maison);
        //-------------------
        Batiment maison2 = new Maison();
        maison2.setNom("MAISON 2");
        Vector maison2_position = new Vector<>();
        maison2_position.add(new Point(300, 300));
        maison2_position.add(new Point(335, 275));
        maison2_position.add(new Point(370, 300));
        maison2_position.add(new Point(370, 370));
        maison2_position.add(new Point(300, 370));
        maison2.setPoints(maison2_position);
        deux.getBatiments().add(maison2);
        /* poste gendarme */
        Batiment poste = new Camp();
        poste.setNom("MILITAIRE 1");
        Vector poste_position = new Vector<>();
        poste_position.add(new Point(900, 400));
        poste_position.add(new Point(950, 350));
        poste_position.add(new Point(1000, 400));
        poste_position.add(new Point(1000, 500));
        poste_position.add(new Point(900, 500));
        poste.setPoints(poste_position);
        un.getBatiments().add(poste);   
        //---------------------------
        Batiment poste2 = new Camp();
        poste2.setNom("MILITAIRE 2");
        Vector poste2_position = new Vector<>();
        poste2_position.add(new Point(800, 100));
        poste2_position.add(new Point(850, 50));
        poste2_position.add(new Point(900, 100));
        poste2_position.add(new Point(900, 200));
        poste2_position.add(new Point(800, 200));
        poste2.setPoints(poste2_position);
        deux.getBatiments().add(poste2); 
        
        Batiment h1 = new Hopital(3, 3);
        h1.setNom("Hopital 1");
         Vector h1_position = new Vector<>();
        h1_position.add(new Point(50, 100));
        h1_position.add(new Point(100, 75));
        h1_position.add(new Point(200, 100));
        h1_position.add(new Point(200, 200));
        h1_position.add(new Point(50, 200));
        h1.setPoints(h1_position);
        un.setHopital( (Hopital) h1);
        Batiment h2 = new Hopital(3, 3);
        h2.setNom("Hopital 2");
        Vector h2_position = new Vector<>();
        h2_position.add(new Point(500, 100));
        h2_position.add(new Point(600, 150));
        h2_position.add(new Point(700, 100));
        h2_position.add(new Point(700, 100));
        h2_position.add(new Point(500, 100));
        h2.setPoints(h2_position);
        deux.setHopital( (Hopital) h2);

        terrain.getCivilisations().add(un);
        terrain.getCivilisations().add(deux);
        terrain.setRessources(ressources1);
       
        MaFenetre fenetre = new MaFenetre(terrain);

       //System.out.println(deux.getHopital().getNom());
      /*  Population[] population = new Population[2];
        population[0] = new Population();
        population[1] = new Population();
        population[1].setVie(1);
        population[0].setVie(0);
        population[0].setPosition(new Point(60, 60));
        population[1].setPosition(new Point(80, 60));
        population[0].setDestination(destination);
        population[1].setDestination(destination); */
       /* for (int i = 0; i < 2; i++) {
            Thread t = new Thread(population[i]);
            t.start();
       } */
       Cercle cercleExterieur = new Cercle(new Point(50, 50), 30); // Cercle extérieur
        Cercle cercleInterieur = new Cercle(new Point(50, 40), 10); // Cercle intérieur

        if (cercleExterieur.contains(cercleInterieur)) {
            System.out.println("Le cercle intérieur est contenu dans le cercle extérieur.");
        } else {
            System.out.println("Le cercle intérieur n'est pas contenu dans le cercle extérieur.");
        }
       

    }
    
}
