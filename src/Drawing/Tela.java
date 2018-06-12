package Drawing;
import java.awt.Insets;

//import javax.swing.*;
import javax.swing.JFrame;

import Tabuleiro.Tabuleiro;

public class Tela extends JFrame {
	
	public final int LARG_DEFAULT=1000;
	public final int ALT_DEFAULT=800;
	private static Tela tela;
	
	private Tela() {
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
	public static Tela getInstanceTela()
	{
		if(tela==null)
		{
			tela=new Tela();
		}
		return tela;
	}
}
