package gameGo;
import java.io.BufferedInputStream; 
import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class musicaAvvioPartita {
	    private String filename;
	    private static Player player;
	    static Thread playMusic;
	  	    public musicaAvvioPartita(String filename) {
	        this.filename = filename;
	    }
	    public void play() {
	        try {
	            FileInputStream fis = new FileInputStream(filename);
	            BufferedInputStream bis = new BufferedInputStream(fis);
	            player = new Player(bis);
	        } catch (Exception e) {
	            System.out.println("Problem playing file " + filename);
	            System.out.println(e);
	        }
	    }

	    public void start() {
	        play();
	        playMusic = new Thread(new PlayMusic());
	        playMusic.start();
	       
	    }

	    public void stop() {
	        close();
	        playMusic.stop();
	        playMusic.suspend();
	    }

	    public static void close() {
	        if (player != null) {
	            player.close();
	        }
	    }

	    class PlayMusic implements Runnable {

	        public void run() {
	            try {
	                player.play();
	                if(InterfP.sonoNelGioco == false) {
	                try {
	                	start();
	                	Thread.sleep(27*1000);
	        		} catch (InterruptedException e) {
	        			e.printStackTrace();
	        		}
	            }
	            }
	            catch (Exception e) {
	                System.out.println(e);
	            }
	        }
	    }		    
}