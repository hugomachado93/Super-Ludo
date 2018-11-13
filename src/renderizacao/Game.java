package renderizacao;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;


public class Game extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private GameFacade gameFacade = new GameFacade();
	private Stroke defaultStroke;
	
	public Game() throws IOException {
		GUI();
	}
	
	private void GUI() {
		setLayout(null);
		add(gameFacade.eventDado());
		
		gameFacade.addObserver((obj, arg)-> {
			System.out.println("Repainting");
			repaint();
		});
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				gameFacade.mouseClicked(e);
			}
		});
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		defaultStroke = g2d.getStroke();
		
		gameFacade.DrawAll(g2d, defaultStroke);
		
	}
	
	
	/*
	public void eventDado(JButton b) {
		b.addMouseListener(new MouseAdapter() {
			@Override
			 public void mouseClicked(MouseEvent e) {
				if(!dadoClicado) {
					dado.getRandNumDado();
				 	repaint();
				}
				dadoClicado = true;
			 }
		});
	}
	
	private class MyMouseListener extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			dadoVal = dado.getNumDado()
			if(dadoClicado) {
				if(player == 1)
					for(int i=0;i<4;i++) {
						if(ellipse1[i].contains(e.getX(), e.getY())) {
							int val;
							val = dadoVal + jogador1.getPecas().get(i).getNumCasa();
							
							if(val > MAX_CASAS) {
								val = MAX_CASAS - (val - MAX_CASAS);
							}
							
							jogador1.getPecas().get(i).setNumCasa(val);
							jogador1.getPecas().get(i).setX(jogador1.getCasas().get(val).getX());
							jogador1.getPecas().get(i).setY(jogador1.getCasas().get(val).getY());
							player++;
							dadoClicado = false;
							break;
						}
					}
				else if(player == 2) {
					for(int i=0;i<4;i++) {
						if(ellipse2[i].contains(e.getX(), e.getY())) {
							int val1, val2;
							jogador2.getPecas().get(i).setX(jogador2.getCasas().get(val1 = dadoVal + jogador2.getPecas().get(i).getNumCasa()).getX());
							jogador2.getPecas().get(i).setY(jogador2.getCasas().get(val2 = dadoVal + jogador2.getPecas().get(i).getNumCasa()).getY());
							jogador2.getPecas().get(i).setNumCasa(val1);
							jogador2.getPecas().get(i).setNumCasa(val2);
							player=1;
							dadoClicado = false;
							break;
						}
					}
				}
			}
			repaint();
		}
	}
	
	private void PecaNaMesmaCasa(Graphics2D g2d) {
		int casaTemp;
		g2d.setColor(Color.RED);
		g2d.setStroke(new BasicStroke(2));
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(((casaTemp = jogador1.getPecas().get(i).getNumCasa()) == jogador1.getPecas().get(j).getNumCasa()) && (i != j) && casaTemp !=0) {
					jogador1.getPecas().get(i).setBarreira(true);
					g2d.drawOval(jogador1.getPecas().get(j).getX()-2, jogador1.getPecas().get(j).getY()-2, 45, 45);
				}
			}
		}
		g2d.setStroke(defaultStroke);
	}
	
	private void diceColor(Graphics2D g2d) {
		if(player == 1) {
			g2d.setColor(Color.RED);
			g2d.setStroke(new BasicStroke(10));
			g2d.drawRect(1000, 500, 100, 100);
		}else if(player == 2) {
			g2d.setColor(Color.GREEN);
			g2d.setStroke(new BasicStroke(10));
			g2d.drawRect(1000, 500, 100, 100);
		}else if(player == 3) {
			
		}
		g2d.setStroke(defaultStroke);
	}
	*/
}
