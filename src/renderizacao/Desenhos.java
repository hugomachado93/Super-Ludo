package renderizacao;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Desenhos {
	
	public Desenhos() {
		
	}

	public void diceColor(Graphics2D g2d, int nJogador, Stroke defaultStroke) {
		if(nJogador == 1) {
			g2d.setColor(Color.RED);
			g2d.setStroke(new BasicStroke(10));
			g2d.drawRect(1000, 500, 100, 100);
		}else if(nJogador == 2) {
			g2d.setColor(Color.GREEN);
			g2d.setStroke(new BasicStroke(10));
			g2d.drawRect(1000, 500, 100, 100);
		}
		g2d.setStroke(defaultStroke);
	}
	
	public void PecaNaMesmaCasa(Jogador[] jogador, Graphics2D g2d, Stroke defaultStroke) {
		int casaTemp;
		g2d.setColor(Color.RED);
		g2d.setStroke(new BasicStroke(2));
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				jogador[0].getPecas().get(j).setBarreira(false);
				if(((casaTemp = jogador[0].getPecas().get(i).getNumCasa()) == jogador[0].getPecas().get(j).getNumCasa()) && (i != j) && casaTemp !=0) {
					jogador[0].getPecas().get(i).setBarreira(true);
					g2d.drawOval(jogador[0].getPecas().get(j).getX()-2, jogador[0].getPecas().get(j).getY()-2, 45, 45);
				}
			}
		}
		g2d.setStroke(defaultStroke);
	}
	
}
