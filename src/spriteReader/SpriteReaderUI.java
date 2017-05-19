package spriteReader;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class SpriteReaderUI{

	private JFrame masterFrame = new JFrame("Sprite Reader");
	private ArrayList<File> possibleSelect = new ArrayList<File>();

	public static void main(String[] args){
		new SpriteReaderUI();
	}
	SpriteReaderUI(){
		processFiles(new File(System.getProperty("user.home")+"/Desktop"));
		panel();
	}
	void processFiles(File dir){
		try{
			File[] listedDirectories = dir.listFiles();
			for(File f : listedDirectories){
				System.out.println(f.getAbsolutePath());
				if(f.isDirectory()){
					processFiles(f);
				}else{
					//is a file
					if(f.getName().substring(f.getName().length()-4).equals(".png"))
						possibleSelect.add(f);
				}

			}
		}catch(Exception e) { }
	}
	void panel(){

		JPanel pane = new JPanel(new GridLayout(possibleSelect.size(),1));
		for(File f : possibleSelect){
			JButton b = new JButton(f.getName());
			b.addActionListener(e->{
				try{
					new SelectedImage(ImageIO.read(f));
				}catch(Exception a) { }
			});
			pane.add(b);
		}

		JScrollPane optionScrollPane = new JScrollPane(pane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		optionScrollPane.getViewport ().setScrollMode (JViewport.BACKINGSTORE_SCROLL_MODE );
		optionScrollPane.getViewport ().setScrollMode (JViewport.SIMPLE_SCROLL_MODE );



		masterFrame.add(optionScrollPane);
		masterFrame.setPreferredSize(new Dimension(800,500));
		masterFrame.pack();
		masterFrame.setVisible(true);
		masterFrame.setResizable(false);
		masterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
