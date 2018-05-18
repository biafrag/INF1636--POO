package Drawing;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.*;

public class Draw extends JPanel{	
	private ListaImagens li;
	
	public Draw() {  } 

	public Draw(ListaImagens l) { li=l; } 
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		
		//Pega as imagens
	/*	Image []PeaoC = li.getPeaoC();
		Image []PeaoP = li.getPeaoP();
		Image []CavaloC = li.getCavaloC();
		Image []CavaloP = li.getCavaloP();
		Image []BispoC = li.getBispoC();
		Image []BispoP = li.getBispoP();
		Image []TorreC = li.getTorreC();
		Image []TorreP = li.getTorreP();
		Image []RainhaC = li.getRainhaC();
		Image []RainhaP = li.getRainhaP();
		Image []ReiC = li.getReiC();
		Image []ReiP = li.getReiP();*/
		
				
		// Desenha retângulo
		double leftX=0.0;
		double topY=00.0;
		double larg=1000/8.0;
		double alt=760/8.0;
		boolean branco=true;
	
		float color1 []=Color.RGBtoHSB(120,80,39, null);
		Color Color1=Color.getHSBColor(color1[0], color1[1], color1[2]);
		float color2 []=Color.RGBtoHSB(63,42,20, null);
		Color Color2=Color.getHSBColor(color2[0], color2[1], color2[2]);
	
		for(int i=0; i<8; i++)
		{
			leftX=0;
			for(int j=0; j<8; j++)
			{
				System.out.println(topY);
				Rectangle2D rt=new Rectangle2D.Double(leftX,topY,larg,alt);
				if(branco==true)
				{
					g2d.setPaint(Color1);
					g2d.fill(rt);
					branco=false;
				}
				else
				{
					g2d.setPaint(Color2);
					g2d.fill(rt);
					branco=true;
				}
				leftX+=larg;
			}
			topY+=alt;
			if(branco)
			{
				branco=false;
			}
			else
			{
				branco=true;
			}
			//System.out.println(topY);
		}
		
		//g.drawImage(TorreC[0],0,0,null);
	}
	//Fazer desenha Tabuleiro
	//Fazer desenha Peças
}
