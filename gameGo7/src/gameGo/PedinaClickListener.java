package gameGo;
import java.awt.Color;      
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

	
	Output o =  handler.startSync();
	AnswerSets answers = (AnswerSets) o;
	InputProgram  program = new ASPInputProgram();
	
	public PedinaClickListener(PedinaPanel panel) {
		super();
		this.panel = panel;
			}

	@Override
	public void mouseClicked(MouseEvent e) { 
		int distanzaDaX = accettaMossaX(e.getX());
		int distanzaDaY = accettaMossaY(e.getY());
		System.out.println(distanzaDaX + " " + " " + distanzaDaY);
		distanzaDaX = distanzaDaX - 10;
		distanzaDaY = distanzaDaY - 10;
		System.out.println(distanzaDaX + " " + " " + distanzaDaY);
			if(distanzaDaX <=10 && distanzaDaY <=10){
				int nuovoX = - distanzaDaX + e.getX()-17;
				int nuovoY = - distanzaDaY + e.getY()-17;
				int nuovoXL = - distanzaDaX + e.getX();
				int nuovoYL = - distanzaDaY + e.getY();
				nuovoXL = + 100;
				nuovoYL = + 100;
				System.out.println(nuovoXL + " " + " " + nuovoYL);
				if(leggi.readFileS(nuovoXL, nuovoYL) && panel.nonPresenteGiaBlu(nuovoXL, nuovoYL) && nuovoXL<=500 && nuovoYL>=100) {
					panel.addPedina(new Pedina(nuovoX,nuovoY, 44, Color.red));
					System.out.println("provo a generare i fatti");
					generaIFatti(nuovoXL, nuovoYL);
				//	System.out.println("lo inserisco " + nuovoXL + " " +nuovoYL);
					scrivi.openFile(nuovoXL,nuovoYL);
					scrivi.aggiornaValore(nuovoXL,nuovoYL,0);
					scrivi.chiudi();
					try {
						panel.controlloChiNonHaVieDiFuga();
					} catch (Exception e1) {
						System.out.print("problema controllo chi non ha vie di fuga!");
					}
				}
			}
		}

	

	private void generaIFatti(int x, int y) {
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
						panel.addPedina(new Pedina(cell.getX()-7,cell.getY()-7, 44, Color.blue));
						scrivi.openFile(cell.getX(),cell.getY());
						scrivi.aggiornaValore(cell.getX(),cell.getY(),1);
						scrivi.chiudi();
						System.out.print("il pc ha posizionato su: " + cell.getX() + " "+ cell.getY());
						}
					}
				}
				System.out.println();
			} catch (Exception e) {
				e.printStackTrace();
			} 	
		}
		//resetta addb
		handler.removeProgram(program);
		handler.removeAll();
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