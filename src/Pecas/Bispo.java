package Pecas;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import Drawing.Cor;
import Tabuleiro.Pair;
import Tabuleiro.Tabuleiro;

public class Bispo extends Peca {
	public Bispo(Cor cor)
	{
		color = cor;
		CarregaImagem(cor);
	}
	protected void CarregaImagem(Cor cor)
	{
		if (cor == Cor.Escuro) 
		{
			try {
				image=ImageIO.read(new File(path + "PurpleB.png"));
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}
		}
		else if (cor == Cor.Claro)
		{
			try {
				image=ImageIO.read(new File(path + "CyanB.png"));
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
		/*Bispo se movimenta nas diagonais*/
		
		for(int n=1;i+n<8 && j+n<8;n++) //pega diagonal pra baixo e pra direita
		{
			if(Tabuleiro.TemPecaIndice(i+n,j+n)) // se tiver peça n dá mais pra andar
			{
				break;
			}
			else
			{
				positions.add(new Pair(i+n,j+n));
			}

		}
		for(int n=1;i-n>=0 && j-n>=0;n++) // pega diagonal pra cima pra esquerda
		{
			if(Tabuleiro.TemPecaIndice(i-n,j-n))
			{
				break;
			}
			else
			{
				positions.add(new Pair(i-n,j-n));
			}
		}
		for(int n=1;i-n>=0 && j+n<8;n++) //pega diagonal pra cima pra direita
		{
			if(Tabuleiro.TemPecaIndice(i-n,j+n))
			{
				break;
			}
			else
			{
				positions.add(new Pair(i-n,j+n));
			}
		}
		for(int n=1;i+n<8 && j-n>=0;n++) //pega diagonal pra baixo e pra esquerda
		{
			if(Tabuleiro.TemPecaIndice(i+n,j-n))
			{
				break;
			}
			else
			{
				positions.add(new Pair(i+n,j-n));
			}
		}
		return positions;
	}
	@Override
	public Vector<Pair> PossibleEats(int x, int y) {
		// TODO Auto-generated method stub
		Vector <Pair> eats = new Vector<Pair>();
		int i,j;
		i=y/alt;
		j=x/larg;
		for(int n=1;i+n<8 && j+n<8;n++)
		{
			if(Tabuleiro.TemPecaIndice(i+n,j+n))
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
			if(Tabuleiro.TemPecaIndice(i-n,j-n) )
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
			if(Tabuleiro.TemPecaIndice(i-n,j+n))
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
			if(Tabuleiro.TemPecaIndice(i+n,j-n))
			{
				if(color!=Tabuleiro.getTabuleiro().getCelula(i+n,j-n).getPeca().getCor())
				{
					eats.add(new Pair(i+n,j-n));
				}
				break;
			}
		}
		return eats;
	}

}
