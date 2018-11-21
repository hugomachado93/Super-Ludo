package renderizacao;

import java.util.ArrayList;
import java.util.List;

public class Caminho {
	
	//private Caminho path[] = new Caminho[56];
	
	private List<Casa> casas = new ArrayList<>();
	
	public Caminho(int num) {
		if(num == 1) {
			casas.add(new Casa(50,300,0, false));   // casa de saida do vermelho
			casas.add(new Casa(100, 300, 0, false));
			casas.add(new Casa(150, 300, 0, false));
			casas.add(new Casa(200, 300, 0, false));
			casas.add(new Casa(250, 300, 0, false));
			casas.add(new Casa(300, 250, 0, false));
			casas.add(new Casa(300, 200, 0, false));
			casas.add(new Casa(300, 150, 0, false));
			casas.add(new Casa(300, 100, 0, false));
			casas.add(new Casa(300, 50, 0, true));  //abrigo
			casas.add(new Casa(300, 5, 0, false));
			casas.add(new Casa(350, 5, 0, false));
			casas.add(new Casa(400, 5, 0, false));
			casas.add(new Casa(400, 50, 0, false));  // casa de saida do verde
			casas.add(new Casa(400, 100, 0, false));
			casas.add(new Casa(400, 150, 0, false));
			casas.add(new Casa(400, 200, 0, false));
			casas.add(new Casa(400, 250, 0, false));
			casas.add(new Casa(445, 300, 0, false));
			casas.add(new Casa(495, 300, 0, false));
			casas.add(new Casa(545, 300, 0, false));
			casas.add(new Casa(595, 300, 0, false));
			casas.add(new Casa(645, 300, 0, true));  //abrigo
			casas.add(new Casa(695, 300, 0, false));
			casas.add(new Casa(695, 350, 0, false));
			casas.add(new Casa(695, 395, 0, false));
			casas.add(new Casa(640, 395, 0, false));  // casa de saida do amarelo
			casas.add(new Casa(590, 395, 0, false));
			casas.add(new Casa(540, 395, 0, false));
			casas.add(new Casa(495, 395, 0, false));
			casas.add(new Casa(445, 395, 0, false));
			casas.add(new Casa(395, 445, 0, false));
			casas.add(new Casa(395, 495, 0, false));
			casas.add(new Casa(395, 545, 0, false));
			casas.add(new Casa(395, 595, 0, false));
			casas.add(new Casa(395, 645, 0, true));  //abrigo
			casas.add(new Casa(395, 690, 0, false));
			casas.add(new Casa(345, 690, 0, false));
			casas.add(new Casa(300, 690, 0, false));
			casas.add(new Casa(300, 640, 0, false)); // casa de saida do azul
			casas.add(new Casa(300, 590, 0, false));
			casas.add(new Casa(300, 540, 0, false));
			casas.add(new Casa(300, 495, 0, false));
			casas.add(new Casa(300, 445, 0, false));
			casas.add(new Casa(250, 395, 0, false));
			casas.add(new Casa(200, 395, 0, false));
			casas.add(new Casa(150, 395, 0, false));
			casas.add(new Casa(100, 395, 0, false));
			casas.add(new Casa(55, 395, 0, true));   //abrigo
			casas.add(new Casa(5, 395, 0, false));
			casas.add(new Casa(5, 345, 0, false));
			//casas.add(new Casa(5, 295, 0, false));
			//TODO FALTA IMPLEMENTAR A PARTE FINAL
			casas.add(new Casa(55,345,0, false));
			casas.add(new Casa(102, 345, 0, false));
			casas.add(new Casa(150, 345, 0, false));
			casas.add(new Casa(200, 345, 0, false));
			casas.add(new Casa(250, 345, 0, false));
			casas.add(new Casa(300, 345, 0, true));
			
		}else if(num == 2) {
			casas.add(new Casa(400, 50, 0, false));  //casa de saida do verde
			casas.add(new Casa(400, 100, 0, false));
			casas.add(new Casa(400, 150, 0, false));
			casas.add(new Casa(400, 200, 0, false));
			casas.add(new Casa(400, 250, 0, false));
			casas.add(new Casa(445, 300, 0, false));
			casas.add(new Casa(495, 300, 0, false));
			casas.add(new Casa(545, 300, 0, false));
			casas.add(new Casa(595, 300, 0, false));
			casas.add(new Casa(645, 300, 0, true));  //abrigo
			casas.add(new Casa(695, 300, 0, false));
			casas.add(new Casa(695, 350, 0, false));
			casas.add(new Casa(695, 395, 0, false));
			casas.add(new Casa(640, 395, 0, false));  //casa de saida do amarelo
			casas.add(new Casa(590, 395, 0, false));
			casas.add(new Casa(540, 395, 0, false));
			casas.add(new Casa(495, 395, 0, false));
			casas.add(new Casa(445, 395, 0, false));
			casas.add(new Casa(395, 445, 0, false));
			casas.add(new Casa(395, 495, 0, false));
			casas.add(new Casa(395, 545, 0, false));
			casas.add(new Casa(395, 595, 0, false));
			casas.add(new Casa(395, 645, 0, true));   //abrigo 
			casas.add(new Casa(395, 690, 0, false));
			casas.add(new Casa(345, 690, 0, false));
			casas.add(new Casa(300, 690, 0, false));
			casas.add(new Casa(300, 640, 0, false));  // casa de saida do azul
			casas.add(new Casa(300, 590, 0, false));
			casas.add(new Casa(300, 540, 0, false));
			casas.add(new Casa(300, 495, 0, false));
			casas.add(new Casa(300, 445, 0, false));
			casas.add(new Casa(250, 395, 0, false));
			casas.add(new Casa(200, 395, 0, false));
			casas.add(new Casa(150, 395, 0, false));
			casas.add(new Casa(100, 395, 0, false));
			casas.add(new Casa(55, 395, 0, true));  //abrigo
			casas.add(new Casa(5, 395, 0, false));
			casas.add(new Casa(5, 345, 0, false));
			casas.add(new Casa(5, 295, 0, false));
			casas.add(new Casa(50,300,0, false));  // casa de saida do vermelho
			casas.add(new Casa(100, 300, 0, false));
			casas.add(new Casa(150, 300, 0, false));
			casas.add(new Casa(200, 300, 0, false));
			casas.add(new Casa(250, 300, 0, false));
			casas.add(new Casa(300, 250, 0, false));
			casas.add(new Casa(300, 200, 0, false));
			casas.add(new Casa(300, 150, 0, false));
			casas.add(new Casa(300, 100, 0, false));
			casas.add(new Casa(300, 50, 0, true));  //abrigo
			casas.add(new Casa(300, 5, 0, false));
			casas.add(new Casa(350, 5, 0, false));
			casas.add(new Casa(400, 5, 0, false));
			//trilha final
			
			
		}
		else if(num==3)
		{
			casas.add(new Casa(640, 395, 0, false));  //casa de saida do amarelo
			casas.add(new Casa(590, 395, 0, false));
			casas.add(new Casa(540, 395, 0, false));
			casas.add(new Casa(495, 395, 0, false));
			casas.add(new Casa(445, 395, 0, false));
			casas.add(new Casa(395, 445, 0, false));
			casas.add(new Casa(395, 495, 0, false));
			casas.add(new Casa(395, 545, 0, false));
			casas.add(new Casa(395, 595, 0, false));
			casas.add(new Casa(395, 645, 0, true));   //abrigo
			casas.add(new Casa(395, 690, 0, false));
			casas.add(new Casa(345, 690, 0, false));
			casas.add(new Casa(300, 690, 0, false));
			casas.add(new Casa(300, 640, 0, false));  // casa de saida do azul
			casas.add(new Casa(300, 590, 0, false));
			casas.add(new Casa(300, 540, 0, false));
			casas.add(new Casa(300, 495, 0, false));
			casas.add(new Casa(300, 445, 0, false));
			casas.add(new Casa(250, 395, 0, false));
			casas.add(new Casa(200, 395, 0, false));
			casas.add(new Casa(150, 395, 0, false));
			casas.add(new Casa(100, 395, 0, false));
			casas.add(new Casa(55, 395, 0, true));   //abrigo
			casas.add(new Casa(5, 395, 0, false));
			casas.add(new Casa(5, 345, 0, false));
			casas.add(new Casa(5, 295, 0, false));
			casas.add(new Casa(50,300,0, false));     //casa de saida do vermelho
			casas.add(new Casa(100, 300, 0, false));
			casas.add(new Casa(150, 300, 0, false));
			casas.add(new Casa(200, 300, 0, false));
			casas.add(new Casa(250, 300, 0, false));
			casas.add(new Casa(300, 250, 0, false));
			casas.add(new Casa(300, 200, 0, false));
			casas.add(new Casa(300, 150, 0, false));
			casas.add(new Casa(300, 100, 0, false));
			casas.add(new Casa(300, 50, 0, true));   //abrigo
			casas.add(new Casa(300, 5, 0, false));
			casas.add(new Casa(350, 5, 0, false));
			casas.add(new Casa(400, 5, 0, false));
			casas.add(new Casa(400, 50, 0, false));  // casa de saida do verde
			casas.add(new Casa(400, 100, 0, false));
			casas.add(new Casa(400, 150, 0, false));
			casas.add(new Casa(400, 200, 0, false));
			casas.add(new Casa(400, 250, 0, false));
			casas.add(new Casa(445, 300, 0, false));
			casas.add(new Casa(495, 300, 0, false));
			casas.add(new Casa(545, 300, 0, false));
			casas.add(new Casa(595, 300, 0, false));
			casas.add(new Casa(645, 300, 0, true));  //abrigo
			casas.add(new Casa(695, 300, 0, false));
			casas.add(new Casa(695, 350, 0, false));
			//trilha final
			casas.add(new Casa(645, 350, 0, false));
			casas.add(new Casa(595, 350, 0, false));
			casas.add(new Casa(545, 350, 0, false));
			casas.add(new Casa(495, 350, 0, false));
			casas.add(new Casa(445, 350, 0, false));
			casas.add(new Casa(395, 350, 0, true));
			
		}
		else if(num==4)
		{
			casas.add(new Casa(300, 640, 0, false));  // casa de saida do azul
			casas.add(new Casa(300, 590, 0, false));
			casas.add(new Casa(300, 540, 0, false));
			casas.add(new Casa(300, 495, 0, false));
			casas.add(new Casa(300, 445, 0, false));
			casas.add(new Casa(250, 395, 0, false));
			casas.add(new Casa(200, 395, 0, false));
			casas.add(new Casa(150, 395, 0, false));
			casas.add(new Casa(100, 395, 0, false));
			casas.add(new Casa(55, 395, 0, true));   //abrigo
			casas.add(new Casa(5, 395, 0, false));
			casas.add(new Casa(5, 345, 0, false));
			casas.add(new Casa(5, 295, 0, false));
			casas.add(new Casa(50,300,0, false));     //casa de saida do vermelho
			casas.add(new Casa(100, 300, 0, false));
			casas.add(new Casa(150, 300, 0, false));
			casas.add(new Casa(200, 300, 0, false));
			casas.add(new Casa(250, 300, 0, false));
			casas.add(new Casa(300, 250, 0, false));
			casas.add(new Casa(300, 200, 0, false));
			casas.add(new Casa(300, 150, 0, false));
			casas.add(new Casa(300, 100, 0, false));
			casas.add(new Casa(300, 50, 0, true));   //abrigo
			casas.add(new Casa(300, 5, 0, false));
			casas.add(new Casa(350, 5, 0, false));
			casas.add(new Casa(400, 5, 0, false));
			casas.add(new Casa(400, 50, 0, false));  // casa de saida do verde
			casas.add(new Casa(400, 100, 0, false));
			casas.add(new Casa(400, 150, 0, false));
			casas.add(new Casa(400, 200, 0, false));
			casas.add(new Casa(400, 250, 0, false));
			casas.add(new Casa(445, 300, 0, false));
			casas.add(new Casa(495, 300, 0, false));
			casas.add(new Casa(545, 300, 0, false));
			casas.add(new Casa(595, 300, 0, false));
			casas.add(new Casa(645, 300, 0, true));  //abrigo
			casas.add(new Casa(695, 300, 0, false));
			casas.add(new Casa(695, 350, 0, false));
			casas.add(new Casa(695, 395, 0, false));
			casas.add(new Casa(640, 395, 0, false));  //casa de saida do amarelo
			casas.add(new Casa(590, 395, 0, false));
			casas.add(new Casa(540, 395, 0, false));
			casas.add(new Casa(495, 395, 0, false));
			casas.add(new Casa(445, 395, 0, false));
			casas.add(new Casa(395, 445, 0, false));
			casas.add(new Casa(395, 495, 0, false));
			casas.add(new Casa(395, 545, 0, false));
			casas.add(new Casa(395, 595, 0, false));
			casas.add(new Casa(395, 645, 0, true));   //abrigo 
			casas.add(new Casa(395, 690, 0, false));
			casas.add(new Casa(345, 690, 0, false));
			// trilha final
			casas.add(new Casa(345, 640, 0, false));
			casas.add(new Casa(345, 590, 0, false));
			casas.add(new Casa(345, 540, 0, false));
			casas.add(new Casa(345, 490, 0, false));
			casas.add(new Casa(345, 440, 0, false));
			casas.add(new Casa(345, 390, 0, true));
		}
		
	}
	
	public List<Casa> getCaminho(){
		return casas;
	}
	
}
