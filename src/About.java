import java.awt.BorderLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

public class About extends JFrame {

	public About() {
		initGUI();
	}
	
	public void initGUI(){

		setTitle("About");
		
		Image image = null;
		
		URL url = getClass().getResource("about.png");
		
		try {
			image = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel(new ImageIcon(image));
		
		setLayout(new BorderLayout());
		
		panel.add(label, BorderLayout.NORTH);
		add(panel);
	}

}
