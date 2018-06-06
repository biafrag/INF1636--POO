package Tabuleiro;

//import java.util.Observer;
//import java.util.Observable;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import Drawing.Cor;
import Interaction.Mouse;

public class Tabuleiro {
	
	/* Pretos
	 * Torre Cavalo Bispo Rei Rainha Bispo Cavalo Torre
	 * Peao ...									  Peao
	 * 
	 * 
	 * Brancos
	 * Peao ...									  Peao
	 * Torre Cavalo Bispo Rainha Rei Bispo Cavalo Torre
	 */
    private static Tabuleiro t;
	private static Celula tabuleiro[][];
	private static int alt;
	private static int larg;
	private static Vector<Pair> _possiblePositions;
	private static Vector<Pair> _possibleEats;
	private static boolean jogador=true;
	private static boolean peaochange=false;
	private Tabuleiro() 
	{
		double posX=0;
		double posY=0;
		larg=Celula.getLarg();
		alt=Celula.getAlt();
		Cor c=Cor.Claro;
		tabuleiro=new Celula[8][8];
		for(int i=0;i<8;i++)
		{
			for(int j=0; j<8;j++)
			{
				tabuleiro[i][j]=new Celula();
				
			}
		}
		setPeca();
		for(int i=0;i<8;i++)
		{
			posX=0;
			for(int j=0; j<8;j++)
			{
				tabuleiro[i][j].setColor(c);
				tabuleiro[i][j].setPosition(posX, posY);
				if(c==Cor.Claro)
				{
					c=Cor.Escuro;
				}
				else
				{
					c=Cor.Claro;
				}
				posX+=larg;
			}
			posY+=alt;
			if(c==Cor.Claro)
			{
				c=Cor.Escuro;
			}
			else
			{
			c=Cor.Claro;
		}
		}
		
	}
	public static Tabuleiro getTabuleiro()
	{
		if(t==null)
		{
			t= new Tabuleiro();
		}
		return t;
	}
	public void Draw(Graphics2D g)
	{
		for(int i=0;i<8;i++)
		{
			for(int j=0; j<8;j++)
			{
				tabuleiro[i][j].draw(g);
			}
		}
	}
	public Celula getCelula (int i,int j)
	{
		return tabuleiro[i][j];
	}
	private void  setPeca()
	{
		//c é claro e e é escuro
		Peca Bc,Be,Cc,Ce,Pe,Pc,Qe,Qc,Ke,Kc,Te,Tc;
		Bc=new Bispo(Cor.Claro);
		Be=new Bispo(Cor.Escuro);
		Cc=new Cavalo(Cor.Claro);
		Ce=new Cavalo(Cor.Escuro);
		Pc=new Peao(Cor.Claro);
		Pe=new Peao(Cor.Escuro);
		Qc=new Rainha(Cor.Claro);
		Qe=new Rainha(Cor.Escuro);
		Kc=new Rei(Cor.Claro);
		Ke=new Rei(Cor.Escuro);
		Tc=new Torre(Cor.Claro);
		Te=new Torre(Cor.Escuro);
		
		//Escuras
		tabuleiro[0][0].setPeca(Te);
		tabuleiro[0][1].setPeca(Ce);
		tabuleiro[0][2].setPeca(Be);
		tabuleiro[0][3].setPeca(Ke);
		tabuleiro[0][4].setPeca(Qe);
		tabuleiro[0][5].setPeca(Be);
		tabuleiro[0][6].setPeca(Ce);
		tabuleiro[0][7].setPeca(Te);
		tabuleiro[1][0].setPeca(Pe);
		tabuleiro[1][1].setPeca(Pe);
		tabuleiro[1][2].setPeca(Pe);
		tabuleiro[1][3].setPeca(Pe);
		tabuleiro[1][4].setPeca(Pe);
		tabuleiro[1][5].setPeca(Pe);
		tabuleiro[1][6].setPeca(Pe);
		tabuleiro[1][7].setPeca(Pe);
		
		//Claras
		tabuleiro[6][0].setPeca(Pc);
		tabuleiro[6][1].setPeca(Pc);
		tabuleiro[6][2].setPeca(Pc);
		tabuleiro[6][3].setPeca(Pc);
		tabuleiro[6][4].setPeca(Pc);
		tabuleiro[6][5].setPeca(Pc);
		tabuleiro[6][6].setPeca(Pc);
		tabuleiro[6][7].setPeca(Pc);
		tabuleiro[7][0].setPeca(Tc);
		tabuleiro[7][1].setPeca(Cc);
		tabuleiro[7][2].setPeca(Bc);
		tabuleiro[7][3].setPeca(Kc);
		tabuleiro[7][4].setPeca(Qc);
		tabuleiro[7][5].setPeca(Bc);
		tabuleiro[7][6].setPeca(Cc);
		tabuleiro[7][7].setPeca(Tc);
	}
	
	public static boolean TemPeca(int x, int y)
	{
		int i,j;
		Peca p;
		i=Math.floorDiv(y, alt); 
		j=Math.floorDiv(x, larg);
		p = tabuleiro[i][j].getPeca();
		if (p == null)
			return false;
		return true;	
	}
	public static boolean TemPecaIndice(int i, int j)
	{
		if (tabuleiro[i][j].getPeca() == null)
			return false;
		return true;	
	}	
	public static void MexePeca(int x1,int y1,int x2,int y2)
	{
		int i1,i2,j1,j2;
		i1=y1/alt;
		j1=x1/larg;
		i2=y2/alt;
		j2=x2/larg;
		Vector<Pair> positions;
		i1=Math.floorDiv((y1 - 40),alt);
		j1=Math.floorDiv(x1,larg);
		i2=Math.floorDiv((y2 - 40),alt);
		j2=Math.floorDiv(x2,larg);
		
		if(jogador && tabuleiro[i1][j1].getPeca().getCor() == Cor.Escuro)
		{
			return;
		}
		else if (!jogador && tabuleiro[i1][j1].getPeca().getCor() == Cor.Claro)
		{
			return;
		}
				
		System.out.println("De "+ y1+ " "+i1+" "+j1);
		System.out.println("Para "+ y2+ " "+i2+" "+j2);
		System.out.println("joga: " + jogador);		
		
		positions=tabuleiro[i1][j1].getPositions();
		for(int i=0;i<positions.size();i++)
		{
			if(positions.get(i).getX()==i2 && positions.get(i).getY()==j2)
			{
				tabuleiro[i2][j2].setPeca(tabuleiro[i1][j1].getPeca());
				tabuleiro[i1][j1].setPeca(null);
				if (jogador)
					jogador = false;
				else
					jogador = true;
				System.out.println("joga: " + jogador);
				break;
			}
		}		
		Peca p = tabuleiro[i2][j2].getPeca();
		if(p instanceof Peao && ((i2==0 && p.getCor()==Cor.Claro) || (i2==7 && p.getCor()==Cor.Escuro)))
		{
			peaochange=true;
		}	
	}
	public static void Acende(int x, int y)
	{
		int i=Math.floorDiv(y,alt);
		int j=Math.floorDiv(x,larg);
		System.out.println(i +" "+ j);
		tabuleiro[i][j].setSelect();
	}
	public static void CatchPossibleMoves(int x, int y)
	{
		int i=Math.floorDiv(y,alt);
		int j=Math.floorDiv(x,larg);
		System.out.println(i +" "+ j);
		 _possiblePositions=tabuleiro[i][j].catchMoves(x,y);
		if( _possiblePositions!=null)
		{
			for(int n=0;n< _possiblePositions.size();n++)
			{
				if(jogador && tabuleiro[i][j].getPeca().getCor() == Cor.Escuro)
					break;
				else if (!jogador && tabuleiro[i][j].getPeca().getCor() == Cor.Claro)
					break;
				tabuleiro[ _possiblePositions.get(n).getX()][ _possiblePositions.get(n).getY()].setPossibleMove();
			}
		}
	}
	
	public static void CatchPossibleEats(int x, int y)
	{
		int i=Math.floorDiv(y,alt);
		int j=Math.floorDiv(x,larg);
		System.out.println(i +" "+ j);
		 _possibleEats=tabuleiro[i][j].catchEats(x,y);
		if( _possibleEats!=null)
		{
			for(int n=0;n< _possibleEats.size();n++)
			{
				if(jogador && tabuleiro[i][j].getPeca().getCor() == Cor.Escuro)
					break;
				else if (!jogador && tabuleiro[i][j].getPeca().getCor() == Cor.Claro)
					break;
				tabuleiro[ _possibleEats.get(n).getX()][ _possibleEats.get(n).getY()].setPossibleEats();
			}
		}
	}
	
	public static void ComePeca(int x1, int y1, int x2, int y2)
	{
		int i1,i2,j1,j2;
		i1=y1/alt;
		j1=x1/larg;
		i2=y2/alt;
		j2=x2/larg;
		Vector<Pair> eats;
		i1=Math.floorDiv((y1 - 40),alt);
		j1=Math.floorDiv(x1,larg);
		i2=Math.floorDiv((y2 - 40),alt);
		j2=Math.floorDiv(x2,larg);
		
		if(jogador && tabuleiro[i1][j1].getPeca().getCor() == Cor.Escuro)
		{
			return;
		}
		else if (!jogador && tabuleiro[i1][j1].getPeca().getCor() == Cor.Claro)
		{
			return;
		}
		
		System.out.println(y1+ " "+i1+" "+j1);
		System.out.println("come: " + jogador);
		eats=tabuleiro[i1][j1].getEats();
		for(int k=0;k<eats.size();k++)
		{
			if(eats.get(k).getX()==i2 && eats.get(k).getY()==j2)
			{
				System.out.println("Come "+ y2+ " "+i2+" "+j2);
				tabuleiro[i2][j2].setPeca(tabuleiro[i1][j1].getPeca());
				tabuleiro[i1][j1].setPeca(null);
				if (jogador)
					jogador = false;
				else
					jogador = true;
				System.out.println("come: " + jogador);
				break;
			}
		}
		Peca p = tabuleiro[i2][j2].getPeca();
		if(p instanceof Peao && ((i2==0 && p.getCor()==Cor.Claro) || (i2==7 && p.getCor()==Cor.Escuro)))
		{
			peaochange=true;
		}	
	}
	public static JPopupMenu CriaPopup(int x, int y) {
		JPopupMenu popup = new JPopupMenu();					

		JMenuItem menuItem = new JMenuItem("Rainha");
		menuItem.addActionListener(new ActionListener() {	 
			public void actionPerformed(ActionEvent e) {	
				Peca p;
				int i,j;
				i=y/alt;
				j=x/larg;
				i=Math.floorDiv((y - 40),alt);
				j=Math.floorDiv(x,larg);				
				if (tabuleiro[i][j].getPeca().getCor() == Cor.Claro) {
					p = new Rainha(Cor.Claro);
				}
				else {
					p = new Rainha(Cor.Escuro);
				}		
				tabuleiro[i][j].setPeca(null);
				tabuleiro[i][j].setPeca(p);
			}
		});
		popup.add(menuItem);     
		
		menuItem = new JMenuItem("Torre");
		menuItem.addActionListener(new ActionListener() {	 
			public void actionPerformed(ActionEvent e) {
				Peca p;
				int i,j;
				i=y/alt;
				j=x/larg;
				i=Math.floorDiv((y - 40),alt);
				j=Math.floorDiv(x,larg);
				if (tabuleiro[i][j].getPeca().getCor() == Cor.Claro) {
					p = new Torre(Cor.Claro);
				}
				else {
					p = new Torre(Cor.Escuro);
				}						
				tabuleiro[i][j].setPeca(null);
				tabuleiro[i][j].setPeca(p);
			}
		});
		popup.add(menuItem);  
		
		menuItem = new JMenuItem("Bispo");
		menuItem.addActionListener(new ActionListener() {	 
			public void actionPerformed(ActionEvent e) {
				Peca p;
				int i,j;
				i=y/alt;
				j=x/larg;
				i=Math.floorDiv((y - 40),alt);
				j=Math.floorDiv(x,larg);
				if (tabuleiro[i][j].getPeca().getCor() == Cor.Claro) {
					p = new Bispo(Cor.Claro);
				}
				else {
					p = new Bispo(Cor.Escuro);
				}		
				tabuleiro[i][j].setPeca(null);
				tabuleiro[i][j].setPeca(p);
			}
		});
		popup.add(menuItem);  
		
		menuItem = new JMenuItem("Cavalo");
		menuItem.addActionListener(new ActionListener() {	 
			public void actionPerformed(ActionEvent e) {
				Peca p;
				int i,j;
				i=y/alt;
				j=x/larg;
				i=Math.floorDiv((y - 40),alt);
				j=Math.floorDiv(x,larg);
				if (tabuleiro[i][j].getPeca().getCor() == Cor.Claro) {
					p = new Cavalo(Cor.Claro);
				}
				else {
					p = new Cavalo(Cor.Escuro);
				}		
				tabuleiro[i][j].setPeca(null);
				tabuleiro[i][j].setPeca(p);
			}
		});
		popup.add(menuItem);  
		return popup;
	}
	public static boolean getPeaoChange()
	{
		return peaochange;
	}
	public static void setPeaoChange(boolean a)
	{
		peaochange=a;
	}
}
