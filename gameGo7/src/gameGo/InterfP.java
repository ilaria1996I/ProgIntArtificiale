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
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
public class InterfP extends JFrame {
	private static JPanel contentPane;
	private static InterfP frame = new InterfP();
	static JFrame windows = new JFrame("Pedina");
	static JFrame risultato = new JFrame("Risultato");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//InterfP frame = new InterfP();
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
					
					bottoni[0].setBounds(0, 520, 632, 33);
					bottoni[0].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent giusta) {
							//caricaFileTxtPrecedente();
							
							//grafica!
							windows.setBounds(100, 100, 591, 631);
							windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							windows.getContentPane().setLayout(null);

						
							
							try {
								creaFileTxt() ;
							} catch (IOException e) {
								System.out.println("Non sono riuscito a creare i file txt");
							}
							PedinaPanel panel = new PedinaPanel();
							windows.setContentPane(panel);
							panel.addMouseListener(new PedinaClickListener(panel));
							windows.setVisible(true);

							frame.setVisible(false);
							
							frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						}
					});
					JLabel lblNewLabel = new JLabel("");
					lblNewLabel.setIcon(new ImageIcon("image"+File.separator+"sfondo.png"));
					lblNewLabel.setOpaque(false);
					contentPane.add(lblNewLabel, BorderLayout.CENTER);
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
 	private static void creaFileTxt() throws IOException{
	for(int i=0; i<=540; i+=30) {
    	for(int l=0; l<=540; l+=30) {
    		String nome=("valori/"+i+" "+l);
    		File f= new File (nome);
    		if(f.exists()) {
    			FileWriter fwOb = new FileWriter(nome, false); 
    		    PrintWriter pwOb = new PrintWriter(fwOb, false);
    		    pwOb.flush();
    		    pwOb.close();
    		    fwOb.close();
        		FileOutputStream fos = new FileOutputStream(nome,true);
    			PrintWriter scrivi = new PrintWriter(fos);
        		scrivi.append(i + " " + l + " 2" );
        		scrivi.close();
    		}
    		else if(f.createNewFile()) {
    			PrintWriter scrivi = new PrintWriter(f);
        		scrivi.println(i + " " + l + " 2" );
        		scrivi.close();
    			}
    		}
    	}
	}
}
