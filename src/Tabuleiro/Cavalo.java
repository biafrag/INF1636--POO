package Tabuleiro;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import Drawing.Cor;


public class Cavalo extends Peca {

	public Cavalo(Cor cor)
	{
		color = cor;
		CarregaImagem(cor);
	}
	protected void CarregaImagem(Cor cor)
	{
		if (cor == Cor.Escuro) 
		{
			try {
				image=ImageIO.read(new File(path+ "PurpleN.png"));
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}
		}
		else if (cor == Cor.Claro)
		{
			try {
				image=ImageIO.read(new File(path + "CyanN.png"));
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}
		}

	}
	
	public int ConfereRegraMov(int x1,int y1,int x2,int y2, Cor cor)
	{
	
	/*  O cavalo se movimenta 2 casas para frente ou para trás e em seguida 1 casa para a direita 
	 * ou para a esquerda, ou 2 casas para a direita ou para a esquerda e em seguida 1 casa para 
	 * frente ou para trás. Pode pular outras pecas*/
		
		return 1;
	}
	@Override
	public Vector<Pair> CatchPossibleMovements(int x, int y) {
		// TODO Auto-generated method stub
		Vector <Pair> position = new Vector<Pair>();
		int i,j;
		i=y/alt;
		j=x/larg;
		if(i+1<8 && j+2<8)
		{
			if(Tabuleiro.TemPecaIndice(i+1,j+2)==false)
			{
				position.add(new Pair(i+1,j+2));
			}
		}
		if(i+2<8 && j+1<8)
		{
			if(Tabuleiro.TemPecaIndice(i+2,j+1)==false)
			{
				position.add(new Pair(i+2,j+1));
			}		
		}
		if(i-1>=0 && j+2<8)
		{
			if(Tabuleiro.TemPecaIndice(i-1,j+2)==false)
			{
				position.add(new Pair(i-1,j+2));
			}	
		}
		if(i-2>=0 && j+1<8)
		{
			if(Tabuleiro.TemPecaIndice(i-2,j+1)==false)
			{
				position.add(new Pair(i-2,j+1));
			}		
		}
		if(i+1<8 && j-2>=0)
		{
			if(Tabuleiro.TemPecaIndice(i+1,j-2)==false)
			{
				position.add(new Pair(i+1,j-2));
			}	
		}
		if(i+2<8 && j-1>=0)
		{
			if(Tabuleiro.TemPecaIndice(i+2,j-1)==false)
			{
				position.add(new Pair(i+2,j-1));
			}
			
		}
		if(i-2>=0 && j-1>=0)
		{
			if(Tabuleiro.TemPecaIndice(i-2,j-1)==false)
			{
				position.add(new Pair(i-2,j-1));
			}
			
		}
		if(i-1>=0 && j-2>=0)
		{
			if(Tabuleiro.TemPecaIndice(i-1,j-2)==false)
			{
				position.add(new Pair(i-1,j-2));
			}
			
		}
		return position;
	}
	@Override
	public Vector<Pair> PossibleEats(int x, int y) {
		// TODO Auto-generated method stub
		Vector <Pair> eats = new Vector<Pair>();
		int i,j;
		i=y/alt;
		j=x/larg;
		if(i+1<8 && j+2<8)
		{
			if(Tabuleiro.TemPecaIndice(i+1,j+2) && Tabuleiro.getTabuleiro().getCelula(i+1, j+2).getPeca().getCor()!=color)
			{
				eats.add(new Pair(i+1,j+2));
			}
		}
		if(i+2<8 && j+1<8)
		{
			if(Tabuleiro.TemPecaIndice(i+2,j+1) && Tabuleiro.getTabuleiro().getCelula(i+2, j+1).getPeca().getCor()!=color)
			{
				eats.add(new Pair(i+2,j+1));
			}		
		}
		if(i-1>=0 && j+2<8)
		{
			if(Tabuleiro.TemPecaIndice(i-1,j+2) &&  Tabuleiro.getTabuleiro().getCelula(i-1, j+2).getPeca().getCor()!=color)
			{
				eats.add(new Pair(i-1,j+2));
			}	
		}
		if(i-2>=0 && j+1<8)
		{
			if(Tabuleiro.TemPecaIndice(i-2,j+1) &&  Tabuleiro.getTabuleiro().getCelula(i-2, j+1).getPeca().getCor()!=color)
			{
				eats.add(new Pair(i-2,j+1));
			}		
		}
		if(i+1<8 && j-2>=0)
		{
			if(Tabuleiro.TemPecaIndice(i+1,j-2) && Tabuleiro.getTabuleiro().getCelula(i+1, j-2).getPeca().getCor()!=color)
			{
				eats.add(new Pair(i+1,j-2));
			}	
		}
		if(i+2<8 && j-1>=0)
		{
			if(Tabuleiro.TemPecaIndice(i+2,j-1) && Tabuleiro.getTabuleiro().getCelula(i+2, j-1).getPeca().getCor()!=color)
			{
				eats.add(new Pair(i+2,j-1));
			}
			
		}
		if(i-2>=0 && j-1>=0)
		{
			if(Tabuleiro.TemPecaIndice(i-2,j-1) && Tabuleiro.getTabuleiro().getCelula(i-2, j-1).getPeca().getCor()!=color)
			{
				eats.add(new Pair(i-2,j-1));
			}
			
		}
		if(i-1>=0 && j-2>=0)
		{
			if(Tabuleiro.TemPecaIndice(i-1,j-2) && Tabuleiro.getTabuleiro().getCelula(i-1, j-2).getPeca().getCor()!=color)
			{
				eats.add(new Pair(i-1,j-2));
			}
			
		}
		return eats;
	}

	
	
}
	
