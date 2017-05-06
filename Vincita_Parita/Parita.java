package Vincita_Parita;

import HomePage.Forza4;
import NuovaPartita.NuovaPartita;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Classe che gestisce la finestra di parità.
 * @author Antonio Faienza e Riccardo Zandegiacomo
 */
public class Parita extends JFrame 
{
    private javax.swing.JButton NewGame;
    private javax.swing.JButton HomePage;
    private javax.swing.JLabel pannello;
    private javax.swing.JLabel Pareggio;
    private javax.swing.JLabel jLabel5;
    
    URL url = getClass().getResource("/Immagini/ScreenFinale.jpg");
    URL url2 = getClass().getResource("/Immagini/smile triste.JPG");
    ImageIcon icona = new ImageIcon(url);
    
    public Parita()
    {
        setTitle("Forza4");
        setLookAndFeel();    
        setIconImage(icona.getImage());           
        posizione();
        suono();
        setResizable(false);
        setLocationRelativeTo(null);
    }
    
    /**
     * Metodo che definisce aspetto, posizione e ruolo dei pulsanti.
     */
    public void posizione()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        Pareggio = new JLabel();
        jLabel5 = new JLabel();
        HomePage = new JButton();
        NewGame = new JButton();
        pannello = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        Pareggio.setFont(new java.awt.Font("Rockwell", 0, 36));
        Pareggio.setText("   PAREGGIO!");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 19;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(130, 160, 0, 0);
        getContentPane().add(Pareggio, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Felix Titling", 1, 24));
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 51;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(17, 160, 0, 0);
        getContentPane().add(jLabel5, gridBagConstraints);

        HomePage.setText("Home Page");
        Home home = new Home();
        HomePage.addActionListener(home);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 67;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(26, 96, 0, 90);
        getContentPane().add(HomePage, gridBagConstraints);

        NewGame.setText("Nuova Partita");
        Rigioca rigioca = new Rigioca();
        NewGame.addActionListener(rigioca);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 55;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(26, 86, 0, 0);
        getContentPane().add(NewGame, gridBagConstraints);

        pannello.setBackground(new java.awt.Color(0, 0, 0));
        pannello.setIcon(new ImageIcon(url2));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.ipady = -90;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 90, 0, 90);
        getContentPane().add(pannello, gridBagConstraints);
        pack();
    }
    
    /**
     * Metodo che cattura il suono e lo esegue una volta richiamato.
     */
    private void suono()
    {
        try
        {
            AudioStream audio = new AudioStream(this.getClass().getResourceAsStream("/pari.wav"));
            AudioPlayer.player.start(audio);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
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
            System.err.println(ex);
        }
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
     * Metodo per l'implementazione del tasto Home
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
     * Metodo per l'implementazione del tasto rigioca.
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
