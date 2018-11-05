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

import javax.swing.JButton;
import javax.swing.JPanel;


public class Game extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private Tabuleiro tabuleiro = new Tabuleiro();
	private Dado dado = new Dado();
	private JButton bDice = new JButton("Dado");
	//private Caminho cam = new Caminho(1);

	private Jogador jogador1 = new Jogador(1);
	private Jogador jogador2 = new Jogador(2);
	private Jogador jogador3 = new Jogador(3);
	private Jogador jogador4 = new Jogador(4);
	
	private Ellipse2D[] ellipse1 = new Ellipse2D[4];
	private Ellipse2D[] ellipse2 = new Ellipse2D[4];
	private Ellipse2D[] ellipse3 = new Ellipse2D[4];
	private Ellipse2D[] ellipse4 = new Ellipse2D[4];
	
	private boolean dadoClicado = false;
	private int dadoVal;
	private int player = 1;
	
	public Game() throws IOException {
		
		GUI();
		
	}
	
	private void GUI() {
		setLayout(null);
		add(bDice);
		bDice.setBounds(1000, 400, 100, 50);
		eventDado(bDice);
		addMouseListener(new MyMouseListener());
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		dado.drawDado(g2d);
		tabuleiro.paintTabuleiro(g2d);
		
		diceColor(g2d);
		
		//printa as pecas
		for(int i=0;i<4;i++) {
			g2d.setColor(new Color(150, 0, 0));
			ellipse1[i] = new Ellipse2D.Double(jogador1.getPecas().get(i).getX(), jogador1.getPecas().get(i).getY(), 40, 40);
			g2d.fill(ellipse1[i]);

		}
		
		for(int i=0;i<4;i++) {
			g2d.setColor(new Color(0, 150, 0));
			ellipse2[i] = new Ellipse2D.Double(jogador2.getPecas().get(i).getX(), jogador2.getPecas().get(i).getY(), 40, 40);
			g2d.fill(ellipse2[i]);
		}
		
		for(int i=0;i<4;i++) {
			g2d.setColor(new Color(0, 0, 150));
			ellipse3[i] = new Ellipse2D.Double(jogador3.getPecas().get(i).getX(), jogador3.getPecas().get(i).getY(), 40, 40);
			g2d.fill(ellipse3[i]);
		}
		
		for(int i=0;i<4;i++) {
			
		}
		
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillOval(495, 495, 40, 40);
	}
	
	public void eventDado(JButton b) {
		b.addMouseListener(new MouseAdapter() {
			@Override
			 public void mouseClicked(MouseEvent e) {
				 dado.getRandNumDado();
				 repaint();
				 dadoClicado = true;
			 }
		});
	}
	
	class MyMouseListener extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			dadoVal = dado.getNumDado();
			if(dadoClicado) {
				if(player == 1)
					for(int i=0;i<4;i++) {
						if(ellipse1[i].contains(e.getX(), e.getY())) {
							int val;
							val = dadoVal + jogador1.getPecas().get(i).getNumCasa();
							jogador1.getPecas().get(i).setX(jogador1.getCasas().get(val).getX());
							jogador1.getPecas().get(i).setY(jogador1.getCasas().get(val).getY());
							jogador1.getPecas().get(i).setNumCasa(val);
							jogador1.getPecas().get(i).setNumCasa(val);
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
							//System.out.println("PECA SELECIONADA");
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
	
	private void diceColor(Graphics2D g2d) {
		Stroke defaultStroke = g2d.getStroke();
		if(player == 1) {
			g2d.setColor(Color.RED);
			g2d.setStroke(new BasicStroke(10));
			g2d.drawRect(1000, 500, 100, 100);
		}else if(player == 2) {
			g2d.setColor(Color.GREEN);
			g2d.setStroke(new BasicStroke(10));
			g2d.drawRect(1000, 500, 100, 100);
		}
		g2d.setStroke(defaultStroke);
	}
	
}
