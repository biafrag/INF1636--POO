package Tabuleiro;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import Drawing.Cor;
import Tabuleiro.Tabuleiro;


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
	public int ConfereRegraMov(int x1,int y1,int x2,int y2, Cor cor)
	{
		int i1,i2,j1,j2;
		int larg=Celula.getLarg();
		int alt=Celula.getAlt();
		int i;
		
		i1=y1/alt;
		j1=x1/larg;
		i2=y2/alt;
		j2=x2/larg;
		
		if (i1 != i2 && j1 != j2) //não se move na diagonal
			return 0;
		
		if (cor == Cor.Escuro) {
			for (i = i1+1; i<=i2; i++) {
				if (Tabuleiro.TemPeca(x1, i*alt) == true)	
					return 0;				
			}
			for (i = j1+1; i<=j2; i++) {
				if (Tabuleiro.TemPeca(i*larg, y1) == true)			
					return 0;
			}
		}
		else if (cor == Cor.Claro) {
			for (i = i1-1; i>=i2; i--) {
				if (Tabuleiro.TemPeca(x1, i*alt) == true)			
					return 0;
			}
			for (i = j1-1; i>=j2; i--) {
				if (Tabuleiro.TemPeca(i*larg, y1) == true)			
					return 0;
			}
		} 
			
		return 1;
	}
	@Override
	public Vector<Pair> CatchPossibleMovements(int x, int y) {
		// TODO Auto-generated method stub
		Vector <Pair> positions = new Vector<Pair>();
		int i,j;
		i=y/alt;
		j=x/larg;
		for(int n=j+1;n<8;n++)
		{
			if(Tabuleiro.TemPecaIndice(i,n)==false)
				{
				    positions.add(new Pair(i,n));
				}
			else
			{
				break;
			}
		}
		if(color==Cor.Escuro)
		{
			for(int n=i+1;n<8;n++)
			{
				if(Tabuleiro.TemPecaIndice(n,j)==false)
					{
					    positions.add(new Pair(n,j));
					}
				else
				{
					break;
				}
			}
		}

		else
		{
			for(int n=i-1;n>=0;n--)
			{
				if(Tabuleiro.TemPecaIndice(n,j)==false)
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
	
}
	
