package spriteReader;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
public class SelectedImage{
	private BufferedImage img;
		protected SelectedImage(BufferedImage b){
		img = b;
		panel();
	}

	void panel(){
			SpriteSheet ss = new SpriteSheet(img);
			JScrollPane scrollpane = new JScrollPane(ss);
			
	        scrollpane.getViewport().setPreferredSize(new Dimension(400, 400));
	        scrollpane.getViewport ().setScrollMode (JViewport.BACKINGSTORE_SCROLL_MODE );
	        scrollpane.getViewport ().setScrollMode (JViewport.SIMPLE_SCROLL_MODE );
	        JFrame frame = new JFrame("Selected Image");
	        frame.add(scrollpane);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.pack();
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);
	        ss.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					ss.algroForBox(e.getX(),e.getY());
					ss.repaint();
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
	
}
