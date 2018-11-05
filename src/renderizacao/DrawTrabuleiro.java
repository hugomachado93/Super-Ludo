package renderizacao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class DrawTrabuleiro{

	public static void main(String[] args) throws IOException {
		
		final int width = 1366;
		final int height = 768;
		JFrame myFrame = new JFrame("LUDO");
		Game game = new Game();
		
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(width, height);
		myFrame.setVisible(true);
		myFrame.setResizable(false);
		myFrame.getContentPane().add(game);
		
	}
	
}
