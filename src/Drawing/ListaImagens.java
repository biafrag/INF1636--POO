package Drawing;

import java.awt.*;
import java.io.*;
import javax.imageio.*;

public class ListaImagens {
	private Image []PeaoC = new Image[8];
	private Image []PeaoP = new Image[8];
	private Image []CavaloC = new Image[10]; //começa com 2 e tem mais 8 peoes que podem se tornar nele
	private Image []CavaloP = new Image[10];
	private Image []BispoC = new Image[10];
	private Image []BispoP = new Image[10];
	private Image []TorreC = new Image[10];
	private Image []TorreP = new Image[10];
	private Image []RainhaC = new Image[9]; //começa com 1 e tem mais 8 peoes que podem se tornar nela
	private Image []RainhaP = new Image[9];
	private Image []ReiC = new Image[2]; //só tem um rei -- usa vetor?
	private Image []ReiP = new Image[2];
		
	private Image pC, pP, cC, cP, bC, bP, tC, tP, raC, raP, rC, rP;
	
	int tampC, tampP, tamcC, tamcP, tambC, tambP, tamtC, tamtP, tamraC, tamraP, tamrC, tamrP;
	
	//TEM COMO FAZER SEM PRECISAR DO CAMINHO?
	String url = "C:\\Users\\bianc\\OneDrive\\Documentos\\GitHub\\INF1636--POO\\bin\\Pecas\\";
	
	public ListaImagens() {
		try {
			pC=ImageIO.read(new File(url + "CyanP"));
			pP=ImageIO.read(new File(url + "PurpleP"));
			cC=ImageIO.read(new File(url + "CyanN"));
			cP=ImageIO.read(new File(url + "PurpleN"));
			bC=ImageIO.read(new File(url + "CyanB"));
			bP=ImageIO.read(new File(url + "PurpleB"));
			tC=ImageIO.read(new File(url + "CyanR"));
			tP=ImageIO.read(new File(url + "PurpleR"));
			raC=ImageIO.read(new File(url + "CyanQ"));
			raP=ImageIO.read(new File(url + "PurpleQ"));
			rC=ImageIO.read(new File(url + "CyanK"));
			rP=ImageIO.read(new File(url + "PurpleK"));
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	
	void addPeaoC() {
		PeaoC[tampC]=pC;
		tampC++;
	}
	void addPeaoP() {
		PeaoP[tampP]=pP;
		tampP++;
	}
	void addCavaloC() {
		CavaloC[tamcC]=cC;
		tamcC++;
	}
	void addCavaloP() {
		CavaloP[tamcP]=cP;
		tamcP++;
	}
	void addBispoC() {
		BispoC[tambC]=bC;
		tambC++;
	}
	void addBispoP() {
		BispoP[tambP]=bP;
		tambP++;
	}
	void addTorreC() {
		TorreC[tamtC]=tC;
		tamtC++;
	}
	void addTorreP() {
		TorreP[tamtP]=tP;
		tamtP++;
	}
	void addRainhaC() {
		RainhaC[tamraC]=raC;
		tamraC++;
	}
	void addRainhaP() {
		RainhaP[tamraP]=raP;
		tamraP++;
	}
	void addReiC() {
		ReiC[tamrC]=rC;
		tamrC++;
	}
	void addReiP() {
		ReiP[tamrP]=rP;
		tamrP++;
	}
	
	Image []getPeaoC() { return PeaoC; }	
	Image []getPeaoP() { return PeaoP; }
	Image []getCavaloC() { return CavaloC; }	
	Image []getCavaloP() { return CavaloP; }
	Image []getBispoC() { return BispoC; }	
	Image []getBispoP() { return BispoP; }	
	Image []getTorreC() { return TorreC; }	
	Image []getTorreP() { return TorreP; }	
	Image []getRainhaC() { return RainhaC; }	
	Image []getRainhaP() { return RainhaP; }
	Image []getReiC() { return ReiC; }
	Image []getReiP() { return ReiP; }

}
