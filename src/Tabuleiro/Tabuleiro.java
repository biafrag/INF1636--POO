package Tabuleiro;
import java.util.Observable;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.Scanner;
import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;

import Tabuleiro.Pecas.Bispo;
import Tabuleiro.Pecas.Cavalo;
import Tabuleiro.Pecas.Peao;
import Tabuleiro.Pecas.Peca;
import Tabuleiro.Pecas.Rainha;
import Tabuleiro.Pecas.Rei;
import Tabuleiro.Pecas.Torre;
import Tools.Cor;
import Tools.Pair;
import Visual.Tela;

/* Essa classe implementa um tabuleiro de xadrez.
 *  É um Singleton e um Observable que será observado pelo JPanel */

public class Tabuleiro extends Observable implements ActionListener{

	/* Pretos
	 * Torre Cavalo Bispo Rei Rainha Bispo Cavalo Torre
	 * Peao ...									  Peao
	 * 
	 * 
	 * Brancos
	 * Peao ...									  Peao
	 * Torre Cavalo Bispo Rainha Rei Bispo Cavalo Torre
	 */
	private static Tabuleiro t;
	private Celula tabuleiro[][];
	private static int alt;
	private static int larg;
	private Vector<Pair> _possiblePositions;
	private Vector<Pair> _possibleEats;
	private boolean jogador;
	private boolean peaochange;
	private int xPeao,yPeao;
	private int iReiE,jReiE,iReiC,jReiC;
	private boolean reimovE,reimovC,torremovEc, torremovCc,torremovEl, torremovCl;
	private boolean possiblecheckpositions;
	private boolean possiblecheckeats;
	private boolean checkmate;
	private boolean empate;
	JPopupMenu popup;
	
	private Tabuleiro() 
	{
		inicializaTudo();
	}
	private void inicializaTudo()
	{
		jogador=true;
		peaochange=false;
		possiblecheckpositions = possiblecheckeats = checkmate = empate = false;
		reimovE = reimovC = torremovEc = torremovCc = torremovEl = torremovCl = false; 
		double posX=0;
		double posY=0;
		larg=Celula.getLarg();
		alt=Celula.getAlt();
		Cor c=Cor.Claro;
		tabuleiro=new Celula[8][8];
		iReiE=0;
		jReiE=4;
		iReiC=7;
		jReiC=4;
		for(int i=0;i<8;i++)
		{
			for(int j=0; j<8;j++)
			{
				tabuleiro[i][j]=new Celula();

			}
		}
		setPeca();
		for(int i=0;i<8;i++)
		{
			posX=0;
			for(int j=0; j<8;j++)
			{
				tabuleiro[i][j].setColor(c);
				tabuleiro[i][j].setPosition(posX, posY);
				if(c==Cor.Claro)
				{
					c=Cor.Escuro;
				}
				else
				{
					c=Cor.Claro;
				}
				posX+=larg;
			}
			posY+=alt;
			if(c==Cor.Claro)
			{
				c=Cor.Escuro;
			}
			else
			{
				c=Cor.Claro;
			}
		}
		
	}
	public static Tabuleiro getTabuleiro()
	{
		if(t==null)
		{
			t= new Tabuleiro();
		}
		return t;
	}
	
	//Recebe um Graphics2D do JPanel e se desenha
	public void Draw(Graphics2D g)
	{
		for(int i=0;i<8;i++)
		{
			for(int j=0; j<8;j++)
			{
				tabuleiro[i][j].draw(g);
			}
		}
	}
	public Celula getCelula (int i,int j)
	{
		return tabuleiro[i][j];
	}
	
	//Inicializa Criando as pecas e as colocando nos luagres iniciais
	private void  setPeca()
	{
		//c é claro e e é escuro
		Peca Bc,Be,Cc,Ce,Pe,Pc,Qe,Qc,Ke,Kc,Te,Tc;
		Bc=new Bispo(Cor.Claro);
		Be=new Bispo(Cor.Escuro);
		Cc=new Cavalo(Cor.Claro);
		Ce=new Cavalo(Cor.Escuro);
		Pc=new Peao(Cor.Claro);
		Pe=new Peao(Cor.Escuro);
		Qc=new Rainha(Cor.Claro);
		Qe=new Rainha(Cor.Escuro);
		Kc=new Rei(Cor.Claro);
		Ke=new Rei(Cor.Escuro);
		Tc=new Torre(Cor.Claro);
		Te=new Torre(Cor.Escuro);

		//Escuras
		tabuleiro[0][0].setPeca(Te);
		tabuleiro[0][1].setPeca(Ce);
		tabuleiro[0][2].setPeca(Be);
		tabuleiro[0][3].setPeca(Qe);
		tabuleiro[0][4].setPeca(Ke);
		tabuleiro[0][5].setPeca(Be);
		tabuleiro[0][6].setPeca(Ce);
		tabuleiro[0][7].setPeca(Te);
		tabuleiro[1][0].setPeca(Pe);
		tabuleiro[1][1].setPeca(Pe);
		tabuleiro[1][2].setPeca(Pe);
		tabuleiro[1][3].setPeca(Pe);
		tabuleiro[1][4].setPeca(Pe);
		tabuleiro[1][5].setPeca(Pe);
		tabuleiro[1][6].setPeca(Pe);
		tabuleiro[1][7].setPeca(Pe);

		//Claras
		tabuleiro[6][0].setPeca(Pc);
		tabuleiro[6][1].setPeca(Pc);
		tabuleiro[6][2].setPeca(Pc);
		tabuleiro[6][3].setPeca(Pc);
		tabuleiro[6][4].setPeca(Pc);
		tabuleiro[6][5].setPeca(Pc);
		tabuleiro[6][6].setPeca(Pc);
		tabuleiro[6][7].setPeca(Pc);
		tabuleiro[7][0].setPeca(Tc);
		tabuleiro[7][1].setPeca(Cc);
		tabuleiro[7][2].setPeca(Bc);
		tabuleiro[7][3].setPeca(Qc);
		tabuleiro[7][4].setPeca(Kc);
		tabuleiro[7][5].setPeca(Bc);
		tabuleiro[7][6].setPeca(Cc);
		tabuleiro[7][7].setPeca(Tc);
	}

	public boolean TemPeca(int x, int y)
	{
		int i,j;
		Peca p;
		i=Math.floorDiv(y, alt); 
		j=Math.floorDiv(x, larg);
		p = tabuleiro[i][j].getPeca();
		if (p == null)
			return false;
		return true;	
	}
	public boolean TemPecaIndice(int i, int j)
	{
		if (tabuleiro[i][j].getPeca() == null)
			return false;
		return true;	
	}	
	
	/*Função que movimenta peca se for possível
	 * Retorna false - Se movimento não for possível
	 * Retorna true - Se movimento for possível
	 */
	
	public boolean MexePeca(int x1,int y1,int x2,int y2)
	{
		int i1,i2,j1,j2;
		i1=Math.floorDiv(y1,alt);
		j1=Math.floorDiv(x1,larg);
		i2=Math.floorDiv(y2,alt);
		j2=Math.floorDiv(x2,larg);
		Cor c;
		Peca p = tabuleiro[i1][j1].getPeca();
		if(p!=null)
		{
			c=p.getCor();
			//Verificação se o jogador eh o jogador da vez
			if(jogador && c == Cor.Escuro)
			{
				return false;
			}
			else if (!jogador && c == Cor.Claro)
			{
				return false;
			}			
			//Checa se a posição que o usuário quer mover é uma posição válida			
			for(int i=0;i<_possiblePositions.size();i++)
			{
				if(_possiblePositions.get(i)!=null)
				{
					if(_possiblePositions.get(i).getX()==i2 && _possiblePositions.get(i).getY()==j2)
					{
						//Posição que o usuário quer é válida então move
						tabuleiro[i2][j2].setPeca(p);
						tabuleiro[i1][j1].setPeca(null);	
						//Se peao chegar na outra ponta é promoção de peão e atualiza o booleano
						if(p instanceof Peao && ((i2==0 && c==Cor.Claro) || (i2==7 && c==Cor.Escuro)))
						{
							peaochange=true;
						}	
						//Ouve movimento e agora é a vez do próximo jogador
						if (jogador)
						{
							jogador = false;
						}
						else 
						{
							jogador = true;
						}
						break;
					}
				}
			}		
			
			//Se peça movida for o Rei atualiza a posição dele e booleano de primeiro movimento
			if(p instanceof Rei)
			{
				if(c==Cor.Escuro)
				{
					this.iReiE=i2;
					this.jReiE=j2;
					reimovE = true;
				}
				else
				{
					this.iReiC=i2;
					this.jReiC=j2;
					reimovC = true;
				}
			}
			//Se peca for booleano atualiza booleano de primeiro movimento
			if (p instanceof Torre)
			{
				if(c==Cor.Escuro)
				{
					if (i1 == 0 && j1==0)
					{
						torremovEl = true;
					}
					else if (i1 == 0 && j1 == 7)
					{
						torremovEc = true;
					}
				}
				else
				{
					if (i1 == 7 && j1==0)
					{
						torremovCl = true;
					}
					else if (i1 == 7 && j1 == 7)
					{
						torremovCc = true;
					}
				}				
			}
			//A cada movimento verifica se nenhuma peca tem movimento 
			this.checkmate=verifyCheckMate(c);
			//Se nenhuma peca tem movimento pode ser xeque mate ou empate
			//Verifica qual é e aparece JPane com mensagem adequada
			if(checkmate==true)
			{
				this.empate=VerifyEmpate(i1,j1,c);
				this.setChanged();
				notifyObservers();
				return true;
			}
		}
		this.setChanged();
		notifyObservers();
        return false;
	}
	//Ilumina peça que foi selecionada
	public void Acende(int x, int y)
	{
		int i=Math.floorDiv(y,alt);
		int j=Math.floorDiv(x,larg);
		tabuleiro[i][j].setSelect();
		this.setChanged();
		notifyObservers();
	}
	//Pega os possíveis movimentos da peça selecionada e preenche o vetor de possiveis movimentos
	public void CatchPossibleMoves(int x, int y)
	{
		int i=Math.floorDiv(y,alt);
		int j=Math.floorDiv(x,larg);
		_possiblePositions=tabuleiro[i][j].catchMoves(x,y);
		VerifyCheck(i,j,_possiblePositions);
		//Se movimento for possível ilumina a célula do movimento possível
		if( _possiblePositions!=null)
		{
			for(int n=0;n< _possiblePositions.size();n++)
			{
				if(jogador && tabuleiro[i][j].getPeca().getCor() == Cor.Escuro)
					break;
				else if (!jogador && tabuleiro[i][j].getPeca().getCor() == Cor.Claro)
					break;
		        if(_possiblePositions.get(n)!=null)
		        {
		        	tabuleiro[ _possiblePositions.get(n).getX()][ _possiblePositions.get(n).getY()].setPossibleMove();
		        }
			}
		}
	}
	//Agora pega possiveis posições em que a peça selecionada pode comer
	public void CatchPossibleEats(int x, int y)
	{
		int i=Math.floorDiv(y,alt);
		int j=Math.floorDiv(x,larg);
		_possibleEats=tabuleiro[i][j].catchEats(x,y);
		VerifyCheck(i,j,_possibleEats);
		if( _possibleEats!=null)
		{
			for(int n=0;n< _possibleEats.size();n++)
			{
				if(jogador && tabuleiro[i][j].getPeca().getCor() == Cor.Escuro)
					break;
				else if (!jogador && tabuleiro[i][j].getPeca().getCor() == Cor.Claro)
					break;
		        if(_possibleEats.get(n)!=null)
		        {
		        	tabuleiro[ _possibleEats.get(n).getX()][ _possibleEats.get(n).getY()].setPossibleEats();
		        }
			}
		}
	}
	//Come peça se for possível comer
	public boolean ComePeca(int x1, int y1, int x2, int y2)
	{
		int i1,i2,j1,j2;
		i1=Math.floorDiv((y1),alt);
		j1=Math.floorDiv(x1,larg);
		i2=Math.floorDiv((y2),alt);
		j2=Math.floorDiv(x2,larg);

		Peca p0 = tabuleiro[i1][j1].getPeca();
		Peca p = tabuleiro[i2][j2].getPeca();
		Cor c;
		if(p0!=null)
		{
			c=p0.getCor();
			if(jogador && c == Cor.Escuro)
			{
				return false;
			}
			else if (!jogador && c == Cor.Claro)
			{
				return false;
			}

			for(int k=0;k<_possibleEats.size();k++)
			{
				if(_possibleEats.get(k)!=null)
				{
					if(_possibleEats.get(k).getX()==i2 && _possibleEats.get(k).getY()==j2)
					{
						tabuleiro[i2][j2].setPeca(p0);
						tabuleiro[i1][j1].setPeca(null);
						if(p0 instanceof Peao && ((i2==0 && c==Cor.Claro) || (i2==7 && c==Cor.Escuro)))
						{
							peaochange=true;
						}
						if (jogador)
						{
							jogador = false;
						}
						else 
						{
							jogador = true;
						}
						break;
					}
				}
			}
			if ((p0 instanceof Rei && p instanceof Torre) && (c==p.getCor()))
			{
				FazRoque(i1,j1,i2,j2,p0,p);
				this.setChanged();
				notifyObservers();
				return false;
			}
			this.checkmate=verifyCheckMate(c);
			if(checkmate==true)
			{
				this.empate=VerifyEmpate(i1,j1,c);
				this.setChanged();
				notifyObservers();
				return true;
			}
		}
		this.setChanged();
		notifyObservers();
		return false;
	}
	//Faz Roque se as condicoes do roque forem satisfeitas
	public void FazRoque(int i1, int j1, int i2, int j2,Peca p0, Peca p)
	{
		Cor c = p.getCor();
		//primeiro verifica condicao: primeira jogada do rei e da torre
		if (j2 == 0 && ((i2 == 7 && c == Cor.Claro) || (i2 == 0 && c == Cor.Escuro)))
		{
			if ((c == Cor.Escuro && reimovE == false && torremovEl == false) || (c == Cor.Claro && reimovC == false && torremovCl == false))
			{
				//Roque Longo
				j2++;
				//segunda condicao: verifica se tem peca entre o rei e a torre
				while (j1>j2) {						
					if (tabuleiro[i2][j2].getPeca() != null)
					{
						return;
					}
					j2++;
				}
				j2=0;	
				//terceira condicao: verifica se o rei esta em cheque
				if (VerifyCheckRoque(i1,j1))
				{
					return;
				}	
				//faz o movimento do roque
				tabuleiro[i1][j1].setPeca(null);
				j1 -= 2;
				tabuleiro[i1][j1].setPeca(p0);
				tabuleiro[i2][j1+1].setPeca(p);
				tabuleiro[i2][j2].setPeca(null);
				//ultima condicao: verifica se o rei vai esta em xeque
				if (VerifyCheckRoque(i1,j1))
				{
					//desfaz o movimento do roque
					tabuleiro[i1][j1].setPeca(null);
					tabuleiro[i1][j1+2].setPeca(p0);
					tabuleiro[i2][j1+1].setPeca(null);
					tabuleiro[i2][j2].setPeca(p);
					return;
				}		
				//atualiza vez do jogador
				if (jogador)
				{
					jogador = false;
				}
				else 
				{
					jogador = true;
				}
			}
		}
		else if (j2 == 7 && ((i2 == 7 && c == Cor.Claro) || (i2 == 0 && c == Cor.Escuro)))
		{
			if ((c == Cor.Escuro && reimovE == false && torremovEc == false) || (c == Cor.Claro && reimovC == false && torremovCc == false))
			{
				//Roque Curto
				j2--;
				//segunda condicao: verifica se tem peca entre o rei e a torre
				while (j1<j2) {						
					if (tabuleiro[i2][j2].getPeca() != null)
					{
						return;
					}
					j2--;
				}
				j2=7;
				//terceira condicao: verifica se o rei esta em cheque
				if (VerifyCheckRoque(i1,j1))
				{
					return;
				}	
				//faz o movimento do roque
				tabuleiro[i1][j1].setPeca(null);
				j1 += 2;
				tabuleiro[i1][j1].setPeca(p0);
				tabuleiro[i2][j1-1].setPeca(p);
				tabuleiro[i2][j2].setPeca(null);
				//ultima condicao: verifica se o rei vai esta em xeque
				if (VerifyCheckRoque(i1,j1))
				{
					//desfaz o movimento do roque
					tabuleiro[i1][j1].setPeca(null);
					tabuleiro[i1][j1-2].setPeca(p0);
					tabuleiro[i2][j1-1].setPeca(null);
					tabuleiro[i2][j2].setPeca(p);
					return;
				}			
				//atualiza vez do jogador
				if (jogador)
				{
					jogador = false;
				}
				else 
				{
					jogador = true;
				}
			}
		}			
	}
	public JPopupMenu  CriaPopupSave()
	{
		popup = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("Load");
		popup.add(menuItem);
		menuItem.addActionListener(this);
		menuItem = new JMenuItem("Save");
		popup.add(menuItem);
		menuItem.addActionListener(this);
		return popup;
	}
	public JPopupMenu  CriaPopup() {
		popup = new JPopupMenu();					

		JMenuItem menuItem = new JMenuItem("Rainha");
		menuItem.addActionListener(this);	 

		popup.add(menuItem);     

		menuItem = new JMenuItem("Torre");
		menuItem.addActionListener(this);	 
		popup.add(menuItem);  

		menuItem = new JMenuItem("Bispo");
		menuItem.addActionListener(this);	 
		popup.add(menuItem);  

		menuItem = new JMenuItem("Cavalo");
		menuItem.addActionListener(this);
		popup.add(menuItem);  
		return popup;
	}
	public boolean getPeaoChange()
	{
		return peaochange;
	}
	public void setPeaoChange(boolean a)
	{
		peaochange=a;
	}
	public void setPositionPeao(int x, int y)
	{
		xPeao=x;
		yPeao=y;
	}
	public void TurnPeao(String s)
	{
		Peca p;
		int i,j;
		i=Math.floorDiv(yPeao,alt);
		j=Math.floorDiv(xPeao,larg);
		Cor c=tabuleiro[i][j].getPeca().getCor();
		switch(s)
		{
			case "Cavalo":
				p = new Cavalo(c);
				break;
			case "Bispo":
				p=new Bispo(c);
				break;
			case "Rainha":
				p=new Rainha(c);
				break;
			case "Torre":
				p=new Torre(c);
				break;
			default:
				p=new Rainha(c);
		}		
		tabuleiro[i][j].setPeca(null);
		tabuleiro[i][j].setPeca(p);
	}
	//Funções que servem de auxílio para salvar arquivo
	private void checkbooleanSaveOneterm(PrintWriter file,boolean x)
	{
		if (x)
		{
			file.println(1);
		}
		else
		{
			file.println(2);
		}
	}
	private void checkbooleanSaveTwoterms(PrintWriter file,boolean x,boolean y)
	{
		int aux;
		if (x)
		{
			aux=1;
		}
		else
		{
			aux=2;
		}
		if(y)
		{
			file.println(aux + " " +1);
		}
		else
		{
			file.println(aux + " " +2);
		}
	}
	//Função que salva arquivo -> Não colocar .txt pois ele já insere
	public void SaveFile() throws IOException
	{
		/*Jogador da vez
		 * possible check positions
		 * possible checkeats
		 * checkmate
		 * empate
		 * reimovE reimovC
		 * torremovEc torremov Cc
		 * torremovEl torremov Cl
		 * Posicao rei claro
		 * Posicao rei escuro
		 * tipo da peça cor da peca
		 * .
		 * .
		 * .
		 * .
		 * .
		 * .
		 * .
		 */
		JFileChooser chooser = new JFileChooser();
		chooser.showSaveDialog(null);
		PrintWriter file=null;
		try 
		{
			file= new PrintWriter(new FileWriter(chooser.getSelectedFile()+".txt"));
		}
		finally
		{
		}
		checkbooleanSaveOneterm(file,jogador);
		checkbooleanSaveOneterm(file,possiblecheckpositions);
		checkbooleanSaveOneterm(file,possiblecheckeats);
		checkbooleanSaveOneterm(file,checkmate);
		checkbooleanSaveOneterm(file,empate);
		checkbooleanSaveTwoterms(file,reimovE,reimovC);
		checkbooleanSaveTwoterms(file,torremovEc, torremovCc);
		checkbooleanSaveTwoterms(file,torremovEl, torremovCl);
		
		file.println(iReiC + " "+jReiC);
		file.println(iReiE + " "+jReiE);
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				Peca p=tabuleiro[i][j].getPeca();
				String cor=new String();
				String name;
				if(p==null)
				{
					name="null";
					cor="Escuro";
				}
				else
				{
					if(p.getCor()==Cor.Escuro)
					{
						cor="Escuro";
					}
					else
					{
						cor="Claro";
					}
					name=p.getName();
				}
				file.println(name+ " "+cor);
			}
		}
		file.close();
	}
	//Auxilio na leitura do arquivo
	private boolean checkbooleanLoadOneterm(Scanner file)
	{
		int x;
		x = file.nextInt();
		if(x==1)
		{
			return true;
		}
		return false;
	}
	//Carrega jogo
	public void Load() throws FileNotFoundException 
	{
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		chooser.setFileFilter(filter);
		 
		chooser.showOpenDialog(Tela.getInstanceTela());
		Scanner load=null;
		String aux,aux2;
		try 
		{
			load=new Scanner(new BufferedReader(new FileReader(chooser.getSelectedFile().getPath())));
		}
		finally
		{
			if(load==null)
			{
				return;
			}
		}
		
		jogador = checkbooleanLoadOneterm(load);
		possiblecheckpositions = checkbooleanLoadOneterm(load);
		possiblecheckeats = checkbooleanLoadOneterm(load);
		checkmate = checkbooleanLoadOneterm(load);
		empate = checkbooleanLoadOneterm(load);
		this.reimovE = checkbooleanLoadOneterm(load);
		this.reimovC = checkbooleanLoadOneterm(load);
		this.torremovEc = checkbooleanLoadOneterm(load);
		this.torremovCc = checkbooleanLoadOneterm(load);
		this.torremovEl = checkbooleanLoadOneterm(load);
		this.torremovCl = checkbooleanLoadOneterm(load);
		
		iReiC=load.nextInt();
		jReiC=load.nextInt();
		iReiE=load.nextInt();
		jReiE=load.nextInt();
		Peca p;
		Cor c;
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				aux=load.next();
				aux2=load.next();
				switch(aux2)
				{
				case "Escuro":
					c=Cor.Escuro;
					break;
				case "Claro":
					c=Cor.Claro;
					break;
				default:
					c=Cor.Claro;	
				}
				
				switch(aux)
				{
				    case "null":
						p = null;
						break;
					case "Cavalo":
						p = new Cavalo(c);
						break;
					case "Bispo":
						p=new Bispo(c);
						break;
					case "Rainha":
						p=new Rainha(c);
						break;
					case "Torre":
						p=new Torre(c);
						break;
					case "Rei":
						p=new Rei(c);
						break;
					case "Peao":
						p=new Peao(c);
						break;
					default:
						p=new Rainha(c);
				}		
				tabuleiro[i][j].setPeca(null);
				tabuleiro[i][j].setPeca(p);
			}
		}
		load.close();
		this.setChanged();
		this.notifyObservers();
	}
	//Tabuleiro agindo como action listener por causa dos 2 popups (De load/save e Peao)
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getActionCommand()=="Load")
		{
			try {
				Load();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else if(arg0.getActionCommand()=="Save")
		{
			try {
				SaveFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(arg0.getActionCommand()=="Cavalo")
		{
			TurnPeao("Cavalo");
		}
		else if(arg0.getActionCommand()=="Rainha")
		{
			TurnPeao("Rainha");
		}
		else if(arg0.getActionCommand()=="Bispo")
		{
			TurnPeao("Bispo");
		}
		else if(arg0.getActionCommand()=="Torre")
		{
			TurnPeao("Torre");
		}
		this.setChanged();
		this.notifyObservers();
	}
	public void verifyChange()
	{
		int i,j;
		i=yPeao/alt;
		j=xPeao/larg;
		Peca p0 = tabuleiro[i][j].getPeca();
		Peca p;
		if(p0 instanceof Peao)
		{
			p=new Rainha(p0.getCor());
			tabuleiro[i][j].setPeca(null);
			tabuleiro[i][j].setPeca(p);
		}
		
	}
	//Verifica se um vetor de posicoes tem tudo null ou não pois se tiver a peca n tem movimentos
	private boolean verifyPositions(Vector<Pair> positions)
	{
		for(int i=0;i<positions.size();i++)
		{
			if(positions.get(i)!=null)
			{
				return true;
			}
		}
		return false;
	}
	
	/*Função que verifica se as posições do vetor enviado deixam o rei em xeque.
	 * Funcionamento: Percorre todo o vetor de posições mudando temporariamente
	 * a peça da posição i1 j1 pra essas posições.
	 * Para cada posição alterada percorre o tabuleiro vendo se alguma pode comer o rei.
	 * Retorna -> Se a peça analisada tem movimentos possiveis ou não
	 * */
	public boolean VerifyCheck(int i1,int j1,Vector<Pair> positions) //1 eh a posição q ele tá e 2 é a que ele vai
	{
		int ireitemp,jreitemp;
		int k=0,size,l=0;
		boolean gotmoves=false;
		int i2,j2;
		Peca p,peca,peca2;
		Cor c;
		Vector<Pair> v;
		Vector<Integer> indices=new Vector<Integer>();
		peca=tabuleiro[i1][j1].getPeca();
		c = peca.getCor();
		size=positions.size();
		ireitemp=0;
		jreitemp=0;
		//percorre vetor de posições
		for(l=0;l<size;l++)
		{
			i2=positions.get(l).getX();
			j2=positions.get(l).getY();
			peca2=tabuleiro[i2][j2].getPeca();
			
			//muda temporariamente a peça de posição
			tabuleiro[i1][j1].setPeca(null);
			tabuleiro[i2][j2].setPeca(peca);
			
			//Se peça for rei tem que mudar posição de análise do rei no xeque também
			if(peca instanceof Rei)
			{
				if(c==Cor.Escuro)
				{
					ireitemp=iReiE;
					jreitemp=jReiE;
					iReiE=i2;
					jReiE=j2;
				}
				else
				{
					ireitemp=iReiC;
					jreitemp=jReiC;
					iReiC=i2;
					jReiC=j2;
				}
			}			
			//Passa pelo tabuleiro todo vendo quais tem peca
			for(int i=0;i<8;i++)
			{
				for(int j=0;j<8;j++)
				{
					p=tabuleiro[i][j].getPeca();
					if(p!=null)
					{
						if(c!=p.getCor())
						{ 
							v=p.PossibleEats((int)larg*j,(int)alt*i);
							//Passa pelo vetor de possível eats de cada peça
							for(k=0;k<v.size();k++)
							{
								Pair comidoposition=new Pair(v.elementAt(k).getX(),v.elementAt(k).getY());
								
								//Se a peça tiver possibilidades de comer o rei tirar essa posição dos possíveis movimentos
								if((comidoposition.getX()==iReiC && comidoposition.getY()==jReiC) ||(comidoposition.getX()==iReiE && comidoposition.getY()==jReiE))
								{									
									if(indices.contains(l)==false)
									{
										indices.add(l);
									}
								}
							}
						}
					}
				}
			}
			tabuleiro[i2][j2].setPeca(peca2);
			tabuleiro[i1][j1].setPeca(peca);
			if(peca instanceof Rei)
			{
				if(c==Cor.Escuro)
				{
					iReiE=ireitemp;
					jReiE=jreitemp;
				}
				else
				{
					iReiC=ireitemp;
					jReiC=jreitemp;
				}
			}
		}
		for(int i=0;i<indices.size();i++)
		{
			positions.setElementAt(null,indices.get(i));
		}
		//Vê se tem alguma posição que a peça pode ir
		gotmoves=verifyPositions(positions);
		return gotmoves;
	}
	//Recomeça o jogo
	public void Recomeca()
	{
		this.inicializaTudo();
		t.setChanged();
		t.notifyObservers();
	}
	//Cria aviso de Xeque Mate ou Empate
	protected void CriaJPane(int x,int y)
	{
		int i,j;
		i=y/alt;
		j=x/larg;
		String s;
		if(this.empate==true)
		{
			s="  EMPATE  ";
		}
		else
		{
			if(tabuleiro[i][j].getPeca().getCor()==Cor.Escuro)
			{
				s="XEQUE MATE !!!\nAS PECAS ESCURAS GANHARAM";
			}
			else
			{
				s="XEQUE MATE !!!\nAS PECAS CLARAS GANHARAM";
			}
		}
		JOptionPane.showMessageDialog(null,
		        s, 
		        "Fim do Jogo", 
		        JOptionPane.INFORMATION_MESSAGE);
	}
	
	//Verifica Xeque Mate passando por todas as peças vendo se alguma delas tem movimento ou não
	private boolean verifyCheckMate(Cor c)
	{
		Vector <Pair> positions;
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
					if(tabuleiro[i][j].getPeca()!=null)
					{
						if(tabuleiro[i][j].getPeca().getCor()!=c)
						{
							positions=tabuleiro[i][j].catchMoves((int)larg*j,(int)alt*i);
							this.possiblecheckpositions=this.VerifyCheck(i, j,positions);
							if(this.possiblecheckpositions==true)
							{
								return false;
							}
							positions=tabuleiro[i][j].catchEats((int)larg*j,(int)alt*i);
							this.possiblecheckeats=this.VerifyCheck(i, j, positions);
							if(this.possiblecheckeats==true)
							{
								return false;
							}
					}
				}
			}
		}
		return true;
	}
	//Já foi verificado que não tem movimento para nenhuma peça, agora ver se não tem 
	public boolean VerifyEmpate(int i1,int j1,Cor c)
	{
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				Peca p=tabuleiro[i][j].getPeca();
				if(p!=null)
				{
					if(p.getCor()==c)
					{
						Vector<Pair> v=p.PossibleEats((int)larg*j,(int)alt*i);
						for(int k=0;k<v.size();k++)
						{
								if((v.get(k).getX()==iReiC && v.get(k).getY()==jReiC) || (v.get(k).getX()==iReiE && v.get(k).getY()==jReiE))
								{
									return false;
								}
						}
					}
				}
			}
			
		}
		return true;
	}
	public  Cor getPecaCor(int i,int j)
	{
		return getCelula(i,j).getPeca().getCor();
	}
	public boolean VerifyCheckRoque(int i1,int j1) 
	{
		Peca p,peca;
		Vector<Pair> v;
		peca=tabuleiro[i1][j1].getPeca();
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				//Passar pelo tabuleiro todo vendo quais tem peca
				p=tabuleiro[i][j].getPeca();
				if(p!=null)
				{
					if(peca.getCor()!=p.getCor())
					{ 
						v=p.PossibleEats((int)larg*j,(int)alt*i);
						for(int k=0;k<v.size();k++)
						{
							Pair comidoposition=new Pair(v.elementAt(k).getX(),v.elementAt(k).getY());
							//verifica se uma das pecas podem comer o rei, se sim estar em xeque
							if((comidoposition.getX()==i1 && comidoposition.getY()==j1))
							{	
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
}