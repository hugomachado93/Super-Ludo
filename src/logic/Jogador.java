package logic;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class Jogador{

	private boolean primeiraJogada = true;
	private boolean pecaIniciada = false;
	private Ellipse2D ellipse;
	private int numJogadas = 0;
	private List<Peca> pecas = new ArrayList<>();
	private List<Casa> casas = new ArrayList<>();
	private List<Integer> iniciaisX = new ArrayList<Integer>();
	private List<Integer> iniciaisY = new ArrayList<Integer>();
	
	
	public Jogador(int num) {
		if(num == 1) {
			casas = new Caminho(1).getCaminho();
			
			pecas.add(new Peca(1, 53, 53, 0));
			setIniciais(53,53);
			pecas.add(new Peca(1, 53, 200, 0 ));
			setIniciais(53,200);
			pecas.add(new Peca(1, 200, 53, 0));
			setIniciais(200,53);
			pecas.add(new Peca(1, 200, 200, 0));
			setIniciais(200,200);
			
		}else if(num == 2) {
			casas = new Caminho(2).getCaminho();
			
			pecas.add(new Peca(2, 495, 53, 0));
			setIniciais(495,53);
			pecas.add(new Peca(2, 495, 200, 0));
			setIniciais(495,200);
			pecas.add(new Peca(2, 640, 53, 0));
			setIniciais(640,53);
			pecas.add(new Peca(2, 640, 200, 0));
			setIniciais(640,200);
			
		}else if(num == 3) {
			casas = new Caminho(3).getCaminho();
			
			pecas.add(new Peca(3, 53, 495, 0));
			setIniciais(53,495);
			pecas.add(new Peca(3, 53, 642, 0));
			setIniciais(53,642);
			pecas.add(new Peca(3, 200, 495, 0));
			setIniciais(200,495);
			pecas.add(new Peca(3, 200, 642, 0));
			setIniciais(200,642);
			
		}else if(num == 4) {
			casas = new Caminho(4).getCaminho();
			
			pecas.add(new Peca(4, 495, 495, 0));
			setIniciais(495,495);
			pecas.add(new Peca(4, 495, 642, 0));
			setIniciais(495,642);
			pecas.add(new Peca(4, 642, 495, 0));
			setIniciais(642,495);
			pecas.add(new Peca(4, 642, 642, 0));
			setIniciais(642,642);
			
		}
	}
	
	public int getNumJogadas() {
		return numJogadas;
	}

	public void setNumJogadas(int numJogadas) {
		this.numJogadas = numJogadas;
	}
	
	public void sumNumJogadas() {
		this.numJogadas++;
	}

	public boolean isPecaIniciada() {
		return pecaIniciada;
	}

	public void setPecaIniciada(boolean pecaIniciada) {
		this.pecaIniciada = pecaIniciada;
	}

	public Ellipse2D getEllipse() {
		return ellipse;
	}

	public void setEllipse(Ellipse2D ellipse) {
		this.ellipse = ellipse;
	}

	public void setPecas(List<Peca> pecas) {
		this.pecas = pecas;
	}

	public void setCasas(List<Casa> casas) {
		this.casas = casas;
	}

	public boolean isPrimeiraJogada() {
		return primeiraJogada;
	}

	public void setPrimeiraJogada(boolean primeiraJogada) {
		this.primeiraJogada = primeiraJogada;
	}

	public List<Casa> getCasas(){
		return casas;
	}
	
	public List<Peca> getPecas(){
		return pecas;
	}
	
	private void setIniciais(int x,int y)
	{
		iniciaisX.add(x);
		iniciaisY.add(y);
	}
	
	public int getInicialX(int num)
	{
		return iniciaisX.get(num);
	}
	
	public int getInicialY(int num)
	{
		return iniciaisY.get(num);
	}
	
}
