package Tabuleiro;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Drawing.Cor;

public class Rei extends Peca {

	public Rei(Cor cor)
	{
		color = cor;
		CarregaImagem(cor);
	}
	private void CarregaImagem(Cor cor)
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
	
	static int ConfereRegraMov(int x1,int y1,int x2,int y2, Cor cor)
	{

		/* movimenta-se apenas 1 casa em qualquer direção. O Rei nunca pode se movimentar para uma 
		 * casa que esteja sob ataque ou capturar uma peça que esteja defendida por uma peça 
		 * adversária. AINDA FALTA OS MOVIMENTOS ESPECIAIS*/
		
		int i1,i2,j1,j2;
		int larg=Celula.getLarg();
		int alt=Celula.getAlt();

		i1=y1/alt;
		j1=x1/larg;
		i2=y2/alt;
		j2=x2/larg;
		
		/* i1 5 i2 4 j3 6 j1 1 j2 2 j3 0
		 * i2=4 <= i1-1 =4
		 * i3=6 <= i1+! =6
		 * j3=0 <= j1-1 =0
		 * j2=2 <= j1+1 =2
		 * 
		 */
		if (cor == Cor.Claro) { 
			if ((i2 > i1-1 || i2 > i1+1) || (j2 > j1+1 || j2 > j1-1)) // só pode andar uma casa
				return 0;
		}
		else if (cor == Cor.Escuro) 
		{
			if ((i2 < i1-1 || i2 < i1+1) || (j2 < j1+1 || j2 < j1-1)) // só pode andar uma casa
				return 0;	
		}		
		
		return 1;
	}
	
}
	
