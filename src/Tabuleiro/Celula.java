package Tabuleiro;
import java.awt.BasicStroke;
import javafx.util.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import Drawing.Cor;
public class Celula {

	private Cor cor;
	private Peca p;
	private double posX, posY;
	private static int larg=1000/8;
	private static int alt=760/8;
	boolean select = false;
	final static BasicStroke Contorno = new BasicStroke(6.0f);
	Pair<Integer, Integer>[][] positions;
	public Celula()
	{
		posX=0;
		posY=0;
	}
	public void setColor(Cor c)
	{
		cor=c;
	}
	public void setPosition(double x,double y)
	{
		posX=x;
		posY=y;
	}
	public void setPeca(Peca nova)
	{
		p=nova;
	}
	public Cor getColor()
	{
		return cor;
	}
	public void draw(Graphics2D g)
	{
		float color1 []=Color.RGBtoHSB(120,80,39, null);
		Color Color1=Color.getHSBColor(color1[0], color1[1], color1[2]);
		float color2 []=Color.RGBtoHSB(63,42,20, null);
		Color Color2=Color.getHSBColor(color2[0], color2[1], color2[2]);
		Rectangle2D rt=new Rectangle2D.Double(posX+1,posY+1,larg+3,alt+3);
		if(cor==Cor.Claro)
		{
			g.setPaint(Color1);
		}
		else
		{
			g.setPaint(Color2);
		}
		g.fill(rt);
		if(p!=null)
		{
			p.Draw(g, (int)posX+(larg/4), (int)posY);
			if(select == true)
			{
				Rectangle2D r2=new Rectangle2D.Double(posX,posY,larg,alt);
				g.setPaint(Color.BLUE);
				g.setStroke(Contorno);
				g.draw(r2);
//				for(int i=0; i<positions.length;i++)
//				{
//					Rectangle2D r1=new Rectangle2D.Double(posX,posY,larg,alt);
//					g.setPaint(Color.RED);
//					g.setStroke(Contorno);
//					g.draw(r1);
//				}
				select=false;
			}
			
		}
		
	}
	public static int getLarg()
	{
		return larg;
	}
	public static int getAlt()
	{
		return alt;
	}
	public Peca getPeca()
	{
		return p;
	}
	public void setSelect()
	{
		select=true;
	}
	public void catchMoves()
	{
		positions=new Pair[8][8];
		positions=this.getPeca().CatchPossibleMovements();
	}
}
