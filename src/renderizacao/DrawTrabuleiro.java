package renderizacao;

import javax.swing.JFrame;

public class DrawTrabuleiro{

	public static void main(String[] args) {
		
		final int width = 1366;
		final int height = 768;
		
		JFrame myFrame = new JFrame("LUDO");
		Tabuleiro tabuleiro = new Tabuleiro();
		
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(width, height);
		myFrame.setVisible(true);
		myFrame.setResizable(false);
		myFrame.getContentPane().add(tabuleiro);
		
	}
	
}
