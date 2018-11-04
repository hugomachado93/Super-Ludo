package renderizacao;

import java.awt.geom.Ellipse2D;

public class Peca {

	private int id;
	private int x;
	private int y;
	private int numCasa;
	private Ellipse2D ellipse;
	
	public Peca(int id, int x, int y, int umCasa) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.numCasa = numCasa;
	}
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
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



	public int getNumCasa() {
		
		return numCasa;
	}
	
	public void setNumCasa(int numCasa) {
		this.numCasa = numCasa;
	}
	
}
