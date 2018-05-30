package Tabuleiro;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import Drawing.Cor;

public class Peao extends Peca {
	int lance = 0;
	public Peao(Cor cor)
	{
		color = cor;
		CarregaImagem(cor);
	}
	protected void CarregaImagem(Cor cor)
	{
		if (cor == Cor.Escuro) 
		{
			try {
				image=ImageIO.read(new File(path + "PurpleP.png"));
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}
		}
		else if (cor == Cor.Claro)
		{
			try {
				image=ImageIO.read(new File(path + "CyanP.png"));
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
		
		i1=y1/alt;
		j1=x1/larg;
		i2=y2/alt;
		j2=x2/larg;
		
		if (j1 != j2 || i1==i2) //S� se movimenta na mesma coluna
			return 0;
		
		if (i1 == 1 || i1 == 6) // est� na primeira posicao, logo ainda n�o ocorreu lances
			lance = 0;	
		
		if (cor == Cor.Claro) { 
				if (i2 > i1)  //s� se movimenta para frente
					return 0; 
				if (lance == 0 && i2 >= i1-2) //Prim lance do peao, ele pode andar 2 casas
				{
					lance++;
					return 1;			
				}
				else if (i2 >= i1-1) //depois do primeiro, s� pode andar uma
				{
					lance++;
					return 1;	
				}
		}
		else if (cor == Cor.Escuro) 
		{
			if (i2 < i1)  //s� se movimenta para frente
				return 0; 
			if (lance == 0 && i2 <= i1+2)
			{
				lance++;   //s� atualiza o lance, se a jogada estiver correta
				return 1;
			}
			else if (i2 <= i1+1)
			{
				lance++;
				return 1;	
			}		
		}
		return 0; //n�o est� dentro das regras
	}
	@Override
	public Vector<Pair> CatchPossibleMovements(int x, int y) {
		//Pe�o s� anda pra frente 1 casa ou 2 se for no primeiro movimento

		Vector <Pair> positions = new Vector<Pair>();
		int i,j;
		i=y/alt;
		j=x/larg;
		if(this.color == Cor.Escuro)
		{
			positions.add(new Pair(i+1,j));
			
		}
		else
		{
			positions.add(new Pair(i-1,j));
		}
		
		if((this.getCor()==Cor.Claro && i==6))
		{
			positions.add(new Pair(i-2,j));

		}
		else if (this.getCor()==Cor.Escuro && i==1)
		{
			positions.add(new Pair(i+2,j));
		}
		return positions;
	}
}