package Tabuleiro;

import java.awt.Graphics2D;
import java.awt.Image;
import Drawing.Cor;
public abstract class Peca  {	
	
	static String url = "src/Imagens/";
		
	protected Image image;
	
	private Cor cor; //true branco e false preto
	
	public void Draw(Graphics2D a,double posX,double posY)
	{
		a.drawImage(image,(int)posX,(int)posY,null); //POR ENQUANTO IREI DEIXAR O TYPECAST		
	}
	
}
