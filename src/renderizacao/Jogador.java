package renderizacao;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class Jogador{

	//private int id;
	private Ellipse2D ellipse;
	private List<Peca> pecas = new ArrayList<>();
	private List<Casa> casas = new ArrayList<>();
	
	public Jogador(int num) {
		if(num == 1) {
			casas = new Caminho(1).getCaminho();
			pecas.add(new Peca(1, 53, 53, 0));
			pecas.add(new Peca(1, 53, 200, 0 ));
			pecas.add(new Peca(1, 200, 53, 0));
			pecas.add(new Peca(1, 200, 200, 0));
		}else if(num == 2) {
			casas = new Caminho(2).getCaminho();
			pecas.add(new Peca(2, 495, 53, 0));
			pecas.add(new Peca(2, 495, 200, 0));
			pecas.add(new Peca(2, 640, 53, 0));
			pecas.add(new Peca(2, 640, 200, 0));
		}else if(num == 3) {
			casas = new Caminho(3).getCaminho();
			pecas.add(new Peca(3, 53, 495, 0));
			pecas.add(new Peca(3, 53, 642, 0));
			pecas.add(new Peca(3, 200, 495, 0));
			pecas.add(new Peca(3, 200, 642, 0));
		}else if(num == 4) {
			casas = new Caminho(4).getCaminho();
			pecas.add(new Peca(4, 495, 495, 0));
			pecas.add(new Peca(4, 495, 642, 0));
			pecas.add(new Peca(4, 642, 495, 0));
			pecas.add(new Peca(4, 642, 642, 0));
		}
	}
	
	public List<Casa> getCasas(){
		return casas;
	}
	
	public List<Peca> getPecas(){
		return pecas;
	}
	
	
	
}
