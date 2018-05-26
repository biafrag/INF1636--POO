package Drawing;
import javax.swing.*;
import Interaction.Mouse;
public class Principal  {

	public static void main(String[] args) {
		Tela f=new Tela();
		Mouse m=new Mouse();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Draw panel = new Draw();
        panel.addMouseListener(m);
        //f.addMouseListener(m); ver qual eh o mais certo
        f.add(panel);
		}

}
