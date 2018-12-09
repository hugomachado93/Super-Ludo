package logic;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class AtualizacaoLogica implements Observer{
	GameFacade gameFacade = new GameFacade();
	
	public AtualizacaoLogica() throws IOException {
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		int nJogador = (int)arg1;
		if(nJogador == 4) {
			 gameFacade.setNumJogador(0);
		}
	}

}
