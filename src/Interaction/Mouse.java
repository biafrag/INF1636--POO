package Interaction;

//import java.util.Observer;
import java.util.Observable;
import java.awt.event.*;

import Tabuleiro.Tabuleiro;

public class Mouse extends Observable implements MouseListener {

	int x1,y1,x2,y2;
	boolean mousepress=false;
	
	public void mouseClicked(MouseEvent e)
	{
		if(mousepress==false)
		{
			x1=e.getX();
			y1=e.getY();
			if (Tabuleiro.TemPeca(x1, y1) == 0) 
			{
				System.out.println("nao tem peca no 1 click");
				return;
			}
			mousepress=true;
		}
		else
		{
			x2=e.getX();
			y2=e.getY();
			mousepress=false;
			if (Tabuleiro.TemPeca(x2, y2) == 0)
			{
				System.out.println("nao tem peca no 2 click");
				Tabuleiro.MexePeca(x1, y1,x2,y2);
				this.notifyObservers();
			}	
			else
				System.out.println("tem peca no 2 click");
		}
	}
	public void mouseEntered(MouseEvent e)
	{
		
	}
	public void mouseExited(MouseEvent e)
	{
		
	}
	public void mousePressed(MouseEvent e)
	{
		
	}
	public void mouseReleased(MouseEvent e)
	{
		
	}

}
