package ressources;

import java.awt.Color;
import java.awt.Graphics;

public class Bois extends Ressource {
    public void paint (Graphics g){
        g.setColor( Color.decode("#014303"));
        super.paint(g);
    }
}
