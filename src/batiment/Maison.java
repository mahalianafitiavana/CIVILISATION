package batiment;
import java.awt.Color;
import java.awt.Graphics;
import civilisation.Civilisation;
import peuple.Population;

public class Maison  extends Batiment {
    @Override
    public Population birth(Civilisation origine){
        Population p = super.birth(origine);
        return p;
    }
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.decode("#dc732d"));
        super.paint(g);
    }
}
