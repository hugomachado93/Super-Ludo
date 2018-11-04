package renderizacao;

import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class Dado{
	
	private static int numDado=1;
	
	private List<BufferedImage> images = new ArrayList<>();
	
	private Random r = new Random();
	
	public Dado() throws IOException {
		images.add(ImageIO.read(new File("src/Dado1.png")));
		images.add(ImageIO.read(new File("src/Dado2.png")));
		images.add(ImageIO.read(new File("src/Dado3.png")));
		images.add(ImageIO.read(new File("src/Dado4.png")));
		images.add(ImageIO.read(new File("src/Dado5.png")));
		images.add(ImageIO.read(new File("src/Dado6.png")));

	}
	
	public void setNumDado(int numDado) {
		this.numDado = numDado;
	}
	
	public int getNumDado() {
		return numDado;
	}
	
	public int getRandNumDado() {
		return numDado = r.nextInt(6) + 1;
	}
	
	public void drawDado(Graphics2D g2d) {
		g2d.drawImage(images.get(numDado-1), 1000, 500, null);
	}
	
}

