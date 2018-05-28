package Tabuleiro;

import java.io.File;
import java.io.IOException;
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
		int larg=Celula.getLarg();
		int alt=Celula.getAlt();
		
		i1=y1/alt;
		j1=x1/larg;
		i2=y2/alt;
		j2=x2/larg;
		
		if (j1 != j2 || i1==i2) //Só se movimenta na mesma coluna
			return 0;
		
		if (i1 == 1 || i1 == 6) // está na primeira posicao, logo ainda não ocorreu lances
			lance = 0;	
		
		if (cor == Cor.Claro) { 
				if (i2 > i1)  //só se movimenta para frente
					return 0; 
				if (lance == 0 && i2 >= i1-2) //Prim lance do peao, ele pode andar 2 casas
				{
					lance++;
					return 1;			
				}
				else if (i2 >= i1-1) //depois do primeiro, só pode andar uma
				{
					lance++;
					return 1;	
				}
		}
		else if (cor == Cor.Escuro) 
		{
			if (i2 < i1)  //só se movimenta para frente
				return 0; 
			if (lance == 0 && i2 <= i1+2)
			{
				lance++;   //só atualiza o lance, se a jogada estiver correta
				return 1;
			}
			else if (i2 <= i1+1)
			{
				lance++;
				return 1;	
			}		
		}
		return 0; //não está dentro das regras
	}
}