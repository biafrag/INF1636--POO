package Drawing;
import javax.swing.*;
import Interaction.Mouse;
public class Principal  {

	public static void main(String[] args) {
		Tela f=new Tela();
		Mouse m=Mouse.getMouse();
		f.setResizable(false);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(null);
        Draw panel = new Draw();
		m.addObserver(panel);
		panel.setLocation(0,0);
		panel.setSize(1000, 800);
		panel.addMouseListener(m);
        f.add(panel);
	}

}
