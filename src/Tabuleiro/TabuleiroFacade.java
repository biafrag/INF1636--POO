package Tabuleiro;

import java.awt.Component;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JPopupMenu;

import Tabuleiro.Tabuleiro;

public class TabuleiroFacade {
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
		}
	}
	public void RealizaJogada(int x1, int y1, int x2, int y2)
	{
		boolean check=false;
		if (t.TemPeca(x2, y2) == false)
		{
			check=t.MexePeca(x1,y1,x2,y2);
		}	
		else 
		{
			check=t.ComePeca(x1,y1,x2,y2);
		}	
		if(check==true)
		{
			t.CriaJPane(x2,y2);
			t.Recomeca();
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
	public void Save() throws IOException
	{
		t.SaveFile();
	}
	public void Draw(Graphics2D g2d) {
		t.Draw(g2d);
	}
	public void RecomecaJogo()
	{
		t.Recomeca();
	}
	public void CarregaJogo() 
	{
		try {
			t.Load();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}	
	public void registra(Visual.Draw d)
	{
		t.addObserver(d);
	}
}
