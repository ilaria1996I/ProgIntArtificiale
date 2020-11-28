package gameGo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class BackgroundImageJFrame{
    public BackgroundImageJFrame(ImageIcon i) {
    	JFrame frame = new JFrame();
    	frame.setSize(500,500);
    	frame.setVisible(true);
    	frame.setLayout(new BorderLayout());
    	
    	Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
		Dimension frameSize = frame.getSize ();
		frame.setLocation ((screenSize.width - frameSize.width) / 2,
		(screenSize.height - frameSize.height) / 2);
		
        JLabel background=new JLabel(i);
        frame.add(background);
        background.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}