package gameGo;
import java.awt.Color;      
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

public class PedinaClickListener extends MouseAdapter {
	private PedinaPanel panel;
	private leggiFile leggi = new leggiFile();
	private scriviFile scrivi = new scriviFile();
	private static String encodingResource="encodings/go";
	
	public Handler handler = new DesktopHandler(new DLVDesktopService("./lib/dlv2-windows-64_6"));

	
	Output o =  handler.startSync();
	AnswerSets answers = (AnswerSets) o;
	InputProgram  program = new ASPInputProgram();
	
	public PedinaClickListener(PedinaPanel panel) {
		super();
		this.panel = panel;
			}

	@Override
	public void mouseClicked(MouseEvent e) { 
		//System.out.println(e.getX() + " " +e.getY()) ;
			int distanzaDaX = accettaMossaX(e.getX()-12);
			int distanzaDaY = accettaMossaY(e.getY()-12);
			if(distanzaDaX <=10 && distanzaDaY <=10){
			//int nuovoX = - distanzaDaX + e.getX()-29;
			//int nuovoY = - distanzaDaY + e.getY()-29;
			int nuovoXL = - distanzaDaX + e.getX()-12;
			int nuovoYL = - distanzaDaY + e.getY()-12;
				
				
				System.out.println(nuovoXL + " "+nuovoYL);
				if(leggi.readFileS(nuovoXL, nuovoYL) && panel.nonPresenteGiaBianca(nuovoXL, nuovoYL) && nuovoYL<=500 && nuovoXL>=100) {
					panel.addPedina(new Pedina(nuovoXL, nuovoYL, 44, Color.black));
					//System.out.println("provo a generare i fatti");
					panel.generaIFatti(nuovoXL, nuovoYL);
					System.out.println("lo inserisco " + nuovoXL + " " +nuovoYL);
					scrivi.openFile(nuovoXL, nuovoYL);
					scrivi.aggiornaValore(nuovoXL, nuovoYL,0);
					scrivi.chiudi();
					try {
						panel.controlloChiNonHaVieDiFuga();
					} catch (Exception e1) {
						System.out.print("problema controllo chi non ha vie di fuga!");
					}
				}
				else
					System.out.println("il valore non è stato inserito");
			}
		}

	

	

	

	
	private int accettaMossaX(int x) {
		while(x>=40) {
			x = x-50;
		}
		return x;}
	
	private int accettaMossaY(int y) {
		while(y>=40) {
			y = y-50;
		}return y;}
}