package gameGo;
import java.awt.Color;  
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv.desktop.DLVDesktopService;

public class PedinaPanel extends JPanel {
	private List<Pedina> pedine = new LinkedList<Pedina>();
	private List<Pedina> p = new LinkedList<Pedina>();
	private scriviFile scrivi = new scriviFile();
	private leggiFile leggi = new leggiFile();
	private static String encodingResource="encodings/Go";
	private static Handler handler = new DesktopHandler(new DLVDesktopService("./lib/idlv-21-10-2020-windows-64bit"));
	
	public void addPedina(Pedina pedina) {
		pedine.add(pedina);
		this.repaint();
	}
	Pedina ricorda;
	public void removeCircle(int x,int y){
		for(Pedina p: pedine) {
			if(p.getX()==x && p.getY()==y ) {
				ricorda = p;
				System.out.println("elimino pedina!!!" + p.getX()+" " + p.getY());
				scrivi.openFile(ricorda.getX(),ricorda.getY());
				scrivi.aggiornaValore(ricorda.getX(),ricorda.getY(),2);
				scrivi.chiudi();
			}
		}
		pedine.remove(ricorda);
    }
		
	boolean nonPresenteGiaBlu(int x, int y) {
		for(Pedina p: pedine) {
			if(p.getX()==x && p.getY()==y && p.getColor()==Color.blue)
				return false;
		}
		return true;
	}
	
	void controlloChiNonHaVieDiFuga() throws Exception { 
		for(Pedina p: pedine) {
			int numVicini = 0;
			int posizioneInferiore = p.getX()-50;
			int posizioneSuperiore = p.getX()+50;
			int posizioneDestra = p.getY()+50;
			int posizioneSinistra = p.getY()-50;
			
			
			for(Pedina p1: pedine) {
			if(p1.getX()==posizioneSuperiore && p1.getY()==p.getY() && p.getColor()!=p1.getColor()) 
				numVicini++;
			if(p1.getX()==posizioneInferiore && p1.getY()==p.getY() && p.getColor()!=p1.getColor()) 
				numVicini++;
			if(p1.getX() == p.getX() && p1.getY() == posizioneSinistra && p.getColor()!=p1.getColor()) 
				numVicini++;
			if(p1.getX() == p.getX() && p1.getY() == posizioneDestra  && p.getColor()!=p1.getColor()) 
				numVicini++;
			
			}//for

			//System.out.println("controllo: "+ p.getX()+" "+p.getY() +"numVicini: " + numVicini + "X: " + p.getX() + "Y: " + p.getX()  );
			//se sei nel bordo:
			if(p.getX() == 100 && numVicini>=3 || p.getY() == 500 && numVicini>=3 || p.getY() == 100 && numVicini>=3 || p.getX() == 500 && numVicini>=3 )
				removeCircle(p.getX(), p.getY());
			//else if(p.getX() == 100 && numVicini==2 || p.getY() == 500 && numVicini==2 || p.getY() == 100 && numVicini==2 || p.getX() == 500 && numVicini==2 )
				//pedineInGabbia();
			
			// se sei nel "centro"
			else if(p.getX() != 100 && numVicini>=4 && p.getY() != 500 && numVicini>=4 )
				removeCircle(p.getX(), p.getY());
			
			
			// se sei nell angolo
			else if(p.getX() == 100 && p.getY() == 100 && numVicini>=2 )
				removeCircle(p.getX(), p.getY());
			else if(p.getX() == 500 && p.getY() == 100 && numVicini>=2 )
				removeCircle(p.getX(), p.getY());
			else if(p.getX() == 500 && p.getY() == 500 && numVicini>=2 )
				removeCircle(p.getX(), p.getY());
			else if(p.getX() == 100 && p.getY() == 500 && numVicini>=2 )
				removeCircle(p.getX(), p.getY());
		}
	}
	
	

	@Override
	public void paint(Graphics g) {
		ImageIcon i = new ImageIcon("C:/Users/ilari/eclipse-workspace/gameGo5/src/gameGo/base1.png");
		i.paintIcon(this, g, 80, 80);
		for(Pedina p: pedine) {
			p.draw(g);
		}
	}
}