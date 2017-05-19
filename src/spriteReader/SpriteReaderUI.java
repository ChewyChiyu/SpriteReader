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
	private CardLayout cardLayout = new CardLayout();
	private JPanel masterDisplayPane = new JPanel(new BorderLayout());
	private JPanel masterScrollPane = new JPanel(cardLayout);
	private JPanel masterChoosePane = new JPanel(new BorderLayout());
	ArrayList<File> listedFilesDesktop = new ArrayList<File>();
	ArrayList<File> listedFilesDownloads = new ArrayList<File>();
	
	public static void main(String[] args){
		new SpriteReaderUI();
	}
	SpriteReaderUI(){
		processFiles();
		panel();
	}
	void processFiles(){
		File[] listedFilesDesktop = new File(System.getProperty("user.home")+"/Desktop/").listFiles();
		File[] listedFilesDownloads = new File(System.getProperty("user.home")+"/Downloads/").listFiles();
		
		for(File f : listedFilesDesktop){
			if(f.toString().substring(f.toString().length()-4).equalsIgnoreCase(".png")){
				this.listedFilesDesktop.add(f);
			}
		}
		for(File f : listedFilesDownloads){
			if(f.toString().substring(f.toString().length()-4).equalsIgnoreCase(".png")){
				this.listedFilesDownloads.add(f);
			}
		}
	
	}
	void panel(){
		setUpMasterChoosePane();
		setUpMasterDisplayPane();
		masterFrame.add(masterChoosePane,BorderLayout.WEST);
		masterFrame.add(masterDisplayPane , BorderLayout.CENTER);
		masterFrame.setPreferredSize(new Dimension(800,500));
		masterFrame.pack();
		masterFrame.setVisible(true);
		masterFrame.setResizable(false);
		masterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	void setUpMasterChoosePane(){
		JButton desktop = new JButton("DESKTOP");
		desktop.addActionListener(e->{
			cardLayout.show(masterScrollPane, "DESKTOP");
		});
		JButton downloads = new JButton("DOWNLOADS");
		downloads.addActionListener(e->{
			cardLayout.show(masterScrollPane, "DOWNLOADS");
		});
		JPanel ms = new JPanel(new GridLayout(2,1));
		ms.add(desktop);
		ms.add(downloads);
		masterChoosePane.add(ms);
	}
	void setUpMasterDisplayPane(){
		//masterDisplayPane
		//desktop
		JPanel desktop = new JPanel();
		JPanel downloads = new JPanel();
		

		desktop.setLayout(new GridLayout(listedFilesDesktop.size(), 1));
		downloads.setLayout(new GridLayout(listedFilesDownloads.size(), 1));

		for(File f : listedFilesDesktop){
			JButton b = new JButton(f.toString());
			b.setPreferredSize(new Dimension(400,70));
			b.addActionListener(e -> {
					try{
					new SelectedImage(ImageIO.read(f));
					}catch(Exception a) { 
					}
			});
			desktop.add(b);
		}
		for(File f : listedFilesDownloads){
			JButton b = new JButton(f.toString());
			b.setPreferredSize(new Dimension(400,70));
			b.addActionListener(e -> {
					try{
					new SelectedImage(ImageIO.read(f));
					}catch(Exception a) { 
					}
			});
			downloads.add(b);
		}
		
		
		JScrollPane scrollDesktop = new JScrollPane(desktop,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollDesktop.getViewport ().setScrollMode (JViewport.BACKINGSTORE_SCROLL_MODE );
		scrollDesktop.getViewport ().setScrollMode (JViewport.SIMPLE_SCROLL_MODE );
		
		JScrollPane scrollDownloads = new JScrollPane(downloads,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollDownloads.getViewport ().setScrollMode (JViewport.BACKINGSTORE_SCROLL_MODE );
		scrollDownloads.getViewport ().setScrollMode (JViewport.SIMPLE_SCROLL_MODE );
		
		
		
		
		masterScrollPane.add(scrollDesktop, "DESKTOP");
		masterScrollPane.add(scrollDownloads, "DOWNLOADS");
		
		masterDisplayPane.add(masterScrollPane);
	}
}
