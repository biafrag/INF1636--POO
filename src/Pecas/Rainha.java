package Pecas;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import Drawing.Cor;
import Tabuleiro.Pair;
import Tabuleiro.Tabuleiro;

public class Rainha extends Peca {
	public Rainha(Cor cor)
	{
		color = cor;
		CarregaImagem(cor);
	}
	protected void CarregaImagem(Cor cor)
	{
		if (cor == Cor.Escuro) 
		{
			try {
				image=ImageIO.read(new File(path + "PurpleQ.png"));
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}
		}
		else if (cor == Cor.Claro)
		{
			try {
				image=ImageIO.read(new File(path + "CyanQ.png"));
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}
		}

	}
	@Override
	public Vector<Pair> CatchPossibleMovements(int x, int y) {
		Vector <Pair> positions = new Vector<Pair>();
		int i,j;
		i=y/alt;
		j=x/larg;
		/*  pode ir para frente ou para trás, para direita ou para a esquerda, ou na diagonal, quantas
		 *  casas quiser, mas não pode pular nenhuma outra peça.*/
		if(j!=7)
		{
			for(int n=j+1;n<8;n++) //vê movimentos possiveis na mesma linha pra direita
			{
				if(Tabuleiro.getTabuleiro().TemPecaIndice(i,n)==false)
				{
				    positions.add(new Pair(i,n));
				}
				else
				{
					break;
				}
				
			}
			for(int n=1;i+n<8 && j+n<8;n++) //pega diagonal pra baixo e pra direita
			{
				if(Tabuleiro.getTabuleiro().TemPecaIndice(i+n,j+n)) 
				{
					break;
				}
				else
				{
					positions.add(new Pair(i+n,j+n));
				}

			}
			for(int n=1;i-n>=0 && j+n<8;n++) //pega diagonal pra cima pra direita
			{
				if(Tabuleiro.getTabuleiro().TemPecaIndice(i-n,j+n))
				{
					break;
				}
				else
				{
					positions.add(new Pair(i-n,j+n));
				}
			}
		}
		if(j!=0)
		{
			for(int n=j-1;n>=0;n--)
			{
				if(Tabuleiro.getTabuleiro().TemPecaIndice(i,n)==false) //vê movimentos possiveis na mesma linha pra esquerda
				{
				    positions.add(new Pair(i,n));
				}
				else
				{
					break;
				}
			}
			for(int n=1;i-n>=0 && j-n>=0;n++) // pega diagonal pra cima pra esquerda
			{
				if(Tabuleiro.getTabuleiro().TemPecaIndice(i-n,j-n))
				{
					break;
				}
				else
				{
					positions.add(new Pair(i-n,j-n));
				}
			}		
			for(int n=1;i+n<8 && j-n>=0;n++) //pega diagonal pra baixo e pra esquerda
			{
				if(Tabuleiro.getTabuleiro().TemPecaIndice(i+n,j-n))
				{
					break;
				}
				else
				{
					positions.add(new Pair(i+n,j-n));
				}
			}	
		}
		if(i!=7)
		{
			for(int n=i+1;n<8;n++) //vê movimentos possiveis na mesma coluna pra baixo
			{
				if(Tabuleiro.getTabuleiro().TemPecaIndice(n,j)==false)
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
			for(int n=i-1;n>=0;n--) //vê movimentos possiveis na mesma coluna pra cima
			{
				if(Tabuleiro.getTabuleiro().TemPecaIndice(n,j)==false)
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

		Vector <Pair> eats = new Vector<Pair>();
		int i,j;
		i=y/alt;
		j=x/larg;
		//Verifica na diagonal
		for(int n=1;i+n<8 && j+n<8;n++)
		{
			if(Tabuleiro.getTabuleiro().TemPecaIndice(i+n,j+n))
			{
				if(color!=Tabuleiro.getTabuleiro().getCelula(i+n,j+n).getPeca().getCor())
				{
					eats.add(new Pair(i+n,j+n));
				}
				break;
			}

		}
		for(int n=1;i-n>=0 && j-n>=0;n++)
		{
			if(Tabuleiro.getTabuleiro().TemPecaIndice(i-n,j-n) )
			{
				if(color!=Tabuleiro.getTabuleiro().getCelula(i-n,j-n).getPeca().getCor())
				{
					eats.add(new Pair(i-n,j-n));	
				}
				break;
			}
		}
		for(int n=1;i-n>=0 && j+n<8;n++)
		{
			if(Tabuleiro.getTabuleiro().TemPecaIndice(i-n,j+n))
			{
				if( color!=Tabuleiro.getTabuleiro().getCelula(i-n,j+n).getPeca().getCor())
				{
					eats.add(new Pair(i-n,j+n));
				}
				break;
			}
		}
		for(int n=1;i+n<8 && j-n>=0;n++)
		{
			if(Tabuleiro.getTabuleiro().TemPecaIndice(i+n,j-n))
			{
				if(color!=Tabuleiro.getTabuleiro().getCelula(i+n,j-n).getPeca().getCor())
				{
					eats.add(new Pair(i+n,j-n));
				}
				break;
			}
		}
		//verifica direita/esquerda na mesma linha e cima/baixo na mesma coluna
		for(int n=j+1;n<8;n++)
		{
			if(Tabuleiro.getTabuleiro().TemPecaIndice(i,n))
			{
				if((Tabuleiro.getTabuleiro().getCelula(i, n).getPeca().getCor()!=color))
				{
					eats.add(new Pair(i,n));	
				}
				break;
			}
		}
		for(int n=j-1;n>=0;n--)
		{
			if(Tabuleiro.getTabuleiro().TemPecaIndice(i,n))
			{
				if((Tabuleiro.getTabuleiro().getCelula(i, n).getPeca().getCor()!=color))
				{
					eats.add(new Pair(i,n));
				}
				break;
			}
		}
		for(int n=i+1;n<8;n++)
		{
			if(Tabuleiro.getTabuleiro().TemPecaIndice(n,j))
			{
			   if(Tabuleiro.getTabuleiro().getCelula(n, j).getPeca().getCor()!=color)
			   {
				   eats.add(new Pair(n,j));
			   }
			   break;
			}
		}
		for(int n=i-1;n>=0;n--)
		{
			if(Tabuleiro.getTabuleiro().TemPecaIndice(n,j) )
			{
				if((Tabuleiro.getTabuleiro().getCelula(n, j).getPeca().getCor()!=color))
				{
					 eats.add(new Pair(n,j));
				}
				break;
			}
		}
		return eats;
	}
	public String getName()
	{
		return "Rainha";
	}
}
