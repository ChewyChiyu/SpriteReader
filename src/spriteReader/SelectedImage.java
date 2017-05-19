package spriteReader;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
public class SelectedImage{
	private BufferedImage img;
	protected SelectedImage(BufferedImage b){
		img = b;
		panel();
	}

	void panel(){
		JFrame frame = new JFrame("Selected Image");

		SpriteSheet ss = new SpriteSheet(img);
		JScrollPane scrollPane = new JScrollPane(ss, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getViewport().setPreferredSize(new Dimension(400, 400));
		scrollPane.getViewport ().setScrollMode (JViewport.BACKINGSTORE_SCROLL_MODE );
		scrollPane.getViewport ().setScrollMode (JViewport.SIMPLE_SCROLL_MODE );
		

		JPanel optionPane = new JPanel(new BorderLayout());
		JTextArea text = new JTextArea(10,10);
		text.setFont(new Font("Sans",Font.PLAIN,16));
		text.setEditable(false);
		optionPane.add(text,BorderLayout.CENTER);
		JScrollPane optionScrollPane = new JScrollPane(optionPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		optionScrollPane.setPreferredSize(new Dimension(300,400));
		optionScrollPane.getViewport ().setScrollMode (JViewport.BACKINGSTORE_SCROLL_MODE );
		optionScrollPane.getViewport ().setScrollMode (JViewport.SIMPLE_SCROLL_MODE );
		
		
		
		JPanel tools = new JPanel(new BorderLayout());
		tools.setPreferredSize(new Dimension(300,50));
		JButton clear = new JButton("CLEAR");
		clear.addActionListener(e->{
			text.setText("");
		});
		tools.add(clear, BorderLayout.CENTER);
		
		
		
		optionPane.add(tools, BorderLayout.NORTH);
		

		
		frame.add(optionScrollPane, BorderLayout.WEST);
		frame.add(scrollPane, BorderLayout.CENTER);
		
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		ss.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				String s;
				try{
				if((s = ss.algroForBox(e.getX(),e.getY())) != null){
				  text.append(s+"\n");
				  ss.repaint();
				}
				}catch(Exception e2) {     JOptionPane.showMessageDialog(null,
					     e2 ,
					    "Inane error",
					    JOptionPane.ERROR_MESSAGE);            }
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
