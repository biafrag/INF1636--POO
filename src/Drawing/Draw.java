package Drawing;

import javax.swing.JPanel;
import java.awt.*;
import java.util.Observer;
import java.util.Observable;

import Tabuleiro.Tabuleiro;
import Interaction.Mouse;

public class Draw extends JPanel implements Observer{
	Tabuleiro t;
	boolean check;
	Mouse m;
	public Draw()
	{
		Mouse m=new Mouse();
		m.addObserver(this);
		check=false;
	}
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		if(check==false)
		{
			t=new Tabuleiro();
			check=true;
			
		}
		t.Draw(g2d);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		this.repaint();
	}

}