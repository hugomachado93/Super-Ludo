package renderizacao;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Desenhos {
	
	public Desenhos() {
		
	}

	public void diceColor(Graphics2D g2d, int nJogador, Stroke defaultStroke) {
		if(nJogador == 0) {
			g2d.setColor(Color.RED);
			g2d.setStroke(new BasicStroke(10));
			g2d.drawRect(1000, 500, 100, 100);
		}else if(nJogador == 1) {
			g2d.setColor(Color.GREEN);
			g2d.setStroke(new BasicStroke(10));
			g2d.drawRect(1000, 500, 100, 100);
		}else if(nJogador == 2) {
			g2d.setColor(Color.BLUE);
			g2d.setStroke(new BasicStroke(10));
			g2d.drawRect(1000, 500, 100, 100);
		}else {
			g2d.setColor(Color.YELLOW);
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
		g2d.setColor(Color.GREEN);
		g2d.setStroke(new BasicStroke(2));
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				jogador[1].getPecas().get(j).setBarreira(false);
				if(((casaTemp = jogador[1].getPecas().get(i).getNumCasa()) == jogador[1].getPecas().get(j).getNumCasa()) && (i != j) && casaTemp !=0) {
					jogador[1].getPecas().get(i).setBarreira(true);
					g2d.drawOval(jogador[1].getPecas().get(j).getX()-2, jogador[1].getPecas().get(j).getY()-2, 45, 45);
				}
			}
		}
		g2d.setColor(Color.BLUE);
		g2d.setStroke(new BasicStroke(2));
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				jogador[2].getPecas().get(j).setBarreira(false);
				if(((casaTemp = jogador[2].getPecas().get(i).getNumCasa()) == jogador[2].getPecas().get(j).getNumCasa()) && (i != j) && casaTemp !=0) {
					jogador[2].getPecas().get(i).setBarreira(true);
					g2d.drawOval(jogador[2].getPecas().get(j).getX()-2, jogador[2].getPecas().get(j).getY()-2, 45, 45);
				}
			}
		}
		g2d.setColor(Color.YELLOW);
		g2d.setStroke(new BasicStroke(2));
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				jogador[3].getPecas().get(j).setBarreira(false);
				if(((casaTemp = jogador[3].getPecas().get(i).getNumCasa()) == jogador[3].getPecas().get(j).getNumCasa()) && (i != j) && casaTemp !=0) {
					jogador[3].getPecas().get(i).setBarreira(true);
					g2d.drawOval(jogador[3].getPecas().get(j).getX()-2, jogador[3].getPecas().get(j).getY()-2, 45, 45);
				}
			}
		}
		g2d.setStroke(defaultStroke);
	}
	
}
