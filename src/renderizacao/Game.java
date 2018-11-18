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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;


public class Game extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private GameFacade gameFacade = new GameFacade();
	private Stroke defaultStroke;
	
	public Game() throws IOException {
		GUI();
	}
	
	private void GUI() {
		setLayout(null);
		add(gameFacade.eventDado());
		//add(gameFacade.DadoDebug());
		
		gameFacade.addObserver((obj, arg)-> {
			System.out.println("Repainting");
			repaint();
		});
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				gameFacade.mouseClicked(e);
			}
		});
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		defaultStroke = g2d.getStroke();
		
		gameFacade.DrawAll(g2d, defaultStroke);
		
	}
}