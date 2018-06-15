package Drawing;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Tabuleiro.Tabuleiro;

public class Tela extends JFrame implements ActionListener {
	
	public final int LARG_DEFAULT=1000;
	public final int ALT_DEFAULT=800;
	private static Tela tela;
	private JMenuBar barraMenu; //barra de menu
	private JMenu Menu; //itens principais da barra de menu
	private JMenuItem Novo, Carregar, Sair; //subitens dos menu
	
	private Tela() {
		Insets i = getInsets();
		setSize(LARG_DEFAULT,ALT_DEFAULT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setResizable(false);
		setTitle("Chess Game");
		//Teste de desenhar alguma coisa na tela
		//JLabel teste;
		criaMenu();
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
		this.Novo.setMnemonic('n'); //PESQUISAR

		this.Carregar = new JMenuItem ("Carregar Jogo");
		this.Carregar.addActionListener(this);
		this.Carregar.setMnemonic('c'); //PESQUISAR

		this.Sair = new JMenuItem ("Sair");
		this.Sair.addActionListener(this);
		this.Sair.setMnemonic('s');

		//Item do da barra de Menu
		this.Menu= new JMenu ("Arquivo");
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
			Tabuleiro.getTabuleiro().Recomeca();
		}
		if (e.getSource() == this.Carregar){
			try {
				Tabuleiro.getTabuleiro().Load();
			} catch (FileNotFoundException er) {
				// TODO Auto-generated catch block
				er.printStackTrace();
			}
			
		}
		if (e.getSource() == this.Sair){
			System.exit(0);
		}
	}
}
