package Drawing;

import javax.swing.JPanel;
import java.awt.*;
import java.util.Observer;
import java.util.Observable;

import Tabuleiro.Tabuleiro;
import Interaction.Mouse;

public class Draw extends JPanel implements Observer{
	private Tabuleiro t;
	private boolean firsttime;
	private Mouse m;
	public Draw()
	{
		firsttime=false;
		Mouse m=Mouse.getMouse();
		m.addObserver(this);
		this.addMouseListener(m);
	}
	public void paintComponent(Graphics g) 
	{
		if(firsttime==false)
		{
			t=Tabuleiro.getTabuleiro();
			t.addObserver(this);
			firsttime=true;
		}
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		t.Draw(g2d);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		System.out.println("Atualizouuuuu");
		this.repaint();
	}

}