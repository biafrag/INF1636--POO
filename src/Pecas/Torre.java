package Pecas;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import Drawing.Cor;
import Tabuleiro.Pair;
import Tabuleiro.TabuleiroFacade;

public class Torre extends Peca {
	
	public Torre(Cor cor)
	{
		color = cor;
		CarregaImagem(cor);
	}
	protected void CarregaImagem(Cor cor)
	{
		if (cor == Cor.Escuro) 
		{
			try {
				image=ImageIO.read(new File(path + "PurpleR.png"));
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}
		}
		else if (cor == Cor.Claro)
		{
			try {
				image=ImageIO.read(new File(path + "CyanR.png"));
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}
		}

	}	
	
	@Override
	public Vector<Pair> CatchPossibleMovements(int x, int y) {
		// TODO Auto-generated method stub
		Vector <Pair> positions = new Vector<Pair>();
		int i,j;
		i=y/alt;
		j=x/larg;
		if(j!=7)
		{
			for(int n=j+1;n<8;n++) //v� movimentos possiveis na mesma linha pra direita
			{
				if(TabuleiroFacade.getTFacade().TemPecaIndice(i,n)==false)
				{
				    positions.add(new Pair(i,n));
				}
				else
				{
					break;
				}
			}
		}
		if(j!=0)
		{
			for(int n=j-1;n>=0;n--)
			{
				if(TabuleiroFacade.getTFacade().TemPecaIndice(i,n)==false) //v� movimentos possiveis na mesma linha pra esquerda
				{
				    positions.add(new Pair(i,n));
				}
				else
				{
					break;
				}
			}
		}
		if(i!=7)
		{
			for(int n=i+1;n<8;n++) //v� movimentos possiveis na mesma coluna pra baixo
			{
				if(TabuleiroFacade.getTFacade().TemPecaIndice(n,j)==false)
				{
				   positions.add(new Pair(n,j));
				}
				else
				{
				   break;
				}
			}
		}		
		if(i!=0) 
		{
			for(int n=i-1;n>=0;n--) //v� movimentos possiveis na mesma coluna pra cima
			{
				if(TabuleiroFacade.getTFacade().TemPecaIndice(n,j)==false)
				{
				   positions.add(new Pair(n,j));
				}
				else
				{
			       break;
				}
			}
		}
		return positions;
	}
	@Override
	public Vector<Pair> PossibleEats(int x, int y) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Vector <Pair> eats = new Vector<Pair>();
		int i,j;
		i=y/alt;
		j=x/larg;
		for(int n=j+1;n<8;n++)
		{
			if(TabuleiroFacade.getTFacade().TemPecaIndice(i,n) )
			{
				if(TabuleiroFacade.getTFacade().getPecaCor(i, n)!=color)
				{
					eats.add(new Pair(i,n));	
				}
				break;
			}
		}
		for(int n=i+1;n<8;n++)
		{
			if(TabuleiroFacade.getTFacade().TemPecaIndice(n,j))
			{
			   if(TabuleiroFacade.getTFacade().getPecaCor(n, j)!=color)
			   {
				   eats.add(new Pair(n,j));
			   }
			   break;
			}
		}
		for(int n=i-1;n>=0;n--)
		{
			if(TabuleiroFacade.getTFacade().TemPecaIndice(n,j) )
			{
				if(TabuleiroFacade.getTFacade().getPecaCor(n, j)!=color)
				{
					 eats.add(new Pair(n,j));
				}
				break;
			}
		}
		for(int n=j-1;n>=0;n--)
		{
			if(TabuleiroFacade.getTFacade().TemPecaIndice(i,n) )
			{
				if(TabuleiroFacade.getTFacade().getPecaCor(i, n)!=color)
				{
					eats.add(new Pair(i,n));	
				}
				break;
			}
		}
		return eats;
	}
	public String getName()
	{
		return "Torre";
	}
	
}
	
