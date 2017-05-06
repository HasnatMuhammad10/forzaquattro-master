package GiocoEffettivo;

import static GiocoEffettivo.Griglia.audio;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Classe che genera la scacchiera di gioco.
 * @author Antonio Faienza, Riccardo Zandegiacomo e Alessandro Coppola
 */
public class GiocoForza4 extends JFrame
{   
    URL url = getClass().getResource("/Immagini/ScreenFinale.jpg");
    ImageIcon icona = new ImageIcon(url);
    
    private JLabel giocatore1;
    private JLabel giocatore2;
    private static JLabel time;
    private static JLabel time2;
    protected static JPanel utente1;
    protected static JPanel utente2;
    private static JPanel timer;
    public static boolean endTimer;
    public static int livello;
    
    // Costruttore che prende in int il valore da cui partirà il countdown.
    public GiocoForza4(int i)
    {
        // Valore del countdown.
        livello = i;
        
        setTitle("Forza 4");         
        setLookAndFeel(); // Definisce il LAF.
        setIconImage(icona.getImage());
        setSize(1000, 700); // Imposta la dimensione della finestra.
        
        // Specifica il layout da utilizzare: BorderLayout.
        Container c = getContentPane();
        c.setLayout( new BorderLayout() );
        
        // Definizione degli elementi presenti nella finestra.
        giocatore1 = new JLabel(NuovaPartita.NuovaPartita.idG1);
        giocatore1.setFont(new java.awt.Font("Felix Titling", 1, 24));
        
        giocatore2 = new JLabel(NuovaPartita.NuovaPartita.idG2);
        giocatore2.setFont(new java.awt.Font("Felix Titling", 1, 24));
        
        time = new JLabel("Timer");        
        time.setFont(new java.awt.Font("Felix Titling", 1, 22)); 
        
        time2 = new JLabel();     
        time2.setFont(new java.awt.Font("Felix Titling", 1, 20));
        
        utente1 = new JPanel();
        utente1.setBackground(new java.awt.Color(255, 0, 0)); // Il giocatore ROSSO ha la label rialzata.
        utente1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        
        utente1.setPreferredSize(new Dimension(400, 100));
        utente1.add(giocatore1);        
        c.add(utente1, BorderLayout.LINE_START);
        
        utente2 = new JPanel();
        utente2.setBackground(Color.WHITE);
        utente2.setBorder(javax.swing.BorderFactory.createMatteBorder(8, 4, 4, 8, new java.awt.Color(0, 0, 255)));
        utente2.setPreferredSize(new Dimension(400, 100)); 
        utente2.add(giocatore2);
        c.add(utente2, BorderLayout.LINE_END);
        
        timer = new JPanel();
        timer.setBackground(Color.WHITE); 
        timer.setBorder(javax.swing.BorderFactory.createMatteBorder(8, 4, 4, 4, new java.awt.Color(0, 0, 255)));
        timer.add(time);
        timer.add(time2);
        c.add(timer, BorderLayout.CENTER);
        
        // Aggiunge la griglia alla finestra principale.
        Griglia griglia = new Griglia();
        griglia.setPreferredSize(new Dimension(1000, 600));
        c.add(griglia, BorderLayout.SOUTH);
        
        setLocationRelativeTo(null);
        setResizable(false); 
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        // Avvia il countdown.
        CountDown.start();
    }
    
    /**
     * Metodo che gestisce il Countdown.
     * Il Countdown è stato realizzato utilizzando l'alternanza di due timer diversi, uno per ogni giocatore,
     * Ogni timer ha un suo lifecycle indipendente e quindi deve essere alternato e sincronizzato con l'altro.
     */
    public static class CountDown
    {
        private static ActionListener action;        
        private static ActionListener action2;
        private static int attesa = 1000; // Definisce lo scorrere dei secondi.
        protected static Timer t;    
        protected static Timer t2;
        public static int valoreTimer = GiocoForza4.livello;
        public static int valoreTimer2 = GiocoForza4.livello;
        
        /**
         * Metodo che gestisce lo scorrere dei secondi del Timer1.
         */
        public static void start() 
        {
            action = new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if(endTimer)
                    {
                        ((Timer) e.getSource()).stop();
                        time2.setText("        " + 0 + "        ");
                    }
                    else
                    {
                        // Il timer parte dal valore dato in ingresso e viene decrementato fino a zero, con un'attesa di un secondo ogni decremento.
                        if(valoreTimer >= 0)
                        {
                            time2.setText("        " + valoreTimer + "        ");
                            valoreTimer--;
                        }
                        else
                        {
                            // Quando il timer arriva a zero, viene invocato il metodo "Random" per giocare la pedina a caso.
                            ((Timer) e.getSource()).stop();
                            try
                            {
                                audio = new AudioStream(this.getClass().getResourceAsStream("/tok.wav"));
                                AudioPlayer.player.start(audio);
                            }
                            catch (IOException ex)
                            {
                                Logger.getLogger(Griglia.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Griglia.random();
                            valoreTimer = GiocoForza4.livello;
                        }
                    }
                }
            };
            
            // Parte il PRIMO timer, eseguendo action con attesa.
            t = new Timer(attesa, action);
            t.start();               
        }         
        
        /**
         * Metodo che gestisce lo scorrere dei secondi del Timer2.
         */
        public static void start2()
        {
            action2 = new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    if(endTimer)
                    {
                        ((Timer) e.getSource()).stop();
                        time2.setText("        " + 0 + "        ");
                        
                    }
                    else
                    {
                        // Il timer parte dal valore dato in ingresso e viene decrementato fino a zero, con un'attesa di un secondo ogni decremento.
                        if(valoreTimer2 >= 0)
                        { 
                            time2.setText("        " + valoreTimer2 + "        ");
                            valoreTimer2--;
                        }
                        else
                        {
                            // Quando il timer arriva a zero, viene invocato il metodo "Random" per giocare la pedina a caso.
                            ((Timer) e.getSource()).stop();
                            try
                            {
                                audio = new AudioStream(this.getClass().getResourceAsStream("/tok.wav"));
                                AudioPlayer.player.start(audio);
                            }
                            catch (IOException ex)
                            {
                                Logger.getLogger(Griglia.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Griglia.random(); // questo genera il Random                                       
                            valoreTimer2 = GiocoForza4.livello;
                        }
                    }
                }
            };
            
            // Parte il SECONDO timer, eseguendo action2 con attesa.
            t2 = new Timer(attesa, action2);
            t2.start(); 
        }
    }
    
    /**
     * Metodo che definisce il LAF della finestra.
     */
    private void setLookAndFeel()  // Definisce l'aspetto della finestra.
    {
        try
        {
            // Indaghiamo su tutti i lookAndFeel installati grazie alla classe UIMAnager.
            for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
                if("Nimbus".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break; // Arresta il ciclo una volta trovato.
                }
            }
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
            System.out.println("!!!! Attenzione Nimbus non ha avuto successo");
            System.err.println(ex); // ci facciamo scrivere il report delle ecezioni
        }
    }
}