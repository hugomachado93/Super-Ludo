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
			pecas.add(new Peca(1, 495, 53, 0));
			pecas.add(new Peca(1, 495, 200, 0));
			pecas.add(new Peca(1, 640, 53, 0));
			pecas.add(new Peca(1, 640, 200, 0));
		}
	}
	
	public List<Casa> getCasas(){
		return casas;
	}
	
	public List<Peca> getPecas(){
		return pecas;
	}
	
	
	
}
