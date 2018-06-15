package Drawing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.*;

import Tabuleiro.Tabuleiro;

public class Interface extends JFrame implements ActionListener {
	public final int LARG_DEFAULT=1000;
	public final int ALT_DEFAULT=800;
	private JMenuBar barraMenu; //barra de menu
	private JMenu Menu; //itens principais da barra de menu
	private JMenuItem Novo, Carregar, Sair; //subitens dos menu
	private JPanel painelInicio; //painel que será mostrado na tela de abertura do jogo
//	private JPanel painelJogo; //painel do jogo
	private Tela f;
	
	public Interface () {
	///	super ("Chess Game");
		
		criaMenu ();
	//	this.painelJogo.setVisible(false);
		fazPainelInicial ();
	}
/*	
	private void configuraJFrame () {
		setSize (LARG_DEFAULT,ALT_DEFAULT);
	//	setResizable (false);
		setLocationRelativeTo(null);
	//	getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Chess Game");
		setVisible(true);	
	}	*/
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
	private void fazPainelInicial () {
		this.painelInicio = new JPanel ();
		this.painelInicio.setSize(LARG_DEFAULT,ALT_DEFAULT);
	//	this.painelInicio.setLocation (280,200);
		//*/
	//	f=Tela.getInstanceTela();
	//	f.setResizable(false);
	//	f.setVisible(true);
	//	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	f.setLayout(null);
		
	//	getContentPane().add(this.painelInicio);		
	}
	public void actionPerformed(ActionEvent e) {
		eventoMenu(e);
	}
	public void eventoMenu (ActionEvent e) {
		if (e.getSource() == this.Novo){
			Draw panel = new Draw();
			panel.setLocation(0,0);
			panel.setSize(1000, 800);
	      //  f.add(panel);            
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