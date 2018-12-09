package logic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;

public class Peca {

	private int id;
	private int x;
	private int y;
	private int numCasa;
	private int ultimoMovimentado = 0;
	private Ellipse2D ellipse;
	private boolean abrigo = false;
	private boolean barreira = false;
	private boolean ultimaCasa = false;
	private boolean podeSair = false;
	
	public Peca(int id, int x, int y, int numCasa) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.numCasa = numCasa;
	}
	
	public int getId() {
		return id;
	}

	public int getUltimoMovimentado() {
		return ultimoMovimentado;
	}

	public void setUltimoMovimentado(int ultimoMovimentado) {
		this.ultimoMovimentado = ultimoMovimentado;
	}

	public boolean isPodeSair() {
		return podeSair;
	}

	public void setPodeSair(boolean podeSair) {
		this.podeSair = podeSair;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isAbrigo() {
		return abrigo;
	}

	public void setAbrigo(boolean abrigo) {
		this.abrigo = abrigo;
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
	
	public boolean isBarreira() {
		return barreira;
	}

	public void setBarreira(boolean barreira) {
		this.barreira = barreira;
	}
	
	public Ellipse2D getEllipse() {
		return ellipse;
	}

	public void setEllipse(Ellipse2D ellipse) {
		this.ellipse = ellipse;
	}

	public boolean isUltimaCasa() {
		return ultimaCasa;
	}

	public void setUltimaCasa(boolean ultimaCasa) {
		this.ultimaCasa = ultimaCasa;
	}
	
}
