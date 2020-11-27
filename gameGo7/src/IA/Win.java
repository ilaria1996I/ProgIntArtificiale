package IA;
import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

//genero 
@Id("win")
public class Win{
	
	//Parametri
	@Param(0)
	private int c;
	
	
	//Costruttore
	public Win(){}
	public Win(int c1){
		this.c=c1;
		
	}
	public int getC() {return c;}
	
	//Setters
	public void setC(int c) {this.c=c;}
	
	public String toString(){
		String s="";
		s+=Integer.toString(this.c);
		return s;
	}
}
