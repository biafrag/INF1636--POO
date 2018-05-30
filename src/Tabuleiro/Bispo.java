package Tabuleiro;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import Drawing.Cor;

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
	public int ConfereRegraMov(int x1,int y1,int x2,int y2, Cor cor)
	{
		int i1,i2,j1,j2;
		int larg=Celula.getLarg();
		int alt=Celula.getAlt();
		
		i1=y1/alt;
		j1=x1/larg;
		i2=y2/alt;
		j2=x2/larg;
		
		if (i1 == i2) //só se move na diagonal
			return 0;
		else if (j1 == j2)
			return 0;
		
		//FALTA VERIFICAR SE A COR INICIAL DO TABULEIRO É A MESMA DO FINAL
		//FALTA VERIFICAR SE TA PULANDO ALGUMA PEÇA
		
		return 1;
	}
	@Override
	public Vector<Pair> CatchPossibleMovements(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

}
