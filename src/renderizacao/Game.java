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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;


public class Game extends JPanel{
	private static final long serialVersionUID = 1L;
	private GameFacade gameFacade = new GameFacade();
	private Stroke defaultStroke;
	private Jogador[] jogador;
	private Desenhos desenhos = new Desenhos();
	private JButton[] bDebug = new JButton[6];
	private JButton[] menu = new JButton[3];
	private int nJogador;
	private JButton bDice;
	Dado dado = new Dado();
	
	public Game() throws IOException {
		bDice = new JButton("DADO");
		jogador = gameFacade.getJogador();
		nJogador = gameFacade.getNumJogador();
		bDebug[0] = new JButton("1");
		bDebug[1] = new JButton("2");
		bDebug[2] = new JButton("3");
		bDebug[3] = new JButton("4");
		bDebug[4] = new JButton("5");
		bDebug[5] = new JButton("6");
	
		menu[0] = new JButton("Novo jogo");
		menu[1] = new JButton("Salvar");
		menu[2] = new JButton("Carregar");
		
		GUI();
	}
	
	private void GUI() {
		setLayout(null);
		bDebug = eventDebugDado();
		menu = eventMenu();
		add(eventDado());
		
		for(int i=0;i<6;i++) {
			add(bDebug[i]);
		}
		
		for(int i=0;i<3;i++) {
			add(menu[i]);
		}
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				gameFacade.mouseClicked(e);
				repaint();
				gameFacade.jogoAcabou();
			}
		});
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		defaultStroke = g2d.getStroke();
		DesenhaTodasAsPecas(g2d);
		DrawAll(g2d, defaultStroke);
		desenhos.PecaNaMesmaCasaCoresDiferentes(jogador, g2d, defaultStroke);
		
	}
	
	public void DesenhaTodasAsPecas(Graphics2D g2d) {
		//printa as pecas
			for(int i=0;i<4;i++) {
				g2d.setColor(new Color(150, 0, 0));
				jogador[0].getPecas().get(i).setEllipse(new Ellipse2D.Double(jogador[0].getPecas().get(i).getX(), jogador[0].getPecas().get(i).getY(), 40, 40));
				g2d.fill(jogador[0].getPecas().get(i).getEllipse());

			}
				
			for(int i=0;i<4;i++) {
				g2d.setColor(new Color(0, 150, 0));
				jogador[1].getPecas().get(i).setEllipse(new Ellipse2D.Double(jogador[1].getPecas().get(i).getX(), jogador[1].getPecas().get(i).getY(), 40, 40));
				g2d.fill(jogador[1].getPecas().get(i).getEllipse());
			}
				
			for(int i=0;i<4;i++) {
				g2d.setColor(new Color(0, 0, 150));
				jogador[2].getPecas().get(i).setEllipse(new Ellipse2D.Double(jogador[2].getPecas().get(i).getX(), jogador[2].getPecas().get(i).getY(), 40, 40));
				g2d.fill(jogador[2].getPecas().get(i).getEllipse());
			}
				
			for(int i=0;i<4;i++) {
				g2d.setColor(new Color(150, 150, 0));
				jogador[3].getPecas().get(i).setEllipse(new Ellipse2D.Double(jogador[3].getPecas().get(i).getX(), jogador[3].getPecas().get(i).getY(), 40, 40));
				g2d.fill(jogador[3].getPecas().get(i).getEllipse());
			}
	}
	
	public void DrawAll(Graphics2D g2d, Stroke defaultStroke) {
		Tabuleiro.getTabuleiro().paintTabuleiro(g2d);
		DrawDado(g2d);
		DiceColor(g2d, defaultStroke);
		DesenhaTodasAsPecas(g2d);
		PecaNaMesmaCasa(g2d, defaultStroke);
	}
	
	public void DiceColor(Graphics2D g2d, Stroke defaultStroke) {
		desenhos.diceColor(g2d,gameFacade.getNumJogador(), defaultStroke);
	}
	
	public void PecaNaMesmaCasa(Graphics2D g2d, Stroke defaultStroke) {
		desenhos.PecaNaMesmaCasa(jogador, g2d, defaultStroke);
	}
	
	public void DrawDado(Graphics2D g2d) {
		dado.drawDado(g2d);
	}
	
	public JButton[] eventDebugDado() {
		bDebug[0].setBounds(950, 650, 50, 50);
		bDebug[0].addMouseListener(new MouseAdapter() {
			@Override
			 public void mouseClicked(MouseEvent e) {
				if(!Dado.dadoClicado) {
					dado.setNumDado(1);
					repaint();
				}
				Dado.dadoClicado = true;
			 }
		});
		
		bDebug[1].setBounds(1000, 650, 50, 50);
		bDebug[1].addMouseListener(new MouseAdapter() {
			@Override
			 public void mouseClicked(MouseEvent e) {
				if(!Dado.dadoClicado) {
					dado.setNumDado(2);
					repaint();
				}
				Dado.dadoClicado = true;
			 }
		});
		
		bDebug[2].setBounds(1050, 650, 50, 50);
		bDebug[2].addMouseListener(new MouseAdapter() {
			@Override
			 public void mouseClicked(MouseEvent e) {
				if(!Dado.dadoClicado) {
					dado.setNumDado(3);
					repaint();
				}
				Dado.dadoClicado = true;
			 }
		});
		
		bDebug[3].setBounds(1100, 650, 50, 50);
		bDebug[3].addMouseListener(new MouseAdapter() {
			@Override
			 public void mouseClicked(MouseEvent e) {
				if(!Dado.dadoClicado) {
					dado.setNumDado(4);
					repaint();
				}
				Dado.dadoClicado = true;
			 }
		});
		
		bDebug[4].setBounds(1150, 650, 50, 50);
		bDebug[4].addMouseListener(new MouseAdapter() {
			@Override
			 public void mouseClicked(MouseEvent e) {
				if(!Dado.dadoClicado) {
					dado.setNumDado(5);
					repaint();
				}
				Dado.dadoClicado = true;
			 }
		});
		
		bDebug[5].setBounds(1200, 650, 50, 50);
		bDebug[5].addMouseListener(new MouseAdapter() {
			@Override
			 public void mouseClicked(MouseEvent e) {
				if(!Dado.dadoClicado) {
					dado.setNumDado(6);
					repaint();
				}
				Dado.dadoClicado = true;
			 }
		});
		
	return bDebug;
	}
	
	public JButton eventDado() {
		bDice.setBounds(1000, 400, 100, 50);
		bDice.addMouseListener(new MouseAdapter() {
			@Override
			 public void mouseClicked(MouseEvent e) {
				if(!Dado.dadoClicado) {
					dado.getRandNumDado();
					repaint();
				}
				Dado.dadoClicado = true;
			 }
		});
		return bDice;
	}
	
	public JButton[] eventMenu() {
		menu[0].setBounds(1000, 200, 100, 50);
		menu[0].addMouseListener(new MouseAdapter() {  // novo jogo
			@Override
			 public void mouseClicked(MouseEvent e) {
				gameFacade.zerarPartida();
				repaint();
			 }
		});
		
		menu[1].setBounds(1000, 140, 100, 50);
		menu[1].addMouseListener(new MouseAdapter() {  //salvar
			@Override
			 public void mouseClicked(MouseEvent e) {
				try {
					gameFacade.salvamento();
				} catch (IOException e1) {
					System.out.println("teste");
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				repaint();
			 }
		});
		
		menu[2].setBounds(1000, 80, 100, 50);
		menu[2].addMouseListener(new MouseAdapter() {  //carregar
			@Override
			 public void mouseClicked(MouseEvent e) {
				try {
					gameFacade.carregamento();
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				repaint();
			 }
		});
		return menu;
	}
	
}