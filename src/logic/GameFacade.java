package logic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Observable;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameFacade extends Observable{

	private Jogador[] jogador = new Jogador[4];
	private Dado dado;
	private int dadoVal;
	private final int MAX_CASAS = 56;
	private static int ultimoMovimentado = 0;
	private boolean mov[]=new boolean[4];
	boolean jogoAcabou = false;
	private static int nJogador = 0;
	private int ultimoMov=0;
	private boolean ultimaCasa = false;
	private int peca;
	private int jogadorTemp;
	private File dados;
	private boolean pecaComida = false;
	private JFileChooser file = new JFileChooser();
	private boolean semMov;
	
	private static int turno = nJogador;
	
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
		dados = new File("saves/jogo.save");
	}
	
	public void mouseClicked(MouseEvent e) {
		dadoVal = dado.getNumDado();
		jogadorTemp = nJogador;
		semMov = false;
		if(verificaMovimentacao() && Dado.dadoClicado) {
			Dado.dadoClicado = false;
			nJogador++;
			semMov = true;
		}
		if(Dado.dadoClicado && !jogoAcabou && !semMov) {
			dadoVal = dado.getNumDado();
			for(int i=0;i<4;i++) {
				System.out.println(nJogador);
				peca = i;
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
					}else {    // É uma peça que está fora da casa inicial
						
						if(verificaMovimentacao()){
							nJogador++;
							break;
						}
						
						if(saidaObrigatoria(i, nJogador, dadoVal)) {
							jogador[nJogador-1].setNumJogadas(0);
							break;
						}
						
						
						int val = dadoVal + jogador[nJogador].getPecas().get(i).getNumCasa();
						
						int casaInicial = jogador[nJogador].getPecas().get(i).getNumCasa();
						int xInicial = jogador[nJogador].getPecas().get(i).getX();
						int yInicial = jogador[nJogador].getPecas().get(i).getY();
						
						if(val > MAX_CASAS) {   //entrou na trilha final
							mov[i] = false;
							break;
						}else if(val == MAX_CASAS) {
							jogador[nJogador].getPecas().get(i).setUltimaCasa(true);
							ultimaCasa = true;
							mov[i]=false;
							jogador[nJogador].getPecas().get(i).setNumCasa(val);
							jogador[nJogador].getPecas().get(i).setX(jogador[nJogador].getCasas().get(val).getX());
							jogador[nJogador].getPecas().get(i).setY(jogador[nJogador].getCasas().get(val).getY());
							dado.setNumDado(6);
							nJogador++;
							break;
						}
						
						if(dadoVal == 6 && (jogador[nJogador].getNumJogadas() != 2) && !pecaComida && !ultimaCasa) {
							i = procuraBarreira(i, val, nJogador, casaInicial, xInicial, yInicial);
							casaInicial = jogador[nJogador].getPecas().get(i).getNumCasa();
							xInicial = jogador[nJogador].getPecas().get(i).getX();
							yInicial = jogador[nJogador].getPecas().get(i).getY();
							val = jogador[nJogador].getPecas().get(i).getNumCasa() + 6;
							jogador[nJogador].getPecas().get(i).setNumCasa(val);
							jogador[nJogador].getPecas().get(i).setX(jogador[nJogador].getCasas().get(jogador[nJogador].getPecas().get(i).getNumCasa()).getX());
							jogador[nJogador].getPecas().get(i).setY(jogador[nJogador].getCasas().get(jogador[nJogador].getPecas().get(i).getNumCasa()).getY());
							
							if(checaBarreira(i, val, dadoVal, nJogador)) {
								jogador[nJogador].getPecas().get(i).setNumCasa(casaInicial);
								jogador[nJogador].getPecas().get(i).setX(xInicial);
								jogador[nJogador].getPecas().get(i).setY(yInicial);
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
						}else if (dadoVal == 6 && (jogador[nJogador].getNumJogadas() == 2) && !pecaComida){
							i = procuraBarreira(i, val, nJogador, casaInicial, xInicial, yInicial);
							jogador[nJogador].getPecas().get(ultimoMov).setX(jogador[nJogador].getInicialX(ultimoMov));
							jogador[nJogador].getPecas().get(ultimoMov).setY(jogador[nJogador].getInicialY(ultimoMov));
							jogador[nJogador].getPecas().get(ultimoMov).setNumCasa(0);
							jogador[nJogador].setNumJogadas(0);
							jogador[nJogador].getPecas().get(ultimoMov).setPodeSair(false);
							nJogador++;
							Dado.dadoClicado = false;
							break;
						}
						ultimaCasa = false;
						pecaComida = false;
						jogador[nJogador].setNumJogadas(0);
						
						jogador[nJogador].getPecas().get(i).setNumCasa(val);
						jogador[nJogador].getPecas().get(i).setX(jogador[nJogador].getCasas().get(val).getX());
						jogador[nJogador].getPecas().get(i).setY(jogador[nJogador].getCasas().get(val).getY());
						
						if(checaBarreira(i, val, dadoVal, nJogador)) {
							jogador[nJogador].getPecas().get(i).setNumCasa(casaInicial);
							jogador[nJogador].getPecas().get(i).setX(xInicial);
							jogador[nJogador].getPecas().get(i).setY(yInicial);
							System.out.println("Barreira 	detectada, escolha outra peca");
							mov[i]=false;
							break;
						}
						
						if(comePeca(i, val, nJogador, casaInicial, xInicial, yInicial)) {
							mov[i]=false;
							break;
						}
						//System.out.println("Moveu");
						Dado.dadoClicado = false;
						nJogador++;
						System.out.println(nJogador);
						break;
					}
				}else if(jogador[nJogador].getPecas().get(i).isUltimaCasa()){
					mov[i]=false;
				}
			}

			if(temMovimento(peca)) {
				//System.out.println("checaMovimento");
				mov[0]=true;
				mov[1]=true;
				mov[2]=true;
				mov[3]=true;
				nJogador++;
				Dado.dadoClicado = false;
			}
		}
		jogoAcabou();
		setChanged();
		notifyObservers(nJogador);
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
						&& (id != j) && jogador[numJogador] == jogador[i] 
								&& (jogador[i].getCasas().get(val).isCasaEspecial() || jogador[i].getCasas().get(val).isCasaInicial())) {
					jogador[numJogador].getPecas().get(id).setNumCasa(casaInicial);
					jogador[numJogador].getPecas().get(id).setX(xInicial);
					jogador[numJogador].getPecas().get(id).setY(yInicial);
					return true;
				}
				
			}
		}
		
		for(int i=0;i<4;i++)
		{
			for(int j=0; j<4;j++)
			{
				px = jogador[numJogador].getPecas().get(id).getX();
				py = jogador[numJogador].getPecas().get(id).getY();
				if( jogador[i].getPecas().get(j).getX()==px && jogador[i].getPecas().get(j).getY()==py && jogador[numJogador]!=jogador[i])
				{
					if(!jogador[numJogador].getCasas().get(val).isCasaEspecial()) {
						jogador[numJogador].getPecas().get(id).setPodeSair(true);
						x=jogador[i].getInicialX(j);
						y=jogador[i].getInicialY(j);
						jogador[i].getPecas().get(j).setX(x);
						jogador[i].getPecas().get(j).setY(y);
						jogador[i].getPecas().get(j).setNumCasa(0);
						jogador[i].getPecas().get(j).setPodeSair(false);
						if(jogador[numJogador].getPecas().get(id).isPodeSair()) {
							dado.setNumDado(6);
							pecaComida = true;
						}
						return true;
					}else if(jogador[i].getCasas().get(j).isCasaInicial() && quant < 2){
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
		
		for(int i=(val-(dadoVal));i<val;i++)  //Procura todas as casas até o a casa de destino
		{
			for(int j=0; j<4;j++)
			{
				for(int k=0;k<4;k++) {
					px = jogador[numJogador].getCasas().get(i).getX();
					py = jogador[numJogador].getCasas().get(i).getY();
					if( jogador[j].getPecas().get(k).getX()==px && jogador[j].getPecas().get(k).getY()==py
							&& jogador[j].getPecas().get(k).isBarreira() && !jogador[j].getPecas().get(k).isUltimaCasa())
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
						&& !jogador[numJogador].getPecas().get(id).isBarreira() && jogador[i].getPecas().get(j).isBarreira() 
						&& !jogador[numJogador].getPecas().get(id).isUltimaCasa() && !jogador[i].getPecas().get(j).isUltimaCasa())
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean verificaMovimentacao() {
		boolean movimento[] = new boolean[4];
		for(int i=0;i<4;i++) {
		/*
			int casaInicial = jogador[nJogador].getPecas().get(i).getNumCasa();
			int  xInicial = jogador[nJogador].getPecas().get(i).getX();
			int yInicial = jogador[nJogador].getPecas().get(i).getY();
			int val = jogador[nJogador].getPecas().get(i).getNumCasa() + dado.getNumDado();
			jogador[nJogador].getPecas().get(i).setNumCasa(val);
			jogador[nJogador].getPecas().get(i).setX(jogador[nJogador].getCasas().get(val).getX());
			jogador[nJogador].getPecas().get(i).setY(jogador[nJogador].getCasas().get(val).getY());
			val = dadoVal + jogador[nJogador].getPecas().get(i).getNumCasa();
			
			movimento[i] = comePeca(i, val , nJogador, casaInicial, xInicial, yInicial);
			movimento[i] = checaBarreira(i, val, dadoVal, nJogador);
			*/
			if(dado.getNumDado() != 5) {
				if(jogador[nJogador].getPecas().get(i).isPodeSair() == false) {
					movimento[i] = true;
				}
			}
			
			if(jogador[nJogador].getPecas().get(i).isUltimaCasa() == true) {
				movimento[i] = true;
			}
			/*	
			jogador[nJogador].getPecas().get(i).setNumCasa(casaInicial);
			jogador[nJogador].getPecas().get(i).setX(xInicial);
			jogador[nJogador].getPecas().get(i).setY(yInicial);
			*/
		}
		
		for(int i=0;i<4;i++) {
			//System.out.println(movimento[i]);
			if(movimento[i] == false) {
				return false;
			}
		}
		Dado.dadoClicado = false;
		return true;
	}
	
	private int procuraBarreira(int id,int val, int numJogador, int casaInicial, int xInicial, int yInicial) {
		
		for(int i=0;i<4;i++) {
			if(jogador[numJogador].getPecas().get(i).isBarreira() && !jogador[numJogador].getPecas().get(i).isUltimaCasa()) {	
				return i;
			}
		}
		return id;
	}
	
	public void todasPecasNoIncio() {
		for(int i=0;i<4;i++) {
				
				if(!jogador[nJogador].isPecaIniciada()) {
					jogador[nJogador].setPecaIniciada(true);
					int x=jogador[nJogador].getInicialX(i);
					int y=jogador[nJogador].getInicialY(i);
					jogador[nJogador].getPecas().get(i).setX(jogador[nJogador].getCasas().get(0).getX());
					jogador[nJogador].getPecas().get(i).setY(jogador[nJogador].getCasas().get(0).getY());
					jogador[nJogador].setNumJogadas(0);
					jogador[nJogador].getPecas().get(i).setPodeSair(true);
					break;
				}
		}
		
	}
	
	public boolean saidaObrigatoria(int id, int numJogador, int dadoVal) {
		int peca = -1;
		if(dadoVal == 5){
			for(int i=0;i<4;i++) {
				if(i != id && jogador[numJogador].getPecas().get(i).isPodeSair() == false) {	
					int x=jogador[numJogador].getInicialX(i);
					int y=jogador[numJogador].getInicialY(i);
					jogador[numJogador].getPecas().get(i).setX(jogador[numJogador].getCasas().get(0).getX());
					jogador[numJogador].getPecas().get(i).setY(jogador[numJogador].getCasas().get(0).getY());
					if(comePeca(i, 0, numJogador, 0, x, y)) {
						peca = i;
						//jogador[numJogador].getPecas().get(i).setX(x);
						//jogador[numJogador].getPecas().get(i).setY(y);
						//jogador[numJogador].getPecas().get(i).setPodeSair(true);
						return false;
					}
					jogador[numJogador].getPecas().get(i).setX(x);
					jogador[numJogador].getPecas().get(i).setY(y);
				}
			}
			
			for(int i=0;i<4;i++) {
				if(!jogador[numJogador].getPecas().get(i).isPodeSair()) {
					jogador[numJogador].getPecas().get(i).setX(jogador[numJogador].getCasas().get(0).getX());
					jogador[numJogador].getPecas().get(i).setY(jogador[numJogador].getCasas().get(0).getY());
					jogador[numJogador].getPecas().get(i).setNumCasa(0);
					jogador[numJogador].getPecas().get(i).setPodeSair(true);
					nJogador++;
					Dado.dadoClicado = false;
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean temMovimento(int id) {
		for(int i=0; i<4;i++)
		{
			for(int j=0;j<4;j++) {	
				//System.out.println("checaMovimento"+id);
				int py = jogador[jogadorTemp].getPecas().get(id).getY();
				int px = jogador[jogadorTemp].getPecas().get(id).getX();
				if( jogador[i].getPecas().get(j).getX()==px && jogador[i].getPecas().get(j).getY()==py 
						&& !jogador[jogadorTemp].getPecas().get(id).isBarreira() && jogador[i].getPecas().get(j).isBarreira() 
						&& !jogador[jogadorTemp].getPecas().get(id).isUltimaCasa() && !jogador[i].getPecas().get(j).isUltimaCasa())
				{
					mov[i] = false;
				}
			}
		}
		for(int i=0;i<4;i++) {
			if(mov[i] == true) {
				return false;
			}
		}
		return true;
	}
	
	public void jogoAcabou() {
		int qtd=0;
		int sumPontos[] = new int[4];
		Integer posicao[] = new Integer[4];
		int colocacao[] = new int[4];
		for(int i=0;i<4;i++) {
			if(jogador[jogadorTemp].getPecas().get(i).isUltimaCasa()) {
				qtd++;
			}
		}
		
		if(qtd == 4) {
		
			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
					sumPontos[i] += jogador[i].getPecas().get(j).getNumCasa();
				}
			}
			
			posicao[0] = sumPontos[0];
			posicao[1] = sumPontos[1];
			posicao[2] = sumPontos[2];
			posicao[3] = sumPontos[3];
			
			Arrays.sort(posicao, Collections.reverseOrder());	
			
			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
					if(sumPontos[i] == posicao[j]) {
						colocacao[i] = j+1;
					}
				}
			}
			
			jogoAcabou = true;
			JOptionPane.showMessageDialog(null, "1 - jogador " + colocacao[0] + "\n"
					+ "2 - jogador " + colocacao[1] + "\n"
					+ "3 - jogador " + colocacao[2] + "\n"
					+ "4 - jogador " + colocacao[3] + "\n");
			
			int input = JOptionPane.showConfirmDialog(null, "Deseja jogar novamente?", "Jogo finalizado", JOptionPane.YES_NO_CANCEL_OPTION);
			
			if(input == JOptionPane.YES_OPTION) {
				zerarPartida();
			}
			
		}
		
	}
	
	public Jogador[] getJogador() {
		return jogador;
	}
	
	public int getNumJogador() {
		return nJogador;
	}
	
	public void setNumJogador(int val) {
		nJogador = val;
	}
	
	public void salvamento() throws IOException
	{
		int cond1 = 0;
		System.out.println("salvou");
		int x,y,num,ultimo,numJog;
		boolean abrigo,barreira,ultimaCasa,podeSair,primeiraJog,pecaIni;
		Peca peca;
		file.setCurrentDirectory(new java.io.File("."));
		file.setDialogTitle("Salvamento");
		file.setFileSelectionMode(JFileChooser.FILES_ONLY);
		cond1 = file.showSaveDialog(file);
		System.out.println(cond1);
		if(cond1 == JFileChooser.APPROVE_OPTION)
		{
			System.out.println("salvou");
			dados = file.getSelectedFile();
			Writer wr = new FileWriter(dados);
			for(int i=0;i<4;i++)
			{
				for(int j=0;j<4;j++)
				{
					peca = jogador[i].getPecas().get(j);
					x = peca.getX();
					y = peca.getY();
					num = peca.getNumCasa();
					ultimo = peca.getUltimoMovimentado();
					abrigo = peca.isAbrigo();
					barreira = peca.isBarreira();
					ultimaCasa = peca.isUltimaCasa();
					podeSair = peca.isPodeSair();
					
					wr.write(String.valueOf(x) + " " + String.valueOf(y) + " " + String.valueOf(num) + " "+ String.valueOf(ultimo) + " ");
					wr.write(String.valueOf(abrigo) + " " + String.valueOf(barreira) + " " + String.valueOf(ultimaCasa) + " " + String.valueOf(podeSair));
					wr.write("\n");
				}
				numJog = jogador[i].getNumJogadas();
				pecaIni = jogador[i].isPecaIniciada();
				primeiraJog = jogador[i].isPrimeiraJogada();
				wr.write(String.valueOf(numJog) + " " + String.valueOf(pecaIni) + " " + String.valueOf(primeiraJog));
				wr.write("\n");
				wr.write("\n");
			}
			wr.write(String.valueOf(nJogador) + " " + String.valueOf(dado.getNumDado()) + " " + String.valueOf(Dado.dadoClicado) + " " + String.valueOf(ultimoMovimentado) + " ");
			wr.write(String.valueOf(ultimoMov) + " " + String.valueOf(jogadorTemp) + " " + String.valueOf(pecaComida)+ " " + String.valueOf(jogoAcabou) + " " + String.valueOf(semMov)+ " ");
			wr.write("\n");
			wr.write(String.valueOf(mov[0])+ " " + String.valueOf(mov[1])+ " " + String.valueOf(mov[2])+ " " + String.valueOf(mov[3]));
			wr.flush();
			wr.close();
		}
		else if(cond1 == JFileChooser.CANCEL_OPTION)
		{
			System.out.println("salvamento cancelado");
		}
		else
		{
			System.out.println("ocorreu erro no salvamento");
		}
	}
	public void carregamento() throws FileNotFoundException
	{
		int cond;
		int x,y,num,ultimo;
		boolean abrigo,barreira,ultimaCasa,podeSair;
		Peca peca;
		
		file.setCurrentDirectory(new java.io.File("."));
		file.setDialogTitle("Carregamento");
		file.setFileSelectionMode(JFileChooser.FILES_ONLY);
		cond = file.showOpenDialog(file);
		System.out.println(cond);
		if(cond == JFileChooser.APPROVE_OPTION)
		{
			System.out.println("carregou");
			dados = file.getSelectedFile();
			//Scanner teste = new Scanner(dados);
			
			Scanner rd = new Scanner(dados);
			for(int i=0;i<4;i++)
			{
				for(int j=0;j<4;j++)
				{
					peca = jogador[i].getPecas().get(j);
					x = rd.nextInt();
					y = rd.nextInt();
					num = rd.nextInt();
					ultimo = rd.nextInt();
					abrigo = rd.nextBoolean();
					barreira = rd.nextBoolean();
					ultimaCasa = rd.nextBoolean();
					podeSair = rd.nextBoolean();
					peca.setX(x);
					peca.setY(y);
					peca.setNumCasa(num);
					peca.setUltimoMovimentado(ultimo);
					peca.setAbrigo(abrigo);
					peca.setBarreira(barreira);
					peca.setUltimaCasa(ultimaCasa);
					peca.setPodeSair(podeSair);
				
				}
				jogador[i].setNumJogadas(rd.nextInt());
				jogador[i].setPecaIniciada(rd.nextBoolean());
				jogador[i].setPrimeiraJogada(rd.nextBoolean());
			}
			nJogador = rd.nextInt();
			dado.setNumDado(rd.nextInt());
			//System.out.println(dadoVal);
			Dado.dadoClicado = rd.nextBoolean();
			ultimoMovimentado = rd.nextInt();
			ultimoMov = rd.nextInt();
			jogadorTemp = rd.nextInt();
			pecaComida = rd.nextBoolean();
			jogoAcabou = rd.nextBoolean();
			semMov = rd.nextBoolean();
			
			for(int k=0;k<4;k++)
			{
				mov[k] = rd.nextBoolean();
			}
			
			rd.close();
		}
		else if(cond == JFileChooser.CANCEL_OPTION)
		{
			System.out.println("carregamento cancelado");
		}
		else
		{
			System.out.println("ocorreu erro no carregamento");
		}
	}
	public void zerarPartida()
	{
		int x,y;
		Peca peca;
		for(int i=0;i<4;i++)
		{
			jogador[i].setPecaIniciada(false);
			jogador[i].setNumJogadas(0);
			for(int j=0;j<4;j++)
			{
				peca = jogador[i].getPecas().get(j);
				x=jogador[i].getInicialX(j);
				y=jogador[i].getInicialY(j);
				peca.setX(x);
				peca.setY(y);
				peca.setNumCasa(0);
				peca.setPodeSair(false);
				peca.setUltimaCasa(false);
			}
		}
		nJogador = 0;
		jogadorTemp = nJogador;
		Dado.dadoClicado = false;
		dadoVal = dado.getRandNumDado();
	}
	
}