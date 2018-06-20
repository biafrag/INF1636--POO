package Interaction;

import java.awt.event.*;

import Tabuleiro.TabuleiroFacade;

public class Mouse implements MouseListener {

	int x1,y1,x2,y2;
	private boolean mousepress=false;
	private static Mouse mouse;
	private TabuleiroFacade t;
	private boolean firsttime=true;

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
		
	}
	public void mouseExited(MouseEvent e)
	{
	}
	public void mousePressed(MouseEvent e)
	{
		if(firsttime==true)
		{
			t = TabuleiroFacade.getTFacade();
			firsttime=false;
		}
		if(e.getButton()==MouseEvent.BUTTON1)
		{
			if(mousepress==false)
			{
				x1=e.getX();
				y1=e.getY();
				t.VerificaMovimentosPossiveis(x1, y1);
				mousepress=true;
			}
			else
			{
				x2=e.getX();
				y2=e.getY();
				mousepress=false;
				t.RealizaJogada(x1, y1, x2, y2);
			}
			t.PromovePeao(x2, y2, e.getComponent(), e.getX(), e.getY());
		}
		if(e.getButton()==MouseEvent.BUTTON3)
		{
			t.SalvaJogo(e.getComponent(), e.getX(), e.getY());
		}		
	}
	public void mouseReleased(MouseEvent e)
	{

	}	
	 
}
