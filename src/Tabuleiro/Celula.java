package Tabuleiro;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import Tabuleiro.Pecas.Peca;
import Tools.Cor;
import Tools.Pair;
public class Celula {

	private Cor cor;
	private Peca p;
	private double posX, posY;
	private static int larg=1000/8;
	private static int alt=760/8;
	boolean select = false;
	boolean ispossiblemove=false;
	boolean ispossibleeat=false;
	private Vector<Pair> positions;
	private Vector<Pair> eats;
	final static BasicStroke Contorno = new BasicStroke(6.0f);
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
		Color Color1=new Color(120,80,39);
		Color Color2=new Color(63,42,20);
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
			if(ispossibleeat==true)
			{
				Rectangle2D r2=new Rectangle2D.Double(posX,posY,larg,alt);
//				g.setPaint(Color.RED);
//				g.setStroke(Contorno);
//				g.draw(r2);
				g.setPaint(new Color(255,0,0,80));
				g.fill(rt);
				g.draw(r2);
				ispossibleeat=false;
			}
			if(select == true)
			{
				Rectangle2D r2=new Rectangle2D.Double(posX,posY,larg,alt);
//				g.setPaint(Color.YELLOW);
//				g.setStroke(Contorno);
//				g.draw(r2);
				g.setPaint(new Color(255,255,0,80));
				g.fill(rt);
				g.draw(r2);
				select=false;
			}
			p.Draw(g, (int)posX+(larg/4), (int)posY);
			
		}
		else
		{
			if(this.ispossiblemove==true)
			{
				Rectangle2D r2=new Rectangle2D.Double(posX,posY,larg,alt);
//				g.setPaint(Color.WHITE);
//				g.setStroke(Contorno);
//				g.draw(r2);
				Color Color3=new Color(255,255,255,80);
				g.setPaint(Color3);
				g.fill(rt);
				g.draw(r2);
				ispossiblemove=false;
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
	public Vector<Pair> catchMoves(int x,int y)
	{
		positions=this.getPeca().CatchPossibleMovements(x,y);
		return positions;
	}
	public Vector<Pair> catchEats(int x,int y)
	{
		eats=this.getPeca().PossibleEats(x, y);
		return eats;
	}
	public void setPossibleMove()
	{
		this.ispossiblemove=true;
	}
	
	public void setPossibleEats()
	{
		this.ispossibleeat=true;
	}
	public Vector<Pair> getPositions()
	{
		return positions;
	}
	public Vector<Pair> getEats()
	{
		return eats;
	}
}
