package gameGo;

import java.io.File;
import java.util.Formatter;

public class scriviFile{
	
private static Formatter x;

	public void openFile(int a, int b) {
	try {
		x = new Formatter ("valori/"+a+" "+b);
	}
	catch (Exception e) {
		System.out.println("Problema file scrittura");
		}
	}//metodo
	
	public void aggiornaValore (int a, int b,int c) {
		//il terzo valore è il colore 0 nero --- 1 bianco --- 2 nessun colore
		x.format("%d %d %d",a,b,c);
		
	}
	
	public void chiudi() {
		x.close();
	}
}//class
