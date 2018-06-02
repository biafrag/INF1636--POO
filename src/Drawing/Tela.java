package Drawing;
import java.awt.Insets;

//import javax.swing.*;
import javax.swing.JFrame;

public class Tela extends JFrame {
	
	public final int LARG_DEFAULT=1000;
	public final int ALT_DEFAULT=800;
	
	public Tela() {
		Insets i = getInsets();
		setSize(LARG_DEFAULT,ALT_DEFAULT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setResizable(false);
		setTitle("Chess Game");
		//Teste de desenhar alguma coisa na tela
		//JLabel teste;
		setVisible(true);
	
	}
}
