package Tabuleiro;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import Drawing.Cor;

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
    public int ConfereRegraMov(int x1,int y1,int x2,int y2, Cor cor)
	{
	
	/* ela pode ir para frente ou para trás, para direita ou para a esquerda, ou na diagonal, 
	 * quantas casas quiser, mas não pode pular nenhuma outra peça.*/
		
		return 1;
	}
	@Override
	public Vector<Pair> CatchPossibleMovements(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Vector<Pair> PossibleEats(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
