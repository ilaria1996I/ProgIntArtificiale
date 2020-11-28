package gameGo;
import java.awt.Color;       
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import IA.AddB;
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

public class PedinaClickListener extends MouseAdapter {
	private PedinaPanel panel;
	private leggiFile leggi = new leggiFile();
	private scriviFile scrivi = new scriviFile();
	private static String encodingResource="encodings/go";
	public Handler handler = new DesktopHandler(new DLVDesktopService("./lib/dlv2-windows-64_6"));
	

	
	public PedinaClickListener(PedinaPanel panel) {
		super();
		this.panel = panel;
			}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		//la tabella varia da 0 a 540 ogni cella misura 30
		
		
		int x = e.getX() - 15;
		int y = e.getY() - 15;
		int distanzaDaX = accettaMossaX(x);
		int distanzaDaY = accettaMossaY(y);
		if(distanzaDaX <=13 && distanzaDaY <=13){
		int nuovoXL = - distanzaDaX + x;
		int nuovoYL = - distanzaDaY + y;
				

		try {
			if(leggi.readFileS(nuovoXL, nuovoYL) && panel.nonPresenteGiaBianca(nuovoXL, nuovoYL) && panel.nonPresenteGiaNero(nuovoXL, nuovoYL) && nuovoYL<=540 && nuovoXL>=0 && nuovoXL<=540 && nuovoYL>=0) {
				panel.addPedina(new Pedina(nuovoXL, nuovoYL, 25, Color.black));
				scrivi.openFile(nuovoXL, nuovoYL);
				scrivi.aggiornaValore(nuovoXL, nuovoYL,0);
				scrivi.chiudi();
				
				// se ritorna 2 vuol dire che ancora non ce nessun vincitore perciò la partita puo continuare!
				if(panel.vincitore()==2) 
					panel.generaFattiPc();
				}
				else
				System.out.println("il valore non è stato inserito");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Prova a essere un pò piu preciso!");
		}
		}
		else
			System.out.println("Prova a essere un pò piu preciso! ");
		//panel.vincitore();
		} // fine metodo mouseClicked

	
	//questi due metodi sono stati creati per controllare quando ti sei "spostato" dal click del mouse al punto reale della tabella, se ti discosti troppo
	//il sistema non accetterà la mossa
	private int accettaMossaX(int x) {
		while(x>=20) {
			x = x-30;
		}
		return x;}
	
	private int accettaMossaY(int y) {
		while(y>=20) {
			y = y-30;
		}return y;}
}