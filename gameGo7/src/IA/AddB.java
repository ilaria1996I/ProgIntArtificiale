package IA;
import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;
//genero i fatti di occupato
@Id("addB")
public class AddB{
	
	//Parametri
	@Param(0)
	private int x;
	@Param(1)
	private int y;
	
	
	//Costruttore
	public AddB(){}
	public AddB(int x1,int y1,int c1){
		this.x=x1;
		this.y=y1;
		
	}
	
	public int getX() {return x;}
	public int getY() {return y;}
	
	//Setters
	public void setX(int x) {this.x=x;}
	public void setY(int y) {this.y=y;}
	
	public String toString(){
		String s="";
		s+=Integer.toString(this.x)+" "+this.y;
		return s;
	}
}
