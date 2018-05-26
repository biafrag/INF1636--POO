package Tabuleiro;
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
	public Celula()
	{
		posX=0;
		posY=0;
	}
	public Celula(double x,double y)
	{
		posX=x;
		posY=y;
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
		Rectangle2D rt=new Rectangle2D.Double(posX,posY,larg,alt);
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
			p.Draw(g, posX+(larg/4), posY);
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
}
