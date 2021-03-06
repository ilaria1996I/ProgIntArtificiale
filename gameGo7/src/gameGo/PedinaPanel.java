package gameGo;
import java.awt.Color; 
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import IA.AddB;
import IA.Occupato;
import IA.Win;
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
	private LeggiFile leggi = new LeggiFile();
	private static String encodingResource="encodings/go";
	BackgroundImageJFrame risultato; 
	ImageIcon vittoria = new ImageIcon("image"+File.separator+"win.jpg");
	ImageIcon sconfitta = new ImageIcon("image"+File.separator+"gameOver.jpg");
	InterfP InterfP = new InterfP();
	
	
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
	
	boolean nonPresenteGiaNero(int x, int y) {
		for(Pedina p: pedine) {
			if(p.getX()==x && p.getY()==y && p.getColor()==Color.black)
				return false;
		}
		return true;
	}

	
	void generaFattiPc(){
	//In questo metodo si generano i fatti del PC
	Handler handler = new DesktopHandler(new DLVDesktopService("./lib/dlv2-windows-64_6 --printlatestmodel"));
	InputProgram facts= new ASPInputProgram();
		
	for(int i=0; i<=540; i=i+30) {
		for(int j=0; j<=540; j = j+30) {
			int c = leggi.coloreAppartenenza(i,j);
				if(c!=2) {
					try {
						facts.addObjectInput(new Occupato(i,j,c));
						//System.out.println("occupato(" + i+ "," + j+ ","  + c+").");
					} 
					catch (Exception e) {
						e.printStackTrace();
						}
					} 
				}//for j
			}// for i
		
	facts.addFilesPath(encodingResource);
	handler.addProgram(facts);
		
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
					facts.addObjectInput(new Occupato(cell.getX(),cell.getY(),1));
					//mettilo nella lista e fallo comparire
					addPedina(new Pedina(cell.getX(),cell.getY(), 25, Color.white));
					scrivi.openFile(cell.getX(),cell.getY());
					scrivi.aggiornaValore(cell.getX(),cell.getY(),1);
					scrivi.chiudi();	
					}
				}
			} catch (Exception e) {
				System.out.println("Errore con AddB");
			} 	
		}
		handler.addProgram(facts);
		vincitore();
	} //generaFattiPc


	int vincitore(){ 
		//In questo metodo si generano i fatti del PC
		Handler handler = new DesktopHandler(new DLVDesktopService("./lib/dlv2-windows-64_6 --printlatestmodel"));
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
					}//for j
				}// for i
			
		facts.addFilesPath(encodingResource);
		handler.addProgram(facts);
			
		try {
			ASPMapper.getInstance().registerClass(Win.class);	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		handler.startSync();
		Output o =  handler.startSync();
		AnswerSets answers = (AnswerSets) o;
		for(AnswerSet a:answers.getAnswersets()){
			try {
				for(Object obj:a.getAtoms()){
					if(obj instanceof Win)  {
						Win win = (Win) obj;
						//System.out.println("Vincitore: + " + win.getC());
						//commenta se non vuoi la la scritta Vittoria/Perso
						if(win.getC() == 0) {
						risultato = new BackgroundImageJFrame (vittoria);
						gameGo.InterfP.windows.setVisible(false);
						return 0;
						}
						else {
						risultato = new BackgroundImageJFrame (sconfitta);
						gameGo.InterfP.windows.setVisible(false);
						return 1;
						}
						//
						
						}
					}
				} catch (Exception e) {
					System.out.println("Errore con Win");
				} 	
			}
			return 2;
	} //vincitore

	@Override
	public void paint(Graphics g) {
		ImageIcon i = new ImageIcon("image"+File.separator+"base3.png");
		i.paintIcon(this, g, 0, 0);
		for(Pedina p: pedine) {
			p.draw(g);
		}
	}
}
