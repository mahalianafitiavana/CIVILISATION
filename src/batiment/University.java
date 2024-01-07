package batiment;

import java.awt.Color;
import java.awt.Graphics;
import civilisation.Civilisation;
import peuple.Population;
import peuple.Professeur;

public class University extends Batiment  {
    @Override
    public Population birth(Civilisation origine){
        Population p = super.birth(origine);
        Professeur m = new Professeur(p);
        origine.setRessource(origine.getRessource()-m.getVie()+p.getVie());
        return m;
    }
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.decode("#7ca1de"));
        super.paint(g);
    }
}
