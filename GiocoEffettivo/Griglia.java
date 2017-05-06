package GiocoEffettivo;

import Vincita_Parita.Parita;
import Vincita_Parita.Vittoria;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Classe cuore del gioco: gestisce l'estetica della scacchiera, l'inizio del gioco, l'alternanza dei giocatori, l'avanzamento del turmo,
 * l'alternanza dei timer, l'estrazione dei numeri casuali, il gioco vero e proprio, il gioco casuale, il controllo delle combinazioni e infine
 * l'interazione attraverso mouse e tasti.
 * @author Antonio Faienza, Riccardo Zandegiacomo e Alessandro Coppola
 */
public class Griglia extends JPanel implements ActionListener
{
    // Definizione variabili che ci serviranno.
    final static int diametro = 88;
    final static int spazio = 6; // Spazio tra due pedine.
    final static int bordiLaterali = 171; // Distanza dai bordi verticali.
    final static int bordiVerticali = 35; // Distanza dai bordi orizzontali.
    private static int colonne = 7; // Definiamo il numero di colonne.
    private static int righe = 6; // Definiamo il numero di righe.
    private static int[][] griglia;
    private static Graphics2D g2;
    
    URL url = getClass().getResource("/Immagini/indicatore.png");
    ImageIcon img = new ImageIcon(url);
    
    // Definiamo i pulsanti.
    private JButton a;
    private JButton b;
    private JButton c;
    private JButton d;
    private JButton e;
    private JButton f;
    private JButton g;
    
    private static JPanel panel;
    
    // Ogni pedina ha un numero identificativo.
    private static int BIANCO = 0;
    private static int ROSSO = 1;
    private static int GIALLO = 2;
    
    // Variabili booleane.
    public static boolean end;    
    public static boolean parita = true;
    
    // Turno di gioco (servirà per decretare il caso di parità).
    public static int turno = 0;
    
    // Serve per definire il vincitore in "Vittoria".
    public static int vincitore;
    
    //
    public static AudioStream audio ;
    
    public static Vittoria x;
    
    /**
     * Con questo metodo vengono definiti i pulsanti di gioco con le loro azioni collegate.
     */
    public Griglia()
    {
        // Definiamo la griglia di gioco.
        panel = new JPanel();
        griglia = new int[colonne][righe];
        
        // Definiamo e descriviamo contenuto, funzione e aspetto di ogni pulsante di gioco.
        a = new JButton();        
        a.setContentAreaFilled(false);  // Questo metodo permette di dare la trasparenza ad un jButton.
        a.setIcon(img);
        a.setPreferredSize(new Dimension(diametro, 25));
        a.addActionListener(this);
        a.addKeyListener(tasti);
        this.add(a);
        
        b = new JButton();
        b.setContentAreaFilled(false);      
        b.setIcon(img);
        b.setPreferredSize(new Dimension(diametro, 25));
        b.addActionListener(this);
        b.addKeyListener(tasti);
        this.add(b);
        
        c = new JButton();
        c.setContentAreaFilled(false);      
        c.setIcon(img);
        c.setPreferredSize(new Dimension(diametro, 25));        
        c.addActionListener(this);
        c.addKeyListener(tasti);
        this.add(c);
        
        d = new JButton();
        d.setContentAreaFilled(false);      
        d.setIcon(img);
        d.setPreferredSize(new Dimension(diametro, 25));
        d.addActionListener(this);
        d.addKeyListener(tasti);
        this.add(d);
        
        e = new JButton();
        e.setContentAreaFilled(false);      
        e.setIcon(img);
        e.setPreferredSize(new Dimension(diametro, 25));
        e.addActionListener(this);
        e.addKeyListener(tasti);
        this.add(e);
        
        f = new JButton();
        f.setContentAreaFilled(false);      
        f.setIcon(img);
        f.setPreferredSize(new Dimension(diametro, 25));
        f.addActionListener(this);
        f.addKeyListener(tasti);
        this.add(f);
        
        g = new JButton();
        g.setContentAreaFilled(false);      
        g.setIcon(img);
        g.setPreferredSize(new Dimension(diametro, 25));
        g.addActionListener(this);
        g.addKeyListener(tasti);
        this.add(g);
        
        inizia();
    }
    
    /**
     * Metodo che disegna la griglia che conterrà le pedine.
     */
    public void paintComponent(Graphics g)
    {         
        super.paintComponent(g);
        g2 = (Graphics2D) g;       
        g2.setColor(Color.BLUE);        
        g2.fillRect(0, 0, getSize().width, getSize().height);
        
        
        /*
         * Definisce un rettangolo le cui dimensioni si adattano al pannello attraverso gli ultimi due parametri,
         * e la cui posizione di partenza è data dai primi due parametri. 
         */
        for (int i = 0; i < colonne; i++)
        {
            for (int j = 0; j < righe; j++)
            {
                // Se uguale a 0, il colore è bianco.
                if(griglia[i][j] == BIANCO)
                {
                    g2.setColor(Color.WHITE);
                }
                // Se uguale a 1, il colore è rosso.
                if(griglia[i][j] == ROSSO)
                {
                    g2.setColor(Color.RED);
                }
                // Se uguale a 2 il colore è giallo.
                if(griglia[i][j] == GIALLO)
                {
                    g2.setColor(Color.YELLOW);
                }
                
                // Disegno dell'ovale.
                g2.fillOval((bordiLaterali + (spazio + diametro) * i), (bordiVerticali + (spazio + diametro) * j) ,diametro, diametro);
                repaint();
            }
        }
    }
    
    /**
     * Metodo che inizializza la griglia come vuota.
     */
    public void inizia()
    {
        griglia = new int[colonne][righe];
        
        // Per ogni casella della griglia viene allocato lo spazio "vuoto".
        for(int i = 0; i < colonne; i++)
        {
            for(int j = 0; j < righe; j++)
            {
                griglia[i][j] = BIANCO; 
            }
        }
    }
    
    /**
     * Metodo che gestisce l'alternanza degli utenti andando a controllare il turno.
     * Viene alternato anche l'aspetto del jPanel attraverso il metodo "RAISED".
     */
    public static void gestioneUtente()
    {
        turno++;
        
        if(turno % 2 != 0)
        {
            GiocoForza4.utente2.setBackground(new java.awt.Color(255, 255, 102));
            GiocoForza4.utente2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            
            GiocoForza4.utente1.setBackground(Color.WHITE);
            GiocoForza4.utente1.setBorder(javax.swing.BorderFactory.createMatteBorder(8, 8, 4, 4, new java.awt.Color(0, 0, 255)));
        }
        else
        {
            GiocoForza4.utente1.setBackground(new java.awt.Color(255, 0, 0)); // ROSSO rialzato 
            GiocoForza4.utente1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        
            GiocoForza4.utente2.setBackground(Color.WHITE);
            GiocoForza4.utente2.setBorder(javax.swing.BorderFactory.createMatteBorder(8, 4, 4, 8, new java.awt.Color(0, 0, 255)));
        }
    }
    
    /**
     * Il metodo "random()" estrae un numero casuale da dare in input al metodo "gioca()" quando il timer di un giocatore arriva allo zero.
     * L'estrazione avviene da una base di numeri [0 - 7] non statici, ma dinamici in base all'andamento della partita.
     */
    protected static void random()
    {
        try
        {
            // ArrayList che andrà a "raccogliere" le colonne piene di pediene, e quindi le colonne da scartare nella generazione del random.
            ArrayList<Integer> appoggio = new ArrayList<>();
            for (int col = 0; col < colonne; col++)
            {
                int confronto = griglia[col][0];
                if(confronto > 0)
                {
                    appoggio.add(col);
                }
            }
            
            /*
             * Generazione del numero con esclusione dei sopracitati numeri da escludere.
             * In base al numero di elementi presenti nell'arraylist (che è uguale al numero di colonne occupate),vengono fatti un numero diverso
             * di controlli: se è presente il numero di una sola colonna il random deve essere diverso da quel numero, se sono presenti due numeri,
             * il random dovrà escludere quei due numeri e via discorrendo.
             */
            if (appoggio.size() == 1)
            {
                int casuale;
                do
                {
                    Random x = new Random();
                    casuale = x.nextInt(7);
                }
                while(casuale == appoggio.get(0)); // Finchè generi qualcosa di uguale ne generi un altro.

                gioca(casuale);
            }
            else if (appoggio.size() == 2)
            {
                int casuale;
                do
                {
                    Random x = new Random();
                    casuale = x.nextInt(7);
                }
                while(casuale == appoggio.get(0) || casuale == appoggio.get(1));
                
                gioca(casuale);
            }
            else if (appoggio.size() == 3)
            {
                int casuale;
                do
                {
                    Random x = new Random();
                    casuale = x.nextInt(7);
                }
                while(casuale == appoggio.get(0) || casuale == appoggio.get(1) || casuale == appoggio.get(2));
                
                gioca(casuale);
            }
            else if (appoggio.size() == 4)
            {
                int casuale;
                do
                {
                    Random x = new Random();
                    casuale = x.nextInt(7);
                }
                while(casuale == appoggio.get(0) || casuale == appoggio.get(1) || casuale == appoggio.get(2) || casuale == appoggio.get(3));
                
                gioca(casuale);
            }
            else if (appoggio.size() == 5)
            {
                int casuale;
                do
                {
                    Random x = new Random();
                    casuale = x.nextInt(7);
                }
                while(casuale == appoggio.get(0) || casuale == appoggio.get(1) || casuale == appoggio.get(2) || casuale == appoggio.get(3) || casuale == appoggio.get(4));
                
                gioca(casuale);
            }
            else if (appoggio.size() == 6)
            {
                int casuale;
                do
                {
                    Random x = new Random();
                    casuale = x.nextInt(7);
                }
                while(casuale == appoggio.get(0) || casuale == appoggio.get(1) || casuale == appoggio.get(2) || casuale == appoggio.get(3) || casuale == appoggio.get(4) || casuale == appoggio.get(5));
                
                gioca(casuale);

                if (turno == 42)
                {
                    GiocoForza4.endTimer = true;
                }
            }
            else
            {   
                Random x = new Random();
                int casuale = x.nextInt(7);
                
                gioca(casuale);
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    /**
     * Metodo che esegue il turno vero e proprio, ossia inserisce la pedina nella colonna decisa dal giocatore.
     * Viene tenuta in considerazione la forza di gravità e fatto il controllo sull'occupazione delle colonne.
     * @author Antonio Faienza e Riccardo Zandegiacomo
     * @param colonna è il numero della colonna nella quale deve essere inserita la pedina
     */
    protected static void gioca(int colonna)
    {
        for(int i = righe - 1; i >= 0; i--) // Controllo che parte dal basso.
        {
            if(griglia[colonna][i] == BIANCO)
            {                
                if((turno % 2) == 0)
                {
                    griglia[colonna][i] = ROSSO;
                }
                else
                {                    
                    griglia[colonna][i] = GIALLO;
                }     
                
                // Ad ogni turno viene invocato il controllo, gestito il timer e il turno del giocatore attraverso questi metodi.
                controllo(g2);            
                gestioneTimer();
                gestioneUtente();
                
                // Controllo parità (se "parita" NON viene cambiato nel corso di tutti i controlli enta in questo if e compare la schermata).
                if(turno == 42 && parita == true)
                {
                    Parita x = new Parita();
                    x.setVisible(true);  
                }
                break; 
            }           
            panel.repaint();
        }
    }
   
    /**
     * Questo metodo esegue una scansione e un controllo su tutta la scacchiera turno dopo turno per "scovare" eventuali combinazioni vincenti.
     * Qui vengono gestite tutte le booleane (end, endTimer, parita) e viene lanciata la schermata di vittoria; inoltre con l'inserimento di "OUTERLOOP:"
     * la finestra di vittaria viene lanciata una sola volta anche in caso ci fossero forza quattro multipli.
     * @author Antonio Faienza e Riccardo Zandegiacomo
     */
    protected static void controllo(Graphics g)
    {     
        // Controllo verticale sulle colonne.
        for (int col = 0; col < colonne; col++) 
        {
            for (int rig = righe - 1; rig >= righe - 3; rig--)
            {
                int confronto = griglia[col][rig];
                if(confronto > 0 && confronto == griglia[col][rig-1] && confronto == griglia[col][rig-2] && confronto == griglia[col][rig-3])
                {
                    // Salva il nome del vincitore in una stringa che verrà usata in Vittoria per decetare il vincitore.
                    vincitore = confronto;
                    
                    // La schermata di vittoria compare.
                    x = new Vittoria();
                    x.setVisible(true);
                    
                    // Il boolean che decreta la parità rimane false.
                    parita = false;
                    
                    // Il boolean che decreta la fine della partita diventa true.
                    end = true;
                    
                    // Il timer può così smettere di conteggiare i secondi.
                    GiocoForza4.endTimer = true;
                }
            }
        }
        
        // Controllo orizzontale sulle righe.
        outerloop:
        for (int rig = righe - 1; rig >= 0 ; rig--)
        {     
            for (int col = 0; col < colonne - 3; col++)
            {
                int confronto = griglia[col][rig];
                if(confronto > 0 && confronto == griglia[col+1][rig] && confronto == griglia[col+2][rig] && confronto == griglia[col+3][rig])
                {
                    vincitore = confronto;
                    x = new Vittoria();
                    x.setVisible(true);
                    parita = false;
                    end = true;
                    GiocoForza4.endTimer = true;
                    
                    // Questo metodo fa si che la finestra di vittoria compaia solo una volta anche in caso di forza quattro multipli.
                    if (x.isEnabled())
                    {  
                        break outerloop;
                    }
                }        
            }
        }
        
        // Controllo diagonale da sinistra a destra.
        outerloop:
        for (int rig = righe - 4; rig >= 0; rig--)
        {
            for(int col = 0; col < colonne - 3; col++)
            {
                int confronto = griglia[col][rig];
                if(confronto > 0 && confronto == griglia[col+1][rig+1] && confronto == griglia[col+2][rig+2] && confronto == griglia[col+3][rig+3])
                {
                    vincitore = confronto;
                    x = new Vittoria();
                    x.setVisible(true);  
                    parita = false;
                    end = true;
                    GiocoForza4.endTimer = true;
                    
                    if (x.isEnabled())
                    {
                        break outerloop;
                    }
                }
            }
        }
        
        // Controllo diagonale da destra a sinistra.
        outerloop:
        for (int rig = righe - 3; rig <= righe - 1; rig++)
        {
            for(int col = 0; col < colonne - 3; col++)
            {
                int confronto = griglia[col][rig];
                if(confronto > 0 && confronto == griglia[col+1][rig-1] && confronto == griglia[col+2][rig-2] && confronto == griglia[col+3][rig-3])
                {
                    vincitore = confronto;
                    x = new Vittoria();
                    x.setVisible(true); 
                    parita = false;  
                    end = true;
                    GiocoForza4.endTimer = true;
                    
                    System.out.println("la finestra è visibile " + x.isEnabled());
                    if (x.isEnabled())
                    {
                        break outerloop;
                    }
                }
            }
        }     
    }
    
    /**
     * Il metodo gestioneTimer() gestisce l'alternanza del Timer di gioco.
     * All'inizio parte sempre t, poi in base al turno (se pari o dispari) viene stoppato t e avviato t2, e così per tutta la partita.
     * @author Antonio Faienza e Riccardo Zandegiacomo
     */
    protected static void gestioneTimer()
    {
        if(turno % 2 == 0)
        {
           GiocoForza4.CountDown.t.stop();           
           GiocoForza4.CountDown.valoreTimer = GiocoForza4.livello;
           GiocoForza4.CountDown.start2(); 
        }
        else 
        {
            GiocoForza4.CountDown.t2.stop();
            GiocoForza4.CountDown.valoreTimer2 = GiocoForza4.livello;
            GiocoForza4.CountDown.start();
        }
    } 
    
    /**
     * Metodo che definisce le azioni che devono eseguire i bottoni di gioco una volta cliccati con il mouse.
     * @author Antonio Faienza e Riccardo Zandegiacomo
     */
    @Override
    public void actionPerformed(ActionEvent evento)
    {
        try
        {
            // Cattura il suono per riprodurlo ad ogni pressione dei bottoni.
            audio = new AudioStream(this.getClass().getResourceAsStream("/tok.wav"));
            AudioPlayer.player.start(audio); // Esegue il suono.
            
            if(a == evento.getSource())
            {
                if (end) return; // Impedisce la pressione di altri jButton dopo che è stata vinta una partita.
                gioca(0); // Inserisce la pedina.
            }  
            if(b == evento.getSource())
            {
                if (end) return;
                gioca(1);
            }   
            if(c == evento.getSource())
            {
                if (end) return;
                gioca(2);
            }   
            if(d == evento.getSource())
            {
                if (end) return;
                gioca(3);
            } 
            if(e == evento.getSource())
            {
                if (end) return;
                gioca(4);
            }       
            if(f == evento.getSource())
            {
                if (end) return;
                gioca(5);
            }       
            if(g == evento.getSource())
            {
                if (end) return;
                gioca(6);
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    /**
     * Questo KeyListener definisce le azioni che devono eseguire i bottoni di gioco e li associa alla pressione della tastiera.
     * Per giocare infatti si possono usare anceh i bottoni da 1 a 7, ogniuno corrispondente alla colonna nella quale si vuole inserire la pedina.
     * @author Antonio Faienza e Riccardo Zandegiacomo
     */
    KeyListener tasti = new KeyListener()
    {

        @Override
        public void keyTyped(KeyEvent e)
        {
            
        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            
        }

        @Override
        public void keyReleased(KeyEvent e)
        {
            try
            {
                // Cattura il suono per riprodurlo ad ogni pressione dei bottoni.
                audio = new AudioStream(this.getClass().getResourceAsStream("/tok.wav"));
                
                if(e.getKeyCode() == KeyEvent.VK_1)
                {
                    if (end) return; // Impedisce la pressione di altri jButton dopo che è stata vinta una partita.
                    AudioPlayer.player.start(audio); // Esegue il suono.
                    gioca(0); // Inserisce la pedina.
                    
                }  
                if(e.getKeyCode() == KeyEvent.VK_2)
                {
                    if (end) return;
                    AudioPlayer.player.start(audio);
                    gioca(1);
                }   
                if(e.getKeyCode() == KeyEvent.VK_3)
                {
                    if (end) return;
                    AudioPlayer.player.start(audio);
                    gioca(2);
                }   
                if(e.getKeyCode() == KeyEvent.VK_4)
                {
                    if (end) return;
                    AudioPlayer.player.start(audio);
                    gioca(3);
                } 
                if(e.getKeyCode() == KeyEvent.VK_5)
                {
                    if (end) return;
                    AudioPlayer.player.start(audio);
                    gioca(4);
                }       
                if(e.getKeyCode() == KeyEvent.VK_6)
                {
                    if (end) return;
                    AudioPlayer.player.start(audio);
                    gioca(5);
                }       
                if(e.getKeyCode() == KeyEvent.VK_7)
                {
                    if (end) return;
                    AudioPlayer.player.start(audio);
                    gioca(6);
                }
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    };
}