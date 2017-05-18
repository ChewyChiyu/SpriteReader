package spriteReader;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SpriteReaderUI extends JPanel{
	private File[] listedFiles = new File(System.getProperty("user.home") + "/Desktop").listFiles();
	public static void main(String[] args){
		new SpriteReaderUI();
	}
	SpriteReaderUI(){
		panel();
	}
	void panel(){
		JFrame frame = new JFrame("SPRITE READER");
		frame.add(this);
		frame.setPreferredSize(new Dimension(700,listedFiles.length*50));
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				int interval = (e.getY()-50)/50;
				try{
				if(ImageIO.read(listedFiles[interval])!=null){
					//file is image
					new SelectedImage(ImageIO.read(listedFiles[interval]));
				}else{
					JOptionPane.showMessageDialog(frame, "File is unselectable");
				}
				}catch(Exception a){}
				
				
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
		
		
		repaint();
		
	}
	
	public void paintComponent(Graphics g){
		int xBuffer = 0;
		int yBuffer = 100;
		g.setFont(new Font("Aerial",Font.BOLD,24));
		g.setColor(Color.gray);
		g.drawString("Click on a URL to open", 0, 50);
		g.setColor(Color.black);
		  if (listedFiles != null) {
		    for (File f : listedFiles) {
		      System.out.println(f.toURI().toString());
		      g.drawString(f.toURI().toString(), xBuffer, yBuffer);
		      yBuffer+=50;
		    }
		  } else {
			  g.drawString("Unable to load files", xBuffer, yBuffer);
		  }
	}
	
}
