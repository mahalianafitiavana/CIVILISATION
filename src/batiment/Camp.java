package batiment;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import civilisation.Civilisation;
import peuple.Militaire;
import peuple.Population;

public class Camp extends Batiment  {
    @Override
    public Population birth(Civilisation origine){
        Population p = super.birth(origine);
        Militaire m = new Militaire(p);
        origine.setRessource(origine.getRessource()-m.getVie()+p.getVie());
        return m;
    }
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.decode("#2e893f"));
        super.paint(g);
    }    
}
