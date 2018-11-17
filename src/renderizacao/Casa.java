package renderizacao;

public class Casa {

	private int x;
	private int y;
	private int numPecas;
	private boolean casaEspecial;

	public Casa(int x, int y, int numPecas, boolean casaEspecial) {
		
		this.x = x;
		this.y = y;
		this.numPecas = numPecas;
		this.casaEspecial = casaEspecial;
		
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
	
}
