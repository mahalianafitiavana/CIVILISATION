package ressources;

import java.awt.Color;
import java.awt.Graphics;

public class Terre extends Ressource {
    public void paint (Graphics g){
        g.setColor(Color.decode("#913f13"));
        super.paint(g);
    }
}
