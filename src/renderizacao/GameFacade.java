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

public class GameFacade{

	private Jogador[] jogador = new Jogador[4];
	private Dado dado;
	private int dadoVal;
	private final int MAX_CASAS = 56;
	private static int ultimoMovimentado = 0;
	private boolean mov[]=new boolean[4];
	boolean jogoAcabou = false;
	private static int nJogador = new Random().nextInt(2);
	private int ultimoMov=0;
	
	public GameFacade() throws IOException {
		mov[0]=true;
		mov[1]=true;
		mov[2]=true;
		mov[3]=true;
		jogador[0] = new Jogador(1);
		jogador[1] = new Jogador(2);
		jogador[2] = new Jogador(3);
		jogador[3] = new Jogador(4);
	
		dado = new Dado();
	}
	
	public void mouseClicked(MouseEvent e) {
		todasPecasNoIncio();
		
		if(Dado.dadoClicado && !jogoAcabou) {
			dadoVal = dado.getNumDado();
			for(int i=0;i<4;i++) {
				if(jogador[nJogador].getPecas().get(i).getEllipse().contains(e.getPoint()) && !jogador[nJogador].getPecas().get(i).isUltimaCasa()) {
					if(!jogador[nJogador].getPecas().get(i).isPodeSair() && dadoVal != 5) {
						if(!jogador[nJogador].isPecaIniciada()) {
							int x=jogador[nJogador].getInicialX(i);
							int y=jogador[nJogador].getInicialY(i);
							jogador[nJogador].getPecas().get(i).setX(jogador[nJogador].getCasas().get(0).getX());
							jogador[nJogador].getPecas().get(i).setY(jogador[nJogador].getCasas().get(0).getY());
							if(comePeca(i, 0, nJogador, 0, x, y)) {
								mov[i] = false;
								break;
							}
							jogador[nJogador].setNumJogadas(0);
							jogador[nJogador].setPecaIniciada(true);
							jogador[nJogador].getPecas().get(i).setPodeSair(true);
							break;
						}
						mov[i] = false;
						break;
					}else if(!jogador[nJogador].getPecas().get(i).isPodeSair() && dadoVal == 5){
						int x=jogador[nJogador].getInicialX(i);
						int y=jogador[nJogador].getInicialY(i);
						if(!jogador[nJogador].isPecaIniciada()) {
							jogador[nJogador].getPecas().get(i).setX(jogador[nJogador].getCasas().get(0).getX());
							jogador[nJogador].getPecas().get(i).setY(jogador[nJogador].getCasas().get(0).getY());
							if(comePeca(i, 0, nJogador, 0, x, y)) {
								mov[i]=false;
								break;
							}
							jogador[nJogador].setPecaIniciada(true);
							jogador[nJogador].getPecas().get(i).setPodeSair(true);
							break;
						}
						jogador[nJogador].getPecas().get(i).setX(jogador[nJogador].getCasas().get(0).getX());
						jogador[nJogador].getPecas().get(i).setY(jogador[nJogador].getCasas().get(0).getY());
						if(comePeca(i, 0, nJogador, 0, x, y)) {
							mov[i]=false;
							break;
						}
						jogador[nJogador].setNumJogadas(0);
						jogador[nJogador].setPecaIniciada(true);
						jogador[nJogador].getPecas().get(i).setPodeSair(true);
						nJogador++;
						Dado.dadoClicado = false;
						break;
					}else {
						
						/*
						if(saidaObrigatoria(i, nJogador, dadoVal)) {
							break;
						}
						*/
						
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
							i = procuraBarreira(i, nJogador);
							jogador[nJogador].getPecas().get(i).setNumCasa(val);
							jogador[nJogador].getPecas().get(i).setX(jogador[nJogador].getCasas().get(val).getX());
							jogador[nJogador].getPecas().get(i).setY(jogador[nJogador].getCasas().get(val).getY());
							
							if(checaBarreira(i, val, dadoVal, nJogador)) {
								jogador[nJogador].getPecas().get(i).setNumCasa(casaInicial);
								jogador[nJogador].getPecas().get(i).setX(xInicial);
								jogador[nJogador].getPecas().get(i).setY(yInicial);
								System.out.println("Barreira detectada, escolha outra peca");
								mov[i]=false;
								break;
							}
							
							if(comePeca(i, val, nJogador, casaInicial, xInicial, yInicial)) {
								mov[i]=false;
								break;
							}
							
							ultimoMov = i;
							jogador[nJogador].sumNumJogadas();
							Dado.dadoClicado = false;
							break;
						}else if (dadoVal == 6 && (jogador[nJogador].getNumJogadas() == 2)){
							i = procuraBarreira(i, nJogador);
							//int peca = ProcuraUltimaPecaMovimentada(nJogador, i);
							jogador[nJogador].getPecas().get(ultimoMov).setX(jogador[nJogador].getInicialX(ultimoMov));
							jogador[nJogador].getPecas().get(ultimoMov).setY(jogador[nJogador].getInicialY(ultimoMov));
							jogador[nJogador].getPecas().get(ultimoMov).setNumCasa(0);
							jogador[nJogador].setNumJogadas(0);
							jogador[nJogador].getPecas().get(ultimoMov).setPodeSair(false);
							nJogador++;
							Dado.dadoClicado = false;
							break;
						}
						
						jogador[nJogador].setNumJogadas(0);
						
						jogador[nJogador].getPecas().get(i).setNumCasa(val);
						jogador[nJogador].getPecas().get(i).setX(jogador[nJogador].getCasas().get(val).getX());
						jogador[nJogador].getPecas().get(i).setY(jogador[nJogador].getCasas().get(val).getY());
						
						if(checaBarreira(i, val, dadoVal, nJogador)) {
							jogador[nJogador].getPecas().get(i).setNumCasa(casaInicial);
							jogador[nJogador].getPecas().get(i).setX(xInicial);
							jogador[nJogador].getPecas().get(i).setY(yInicial);
							System.out.println("Barreira detectada, escolha outra peca");
							mov[i]=false;
							break;
						}
						
						if(comePeca(i, val, nJogador, casaInicial, xInicial, yInicial)) {
							mov[i]=false;
							break;
						}
						
						Dado.dadoClicado = false;
						nJogador++;
						break;
					}
				}
			}
			if(temMovimento()) {
				mov[0]=true;
				mov[1]=true;
				mov[2]=true;
				mov[3]=true;
				nJogador++;
				Dado.dadoClicado = false;
			}
		}
		
		jogoAcabou();
		nJogador = (nJogador == 2) ? 0: nJogador;
	}
	
	private boolean checaCasaInicial(int id, int val, int numJogador) {
		int x,y,px,py, quant=0;
		
		for(int i=0;i<4;i++)
		{
			for(int j=0; j<4;j++)
			{
				px = jogador[numJogador].getPecas().get(id).getX();
				py = jogador[numJogador].getPecas().get(id).getY();
				if( jogador[i].getPecas().get(j).getX()==px && jogador[i].getPecas().get(j).getY()==py && jogador[numJogador]!=jogador[i])
				{
					quant++;
				}
				if(jogador[i].getPecas().get(j).getX()==px && jogador[i].getPecas().get(j).getY()==py
						&& (id != j) && jogador[numJogador] == jogador[i] && jogador[i].getCasas().get(val).isCasaEspecial()) {
					x=jogador[i].getInicialX(j);
					y=jogador[i].getInicialY(j);
					jogador[numJogador].getPecas().get(id).setNumCasa(0);
					jogador[numJogador].getPecas().get(id).setX(x);
					jogador[numJogador].getPecas().get(id).setY(y);
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
						x=jogador[i].getInicialX(j);
						y=jogador[i].getInicialY(j);
						jogador[numJogador].getPecas().get(id).setNumCasa(0);
						jogador[numJogador].getPecas().get(id).setX(x);
						jogador[numJogador].getPecas().get(id).setY(y);
						return true;
					}
				}
			}
		}
		return false;
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
				if( jogador[i].getPecas().get(j).getX()==px && jogador[i].getPecas().get(j).getY()==py && jogador[numJogador]!=jogador[i])
				{
					quant++;
				}
				if(jogador[i].getPecas().get(j).getX()==px && jogador[i].getPecas().get(j).getY()==py
						&& (id != j) && jogador[numJogador] == jogador[i] && jogador[i].getCasas().get(val).isCasaEspecial()) {
					jogador[numJogador].getPecas().get(id).setNumCasa(casaInicial);
					jogador[numJogador].getPecas().get(id).setX(xInicial);
					jogador[numJogador].getPecas().get(id).setY(yInicial);
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
						jogador[numJogador].getPecas().get(id).setNumCasa(casaInicial);
						jogador[numJogador].getPecas().get(id).setX(xInicial);
						jogador[numJogador].getPecas().get(id).setY(yInicial);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean checaBarreira(int id, int val, int dadoVal, int numJogador) {
		int px,py;
		
		for(int i=(val-(dadoVal));i<val;i++)
		{
			for(int j=0; j<4;j++)
			{
				for(int k=0;k<4;k++) {	
					px = jogador[numJogador].getCasas().get(i).getX();
					py = jogador[numJogador].getCasas().get(i).getY();
					if( jogador[j].getPecas().get(k).getX()==px && jogador[j].getPecas().get(k).getY()==py
							&& jogador[j].getPecas().get(k).isBarreira())
					{
						return true;
					}
				}
			}
		}
		
		for(int i=0; i<4;i++)
		{
			for(int j=0;j<4;j++) {	
				px = jogador[numJogador].getPecas().get(id).getX();
				py = jogador[numJogador].getPecas().get(id).getY();
				if( jogador[i].getPecas().get(j).getX()==px && jogador[i].getPecas().get(j).getY()==py 
						&& !jogador[numJogador].getPecas().get(id).isBarreira() && jogador[i].getPecas().get(j).isBarreira())
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	private int procuraBarreira(int id, int numJogador) {
		for(int i=0;i<4;i++) {
			if(jogador[numJogador].getPecas().get(i).isBarreira()) {
				return i;
			}
		}
		return id;
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
	
	private boolean saidaObrigatoria(int id, int numJogador, int dadoVal) {
		if(dadoVal == 5){
			
		}
		return true;
	}
	
	private boolean temMovimento() {
		for(int i=0;i<4;i++) {
			if(mov[i] == true) {
				return false;
			}
		}
		return true;
	}
	
	private void jogoAcabou() {
		int qtd=0;
		for(int i=0;i<4;i++) {
			if(jogador[nJogador].getPecas().get(i).isUltimaCasa()) {
				qtd++;
			}
		}
		
		if(qtd == 4) {
			jogoAcabou = true;
			System.out.println("Jogo acabou. Jogador " + nJogador + " venceu.");
		}
		
	}
	
	public Jogador[] getJogador() {
		return jogador;
	}
	
	public int getNumJogador() {
		return nJogador;
	}
	
}
