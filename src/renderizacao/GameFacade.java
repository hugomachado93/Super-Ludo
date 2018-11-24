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
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GameFacade extends Observable{

	private static int nJogador = new Random().nextInt(2);
	private Jogador[] jogador = new Jogador[4];
	private JButton[] bDebug = new JButton[6];
	private JButton bDice;
	private Dado dado;
	private Desenhos desenhos;
	private int dadoVal;
	private final int MAX_CASAS = 56;
	private static int ultimoMovimentado = 0;
	private int dadoValDebug;
	
	public GameFacade() throws IOException {
		jogador[0] = new Jogador(1);
		jogador[1] = new Jogador(2);
		jogador[2] = new Jogador(3);
		jogador[3] = new Jogador(4);
		
		bDice = new JButton("Dado");
		dado = new Dado();
		desenhos = new Desenhos();
		
		bDebug[0] = new JButton("1");
		bDebug[1] = new JButton("2");
		bDebug[2] = new JButton("3");
		bDebug[3] = new JButton("4");
		bDebug[4] = new JButton("5");
		bDebug[5] = new JButton("6");

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
	
	public JButton[] eventDebugDado() {
			bDebug[0].setBounds(950, 650, 50, 50);
			bDebug[0].addMouseListener(new MouseAdapter() {
				@Override
				 public void mouseClicked(MouseEvent e) {
					if(!Dado.dadoClicado) {
						dado.setNumDado(1);
						setChanged();
						notifyObservers();
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
						setChanged();
						notifyObservers();
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
						setChanged();
						notifyObservers();
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
						setChanged();
						notifyObservers();
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
						setChanged();
						notifyObservers();
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
						setChanged();
						notifyObservers();
					}
					Dado.dadoClicado = true;
				 }
			});
			
		return bDebug;
	}
	
	public void mouseClicked(MouseEvent e) {
		
		todasPecasNoIncio();
		
		if(Dado.dadoClicado) {
			dadoVal = dado.getNumDado();
			for(int i=0;i<4;i++) {
				if(jogador[nJogador].getPecas().get(i).getEllipse().contains(e.getPoint()) && !jogador[nJogador].getPecas().get(i).isUltimaCasa()) {
					if(!jogador[nJogador].getPecas().get(i).isPodeSair() && dadoVal != 5) {
						if(!jogador[nJogador].isPecaIniciada()) {
							jogador[nJogador].setPecaIniciada(true);
							jogador[nJogador].getPecas().get(0).setPodeSair(true);
							jogador[nJogador].getPecas().get(0).setX(jogador[nJogador].getCasas().get(0).getX());
							jogador[nJogador].getPecas().get(0).setY(jogador[nJogador].getCasas().get(0).getY());
							setChanged();
							notifyObservers();
							break;
						}
						break;
					}else if(!jogador[nJogador].getPecas().get(i).isPodeSair() && dadoVal == 5){
						if(!jogador[nJogador].isPecaIniciada()) {
							jogador[nJogador].setPecaIniciada(true);
							jogador[nJogador].getPecas().get(0).setPodeSair(true);
							jogador[nJogador].getPecas().get(0).setX(jogador[nJogador].getCasas().get(0).getX());
							jogador[nJogador].getPecas().get(0).setY(jogador[nJogador].getCasas().get(0).getY());
							setChanged();
							notifyObservers();
							break;
						}
						jogador[nJogador].setPecaIniciada(true);
						jogador[nJogador].getPecas().get(i).setPodeSair(true);
						jogador[nJogador].getPecas().get(i).setX(jogador[nJogador].getCasas().get(0).getX());
						jogador[nJogador].getPecas().get(i).setY(jogador[nJogador].getCasas().get(0).getY());
						System.out.println("AQUI3");
						nJogador++;
						Dado.dadoClicado = false;
						setChanged();
						notifyObservers();
						break;
					}else {
						jogador[nJogador].getPecas().get(i).setUltimoMovimentado(ultimoMovimentado++);
						
						int val = dadoVal + jogador[nJogador].getPecas().get(i).getNumCasa();
						
						int casaInicial = jogador[nJogador].getPecas().get(i).getNumCasa();
						int xInicial = jogador[nJogador].getPecas().get(i).getX();
						int yInicial = jogador[nJogador].getPecas().get(i).getY();
						
						if(val > MAX_CASAS) {
							val = MAX_CASAS - (val - MAX_CASAS);
						}else if(val == MAX_CASAS) {
							jogador[nJogador].getPecas().get(i).setUltimaCasa(true);
						}
						
						if(dadoVal == 6 && (jogador[nJogador].getNumJogadas() != 2)) {
							jogador[nJogador].sumNumJogadas();
							jogador[nJogador].getPecas().get(i).setNumCasa(val);
							jogador[nJogador].getPecas().get(i).setX(jogador[nJogador].getCasas().get(val).getX());
							jogador[nJogador].getPecas().get(i).setY(jogador[nJogador].getCasas().get(val).getY());
							
							if(comePeca(i, val, nJogador, casaInicial, xInicial, yInicial)) {
								break;
							}
							
							if(checaBarreira(i, val, dadoVal, nJogador)) {
								jogador[nJogador].getPecas().get(i).setNumCasa(casaInicial);
								jogador[nJogador].getPecas().get(i).setX(xInicial);
								jogador[nJogador].getPecas().get(i).setY(yInicial);
								System.out.println("Barreira detectada, escolha outra peca");
								break;
							}
							
							Dado.dadoClicado = false;
							break;
						}else if (dadoVal == 6 && (jogador[nJogador].getNumJogadas() == 2)){
							int peca = ProcuraUltimaPecaMovimentada(nJogador, i);
							jogador[nJogador].getPecas().get(peca).setX(jogador[nJogador].getInicialX(peca));
							jogador[nJogador].getPecas().get(peca).setY(jogador[nJogador].getInicialY(peca));
							jogador[nJogador].getPecas().get(peca).setNumCasa(0);
							jogador[nJogador].setNumJogadas(0);
							jogador[nJogador].getPecas().get(peca).setPodeSair(false);
							nJogador++;
							Dado.dadoClicado = false;
							break;
						}
						
						jogador[nJogador].getPecas().get(i).setNumCasa(val);
						jogador[nJogador].getPecas().get(i).setX(jogador[nJogador].getCasas().get(val).getX());
						jogador[nJogador].getPecas().get(i).setY(jogador[nJogador].getCasas().get(val).getY());
						
						if(comePeca(i, val, nJogador, casaInicial, xInicial, yInicial)) {
							break;
						}
						
						if(checaBarreira(i, val, dadoVal, nJogador)) {
							jogador[nJogador].getPecas().get(i).setNumCasa(casaInicial);
							jogador[nJogador].getPecas().get(i).setX(xInicial);
							jogador[nJogador].getPecas().get(i).setY(yInicial);
							System.out.println("Barreira detectada, escolha outra peca");
							break;
						}
							
						jogador[nJogador].setNumJogadas(0);
						nJogador++;
						Dado.dadoClicado = false;
						break;
					}
				}
			}
		}
		setChanged();
		notifyObservers();
		nJogador = (nJogador == 2) ? 0: nJogador;
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
	
	
	private boolean comePeca(int id,int val, int numJogador, int casaInicial, int xInicial, int yInicial)
	{
		int x,y,px,py, quant=0;
		
		for(int i=0;i<4;i++)
		{
			for(int j=0; j<4;j++)
			{
				px = jogador[numJogador].getPecas().get(id).getX();
				py = jogador[numJogador].getPecas().get(id).getY();
				if( jogador[i].getPecas().get(j).getX()==px && jogador[j].getPecas().get(j).getY()==py && jogador[numJogador]!=jogador[i])
				{
					quant++;
				}
				if(jogador[i].getPecas().get(j).getX()==px && jogador[i].getPecas().get(j).getY()==py
						&& (id != j) && jogador[i].getCasas().get(val).isCasaEspecial()) {
					System.out.println("COMEPECA!!");
					jogador[nJogador].getPecas().get(i).setNumCasa(casaInicial);
					jogador[nJogador].getPecas().get(i).setX(xInicial);
					jogador[nJogador].getPecas().get(i).setY(yInicial);
					return true;
				}
			}
		}
		System.out.println(quant);
		for(int i=0;i<4;i++)
		{
			for(int j=0; j<4;j++)
			{
				px = jogador[numJogador].getPecas().get(id).getX();
				py = jogador[numJogador].getPecas().get(id).getY();
				if( jogador[i].getPecas().get(j).getX()==px && jogador[i].getPecas().get(j).getY()==py && jogador[numJogador]!=jogador[i])
				{
					if(!jogador[numJogador].getCasas().get(val).isCasaEspecial()) {
						x=jogador[i].getInicialX(j);
						y=jogador[i].getInicialY(j);
						jogador[i].getPecas().get(j).setX(x);
						jogador[i].getPecas().get(j).setY(y);
						jogador[i].getPecas().get(j).setNumCasa(0);
						jogador[i].getPecas().get(j).setPodeSair(false);
						return false;
					}else if(jogador[numJogador].getCasas().get(val).isCasaEspecial() && quant < 2){
						return false;
					}else {
						jogador[nJogador].getPecas().get(i).setNumCasa(casaInicial);
						jogador[nJogador].getPecas().get(i).setX(xInicial);
						jogador[nJogador].getPecas().get(i).setY(yInicial);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean checaBarreira(int id, int val, int dadoVal, int numJogador) {
		int px,py;
		
		for(int i=(val-dadoVal);i<val;i++)
		{
			for(int j=0; j<4;j++)
			{
				for(int k=0;k<4;k++) {	
					px = jogador[numJogador].getCasas().get(i).getX();
					py = jogador[numJogador].getCasas().get(i).getY();
					if( jogador[j].getPecas().get(k).getX()==px && jogador[j].getPecas().get(k).getY()==py && jogador[numJogador]!=jogador[j]
							&& jogador[j].getPecas().get(k).isBarreira())
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private int ProcuraUltimaPecaMovimentada(int numJogador, int id) {
		int max = Integer.MAX_VALUE;
		for(int i=0;i<4;i++) {
			if(jogador[numJogador].getPecas().get(i).getUltimoMovimentado() < max) {
				return i;
			}
		}
		return -1;
	}
	
	private void todasPecasNoIncio() {
		for(int i=0;i<4;i++) {
			if(jogador[nJogador].getPecas().get(i).isPodeSair() == false) {
				jogador[nJogador].setPecaIniciada(false);
			}else {
				jogador[nJogador].setPecaIniciada(true);
				break;
			}
		}
	}
	
}
