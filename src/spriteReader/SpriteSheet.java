package spriteReader;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
@SuppressWarnings("serial")
public class SpriteSheet extends JPanel {
	private final int WIDTH;
	private final int HEIGHT;
	private final String imageName;
	private boolean[][] isAlpha;
	private int x1, x2, y1, y2;
	private BufferedImage img;
  protected SpriteSheet(BufferedImage img){
	  	this.img = img;
		WIDTH = img.getWidth();
		HEIGHT = img.getHeight();
		imageName = JOptionPane.showInputDialog("What is the image name");
		isAlpha = new boolean[WIDTH][HEIGHT];
		for( int i = 0; i < isAlpha.length; i++ ){
			for( int j = 0; j < isAlpha[0].length; j++ ){
				if( (img.getRGB(i,j)>>24) == 0x00 ) {
					isAlpha[i][j] = true;
				}else{
					isAlpha[i][j] = false;
				}
			}
		}
  }
  String algroForBox(int x, int y) throws SpriteReaderException{
		//origins will expand outwards
		x1 = x;
		y1 = y;
		x2 = x;
		y2 = y;
		boolean expandTop = true;
		boolean expandBottom = true;
		boolean expandRight = true;
		boolean expandLeft = true;

		while(expandTop || expandBottom || expandRight || expandLeft){
			//check expandTop
			//check expandBottom
			expandTop = false;
			expandBottom = false;
			expandLeft = false;
			expandRight = false;
			for(int index = x1; index <= x2; index++){ 	
				if(!isAlpha[index][y1]){
					expandTop = true;
				}
				if(!isAlpha[index][y2]){
					expandBottom = true;
				}
			}

			for(int index = y1; index <= y2; index++){	
				if(!isAlpha[x1][index]){
					expandLeft = true;
				}
				if(!isAlpha[x2][index]){
					expandRight = true;
				}
			}
			if(x1<=0 || y1 <=0 || x2 >= WIDTH || y2 >= HEIGHT){
				throw new SpriteReaderException();
			}
			if(expandTop){
				y1--;
			}
			if(expandBottom){
				y2++;
			}
			if(expandLeft){
				x1--;
			}
			if(expandRight){
				x2++;
			}
		}

		if(x2-x1==0&&y2-y1==0){
			return null;
		}

		return (imageName+".getSubimage("+x1+","+y1+","+(x2-x1)+","+(y2-y1)+");");


	}
  @Override
  protected void paintComponent(Graphics g) {
    g.drawImage(img,0,0,WIDTH,HEIGHT, null);
    g.drawRect(x1, y1, x2-x1, y2-y1);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(WIDTH, HEIGHT);
  }
}