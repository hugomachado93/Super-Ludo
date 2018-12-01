package renderizacao;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameFacade{

	private Jogador[] jogador = new Jogador[4];
	private Dado dado;
	private int dadoVal;
	private final int MAX_CASAS = 56;
	private static int ultimoMovimentado = 0;
	private boolean mov[]=new boolean[4];
	boolean jogoAcabou = false;
	private static int nJogador = 0;
	private int ultimoMov=0;
	private int peca;
	private int jogadorTemp;
	private File dados;
	
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
	}
	
	public void mouseClicked(MouseEvent e) {
		jogadorTemp = nJogador; 
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
						
						
						if(saidaObrigatoria(i, nJogador, dadoVal)) {
							jogador[nJogador].setNumJogadas(0);
							break;
						}
						
						
						int val = dadoVal + jogador[nJogador].getPecas().get(i).getNumCasa();
						
						int casaInicial = jogador[nJogador].getPecas().get(i).getNumCasa();
						int xInicial = jogador[nJogador].getPecas().get(i).getX();
						int yInicial = jogador[nJogador].getPecas().get(i).getY();
						
						if(val > MAX_CASAS) {
							val = MAX_CASAS - (val - MAX_CASAS);
						}else if(val == MAX_CASAS) {
							jogador[nJogador].getPecas().get(i).setUltimaCasa(true);
							mov[i]=false;
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
				}else if(jogador[nJogador].getPecas().get(i).isUltimaCasa()){
					mov[i]=false;
				}
			}
			/*
			if(temMovimento()) {
				mov[0]=true;
				mov[1]=true;
				mov[2]=true;
				mov[3]=true;
				nJogador++;
				Dado.dadoClicado = false;
			}*/
		}
		
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
			for(int i=0;i<4;i++) {
				if(i != id && jogador[numJogador].getPecas().get(i).isPodeSair() == false) {	
					int x=jogador[numJogador].getInicialX(i);
					int y=jogador[numJogador].getInicialY(i);
					jogador[numJogador].getPecas().get(i).setX(jogador[numJogador].getCasas().get(0).getX());
					jogador[numJogador].getPecas().get(i).setY(jogador[numJogador].getCasas().get(0).getY());
					if(comePeca(i, 0, numJogador, 0, x, y)) {
						//jogador[numJogador].setPecaIniciada(true);
						//jogador[numJogador].getPecas().get(i).setPodeSair(true);
						jogador[numJogador].getPecas().get(i).setX(x);
						jogador[numJogador].getPecas().get(i).setY(y);
						return false;
					}
					jogador[numJogador].getPecas().get(i).setX(x);
					jogador[numJogador].getPecas().get(i).setY(y);
					//jogador[numJogador].setPecaIniciada(true);
					//jogador[numJogador].getPecas().get(i).setPodeSair(true);
				}
					System.out.println("AQUII");
			}
			
			for(int i=0;i<4;i++) {
				if(!jogador[numJogador].getPecas().get(i).isPodeSair() && jogador[numJogador].getPecas().get(i).isPodeSair() == false) {
					jogador[numJogador].getPecas().get(i).setX(jogador[numJogador].getCasas().get(0).getX());
					jogador[numJogador].getPecas().get(i).setY(jogador[numJogador].getCasas().get(0).getY());
					jogador[numJogador].getPecas().get(i).setPodeSair(true);
					nJogador++;
					Dado.dadoClicado = false;
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean temMovimento() {
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
			//System.out.println("Jogo acabou. Jogador " + nJogador + " venceu.");
		}
		
	}
	
	public Jogador[] getJogador() {
		return jogador;
	}
	
	public int getNumJogador() {
		return nJogador;
	}
	
	public void salvamento() throws IOException
	{
		System.out.println("salvou");
		int x,y,num,ultimo;
		boolean abrigo,barreira,ultimaCasa,podeSair;
		Peca peca;
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
			wr.write("\n");
		}
		wr.write(String.valueOf(turno) + " " + String.valueOf(dadoVal) + " " + String.valueOf(Dado.dadoClicado) + " " + String.valueOf(ultimoMovimentado));
		wr.flush();
		wr.close();
	}
	public void carregamento() throws FileNotFoundException
	{
		System.out.println("carregou");
		int x,y,num,ultimo;
		boolean abrigo,barreira,ultimaCasa,podeSair;
		Peca peca;
		Scanner rd = new Scanner(dados);
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				//System.out.print(rd.nextInt() + " " + rd.nextInt() + " " + rd.nextInt() + " " + rd.nextInt() + " ");
				//System.out.println(rd.nextBoolean() + " " + rd.nextBoolean() + " " + rd.nextBoolean() + " " + rd.nextBoolean());
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
			//System.out.println(" ");
		}
		turno = rd.nextInt();
		nJogador = turno;
		dadoVal = rd.nextInt();
		System.out.println(dadoVal);
		Dado.dadoClicado = rd.nextBoolean();
		ultimoMovimentado = rd.nextInt();
		//System.out.println(rd.nextInt() + " " + rd.nextInt() + " " + rd.nextBoolean() + " " + rd.nextInt());
		rd.close();
	}
	public void zerarPartida()
	{
		int x,y;
		Peca peca;
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				peca = jogador[i].getPecas().get(j);
				x=jogador[i].getInicialX(j);
				y=jogador[i].getInicialY(j);
				peca.setX(x);
				peca.setY(y);
				peca.setNumCasa(0);
				peca.setPodeSair(false);
				//peca.setBarreira(false);
				//peca.setAbrigo(false);
			}
		}
		nJogador = new Random().nextInt(2);
		turno = nJogador;
		Dado.dadoClicado = false;
		dadoVal = dado.getRandNumDado();
	}
	
}
