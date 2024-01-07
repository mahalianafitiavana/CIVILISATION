package listener;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import batiment.Batiment;
import batiment.Hopital;
import civilisation.Civilisation;
import file.Writer;
import peuple.Population;
import terrain.MaFenetre;
public class Mouvement implements MouseListener {
    MaFenetre  fenetre;    
    public MaFenetre getFenetre() {
        return fenetre;
    }
    public void setFenetre(MaFenetre fenetre) {
        this.fenetre = fenetre;
    }
    public Mouvement (MaFenetre t){
        setFenetre(t);
    }
    @Override
    public void mouseClicked(MouseEvent e)   {
        /* suelement apres avoir indique une direction qu on verifie le point de depart  */
        Point destination = new Point();
        Point init = new Point();
        Writer w = new Writer();
        try {
            /* destination */
            if (e.getButton() == MouseEvent.BUTTON1) {
                destination = new Point(e.getX(), e.getY()-30);
                w.writeJson("destination", destination);
                Vector liste  = w.readJson("depart"); //liste des departs
                if (!liste.isEmpty()) {
                    init = (Point) liste.lastElement();  /* pont de depart */
                    int o = this.getFenetre().getTerrain().getPeople(init); /* indice de la population dans le terrain */
                    if (getFenetre().getTerrain().isInHopital(init) != null && o!= -1) {
                        Hopital h = getFenetre().getTerrain().isInHopital(init);
                        h.getMalades().remove(this.getFenetre().getTerrain().all_people().get(o));
                    }
                    /* set destination */
                    if ( o != -1) {
                        this.getFenetre().getTerrain().all_people().get(o).setDestination(destination);
                    }
                }
                System.out.println("destination :  "+destination); 
            }
            /* depart */
            if (e.getButton() == MouseEvent.BUTTON3) {
                Object[ ] o = this.getFenetre().getTerrain().clickedBatiment(e.getX(), e.getY()-30);
                if (o != null) {
                    System.out.println("maison   ");
                    Batiment origine = (Batiment) o[0];
                    Civilisation c = (Civilisation) o[1];
                    Population p = origine.birth(c); // le olone creer a partire de la position du batiment
                    System.out.println(p.getPosition());
                    c.getPopulation().add(p);
                    this.getFenetre().getTerrain().repaint();
                }else{
                    /* si ce n est pas un batiment */
                    init = new Point(e.getX(), e.getY()-30);
                    w.writeJson("depart", init);
                    System.out.println("depart:   "+init);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

}
