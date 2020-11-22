package IA;
import java.awt.Color;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;
//genero i fatti della pedina rossi
@Id("occupato")
public class Occupato{
	
	//Parametri
	@Param(0)
	private int x;
	@Param(1)
	private int y;
	@Param(2)
	private int c;
	
	
	//Costruttore
	public Occupato(){}
	public Occupato(int x1,int y1,int c1){
		this.x=x1;
		this.y=y1;
		this.c=c1;
		
	}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public int getC() {return c;}
	
	//Setters
	public void setX(int x) {this.x=x;}
	public void setY(int y) {this.y=y;}
	public void setC(int c) {this.c=c;}
	
	
	public String toString(){
		String s="";
		s+="Risposta : "+Integer.toString(this.x)+" "+this.y+" "+this.c;
		return s;
	}
}
