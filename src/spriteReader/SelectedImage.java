package spriteReader;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
@SuppressWarnings("serial")
public class SelectedImage extends JPanel{
	private BufferedImage img;
	private final int WIDTH;
	private final int HEIGHT;
	private boolean[][] isAlpha;
	private int x1, x2, y1, y2;
	protected SelectedImage(BufferedImage b){
		img = b;
		WIDTH = img.getWidth();
		HEIGHT = img.getHeight();

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




		panel();
		repaint();
	}
	void panel(){
		JFrame frame = new JFrame("SELECTED IMAGE");
		frame.add(this);
		frame.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				algroForBox(e.getX(),e.getY());
				repaint();

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

		});


	}
	void algroForBox(int x, int y){
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
		
		
		
		System.out.println(".getSubimage("+x1+","+y1+","+(x2-x1)+","+(y2-y1)+");");
		

	}

	public void paintComponent(Graphics g){
		
		g.drawImage(img, 0, 0,WIDTH,HEIGHT ,null);
		g.drawRect(x1, y1, x2 - x1, y2 - y1);

	}
}
