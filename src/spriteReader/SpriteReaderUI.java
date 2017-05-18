package spriteReader;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

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
		JScrollPane sp = new JScrollPane(this,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		for(File f : listedFiles){
			JButton b = new JButton(f.toString());
			b.addActionListener(e -> {
				if(f.toString().substring(f.toString().length()-4).equalsIgnoreCase(".png")){
					try{
					new SelectedImage(ImageIO.read(f));
					}catch(Exception a) { }
				}
			});
			this.add(b);
		}
		sp.getViewport ().setScrollMode (JViewport.BACKINGSTORE_SCROLL_MODE );
		sp.getViewport ().setScrollMode (JViewport.SIMPLE_SCROLL_MODE );
		frame.add(sp);
		frame.setPreferredSize(new Dimension(500,200));
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		repaint();
		
	}
	
}
