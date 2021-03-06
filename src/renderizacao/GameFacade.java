package renderizacao;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GameFacade extends Observable{

	private static int nJogador = 0;
	private Jogador[] jogador = new Jogador[4];
	private JButton bDice;
	private Ellipse2D[] desenhoPeca1 = new Ellipse2D[4];
	private Ellipse2D[] desenhoPeca2 = new Ellipse2D[4];
	private Ellipse2D[] desenhoPeca3 = new Ellipse2D[4];
	private Ellipse2D[] desenhoPeca4 = new Ellipse2D[4];
	private Dado dado;
	private Desenhos desenhos;
	private Eventos eventos;
	private int dadoVal;
	private final int MAX_CASAS = 56;
	
	public GameFacade() throws IOException {
		jogador[0] = new Jogador(1);
		jogador[1] = new Jogador(2);
		jogador[2] = new Jogador(3);
		jogador[3] = new Jogador(4);
		
		bDice = new JButton("Dado");
		dado = new Dado();
		desenhos = new Desenhos();
		//eventos = new Eventos();
	}

	public void DesenhaTodasAsPecas(Graphics2D g2d) {
		//printa as pecas
			for(int i=0;i<4;i++) {
				g2d.setColor(new Color(150, 0, 0));
				desenhoPeca1[i] = new Ellipse2D.Double(jogador[0].getPecas().get(i).getX(), jogador[0].getPecas().get(i).getY(), 40, 40);
				g2d.fill(desenhoPeca1[i]);

			}
				
			for(int i=0;i<4;i++) {
				g2d.setColor(new Color(0, 150, 0));
				desenhoPeca2[i] = new Ellipse2D.Double(jogador[1].getPecas().get(i).getX(), jogador[1].getPecas().get(i).getY(), 40, 40);
				g2d.fill(desenhoPeca2[i]);
			}
				
			for(int i=0;i<4;i++) {
				g2d.setColor(new Color(0, 0, 150));
				desenhoPeca3[i] = new Ellipse2D.Double(jogador[2].getPecas().get(i).getX(), jogador[2].getPecas().get(i).getY(), 40, 40);
				g2d.fill(desenhoPeca3[i]);
			}
				
			for(int i=0;i<4;i++) {
				g2d.setColor(new Color(150, 150, 0));
				desenhoPeca4[i] = new Ellipse2D.Double(jogador[3].getPecas().get(i).getX(), jogador[3].getPecas().get(i).getY(), 40, 40);
				g2d.fill(desenhoPeca4[i]);
			}
	}
	
	public void DrawAll(Graphics2D g2d, Stroke defaultStroke) {
		Tabuleiro.getTabuleiro().paintTabuleiro(g2d);
		DrawDado(g2d);
		DiceColor(g2d, defaultStroke);
		DesenhaTodasAsPecas(g2d);
		PecaNaMesmaCasa(g2d, defaultStroke);
	}
	
	public JButton eventDado() {
		bDice.setBounds(1000, 400, 100, 50);
		bDice.addMouseListener(new MouseAdapter() {
			@Override
			 public void mouseClicked(MouseEvent e) {
				if(!Dado.dadoClicado) {
					dado.getRandNumDado();
					setChanged();
					notifyObservers();
				}
				Dado.dadoClicado = true;
			 }
		});
		return bDice;
	}
	
	public void mouseClicked(MouseEvent e) {
		Ellipse2D[] desenhoTemp;
		dadoVal = dado.getNumDado();
		
		if(nJogador == 0) {
			desenhoTemp = desenhoPeca1;
		}else if(nJogador == 1) {
			desenhoTemp = desenhoPeca2;
		}else if(nJogador == 2) {
			desenhoTemp = desenhoPeca3;
		}else {
			desenhoTemp = desenhoPeca4;
		}
		
		if(Dado.dadoClicado) {
			for(int i=0;i<4;i++) {
				if(desenhoTemp[i].contains(e.getPoint()) && !jogador[nJogador].getPecas().get(i).isUltimaCasa()) {
					int val;
					val = dadoVal + jogador[nJogador].getPecas().get(i).getNumCasa();
						
					if(val > MAX_CASAS) {
						val = MAX_CASAS - (val - MAX_CASAS);
					}else if(val == MAX_CASAS) {
						jogador[nJogador].getPecas().get(i).setUltimaCasa(true);
					}
						
					jogador[nJogador].getPecas().get(i).setNumCasa(val);
					jogador[nJogador].getPecas().get(i).setX(jogador[nJogador].getCasas().get(val).getX());
					jogador[nJogador].getPecas().get(i).setY(jogador[nJogador].getCasas().get(val).getY());
					
					nJogador++;
					Dado.dadoClicado = false;
					break;
				}
			}
			setChanged();
			notifyObservers();
		}
		nJogador = (nJogador == 2) ? 0: nJogador;
	}
	
	public Eventos getEventos() {
		return eventos;
	}
	
	public void DiceColor(Graphics2D g2d, Stroke defaultStroke) {
		desenhos.diceColor(g2d, nJogador, defaultStroke);
	}
	
	public void PecaNaMesmaCasa(Graphics2D g2d, Stroke defaultStroke) {
		desenhos.PecaNaMesmaCasa(jogador, g2d, defaultStroke);
	}
	
	public void DrawDado(Graphics2D g2d) {
		dado.drawDado(g2d);
	}
	
}
