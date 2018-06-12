package Tabuleiro;

//import java.util.Observer;
//import java.util.Observable;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileNameExtensionFilter;

import Drawing.Cor;
import Drawing.Tela;
import Pecas.Bispo;
import Pecas.Cavalo;
import Pecas.Peao;
import Pecas.Peca;
import Pecas.Rainha;
import Pecas.Rei;
import Pecas.Torre;
import java.util.Observable;
import java.util.Scanner;
import java.io.*;
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
	private static Celula tabuleiro[][];
	private static int alt;
	private static int larg;
	private Vector<Pair> _possiblePositions;
	private Vector<Pair> _possibleEats;
	private boolean jogador=true;
	private boolean peaochange=false;
	private int xPeao,yPeao;
	JPopupMenu popup;
	private Tabuleiro() 
	{
		double posX=0;
		double posY=0;
		larg=Celula.getLarg();
		alt=Celula.getAlt();
		Cor c=Cor.Claro;
		tabuleiro=new Celula[8][8];
		this.CriaPopup();
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
		tabuleiro[0][3].setPeca(Ke);
		tabuleiro[0][4].setPeca(Qe);
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
		tabuleiro[7][3].setPeca(Kc);
		tabuleiro[7][4].setPeca(Qc);
		tabuleiro[7][5].setPeca(Bc);
		tabuleiro[7][6].setPeca(Cc);
		tabuleiro[7][7].setPeca(Tc);
	}

	public static boolean TemPeca(int x, int y)
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
	public static boolean TemPecaIndice(int i, int j)
	{
		if (tabuleiro[i][j].getPeca() == null)
			return false;
		return true;	
	}	
	public void MexePeca(int x1,int y1,int x2,int y2)
	{
		int i1,i2,j1,j2;
		i1=y1/alt;
		j1=x1/larg;
		i2=y2/alt;
		j2=x2/larg;
		Vector<Pair> positions;
		i1=Math.floorDiv((y1 - 40),alt);
		j1=Math.floorDiv(x1,larg);
		i2=Math.floorDiv((y2 - 40),alt);
		j2=Math.floorDiv(x2,larg);

		if(jogador && tabuleiro[i1][j1].getPeca().getCor() == Cor.Escuro)
		{
			return;
		}
		else if (!jogador && tabuleiro[i1][j1].getPeca().getCor() == Cor.Claro)
		{
			return;
		}

		System.out.println("De "+ y1+ " "+i1+" "+j1);
		System.out.println("Para "+ y2+ " "+i2+" "+j2);
		System.out.println("joga: " + jogador);		

		positions=tabuleiro[i1][j1].getPositions();
		for(int i=0;i<positions.size();i++)
		{
			if(positions.get(i).getX()==i2 && positions.get(i).getY()==j2)
			{
				tabuleiro[i2][j2].setPeca(tabuleiro[i1][j1].getPeca());
				tabuleiro[i1][j1].setPeca(null);
				if (jogador)
					jogador = false;
				else
					jogador = true;
				System.out.println("joga: " + jogador);
				break;
			}
		}		
		Peca p = tabuleiro[i2][j2].getPeca();
		if(p instanceof Peao && ((i2==0 && p.getCor()==Cor.Claro) || (i2==7 && p.getCor()==Cor.Escuro)))
		{
			peaochange=true;
		}	

	}
	public static void Acende(int x, int y)
	{
		int i=Math.floorDiv(y,alt);
		int j=Math.floorDiv(x,larg);
		System.out.println(i +" "+ j);
		tabuleiro[i][j].setSelect();
	}
	public void CatchPossibleMoves(int x, int y)
	{
		int i=Math.floorDiv(y,alt);
		int j=Math.floorDiv(x,larg);
		System.out.println(i +" "+ j);
		_possiblePositions=tabuleiro[i][j].catchMoves(x,y);
		if( _possiblePositions!=null)
		{
			for(int n=0;n< _possiblePositions.size();n++)
			{
				if(jogador && tabuleiro[i][j].getPeca().getCor() == Cor.Escuro)
					break;
				else if (!jogador && tabuleiro[i][j].getPeca().getCor() == Cor.Claro)
					break;
				tabuleiro[ _possiblePositions.get(n).getX()][ _possiblePositions.get(n).getY()].setPossibleMove();
			}
		}
	}

	public void CatchPossibleEats(int x, int y)
	{
		int i=Math.floorDiv(y,alt);
		int j=Math.floorDiv(x,larg);
		System.out.println(i +" "+ j);
		_possibleEats=tabuleiro[i][j].catchEats(x,y);
		if( _possibleEats!=null)
		{
			for(int n=0;n< _possibleEats.size();n++)
			{
				if(jogador && tabuleiro[i][j].getPeca().getCor() == Cor.Escuro)
					break;
				else if (!jogador && tabuleiro[i][j].getPeca().getCor() == Cor.Claro)
					break;
				tabuleiro[ _possibleEats.get(n).getX()][ _possibleEats.get(n).getY()].setPossibleEats();
			}
		}
	}

	public void ComePeca(int x1, int y1, int x2, int y2)
	{
		int i1,i2,j1,j2;
		i1=y1/alt;
		j1=x1/larg;
		i2=y2/alt;
		j2=x2/larg;
		Vector<Pair> eats;
		i1=Math.floorDiv((y1 - 40),alt);
		j1=Math.floorDiv(x1,larg);
		i2=Math.floorDiv((y2 - 40),alt);
		j2=Math.floorDiv(x2,larg);

		Peca p0 = tabuleiro[i1][j1].getPeca();
		Peca p = tabuleiro[i2][j2].getPeca();

		if(jogador && p0.getCor() == Cor.Escuro)
		{
			return;
		}
		else if (!jogador && p0.getCor() == Cor.Claro)
		{
			return;
		}

		System.out.println(y1+ " "+i1+" "+j1);

		System.out.println("come: " + jogador);
		eats=tabuleiro[i1][j1].getEats();
		for(int k=0;k<eats.size();k++)
		{
			if(eats.get(k).getX()==i2 && eats.get(k).getY()==j2)
			{
				System.out.println("Come "+ y2+ " "+i2+" "+j2);
				tabuleiro[i2][j2].setPeca(tabuleiro[i1][j1].getPeca());
				tabuleiro[i1][j1].setPeca(null);
				if (jogador)
					jogador = false;
				else
					jogador = true;
				System.out.println("come: " + jogador);
				break;
			}
		}		
		if ((p0 instanceof Rei && p instanceof Torre) && (p0.getCor()==p.getCor()))
		{
			if (j1 == 3 && ((i1 == 7 && p0.getCor() == Cor.Claro) || (i1 == 0 && p0.getCor() == Cor.Escuro)))
			{
				if (j2 == 0 && ((i2 == 7 && p0.getCor() == Cor.Claro) || (i2 == 0 && p0.getCor() == Cor.Escuro)))
				{
					//Roque Curto
					j2++;
					while (j1>j2) {						
						if (tabuleiro[i2][j2].getPeca() != null)
						{
							System.out.println("tem peça no meio");
							return;
						}
						j2++;
					}
					tabuleiro[i1][j1].setPeca(null);
					j1 -= 2;
					tabuleiro[i1][j1].setPeca(p0);
					j1+=1;
					tabuleiro[i2][j1].setPeca(p);
					tabuleiro[i2][0].setPeca(null);	
				}
				else if (j2 == 7 && ((i2 == 7 && p0.getCor() == Cor.Claro) || (i2 == 0 && p0.getCor() == Cor.Escuro)))
				{
					//Roque Longo
					j2--;
					while (j1<j2) {						
						if (tabuleiro[i2][j2].getPeca() != null)
						{
							System.out.println("tem peça no meio");
							return;
						}
						j2--;
					}
					tabuleiro[i1][j1].setPeca(null);
					j1 += 2;
					tabuleiro[i1][j1].setPeca(p0);
					j1-=1;
					tabuleiro[i2][j1].setPeca(p);
					tabuleiro[i2][7].setPeca(null);
				}
			}
			if (jogador)
				jogador = false;
			else
				jogador = true;
		}
		if(p0 instanceof Peao && ((i2==0 && p0.getCor()==Cor.Claro) || (i2==7 && p0.getCor()==Cor.Escuro)))
		{
			peaochange=true;
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
		i=yPeao/alt;
		j=xPeao/larg;
		i=Math.floorDiv((yPeao - 40),alt);
		j=Math.floorDiv(xPeao,larg);
		Cor c=tabuleiro[i][j].getPeca().getCor();
		System.out.println("AAAAAAA "+s);
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
	public void SaveFile() throws IOException
	{
		/*Jogador da vez
		 * [0][0] booleano se tem peça tipo da peça cor da peca
		 * .
		 * .
		 * .
		 * .
		 * .
		 * .
		 * .
		 */
		PrintWriter file=null;
		try 
		{
			file= new PrintWriter(new FileWriter("tabuleiro.txt"));
		}
		finally
		{
		}
		file.println(jogador);
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
	public void Load() throws FileNotFoundException 
	{
		System.out.println("LOadddd");
		JFileChooser chooser = new JFileChooser();
		//FileNameExtensionFilter filter = new FileNameExtensionFilter("txt");
//		 chooser.setFileFilter(filter);
//		 
		 int returnVal = chooser.showOpenDialog(Tela.getInstanceTela());
//		  if(returnVal == JFileChooser.APPROVE_OPTION) {
//		       System.out.println("You chose to open this file: " +
//		            chooser.getSelectedFile().getName());}
		Scanner load=null;
		String aux,aux2;
		try 
		{
			load=new Scanner(new BufferedReader(new FileReader("tabuleiro.txt")));
		}
		finally
		{
			if(load==null)
			{
				return;
			}
		}
		
		aux=load.next();
		if(aux=="false")
		{
			jogador=false;
		}
		else
		{
			jogador=true;
		}
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
				System.out.println(aux+" "+aux2);
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
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("AAAAAAA "+arg0.getActionCommand());
		System.out.println("Ta entrando");
		if(arg0.getActionCommand()=="Load")
		{
			try {
				Load();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(arg0.getActionCommand()=="Save")
		{
			try {
				SaveFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
		Peca p;
		if(tabuleiro[i][j].getPeca() instanceof Peao)
		{
			p=new Rainha(tabuleiro[i][j].getPeca().getCor());
			tabuleiro[i][j].setPeca(null);
			tabuleiro[i][j].setPeca(p);
		}
		
	}
}
