package Interaction;

//import java.util.Observer;
import java.util.Observable;

import javax.swing.JPopupMenu;

import java.awt.event.*;

import Tabuleiro.Tabuleiro;

public class Mouse extends Observable implements MouseListener {

	int x1,y1,x2,y2;
	boolean mousepress=false;
	private static Mouse mouse;
	private Tabuleiro t;
	boolean atualizationpeao=false;

	private Mouse()
	{
	}
	public static Mouse getMouse()
	{
		if(mouse==null)
		{
			mouse=new Mouse();
		}
		return mouse;
	}
	public void mouseClicked(MouseEvent e)
	{


	}
	
	public void mouseEntered(MouseEvent e)
	{
		if(atualizationpeao==true)
		{
			this.setChanged();
			this.notifyObservers();
			atualizationpeao=false;
			
		}
		
	}
	public void mouseExited(MouseEvent e)
	{
		
	}
	public void mousePressed(MouseEvent e)
	{
		System.out.println("Posicao X "+e.getX());
		System.out.println("Posicao Y "+e.getY());
		if(mousepress==false)
		{
			System.out.println("click1");
			x1=e.getX()+40;
			y1=e.getY()+40;
			if (Tabuleiro.TemPeca(x1, y1) == false) 
			{
				System.out.println("nao tem peca no 1 click");
				return;
			}
			else
			{
				Tabuleiro.Acende(x1, y1);	
				Tabuleiro.CatchPossibleMoves(x1, y1);
				Tabuleiro.CatchPossibleEats(x1, y1);
				this.setChanged();	
				notifyObservers();
			}
			mousepress=true;
		}
		else
		{
			System.out.println("click2");
			x2=e.getX()+40;
			y2=e.getY()+40;
			mousepress=false;
			if (Tabuleiro.TemPeca(x2, y2) == false)
			{
				Tabuleiro.MexePeca(x1,y1,x2,y2);
				this.setChanged();
				notifyObservers();
			}	
			else 
			{
				Tabuleiro.ComePeca(x1,y1,x2,y2);
				this.setChanged();
				notifyObservers();
			}		
				
		}
		if(Tabuleiro.getPeaoChange()==true)
		{
			/*Tabuleiro.ComePeca(x1,y1,x2,y2); */
			this.setChanged(); 
			notifyObservers();
			JPopupMenu popup = Tabuleiro.CriaPopup(x2,y2);
			popup.show(e.getComponent(),e.getX(), e.getY()); 	
			this.setChanged(); 
			notifyObservers();
			Tabuleiro.setPeaoChange(false);
			atualizationpeao=true;			
		}
		
	}
	public void mouseReleased(MouseEvent e)
	{

	}	 
}
