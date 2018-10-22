package renderizacao;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

public class Dado {
	
	private int numDado;
	
	List<BufferedImage> images = new ArrayList<>();
	
	Random r = new Random();
	
	public Dado() throws IOException {
		images.add(ImageIO.read(new File("src/Dado1.png")));
		images.add(ImageIO.read(new File("src/Dado2.png")));
		images.add(ImageIO.read(new File("src/Dado3.png")));
		images.add(ImageIO.read(new File("src/Dado4.png")));
		images.add(ImageIO.read(new File("src/Dado5.png")));
		images.add(ImageIO.read(new File("src/Dado6.png")));
		
	}
	
		
}

