package Drawing;

import javax.swing.JPanel;
import java.awt.*;
import java.util.Observer;
import java.util.Observable;

import Tabuleiro.Tabuleiro;
import Interaction.Mouse;

public class Draw extends JPanel implements Observer{
	Tabuleiro t;
	Mouse m;
	public Draw()
	{
		Mouse m=Mouse.getMouse();
		m.addObserver(this);
		this.addMouseListener(m);
	}
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		t=Tabuleiro.getTabuleiro();
		t.Draw(g2d);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		System.out.println("Atualizouuuuu");
		this.repaint();
	}

}