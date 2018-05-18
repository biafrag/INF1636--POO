package Tabuleiro;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Peao extends Pecas {
	
	public static void Draw(Graphics2D a,double posX,double posY)
	{
		a.setColor(Color.pink);
		Rectangle2D rt=new Rectangle2D.Double(posX,posY,5,5);
		a.fill(rt);
	}

}
