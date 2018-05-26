package Tabuleiro;

import java.awt.Graphics2D;
import java.awt.Image;
import Drawing.Cor;
public abstract class Peca  {	
	
	static String path = "Imagens/";
		
	protected Image image;
	
	private Cor cor; //true branco e false preto
	
	public void Draw(Graphics2D a,int posX,int posY)
	{
		a.drawImage(image,posX,posY,null); //POR ENQUANTO IREI DEIXAR O TYPECAST		
	}
	
}
