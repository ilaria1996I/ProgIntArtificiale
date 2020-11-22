package gameGo;
import java.io.File;  
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.swing.JFrame;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;
public class pedinaApplication{
static JFrame windows = new JFrame("Pedina");

private static void creaFileTxt() throws IOException{
	for(int i=100; i<=550; i+=50) {
    	for(int l=100; l<=550; l+=50) {
    		String nome=("valori/"+i+" "+l);
    		File f= new File (nome);
    		if(f.exists()) {
    			FileWriter fwOb = new FileWriter(nome, false); 
    		    PrintWriter pwOb = new PrintWriter(fwOb, false);
    		    pwOb.flush();
    		    pwOb.close();
    		    fwOb.close();
        		FileOutputStream fos = new FileOutputStream(nome,true);
    			PrintWriter scrivi = new PrintWriter(fos);
        		scrivi.append(i + " " + l + " 2" );
        		scrivi.close();
    		}
    		else if(f.createNewFile()) {
    			PrintWriter scrivi = new PrintWriter(f);
        		scrivi.println(i + " " + l + "2 " );
        		scrivi.close();
    		}
    	}
    }
}

public static void main(String[] args) throws IOException {
	
	creaFileTxt();
	
	//grafica!
	windows.setBounds(100, 100, 591, 631);
	windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	windows.getContentPane().setLayout(null);

	PedinaPanel panel = new PedinaPanel();
	windows.setContentPane(panel);
	panel.addMouseListener(new PedinaClickListener(panel));
	windows.setVisible(true);

	
		}	
	}