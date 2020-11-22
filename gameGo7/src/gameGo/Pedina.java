package gameGo;

import java.awt.Color;
import java.awt.Graphics;

public class Pedina {
	private int x;
	private int y;
	private Color color;
	private int diameter;
	
public int getDiameter() {
		return diameter;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}

public Pedina(int x, int y, int diameter, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.color = color;
		this.diameter = diameter;
	}

public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}
public Color getColor() {
	return color;
}

public void setColor(Color color) {
	this.color = color;
}

public void draw(Graphics g) {
	g.setColor(color);
	g.fillOval(x, y, diameter, diameter);
}

}

