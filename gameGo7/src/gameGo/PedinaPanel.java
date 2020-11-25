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
	private static List<Pedina> pedine = new LinkedList<Pedina>();
	private static scriviFile scrivi = new scriviFile();
	private leggiFile leggi = new leggiFile();
	private static String encodingResource="encodings/go";
	private static Handler handler = new DesktopHandler(new DLVDesktopService("./lib/dlv2-windows-64_6"));
	
	public void addPedina(Pedina pedina) {
		pedine.add(pedina);
		this.repaint();
	}
		
	boolean nonPresenteGiaBianca(int x, int y) {
		for(Pedina p: pedine) {
			if(p.getX()==x && p.getY()==y && p.getColor()==Color.white)
				return false;
		}
		return true;
	}
	
	void generaIFatti(int x, int y) {
		InputProgram facts= new ASPInputProgram();
		for(int i=0; i<=540; i=i+30) {
			for(int j=0; j<=540; j = j+30) {
					int c = leggi.coloreAppartenenza(i,j);
						if(c!=2) {
							try {
						facts.addObjectInput(new Occupato(i,j,c));
					} 
					catch (Exception e) {
						e.printStackTrace();
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
						System.out.println(cell.getX() + " " + cell.getY());
						addPedina(new Pedina(cell.getX(),cell.getY(), 25, Color.white));
						scrivi.openFile(cell.getX(),cell.getY());
						scrivi.aggiornaValore(cell.getX(),cell.getY(),1);
						scrivi.chiudi();
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
		//Per Input
		InputProgram facts= new ASPInputProgram();
		for(int i=0; i<=540; i=i+30) {
			for(int j=0; j<=540; j = j+30) {

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
		handler.addProgram(facts);
		handler.removeAll();	
	}

	@Override
	public void paint(Graphics g) {
		ImageIcon i = new ImageIcon("../gameGo7/src/image/base3.png");
		i.paintIcon(this, g, 0, 0);
		for(Pedina p: pedine) {
			p.draw(g);
		}
	}
}