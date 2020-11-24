package gameGo;
import java.awt.Color;  
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import IA.AddB;
import IA.AddInP;
import IA.Occupato;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv.desktop.DLVDesktopService;

public class PedinaPanel extends JPanel {
	private List<Pedina> pedineDaEliminare = new LinkedList<Pedina>();
	private List<Pedina> pedine = new LinkedList<Pedina>();
	private scriviFile scrivi = new scriviFile();
	private leggiFile leggi = new leggiFile();
	private static String encodingResource="encodings/go";
	private static Handler handler = new DesktopHandler(new DLVDesktopService("./lib/dlv2-windows-64_6"));
	
	public void addPedina(Pedina pedina) {
		pedine.add(pedina);
		this.repaint();
	}
	Pedina ricorda;
	boolean trovato = false;
	public void removeCircle(int x,int y){
		for(Pedina p: pedine) {
			//System.out.println("Pedine presenti" + p.getX() + " " + p.getY());
			if(p.getX()==x && p.getY()==y ) {
				trovato = true;
				ricorda = p;
			//	System.out.println("elimino pedina!!!" + p.getX()+" " + p.getY());
				scrivi.openFile(ricorda.getX(),ricorda.getY());
				scrivi.aggiornaValore(ricorda.getX(),ricorda.getY(),2);
				scrivi.chiudi();
			}
		}
		pedine.remove(ricorda);
		if(trovato == false)
			System.out.println("attenzione non ho trovato la pedina errore!!!! " + x +" " + y);
    }
		
	boolean nonPresenteGiaBianca(int x, int y) {
		for(Pedina p: pedine) {
			if(p.getX()==x && p.getY()==y && p.getColor()==Color.white)
				return false;
		}
		return true;
	}
	
	void controlloChiNonHaVieDiFuga2() throws Exception { 
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
			if(p.getX() == 100 && numVicini>=3 || p.getY() == 500 && numVicini>=3 || p.getY() == 100 && numVicini>=3 || p.getX() == 500 && numVicini>=3)
				pedineDaEliminare.add(p);
				//removeCircle(p.getX(), p.getY());
			
			
			// se sei nel "centro"
			else if(p.getX() != 100 && numVicini>=4 && p.getY() != 500 && numVicini>=4 )
				pedineDaEliminare.add(p);
				//removeCircle(p.getX(), p.getY());
			
			
			// se sei nell angolo
			else if(p.getX() == 100 && p.getY() == 100 && numVicini>=2 )
				pedineDaEliminare.add(p);
				//removeCircle(p.getX(), p.getY());
			else if(p.getX() == 500 && p.getY() == 100 && numVicini>=2 )
				pedineDaEliminare.add(p);
				//removeCircle(p.getX(), p.getY());
			else if(p.getX() == 500 && p.getY() == 500 && numVicini>=2 )
				pedineDaEliminare.add(p);
				//removeCircle(p.getX(), p.getY());
			else if(p.getX() == 100 && p.getY() == 500 && numVicini>=2 )
				pedineDaEliminare.add(p);
				//removeCircle(p.getX(), p.getY());
		}
		
		//è il momento di eliminare le pedine segnate prima
		for(Pedina d: pedineDaEliminare) {
			removeCircle(d.getX(), d.getY());
			System.out.println("Ho elimato la " + d.getX() + d.getY());
		}
		pedineDaEliminare.removeAll(pedineDaEliminare);
	}

	void generaIFatti(int x, int y) {
		//String encodingResource="encodings/go";
		//handler = new DesktopHandler(new DLVDesktopService("./lib/dlv2-windows-64_6"));
		//Per Input
		InputProgram facts= new ASPInputProgram();
		for(int i=100; i<501;i++) {
			for(int j=100; j<501;j++) {
				if(i == 100||i == 150||i == 200||i == 250||i == 300||i == 350||i == 400||i == 450||i == 500){
					if(j== 100||j == 150||j == 200||j == 250||j == 300||j == 350||j == 400||j == 450||j == 500){
					int c = leggi.coloreAppartenenza(i,j);
						if(c!=2) {
							try {
						facts.addObjectInput(new Occupato(i,j,c));
						//System.out.println(" " + i + " " + j + " " + c);
					} 
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
		handler.addProgram(facts);
		
		//Per resto
		InputProgram  program = new ASPInputProgram();
		program.addFilesPath(encodingResource);
		handler.addProgram(program);
		//
		try {
			ASPMapper.getInstance().registerClass(AddB.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		handler.startSync();
		Output o =  handler.startSync();
		AnswerSets answers = (AnswerSets) o;
		for(AnswerSet a:answers.getAnswersets()){
			try {

				for(Object obj:a.getAtoms()){
					if(obj instanceof AddB)  {
						AddB cell = (AddB) obj;
						if(!leggi.coloreRosso(cell.getX(), cell.getY())) {
						facts.addObjectInput(new Occupato(cell.getX(),cell.getY(),1));
						//mettilo nella lista e fallo comparire
						addPedina(new Pedina(cell.getX(),cell.getY(), 44, Color.white));
						scrivi.openFile(cell.getX(),cell.getY());
						scrivi.aggiornaValore(cell.getX(),cell.getY(),1);
						scrivi.chiudi();
						//System.out.print("il pc ha posizionato su: " + cell.getX() + " "+ cell.getY());
						}
					}
				}
				System.out.println();
			} catch (Exception e) {
				System.out.println();
			} 	
		}
		//resetta addb
		handler.removeProgram(program);
		handler.removeAll();
	}
	
	void controlloChiNonHaVieDiFuga() throws Exception {
		String encodingResource="encodings/go";
		handler = new DesktopHandler(new DLVDesktopService("./lib/dlv2-windows-64_6"));
		//Per Input
		InputProgram facts= new ASPInputProgram();
		for(int i=100; i<501;i++) {
			for(int j=100; j<501;j++) {
				if(i == 100||i == 150||i == 200||i == 250||i == 300||i == 350||i == 400||i == 450||i == 500){
					if(j== 100||j == 150||j == 200||j == 250||j == 300||j == 350||j == 400||j == 450||j == 500){
					int c = leggi.coloreAppartenenza(i,j);
						if(c!=2) {
							try {
						facts.addObjectInput(new Occupato(i,j,c));
						System.out.println(" " + i + " " + j + " " + c);
					} 
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
		handler.addProgram(facts);
		
		//Per resto
		InputProgram  program = new ASPInputProgram();
		program.addFilesPath(encodingResource);
		handler.addProgram(program);
		//
		try {
			ASPMapper.getInstance().registerClass(AddInP.class);
			
		} catch (Exception e) {
			System.out.println("ERRORE la classe AddInP non è stata trovata!");
		}
		handler.startSync();
		Output o =  handler.startSync();
		AnswerSets answers = (AnswerSets) o;
		for(AnswerSet a:answers.getAnswersets()){
			try {
				System.out.println();
				for(Object obj:a.getAtoms()){
					System.out.println();
					if(obj instanceof AddInP)  {
						AddInP addInP = (AddInP) obj;
						//System.out.println("add in p " + addInP.getX() + addInP.getY() + "!!!!!!!!!!!!");
						removeCircle(addInP.getX(),addInP.getY());
						scrivi.openFile(addInP.getX(),addInP.getY());
						scrivi.aggiornaValore(addInP.getX(),addInP.getY(),2);
						scrivi.chiudi();
						removeCircle(addInP.getX(),addInP.getY());
						System.out.println(" eliminato : " + addInP.getX() + " "+ addInP.getY());
						
						}
					}
				System.out.println();
			} catch (Exception e) {
				//System.out.println("nessun add in p");
			} 	
		}
		//resetta addb
				handler.removeProgram(program);
				handler.removeAll();	
	}

	@Override
	public void paint(Graphics g) {
		ImageIcon i = new ImageIcon("../gameGo7/src/image/base1.png");
		i.paintIcon(this, g, 80, 80);
		for(Pedina p: pedine) {
			p.draw(g);
		}
	}
}