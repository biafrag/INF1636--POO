package Tabuleiro;

import java.io.File;
import java.io.IOException;

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
	
	
}
	
