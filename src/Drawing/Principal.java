package Drawing;
import javax.swing.*;

public class Principal  {

	public static void main(String[] args) {
		Tela f=new Tela();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Draw panel = new Draw();
        f.add(panel);
		}

}
