package Drawing;

import javax.swing.*;

public class Principal  {
	
	public static void main(String[] args) {
		Tela f=Tela.getInstanceTela();
		f.setResizable(false);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(null);
        Draw panel = new Draw();
		panel.setLocation(0,0);
		panel.setSize(1000,800);
        f.add(panel);              
        
	}
	
}
