package Tabuleiro;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import Drawing.Cor;

public class Rei extends Peca {

	public Rei(Cor cor)
	{
		color = cor;
		CarregaImagem(cor);
	}
	protected void CarregaImagem(Cor cor)
	{
		if (cor == Cor.Escuro) 
		{
			try {
				image=ImageIO.read(new File(path + "PurpleK.png"));
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}
		}
		else if (cor == Cor.Claro)
		{
			try {
				image=ImageIO.read(new File(path + "CyanK.png"));
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
		
		/* movimenta-se apenas 1 casa em qualquer direção. Nunca pode se movimentar para uma casa que 
		 * esteja sob ataque ou capturar uma peça que esteja defendida por uma peça adversária.  */
		if (i!=7)
		{
			if(Tabuleiro.TemPecaIndice(i+1,j)==false) //para baixo
			{
				positions.add(new Pair(i+1,j));
			}
		}
		if (i!=0) 
		{
			if(Tabuleiro.TemPecaIndice(i-1,j)==false) //para cima
			{
				positions.add(new Pair(i-1,j));
			}
		}
		if (j!=7)
		{
			if(Tabuleiro.TemPecaIndice(i,j+1)==false) //para direita
			{
				positions.add(new Pair(i,j+1));
			}
			if (i!=0)
			{
				if(Tabuleiro.TemPecaIndice(i-1,j+1)==false) //diagonal para cima e para direita
				{
					positions.add(new Pair(i-1,j+1));
				}
			}
			if (i!=7)
			{
				if(Tabuleiro.TemPecaIndice(i+1,j+1)==false) //diagonal para baixo e para direita
				{
					positions.add(new Pair(i+1,j+1));
				}
			}
		}
		if (j!=0)
		{
			if(Tabuleiro.TemPecaIndice(i,j-1)==false) //para esquerda
			{
				positions.add(new Pair(i,j-1));
			}
			if (i!=0)
			{
				if(Tabuleiro.TemPecaIndice(i-1,j-1)==false) //diagonal para cima e para esquerda
				{
					positions.add(new Pair(i-1,j-1));
				}
			}
			if (i!=7)
			{
				if(Tabuleiro.TemPecaIndice(i+1,j-1)==false) //diagonal para baixo e para esquerda
				{
					positions.add(new Pair(i+1,j-1));
				}
			}
		}		
		
//		for(int n = 1;n<4 && j+n<8 && i+1<8;n++)
//		{
//			if(Tabuleiro.TemPeca(i+1, j+n)==false)
//			{
//				positions.add(new Pair(i+1,j+n));
//			}
//		}
//		for(int n = 2;n<4 && i+n<8 && j-1>=0;n++)
//		{
//			if(Tabuleiro.TemPeca(i+n, j-1)==false)
//			{
//				positions.add(new Pair(i+n,j-n));
//			}
//		}
//		for(int n = 2;n<4 && j+n<8 && i+1<8;n++)
//		{
//			if(Tabuleiro.TemPeca(i+n, j+3)==false)
//			{
//				positions.add(new Pair(i+n,j+3));
//			}
//		}
//		for(int n = 2;n<3 && j+n<8 && i+1<8;n++)
//		{
//			if(Tabuleiro.TemPeca(i+3, j+n)==false)
//			{
//				positions.add(new Pair(i+3,j+n));
//			}
//		}
		return positions;
	}
	@Override
	public Vector<Pair> PossibleEats(int x, int y) {
		Vector <Pair> eats = new Vector<Pair>();
		int i,j;
		i=y/alt;
		j=x/larg;
		if (i!=0)
		{
			if(Tabuleiro.TemPecaIndice(i-1,j)) 
			{
				if((Tabuleiro.getTabuleiro().getCelula(i-1, j).getPeca().getCor()!=color))
				{
					eats.add(new Pair(i-1,j));	
				}	
			}
			if(Tabuleiro.TemPecaIndice(i-1,j+1)) //diagonal para cima e para direita
			{
				if((Tabuleiro.getTabuleiro().getCelula(i-1, j+1).getPeca().getCor()!=color))
				{
					eats.add(new Pair(i-1,j+1));	
				}
			}
			if (j!=0)
			{
				if(Tabuleiro.TemPecaIndice(i-1,j-1)) //diagonal para cima e para esquerda
				{
					if((Tabuleiro.getTabuleiro().getCelula(i-1, j-1).getPeca().getCor()!=color))
					{
						eats.add(new Pair(i-1,j-1));	
					}
				}
			}
		}
		if (j!=0)
		{
			if(Tabuleiro.TemPecaIndice(i,j-1)) //para esquerda
			{
				if((Tabuleiro.getTabuleiro().getCelula(i, j-1).getPeca().getCor()!=color))
				{
					eats.add(new Pair(i,j-1));	
				}
			}			
			if(Tabuleiro.TemPecaIndice(i+1,j-1)) //diagonal para baixo e para esquerda
			{
				if((Tabuleiro.getTabuleiro().getCelula(i+1, j-1).getPeca().getCor()!=color))
				{
					eats.add(new Pair(i+1,j-1));	
				}
			}		
		}		
		if(Tabuleiro.TemPecaIndice(i,j+1)) 
		{
			if((Tabuleiro.getTabuleiro().getCelula(i, j+1).getPeca().getCor()!=color))
			{
				eats.add(new Pair(i,j+1));	
			}
		}
		if(Tabuleiro.TemPecaIndice(i+1,j)) 
		{
			if((Tabuleiro.getTabuleiro().getCelula(i+1, j).getPeca().getCor()!=color))
			{
				eats.add(new Pair(i+1,j));	
			}
		}
		if(Tabuleiro.TemPecaIndice(i+1,j+1)) 
		{
			if((Tabuleiro.getTabuleiro().getCelula(i+1, j+1).getPeca().getCor()!=color))
			{
				eats.add(new Pair(i+1,j+1));	
			}
		}		
		return eats;
	}	
}
	
