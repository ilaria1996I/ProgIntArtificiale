package gameGo;

import java.io.File;
import java.util.Scanner;

public class leggiFile{
	private Scanner y;
	
	public void openFileS(int a,int b) {
			try {
				y = new Scanner (new File("valori/"+a+" "+b));
			}
			catch (Exception e) {
				System.out.println("Problema lettura file" );
				}
			}
	public boolean readFileS (int a,int b) {
		
		openFileS(a,b);
		
		while(y.hasNext()){
			int i = y.nextInt();
			int j = y.nextInt();
			int c = y.nextInt();
			//int d = y.nextInt();
			return true;
		}
		return false;
	}			
	
public boolean coloreRosso (int a,int b) {
		openFileS(a,b);
		while(y.hasNext()){
			int i = y.nextInt();
			int j = y.nextInt();
			int c = y.nextInt();
			//int d = y.nextInt();
			if(a==i && b==j && c == 1)
				return true;
		}
		return false;
	}	
public boolean coloreBlu (int a,int b) {
	openFileS(a,b);
	while(y.hasNext()){
		int i = y.nextInt();
		int j = y.nextInt();
		int c = y.nextInt();
		if(a==i && b==j && c == 1)
			return true;
	}
	return false;
}	

public int coloreAppartenenza (int a,int b) {
	openFileS(a,b);
	while(y.hasNext()){
		int i = y.nextInt();
		int j = y.nextInt();
		int c = y.nextInt();
			return c;
	}
	return 2;
}	
}
