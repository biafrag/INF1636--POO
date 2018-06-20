package Drawing;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Tabuleiro.TabuleiroFacade;

public class Tela extends JFrame implements ActionListener {
	
	public final int LARG_DEFAULT=1000;
	public final int ALT_DEFAULT=800;
	private static Tela tela;
	private JMenuBar barraMenu; //barra de menu
	private JMenu Menu; //itens principais da barra de menu
	private JMenuItem Novo, Carregar, Sair; //subitens dos menu
	
	private Tela() {
		Insets i = getInsets();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Chess Game");
		criaMenu();
		setSize(LARG_DEFAULT,ALT_DEFAULT + 20);
		setLocationRelativeTo(null);
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
	private void criaMenu () {
		//itens do menu ARQUIVO
		this.Novo = new JMenuItem ("Novo Jogo");
		this.Novo.addActionListener(this);
		this.Novo.setMnemonic('n'); 

		this.Carregar = new JMenuItem ("Carregar Jogo");
		this.Carregar.addActionListener(this);
		this.Carregar.setMnemonic('c'); 

		this.Sair = new JMenuItem ("Sair");
		this.Sair.addActionListener(this);
		this.Sair.setMnemonic('s');

		//Item do da barra de Menu
		this.Menu= new JMenu ("Menu");
		this.Menu.setMnemonic('M');
		this.Menu.addActionListener(this);
		this.Menu.add(this.Novo);
		this.Menu.add(this.Carregar);
		this.Menu.add(this.Sair);

		//Barra de Menu
		this.barraMenu = new JMenuBar();
		setJMenuBar(barraMenu);
		this.barraMenu.add(this.Menu);
	}
	public void actionPerformed(ActionEvent e) {
		eventoMenu(e);
	}
	public void eventoMenu (ActionEvent e) {
		if (e.getSource() == this.Novo){
			TabuleiroFacade.getTFacade().RecomecaJogo();
		}
		else if (e.getSource() == this.Carregar){
			TabuleiroFacade.getTFacade().CarregaJogo();			
		}
		else if (e.getSource() == this.Sair){
			System.exit(0);
		}
	}
}
