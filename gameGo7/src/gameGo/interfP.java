package gameGo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;  
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
public class interfP extends JFrame {

	private static JPanel contentPane;
	private static interfP frame = new interfP();
	static JFrame windows = new JFrame("Pedina");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfP frame = new interfP();
					frame.setResizable(false);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setBounds(700, 700, 650, 600);
					frame.contentPane = new JPanel();
					frame.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
					frame.contentPane.setLayout(new BorderLayout(0, 0));
					frame.setContentPane(contentPane);
					frame.setVisible(true);
					
					Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
					Dimension frameSize = frame.getSize ();
					frame.setLocation ((screenSize.width - frameSize.width) / 2,
					(screenSize.height - frameSize.height) / 2);
					
					
					String testo_bott[] = {"NUOVA PARTITA","CARICA PARTITA"};
					JButton[] bottoni= new JButton[2];
					
					for (int i = 0; i < bottoni.length; i++) {
						bottoni[i] = new JButton(testo_bott[i]);
						bottoni[i].setFont(new Font("Arial", Font.ITALIC, 18));
						bottoni[i].setOpaque(true);
						bottoni[i].setVisible(true);
						bottoni[i].setBackground(new Color(72,118,255));
						bottoni[i].setForeground(Color.white);
						frame.getContentPane().add(bottoni[i]);
					}	
					
					//do una posizione ai bottoni
					bottoni[0].setBounds(253, 482, 365, 25);
					bottoni[1].setBounds(0, 520, 632, 33);
					
					
					bottoni[0].addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent giusta) {
								//grafica!
								windows.setBounds(100, 100, 591, 631);
								windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								windows.getContentPane().setLayout(null);

								PedinaPanel panel = new PedinaPanel();
								windows.setContentPane(panel);
								panel.addMouseListener(new PedinaClickListener(panel));
								windows.setVisible(true);

								frame.setVisible(false);					
							}
					});
					bottoni[1].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent giusta) {
							//caricaFileTxtPrecedente();
							
							//grafica!
							windows.setBounds(100, 100, 591, 631);
							windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							windows.getContentPane().setLayout(null);

							PedinaPanel panel = new PedinaPanel();
							windows.setContentPane(panel);
							panel.addMouseListener(new PedinaClickListener(panel));
							windows.setVisible(true);

							frame.setVisible(false);
							frame.setVisible(false);
							
							frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						}
					});
					JLabel lblNewLabel = new JLabel("");
					lblNewLabel.setIcon(new ImageIcon(interfP.class.getResource("/image/sfondo.png")));
					lblNewLabel.setOpaque(false);
					contentPane.add(lblNewLabel, BorderLayout.CENTER);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
