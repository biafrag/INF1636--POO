package Tabuleiro;
import static Tabuleiro.Peca.larg;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import Drawing.Cor;
public abstract class Peca  {		
	static String path = "Imagens/";
	protected static int larg=Celula.getLarg();
	protected static int alt=Celula.getAlt();
	protected Image image;
	
	Cor color; //claro cyan escuro purple
	
	public void Draw(Graphics2D a,int posX,int posY)
	{
		a.drawImage(image,posX,posY,null); //POR ENQUANTO IREI DEIXAR O TYPECAST		
	}
	
	public Cor getCor() {
		return color;
	}
	protected abstract void CarregaImagem(Cor cor);
	public abstract int ConfereRegraMov(int x1,int y1,int x2,int y2, Cor cor);
	public abstract Vector<Pair> CatchPossibleMovements(int x,int y); 
}
