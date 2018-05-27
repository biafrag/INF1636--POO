package Tabuleiro;

import java.awt.Graphics2D;
import java.awt.Image;

import Drawing.Cor;

public abstract class Peca  {		
	static String path = "Imagens/";
		
	protected Image image;
	
	Cor color; //claro cyan escuro purple
	
	public void Draw(Graphics2D a,int posX,int posY)
	{
		a.drawImage(image,posX,posY,null); //POR ENQUANTO IREI DEIXAR O TYPECAST		
	}
	
	public Cor getCor() {
		return color;
	}
	
	public int ConfereMov (Object objeto,int x1,int y1,int x2,int y2, Cor cor) {
		if (objeto instanceof Bispo) 
	       return Bispo.ConfereRegraMov(x1,y1,x2,y2,cor);
	    else if (objeto instanceof Cavalo)
	       return Cavalo.ConfereRegraMov(x1,y1,x2,y2,cor);
	    else if (objeto instanceof Peao)
		       return Peao.ConfereRegraMov(x1,y1,x2,y2,cor);
	    else if (objeto instanceof Rainha)
		       return Rainha.ConfereRegraMov(x1,y1,x2,y2,cor);
	    else if (objeto instanceof Rei)
		       return Rei.ConfereRegraMov(x1,y1,x2,y2,cor);
	    else if (objeto instanceof Torre)
		       return Torre.ConfereRegraMov(x1,y1,x2,y2,cor);;
	    return 1;
	}
}
