package Interaction;
import Tabuleiro.Tabuleiro;
import java.awt.event.*;
public class Mouse implements MouseListener {

	int x1,y1,x2,y2;
	boolean mousepress=false;
	public void mouseClicked(MouseEvent e)
	{
		if(mousepress==false)
		{
			x1=e.getX();
			y1=e.getY();
			mousepress=true;
		}
		else
		{
			x2=e.getX();
			y2=e.getY();
			mousepress=false;
			Tabuleiro.MexePeca(x1, y1,x2,y2);
			
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
