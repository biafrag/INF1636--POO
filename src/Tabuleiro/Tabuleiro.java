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

import Drawing.Cor;
import Drawing.Tela;
import Pecas.Bispo;
import Pecas.Cavalo;
import Pecas.Peao;
import Pecas.Peca;
import Pecas.Rainha;
import Pecas.Rei;
import Pecas.Torre;

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
	private boolean jogador=true;
	private boolean peaochange=false;
	private int xPeao,yPeao;
	private int iReiE,jReiE,iReiC,jReiC;
	private boolean reimovE = false,reimovC = false;
	private boolean torremovEc = false, torremovCc = false,torremovEl = false, torremovCl = false;
	private boolean possiblecheckpositions=false;
	private boolean possiblecheckeats=false;
	private boolean checkmate=false;
	private boolean empate=false;
	JPopupMenu popup;
	private Tabuleiro() 
	{
		inicializaTudo();
	}
	private void inicializaTudo()
	{
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
	public void MexePeca(int x1,int y1,int x2,int y2)
	{
		int i1,i2,j1,j2;
		i1=Math.floorDiv((y1),alt);
		j1=Math.floorDiv(x1,larg);
		i2=Math.floorDiv((y2),alt);
		j2=Math.floorDiv(x2,larg);
		Cor c;
		if(tabuleiro[i1][j1].getPeca()!=null)
		{
			c=tabuleiro[i1][j1].getPeca().getCor();
			if(jogador && c == Cor.Escuro)
			{
				return;
			}
			else if (!jogador && c == Cor.Claro)
			{
				return;
			}

//			System.out.println("De "+ y1+ " "+i1+" "+j1);
//			System.out.println("Para "+ y2+ " "+i2+" "+j2);
//			System.out.println("joga: " + jogador);		
			Peca p = tabuleiro[i1][j1].getPeca();
			for(int i=0;i<_possiblePositions.size();i++)
			{
				if(_possiblePositions.get(i)!=null)
				{
					if(_possiblePositions.get(i).getX()==i2 && _possiblePositions.get(i).getY()==j2)
					{
						tabuleiro[i2][j2].setPeca(p);
						tabuleiro[i1][j1].setPeca(null);
						if(p instanceof Peao && ((i2==0 && c==Cor.Claro) || (i2==7 && c==Cor.Escuro)))
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
			if(p instanceof Peao && ((i2==0 && p.getCor()==Cor.Claro) || (i2==7 && p.getCor()==Cor.Escuro)))
			{
				peaochange=true;
			}	
			if(p instanceof Rei)
			{
				if(p.getCor()==Cor.Escuro)
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
			if (p instanceof Torre)
			{
				if(p.getCor()==Cor.Escuro)
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
			this.checkmate=verifyCheckMate(c);
			if(checkmate==true)
			{
				this.empate=VerifyEmpate(i1,j1,c);
				tabuleiro[i2][j2].setPeca(p);
				tabuleiro[i1][j1].setPeca(null);
				t.setChanged();
				t.notifyObservers();
				CriaJPane(c);
				Recomeca();
			}
		}

	}
	public void Acende(int x, int y)
	{
		int i=Math.floorDiv(y,alt);
		int j=Math.floorDiv(x,larg);
//		System.out.println(i +" "+ j);
		tabuleiro[i][j].setSelect();
	}
	public void CatchPossibleMoves(int x, int y)
	{
		int i=Math.floorDiv(y,alt);
		int j=Math.floorDiv(x,larg);
//		System.out.println(i +" "+ j);
		_possiblePositions=tabuleiro[i][j].catchMoves(x,y);
//		System.out.println("Quantidade de posicoes possiveis antes: "+_possiblePositions.size());
//		System.out.println("Verifica posicoes");
		VerifyCheck(i,j,_possiblePositions);
//		System.out.println("Quantidade de posicoes possiveis: "+_possiblePositions.size());
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

	public void CatchPossibleEats(int x, int y)
	{
		int i=Math.floorDiv(y,alt);
		int j=Math.floorDiv(x,larg);
//		System.out.println(i +" "+ j);
		_possibleEats=tabuleiro[i][j].catchEats(x,y);
//		System.out.println("Verifica eats");
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

	public void ComePeca(int x1, int y1, int x2, int y2)
	{
		int i1,i2,j1,j2;
		i1=y1/alt;
		j1=x1/larg;
		i2=y2/alt;
		j2=x2/larg;
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
				return;
			}
			else if (!jogador && c == Cor.Claro)
			{
				return;
			}

//			System.out.println(y1+ " "+i1+" "+j1);

//			System.out.println("come: " + jogador);

			for(int k=0;k<_possibleEats.size();k++)
			{
				if(_possibleEats.get(k)!=null)
				{
					if(_possibleEats.get(k).getX()==i2 && _possibleEats.get(k).getY()==j2)
					{
//						System.out.println("Come "+ y2+ " "+i2+" "+j2);
						tabuleiro[i2][j2].setPeca(tabuleiro[i1][j1].getPeca());
						tabuleiro[i1][j1].setPeca(null);
						if(p0 instanceof Peao && ((i2==0 && p0.getCor()==Cor.Claro) || (i2==7 && p0.getCor()==Cor.Escuro)))
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
			if ((p0 instanceof Rei && p instanceof Torre) && (p0.getCor()==p.getCor()))
			{
				FazRoque(i1,j1,i2,j2,p0,p);
				return;
			}
			this.checkmate=verifyCheckMate(c);
			if(checkmate==true || (p instanceof Rei && p0.getCor()!=p.getCor()))
			{
				this.empate=VerifyEmpate(i1,j1,c);
				tabuleiro[i2][j2].setPeca(tabuleiro[i1][j1].getPeca());
				tabuleiro[i1][j1].setPeca(null);
				t.setChanged();
				t.notifyObservers();
				CriaJPane(c);
				Recomeca();
			}
		}
	}
	public void FazRoque(int i1, int j1, int i2, int j2,Peca p0, Peca p)
	{
		Vector <Pair> positions;
		if (j2 == 0 && ((i2 == 7 && p.getCor() == Cor.Claro) || (i2 == 0 && p.getCor() == Cor.Escuro)))
		{
			if ((reimovE == false && torremovEl == false) || (reimovC == false && torremovCl == false))
			{
//				System.out.println("entrou no roque longo");
				//Roque Longo
				j2++;
				while (j1>j2) {						
					if (tabuleiro[i2][j2].getPeca() != null)
					{
//						System.out.println("tem peça no meio do roque");
						return;
					}
					j2++;
				}	
				positions=tabuleiro[i1][j1].catchMoves((int)larg*j1,(int)alt*i1);
				if (!VerifyCheck(i1,j1,positions))
				{
//					System.out.println("Rei esta em xeque");
					return;
				}
				positions=tabuleiro[i2][j2].catchMoves((int)larg*j2,(int)alt*i2);
				if (!VerifyCheck(i2,j2,positions))
				{
//					System.out.println("Rei vai esta em xeque se ocorrer o roque");
					return;
				}
				tabuleiro[i1][j1].setPeca(null);
				j1 -= 2;
				tabuleiro[i1][j1].setPeca(p0);
				j1+=1;
				tabuleiro[i2][j1].setPeca(p);
				tabuleiro[i2][0].setPeca(null);		
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
		else if (j2 == 7 && ((i2 == 7 && p.getCor() == Cor.Claro) || (i2 == 0 && p.getCor() == Cor.Escuro)))
		{
			if ((reimovE == false && torremovEc == false) || (reimovC == false && torremovCc == false))
			{
//				System.out.println("entrou no roque curto");
				//Roque Curto
				j2--;
				while (j1<j2) {						
					if (tabuleiro[i2][j2].getPeca() != null)
					{
//						System.out.println("tem peça no meio do roque");
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
		i=yPeao/alt;
		j=xPeao/larg;
		i=Math.floorDiv(yPeao,alt);
		j=Math.floorDiv(xPeao,larg);
		Cor c=tabuleiro[i][j].getPeca().getCor();
//		System.out.println("AAAAAAA "+s);
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
		 * Posicao rei claro
		 * Posicao rei escuro
		 * [0][0] booleano se tem peça tipo da peça cor da peca
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
		if (jogador)
		{
			file.println(1);
		}
		else
		{
			file.println(2);
		}
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
	public void Load() throws FileNotFoundException 
	{
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		chooser.setFileFilter(filter);
		 
		chooser.showOpenDialog(Tela.getInstanceTela());
		Scanner load=null;
		String aux,aux2;
		int jog;
//		System.out.println(chooser.getSelectedFile().getPath());
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
		
		jog=load.nextInt();
		if(jog==1)
		{
			jogador=true;
		}
		else 
		{
			jogador=false;
		}
		
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
//				System.out.println(aux+" "+aux2);
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
//		System.out.println("AAAAAAA "+arg0.getActionCommand());
//		System.out.println("Ta entrando");
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
		Peca p;
		if(tabuleiro[i][j].getPeca() instanceof Peao)
		{
			p=new Rainha(tabuleiro[i][j].getPeca().getCor());
			tabuleiro[i][j].setPeca(null);
			tabuleiro[i][j].setPeca(p);
		}
		
	}
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
	public boolean VerifyCheck(int i1,int j1,Vector<Pair> positions) //1 eh a posição q ele tá e 2 é a que ele vai
	{
		int ireitemp,jreitemp;
		int k=0,size,l=0;
		boolean gotmoves=false;
		int i2,j2;
		Peca p,peca,peca2;
		Vector<Pair> v;
		Vector<Integer> indices=new Vector<Integer>();
		peca=tabuleiro[i1][j1].getPeca();
		size=positions.size();
		ireitemp=0;
		jreitemp=0;
//		System.out.println("Size do vetor: " + size);
		for(l=0;l<size;l++)
		{
			i2=positions.get(l).getX();
			j2=positions.get(l).getY();
			peca2=tabuleiro[i2][j2].getPeca();
			tabuleiro[i1][j1].setPeca(null);
			tabuleiro[i2][j2].setPeca(peca);
			if(peca instanceof Rei)
			{
				if(peca.getCor()==Cor.Escuro)
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
			for(int i=0;i<8;i++)
			{
				for(int j=0;j<8;j++)
				{
					//				//Passar pelo tabuleiro todo vendo quais tem peca
					p=tabuleiro[i][j].getPeca();
					if(p!=null)
					{
						if(peca.getCor()!=p.getCor())
						{ 
							v=p.PossibleEats((int)larg*j,(int)alt*i);
//							System.out.println("Peca :"+p.getName()+ " Size: "+v.size());
							for(k=0;k<v.size();k++)
							{
								Pair comidoposition=new Pair(v.elementAt(k).getX(),v.elementAt(k).getY());
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
				if(peca.getCor()==Cor.Escuro)
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
			//        	System.out.println("Tem que remover: "+indices.get(i));
			positions.setElementAt(null,indices.get(i));
		}
		//        System.out.println(i1 +" "+j1);
		//        System.out.println(tabuleiro[i1][j1].getPeca().getName());
		gotmoves=verifyPositions(positions);
		return gotmoves;
	}
	public void Recomeca()
	{
		this.inicializaTudo();
		jogador=true;
		t.setChanged();
		t.notifyObservers();
	}
	private void CriaJPane(Cor c)
	{
		String s;
		if(this.empate==true)
		{
			s="EMPATE";
		}
		if(c==Cor.Escuro)
		{
			s="AS PECAS ESCURAS GANHARAM";
		}
		else
		{
			s="AS PECAS CLARAS GANHARAM";
		}
//		System.out.println("CHECK MATEEEEEE OTARIO");
		JOptionPane.showMessageDialog(null,
		        s, 
		        "Fim do Jogo", 
		        JOptionPane.INFORMATION_MESSAGE);
	}
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
//								System.out.println("POSITIONS");
								return false;
							}
							positions=tabuleiro[i][j].catchEats((int)larg*j,(int)alt*i);
							this.possiblecheckeats=this.VerifyCheck(i, j, positions);
							if(this.possiblecheckeats==true)
							{
//								System.out.println("EATS");
								return false;
							}
					}
				}
			}
		}
		return true;
	}
	
	public boolean VerifyEmpate(int i1,int j1,Cor c)
	{
//		for(int i=0;i<8;i++)
//		{
//			for(int j=0;j<8;j++)
//			{
//				Peca p=tabuleiro[i][j].getPeca();
//				if(p!=null)
//				{
//					if(p.getCor()==c)
//					{
//						Vector<Pair> v=p.PossibleEats(larg*j,alt*i);
//						for(int k=0;k<v.size();k++)
//						{
//							if(c==Cor.Escuro)
//							{
//								if(v.get(k).getX()==iReiC && v.get(k).getY()==iReiC)
//								{
//									return false;
//								}
//							}
//							else
//							{					
//								if(v.get(k).getX()==iReiE && v.get(k).getY()==iReiE)
//								{
//									return false;
//								}
//							}
//						}
//					}
//				}
//			}
//			
//		}
		return false;
	}
}
