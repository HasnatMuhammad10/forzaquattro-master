package Vincita_Parita;

import HomePage.Forza4;
import NuovaPartita.NuovaPartita;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Window;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.swing.JOptionPane;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Classe che gestisce la schermata di vittoria.
 * @author Antonio Faienza e Riccardo Zandegiacomo
 */
public class Vittoria extends JFrame 
{
    private JButton NewGame;
    private JButton HomePage;
    private JLabel jLabel1;
    public static JLabel jLabel4;
    private JLabel jLabel5;
    
    public static String perdente;
    
    URL url = getClass().getResource("/Immagini/ScreenFinale.jpg"); 
    URL url2 = getClass().getResource("/Immagini/vittoria.jpg"); 
    ImageIcon icona = new ImageIcon(url); 
    
    /**
     * Il costruttore richiama i componenti e il suono.
     */
    public Vittoria() 
    {
        componenti();
        suono();
    }

    /**
     * Metodo che esegue il suono in caso di vittoria.
     */ 
    private void suono()
    {
        try
        {
            AudioStream audio = new AudioStream(this.getClass().getResourceAsStream("/win.wav"));
            AudioPlayer.player.start(audio);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    /**
     * Definisce le componenti principali e la loro posizione nella finestra.
     */
    private void componenti()
    {
        setTitle("Forza 4");
        setLookAndFeel();     
        setIconImage(icona.getImage());           
        posizione();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    /**
     * Definisce la posizione delle componenti nella finestra.
     */
    public void posizione()
    {
        java.awt.GridBagConstraints gridBagConstraints;
        
        jLabel1 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        
        HomePage = new JButton("Home Page");
        Home home = new Home();
        HomePage.addActionListener(home);
        
        NewGame = new JButton("Nuova Partita");
        Rigioca rigioca = new Rigioca();
        NewGame.addActionListener(rigioca);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 255, 102));
        getContentPane().setLayout(new java.awt.GridBagLayout());
        setLocationRelativeTo(null);
        setResizable(false); 
        jLabel4.setFont(new java.awt.Font("Rockwell", 0, 36));
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        
        // Definisce il nome del vincitore che comparirà sulla finestra.
        if (GiocoEffettivo.Griglia.vincitore == 1) // Vince il rosso.
        {
            jLabel4.setText(NuovaPartita.idG1);
            perdente = NuovaPartita.idG2;
           
            // Collegamento con il Database.
            Database.InserisciRecord x = new Database.InserisciRecord();
        }
        else // Vince il giallo.
        {
            jLabel4.setText(NuovaPartita.idG2);
            perdente = NuovaPartita.idG1;
            Database.InserisciRecord x = new Database.InserisciRecord();
        }

        // Meccanismo di centramento della label in base alla sua lunghezza.
        if(jLabel4.getText().length() <= 5)
        {
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridwidth = 3;
            gridBagConstraints.ipadx = 19;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.insets = new java.awt.Insets(130, 260, 0, 0); // 130, 160, 0, 0
            getContentPane().add(jLabel4, gridBagConstraints);
        }
        else
        {
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0; 
            gridBagConstraints.gridy = 0; 
            gridBagConstraints.gridwidth = 3; 
            gridBagConstraints.ipadx = 19; 
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.insets = new java.awt.Insets(130, 215, 0, 0); // 130, 160, 0, 0
            getContentPane().add(jLabel4, gridBagConstraints);
        }
        
        jLabel5.setFont(new java.awt.Font("Rockwell", 0, 36));
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("     HAI VINTO !"); 
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 51;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(17, 160, 0, 0);
        getContentPane().add(jLabel5, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 67; 
        gridBagConstraints.ipady = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(26, 108, 0, 86);
        getContentPane().add(HomePage, gridBagConstraints);

        NewGame.setText("Nuova Partita");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 55;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(26, 86, 0, 0);
        getContentPane().add(NewGame, gridBagConstraints);
        
        jLabel1.setIcon(new ImageIcon(url2));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = -7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 150, 3, 0);
        getContentPane().add(jLabel1, gridBagConstraints);

        pack();
    }

    /**
     * Metodo che chiude le finestre precedentemente aperte.
     */
    public void chiudi()
    {
        System.gc();                    
        Window[] win = Window.getWindows();
        for(int i = 0; i < win.length; i++)
        {                        
            win[i].dispose();
            win[i] = null;                    
        } 
    }
    
    /**
     * Metodo che definisce il LAF della finestra.
     */
    private void setLookAndFeel()
    {
        try
        {
            for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
                if("Nimbus".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
            System.out.println("!!!! Attenzione Nimbus non ha avuto successo");
            System.err.println(ex); // ci facciamo scrivere il report delle ecezioni
        }
    }
    
    /**
     * Metodo che definisce il tasto "Home Page".
     */
    private class Home implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(("Home Page").equals(e.getActionCommand()))
            {    
                chiudi();
                
                Forza4 f = new Forza4();
                f.setVisible(true);
            }
        }
    }
    
    /**
     * Metodo che definisce il tasto "Rigioca".
     */
    private class Rigioca implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(("Nuova Partita").equals(e.getActionCommand()))
            {
                chiudi();
                
                // Salva gli ultimi due giocatori su una stringa.
                String replaceG1 = NuovaPartita.idG1.toString();
                String replaceG2 = NuovaPartita.idG2.toString();
                
                // Apre la finestra.
                NuovaPartita n = new NuovaPartita();
                n.setVisible(true);
                
                // Inserisce già come preimpostati i nomi degli ultimi due giocatori.
                n.inserisciUtente1.setText(replaceG1);
                n.inserisciUtente2.setText(replaceG2);
            }
        }
    }
}
