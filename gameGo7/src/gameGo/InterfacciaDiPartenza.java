//package gameGo;
//import java.awt.Color;  
//import java.awt.Dimension;
//import javax.swing.JFrame;
//import javax.swing.JButton;
//import java.awt.event.ActionListener;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.awt.event.ActionEvent;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Toolkit;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.ImageIcon;
//import java.awt.BorderLayout;
//
//public class InterfacciaDiPartenza{
//	public static JFrame frame;
//	static JFrame windows = new JFrame("Pedina");
//
//	
//
//	
//	public void run(){
//		 try {  
//				initialize();
//				InterfacciaDiPartenza.frame.setVisible(true);
//		 } catch (Exception e) {
//			 }
//	}
//	
//	public void initialize() {
//		
//		
//		frame = new JFrame();
//		frame.setBounds(700, 700, 650, 600);
//		//frame.getContentPane().setLayout(null);
//		frame.setResizable(false);
//		frame.setVisible(true);
//		
//		Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
//		Dimension frameSize = frame.getSize ();
//		frame.setLocation ((screenSize.width - frameSize.width) / 2,
//		(screenSize.height - frameSize.height) / 2);
//		
//	
//		
//		String testo_bott[] = {"NUOVA PARTITA","CARICA PARTITA"};
//		JButton[] bottoni= new JButton[2];
//		
//		for (int i = 0; i < bottoni.length; i++) {
//			bottoni[i] = new JButton(testo_bott[i]);
//			bottoni[i].setFont(new Font("Arial", Font.ITALIC, 18));
//			bottoni[i].setOpaque(true);
//			bottoni[i].setVisible(true);
//			bottoni[i].setBackground(new Color(72,118,255));
//			bottoni[i].setForeground(Color.white);
//			frame.getContentPane().add(bottoni[i]);
//		}	
//		
//		//do una posizione ai bottoni
//		bottoni[0].setBounds(253, 482, 365, 25);
//		bottoni[1].setBounds(0, 520, 632, 33);
//		
//		
//		bottoni[0].addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent giusta) {
//					try {
//						creaFileTxt();
//						
//						//grafica!
//						windows.setBounds(100, 100, 591, 631);
//						windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//						windows.getContentPane().setLayout(null);
//
//						PedinaPanel panel = new PedinaPanel();
//						windows.setContentPane(panel);
//						panel.addMouseListener(new PedinaClickListener(panel));
//						windows.setVisible(true);
//
//						frame.setVisible(false);
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						System.out.println("problemi a caricare il file txt");
//					}					
//				}
//		});
//		bottoni[1].addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent giusta) {
//				caricaFileTxtPrecedente();
//				
//				//grafica!
//				windows.setBounds(100, 100, 591, 631);
//				windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				windows.getContentPane().setLayout(null);
//
//				PedinaPanel panel = new PedinaPanel();
//				windows.setContentPane(panel);
//				panel.addMouseListener(new PedinaClickListener(panel));
//				windows.setVisible(true);
//
//				frame.setVisible(false);
//				frame.setVisible(false);
//			}
//
//			private void caricaFileTxtPrecedente() {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//
//
//		
//		
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
//	
//	private static void creaFileTxt() throws IOException{
//		for(int i=100; i<=500; i+=50) {
//	    	for(int l=100; l<=500; l+=50) {
//	    		String nome=("valori/"+i+" "+l);
//	    		File f= new File (nome);
//	    		if(f.exists()) {
//	    			FileWriter fwOb = new FileWriter(nome, false); 
//	    		    PrintWriter pwOb = new PrintWriter(fwOb, false);
//	    		    pwOb.flush();
//	    		    pwOb.close();
//	    		    fwOb.close();
//	        		FileOutputStream fos = new FileOutputStream(nome,true);
//	    			PrintWriter scrivi = new PrintWriter(fos);
//	        		scrivi.append(i + " " + l + " 2" );
//	        		scrivi.close();
//	    		}
//	    		else if(f.createNewFile()) {
//	    			PrintWriter scrivi = new PrintWriter(f);
//	        		scrivi.println(i + " " + l + " 2 " );
//	        		scrivi.close();
//	    		}
//	    	}
//	    }
//	}
//	
//
//}