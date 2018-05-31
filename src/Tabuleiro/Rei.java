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
//		return positions;
		return null;
	}
	@Override
	public Vector<Pair> PossibleEats(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
	
