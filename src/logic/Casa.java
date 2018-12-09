package logic;

public class Casa {

	private int x;
	private int y;
	private int numPecas;
	private boolean casaEspecial;
	private boolean casaInicial;

	public Casa(int x, int y, int numPecas, boolean casaEspecial, boolean casaInicial) {
		
		this.x = x;
		this.y = y;
		this.numPecas = numPecas;
		this.casaEspecial = casaEspecial;
		this.casaInicial = casaInicial;
		
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

	public int getNumPecas() {
		return numPecas;
	}

	public void setNumPecas(int numPecas) {
		this.numPecas = numPecas;
	}

	public boolean isCasaEspecial() {
		return casaEspecial;
	}

	public void setCasaEspecial(boolean casaEspecial) {
		this.casaEspecial = casaEspecial;
	}

	public boolean isCasaInicial() {
		return casaInicial;
	}

	public void setCasaInicial(boolean casaInicial) {
		this.casaInicial = casaInicial;
	}
	
}
