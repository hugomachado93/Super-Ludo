package renderizacao;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class AtualizaLogica implements Observer{
	GameFacade gameFacade = new GameFacade();
	
	Jogador[] jogador = gameFacade.getJogador();
	private int nJogador = gameFacade.getNumJogador();
	private int dadoVal;
	private final int MAX_CASAS = 56;
	private int ultimoMov=0;
	private MouseEvent e;
	
	public AtualizaLogica() throws IOException {
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		MouseEvent e = (MouseEvent) arg1;
	
		
	}

}
