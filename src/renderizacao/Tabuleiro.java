package renderizacao;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/*coordenadas da fronteira
	(0, 0)		(735, 0)
	(0, 735)	(735, 735)
*/

public class Tabuleiro{

	private final int squareSize = 49;
	private final int bigSquareSize = 294;
	private final int nineSquares = 441;
	private final int middle = 367;
	private final int middleSquare = 25;
	private final int sizePoligonTri = 3;
	private final int sizeBall = 49;
	
	private static Tabuleiro tabuleiro = null;
	
	private Tabuleiro() {
		
	}
	
	public void DrawGrid(Graphics2D g2d) {
	
		g2d.setColor(Color.BLACK);
		for(int x=0;x<735;x+=squareSize)
			for(int y=0;y<735;y+=squareSize)
				g2d.drawRect(x, y, squareSize, squareSize);
			
		
	}
	
	public void DrawBigSquare(Graphics2D g2d) {

		g2d.setColor(Color.RED);
		g2d.fillRect(0, 0, bigSquareSize, bigSquareSize);
		
		g2d.setColor(Color.GREEN);
		g2d.fillRect(441, 0, bigSquareSize, bigSquareSize);
		
		g2d.setColor(Color.BLUE);
		g2d.fillRect(0, 441, bigSquareSize, bigSquareSize);
		
		g2d.setColor(Color.YELLOW);
		g2d.fillRect(441, 441, bigSquareSize, bigSquareSize);
		
	}
	
	public void DrawMiddle(Graphics2D g2d) {
		g2d.setColor(Color.BLUE);
		g2d.fillPolygon(new int[] {bigSquareSize, nineSquares, middle}, new int[] {nineSquares, nineSquares, middle}, sizePoligonTri);
		g2d.setColor(Color.RED);
		g2d.fillPolygon(new int[] {bigSquareSize, bigSquareSize, middle}, new int[] {nineSquares, bigSquareSize, middle}, sizePoligonTri);
		g2d.setColor(Color.GREEN);
		g2d.fillPolygon(new int[] {bigSquareSize, nineSquares, middle}, new int[] {bigSquareSize, bigSquareSize, middle}, sizePoligonTri);
		g2d.setColor(Color.YELLOW);
		g2d.fillPolygon(new int[] {nineSquares, nineSquares, middle}, new int[] {bigSquareSize, nineSquares, middle}, sizePoligonTri);
	}
	
	public void DrawPath(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.fillRect(squareSize, squareSize*8, squareSize, squareSize);
		g2d.setColor(Color.RED);
		g2d.fillRect(squareSize, squareSize*6, squareSize, squareSize);
		for(int x=squareSize; x<=squareSize*5; x+=squareSize)
			g2d.fillRect(x, squareSize*7, squareSize, squareSize);
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(squareSize*6, squareSize, squareSize, squareSize);
		g2d.setColor(Color.GREEN);
		g2d.fillRect(squareSize*8, squareSize, squareSize, squareSize);
		for(int x=squareSize; x<=squareSize*5; x+=squareSize)
			g2d.fillRect(squareSize*7, x, squareSize, squareSize);
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(squareSize*13, squareSize*6, squareSize, squareSize);
		g2d.setColor(Color.YELLOW);
		g2d.fillRect(squareSize*13, squareSize*8, squareSize, squareSize);
		for(int x=squareSize*13; x>=squareSize*9; x-=squareSize)
			g2d.fillRect(x, squareSize*7, squareSize, squareSize);
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(squareSize*8, squareSize*13, squareSize, squareSize);
		g2d.setColor(Color.BLUE);
		g2d.fillRect(squareSize*6, squareSize*13, squareSize, squareSize);
		for(int x=squareSize*9; x<=squareSize*13; x+=squareSize)
			g2d.fillRect(squareSize*7, x, squareSize, squareSize);
	}
	
	public void DrawStart(Graphics2D g2d) {
		g2d.setColor(Color.WHITE);
		g2d.fillPolygon(new int[] {squareSize +10, squareSize +10, squareSize*2 - middleSquare}, new int[] {(squareSize*6) +10, (squareSize*7)-10, squareSize*6 + middleSquare}, 3);
		
		
		g2d.setColor(Color.WHITE);
		g2d.fillPolygon(new int[] {squareSize*14 - 10, squareSize*14 - 10, squareSize*14 - middleSquare}, new int[] {(squareSize*8) +10, (squareSize*9)-10, squareSize*8 + middleSquare}, 3);
		
		g2d.setColor(Color.WHITE);
		g2d.fillPolygon(new int[] {(squareSize*8) +10, (squareSize*9)-10, squareSize*8 + middleSquare}, new int[] {squareSize +10, squareSize +10, squareSize*2 - middleSquare}, 3);
		
		g2d.setColor(Color.WHITE);
		g2d.fillPolygon(new int[] {(squareSize*6) +10, (squareSize*7)-10, squareSize*6 + middleSquare}, new int[] {squareSize*14 - 10, squareSize*14 - 10, squareSize*14 - middleSquare}, 3);
		
	}
	
	public void DrawStartPlaces(Graphics2D g2d) {
		
		g2d.setColor(Color.WHITE);
		
		//1 Quadrado
		g2d.fillOval(squareSize, squareSize, sizeBall, sizeBall);
		
		g2d.fillOval(squareSize*4, squareSize, sizeBall, sizeBall);
		
		g2d.fillOval(squareSize, squareSize*4, sizeBall, sizeBall);
		
		g2d.fillOval(squareSize*4, squareSize*4, sizeBall, sizeBall);
		
		//2
		
		g2d.fillOval(squareSize*10, squareSize, sizeBall, sizeBall);
		
		g2d.fillOval(squareSize*13, squareSize, sizeBall, sizeBall);
		
		g2d.fillOval(squareSize*10, squareSize*4, sizeBall, sizeBall);

		g2d.fillOval(squareSize*13, squareSize*4, sizeBall, sizeBall);
		
		//3
		
		g2d.fillOval(squareSize, squareSize*10, sizeBall, sizeBall);
		
		g2d.fillOval(squareSize*4, squareSize*10, sizeBall, sizeBall);
		
		g2d.fillOval(squareSize, squareSize*13, sizeBall, sizeBall);
		
		g2d.fillOval(squareSize*4, squareSize*13, sizeBall, sizeBall);
		
		//4
		
		g2d.fillOval(squareSize*10, squareSize*10, sizeBall, sizeBall);
		
		g2d.fillOval(squareSize*13, squareSize*10, sizeBall, sizeBall);
		
		g2d.fillOval(squareSize*10, squareSize*13, sizeBall, sizeBall);
		
		g2d.fillOval(squareSize*13, squareSize*13, sizeBall, sizeBall);
		
	}
	
	public void paintTabuleiro(Graphics2D g2d){
		
		//Borda do tabuleiro
		g2d.drawRect(0, 0, 735, 735);
		//Desenha os caminhos coloridos
		DrawPath(g2d);
		//Desenha grid
		DrawGrid(g2d);
		
		//Desenha os 4 quadrados
		DrawBigSquare(g2d);
		
		//Desenha os triangulos do meio
		DrawMiddle(g2d);
		//desenha os trigulos das casas iniciais
		DrawStart(g2d);
		
		//desenha as casas iniciais
		DrawStartPlaces(g2d);
		
		
	}
	
	public static Tabuleiro getTabuleiro() {
		if(tabuleiro == null) {
			tabuleiro = new Tabuleiro();
		}
		return tabuleiro;
	}
	
}
