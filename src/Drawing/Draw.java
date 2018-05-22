package Drawing;

import javax.swing.JPanel;

import Tabuleiro.*;
import java.awt.*;
import java.awt.geom.*;
public class Draw extends JPanel{	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		Tabuleiro t;
		t=new Tabuleiro();
		t.Draw(g2d);
	}

}