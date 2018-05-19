package Tabuleiro;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Torre extends Pecas {
	
	public static Image CarregaImagem(int Jogador)
	{
		if (Jogador == 1) 
		{
			try {
				a=ImageIO.read(new File(url + "PurpleR.png"));
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}
		}
		else if (Jogador == 2)
		{
			try {
				a=ImageIO.read(new File(url + "CyanR.png"));
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}
		}
		
		return a;
	}
	
	public static void Draw(Graphics2D a,double posX,double posY, int Jogador)
	{
		a.drawImage(CarregaImagem(Jogador),(int)posX,(int)posY,null); //POR ENQUANTO IREI DEIXAR O TYPECAST		
	}
	
}
