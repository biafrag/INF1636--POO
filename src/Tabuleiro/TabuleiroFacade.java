package Tabuleiro;

import java.awt.Component;
import java.awt.Graphics2D;
import java.util.Observable;

import javax.swing.JPopupMenu;

import Drawing.Cor;
//import Interaction.Mouse;
import Tabuleiro.Tabuleiro;

public class TabuleiroFacade extends Observable {
	private static TabuleiroFacade tfa;
	private static Tabuleiro t; 
	private TabuleiroFacade() {
		t = Tabuleiro.getTabuleiro();
	}
	public static TabuleiroFacade getTFacade()
	{
		if(tfa==null)
		{
			tfa=new TabuleiroFacade();
		}
		return tfa;
	}
	public void VerificaMovimentosPossiveis(int x, int y)
	{
		if (t.TemPeca(x, y) == false) 
		{
			System.out.println("nao tem peca no 1 click");
			return;
		}
		else
		{
			t.Acende(x, y);	
			t.CatchPossibleMoves(x, y);
			t.CatchPossibleEats(x, y);
			this.setChanged();	
			notifyObservers();
		}
	}
	public void RealizaJogada(int x1, int y1, int x2, int y2)
	{
		if (t.TemPeca(x2, y2) == false)
		{
			t.MexePeca(x1,y1,x2,y2);
			this.setChanged();
			notifyObservers();
		}	
		else 
		{
			t.ComePeca(x1,y1,x2,y2);
			this.setChanged();
			notifyObservers();
		}								
	}
	public void PromovePeao(int x2, int y2, Component component, int x, int y)
	{
		if(t.getPeaoChange()==true)
		{
			JPopupMenu popup=t.CriaPopup();
			t.setPositionPeao(x2,y2);
			popup.show(component,x,y); 	
			t.verifyChange();
			t.setPeaoChange(false);
		}
	}
	public void SalvaJogo(Component component, int x, int y)
	{
		JPopupMenu popupsave=t.CriaPopupSave();
		popupsave.show(component,x,y); 
	}
	public void Draw(Graphics2D g2d) {
		t.Draw(g2d);
	}
	
	public boolean TemPecaIndice(int i, int j)
	{
		return t.TemPecaIndice(i, j);
	}
	public Cor getPecaCor(int i,int j)
	{
		return Tabuleiro.getTabuleiro().getCelula(i,j).getPeca().getCor();
	}
	
}
