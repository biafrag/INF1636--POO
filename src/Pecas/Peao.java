package Pecas;

import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.swing.*;
import java.awt.event.*;

import javax.imageio.ImageIO;

import Drawing.Cor;
import Tabuleiro.Pair;
import Tabuleiro.Tabuleiro;

public class Peao extends Peca {
	public Peao(Cor cor)
	{
		color = cor;
		CarregaImagem(cor);
	}
	protected void CarregaImagem(Cor cor)
	{
		if (cor == Cor.Escuro) 
		{
			try {
				image=ImageIO.read(new File(path + "PurpleP.png"));
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}
		}
		else if (cor == Cor.Claro)
		{
			try {
				image=ImageIO.read(new File(path + "CyanP.png"));
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}
		}

	}
	@Override	
	public Vector<Pair> CatchPossibleMovements(int x, int y) {
		//Peão só anda pra frente 1 casa ou 2 se for no primeiro movimento		
		Vector <Pair> positions = new Vector<Pair>();
		int i,j;
		i=y/alt;
		j=x/larg;
		//JPopupMenu popup = Tabuleiro.CriaPopup(x,y);
		if(this.color == Cor.Escuro )
		{
			if(Tabuleiro.TemPecaIndice(i+1,j)==false)
			{
				positions.add(new Pair(i+1,j));
			}
		/*	 System.out.println("i: "+i);
			if (i==6) {
				//MOSTRAR O POPUP MENU DE ESCOLHA
				 System.out.println("teste b");
				 popup.show(null,x,y);
				 System.out.println("teste c");
			}*/
			
		}
		else
		{
			if(Tabuleiro.TemPecaIndice(i-1,j)==false)
			{
				positions.add(new Pair(i-1,j));
			}
		}
		
		if(this.getCor()==Cor.Claro && i==6 && Tabuleiro.TemPecaIndice(i-1, j)==false)
		{
			if(Tabuleiro.TemPecaIndice(i-2,j)==false)
			{
				positions.add(new Pair(i-2,j));
			}

		}
		else if (this.getCor()==Cor.Escuro && i==1 && Tabuleiro.TemPecaIndice(i+1, j)==false)
		{
			if(Tabuleiro.TemPecaIndice(i+2,j)==false)
			{
				positions.add(new Pair(i+2,j));
				
			}
		}
		return positions;
	}
	
	public Vector<Pair> PossibleEats(int x, int y)
	{
		Vector <Pair> eats = new Vector<Pair>();
		int i,j;
		i=y/alt;
		j=x/larg;
		if(color==Cor.Escuro)
		{
			if (i!=7)
			{
				if(j==0)
				{
					if(Tabuleiro.TemPecaIndice(i+1, j+1) && Tabuleiro.getTabuleiro().getCelula(i+1, j+1).getPeca().getCor()==Cor.Claro)
					{
						eats.add(new Pair(i+1,j+1));
					}
				}
				else if(j==7)
				{
					if(Tabuleiro.TemPecaIndice(i+1, j-1) && Tabuleiro.getTabuleiro().getCelula(i+1, j-1).getPeca().getCor()==Cor.Claro)
					{
						eats.add(new Pair(i+1,j-1));	
					}
				}
				else
				{
					if(Tabuleiro.TemPecaIndice(i+1, j+1) && Tabuleiro.getTabuleiro().getCelula(i+1, j+1).getPeca().getCor()==Cor.Claro)
					{
						eats.add(new Pair(i+1,j+1));
					}
					if(Tabuleiro.TemPecaIndice(i+1, j-1) && Tabuleiro.getTabuleiro().getCelula(i+1, j-1).getPeca().getCor()==Cor.Claro)
					{
						eats.add(new Pair(i+1,j-1));	
					}
				}
				
			}
		}
		else
		{
			if (i!=0)
			{
				if(j==0)
				{
					if(Tabuleiro.TemPecaIndice(i-1, j+1) && Tabuleiro.getTabuleiro().getCelula(i-1, j+1).getPeca().getCor()==Cor.Escuro)
					{
						eats.add(new Pair(i-1,j+1));	
					}
					
				}
				else if(j==7)
				{
					if(Tabuleiro.TemPecaIndice(i-1, j-1) && Tabuleiro.getTabuleiro().getCelula(i-1, j-1).getPeca().getCor()==Cor.Escuro)
					{
						eats.add(new Pair(i-1,j-1));	
					}
				}
				else
				{
					if(Tabuleiro.TemPecaIndice(i-1, j+1) && Tabuleiro.getTabuleiro().getCelula(i-1, j+1).getPeca().getCor()==Cor.Escuro)
					{
						eats.add(new Pair(i-1,j+1));
					}
					if(Tabuleiro.TemPecaIndice(i-1, j-1) && Tabuleiro.getTabuleiro().getCelula(i-1, j-1).getPeca().getCor()==Cor.Escuro)
					{
						eats.add(new Pair(i-1,j-1));	
					}
				}
			}
		}
		return eats;
	}
}