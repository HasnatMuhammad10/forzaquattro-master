package NuovaPartita;

import CreaNuovoUtente.CreaNuovoUtente;
import Database.ConnessioneDB;
import Difficolta.Livello;
import HomePage.Forza4;
import com.mysql.jdbc.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Classe che gestisce il pannello "Nuova Partita".
 * @author Antonio Faienza e Riccardo Zandegiacomo
 */
public class NuovaPartita extends JFrame 
{
    private JButton login;
    private JButton NuovoUtente;
    private JButton Home;
    private JLabel utente1;
    private JLabel utente2;
    private JLabel passw;
    private JLabel controllo1;
    private JLabel controllo2;
    private JPasswordField jPasswordField1;
    private JPasswordField jPasswordField2;
    public JTextField inserisciUtente1;
    public JTextField inserisciUtente2;
    
    URL url = getClass().getResource("/Immagini/ScreenFinale.jpg");
    ImageIcon icona = new ImageIcon(url);
    
    private String idFile;
    private String pswFile;
    public static String idG1;
    public static String idG2;
    
    
    private boolean g1;
    private boolean g2;
    
    private static Connection conn = ConnessioneDB.conn;
    private static PreparedStatement ps;
    
    public NuovaPartita() 
    {
        setTitle("Forza 4");
        setLookAndFeel();
        setIconImage(icona.getImage());
        componenti();
        setLocationRelativeTo(null);
        setResizable(false); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Metodo che rende utilizzabile anche il tasto ENTER per confermare il login degli utenti.
     * Contiene anche la definizione della varie Label e campi di inserimento.
     */
    private void componenti() 
    {
        KeyListener enter = new KeyListener()
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
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    Controllo controllo = new Controllo();
                }
            }
            
        };
        
        utente1 = new JLabel("UTENTE 1");
        utente2 = new JLabel("UTENTE 2");
        inserisciUtente1 = new JTextField("");
        inserisciUtente1.addKeyListener(enter);
        
        inserisciUtente2 = new JTextField("");
        inserisciUtente2.addKeyListener(enter);
        passw = new JLabel("   PASSWORD");
        passw.setPreferredSize(new java.awt.Dimension(73, 23));
        jPasswordField1 = new JPasswordField("");
        jPasswordField1.addKeyListener(enter);
        jPasswordField2 = new JPasswordField("");
        jPasswordField2.addKeyListener(enter);
        
        login = new JButton("LOGIN");
        NuovaPartita.Login liv = new NuovaPartita.Login();
        login.addActionListener(liv);
        
        NuovoUtente = new JButton("Nuovo Utente");
        NuovoUtente.setToolTipText("Crea un nuovo utente");
        NuovaPartita.Nuovo nuovo = new NuovaPartita.Nuovo();
        NuovoUtente.addActionListener(nuovo);
        
        Home = new JButton("Home Page");        
        NuovaPartita.Indietro home = new NuovaPartita.Indietro();
        Home.addActionListener(home);
        Home.setToolTipText("Torna alla Home Page");

        
        controllo1 = new JLabel("");
        controllo1.setForeground(new java.awt.Color(255, 0, 0));

        controllo2 = new JLabel("");
        controllo2.setForeground(new java.awt.Color(255, 0, 0));
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(NuovoUtente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Home, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inserisciUtente1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(utente1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inserisciUtente2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(utente2, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(38, 38, 38))
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(controllo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(controllo2)
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(167, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passw, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(167, 167, 167))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(utente1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(utente2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inserisciUtente2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inserisciUtente1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(passw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(controllo1)
                    .addComponent(controllo2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(login)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NuovoUtente)
                    .addComponent(Home))
                .addGap(25, 25, 25))
        );

        pack();
    }

    /**
     * Metodo che definisce il jbutton "Nuovo Utente".
     */
    private class Nuovo implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {   
            if(("Nuovo Utente").equals(e.getActionCommand()))
            {
                CreaNuovoUtente x = new CreaNuovoUtente();
                x.setVisible(true);
            }   
        }
    }
    
    /**
     * Metodo che definisce il jbutton "Login".
     */
    private class Login implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent evento)
        {
            if(("LOGIN").equals(evento.getActionCommand()))
            {
                Controllo controllo = new Controllo();
            }
        }
    }
    
    /**
     * Metodo che definisce il jbutton "Indietro".
     */
    private class Indietro implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if("Home Page".equals(e.getActionCommand()))
            {
                dispose();
                Forza4 riapri = new Forza4();
                riapri.setVisible(true);
            }
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
     * Metodo che controlla l'accesso degli utenti.
     * Al suo interno è anche presente un meccanismo che gestisce i messaggi di output
     * che compaiono se ci si prova a loggare senza riempire tutti i campi.
     */
    private class Controllo
    {
        public Controllo()
        {
            // Inizializzo le variabili booleane a "false" e le jLabel vuote per il controllo.
            g1 = false;
            g2 = false;
            controllo1.setText("");
            controllo2.setText("");

            // Messaggi di output nel caso in cui vengano lasciati spazi vuoti.
            if (inserisciUtente1.getText().isEmpty() == true || inserisciUtente2.getText().isEmpty() == true 
                    || jPasswordField1.getText().isEmpty() == true || jPasswordField2.getText().isEmpty() == true)
            {
                // Campi completamente vuoti.
                if (inserisciUtente1.getText().isEmpty() == true && inserisciUtente2.getText().isEmpty() == true
                        && jPasswordField1.getText().isEmpty() == true && jPasswordField2.getText().isEmpty() == true)
                {
                    controllo1.setText("Riempi i campi vuoti!!!");
                    controllo2.setText("Riempi i campi vuoti!!!");
                }
                else
                {
                    // Campi G1 vuoti.
                    if (inserisciUtente1.getText().isEmpty() == true && jPasswordField1.getText().isEmpty() == true)
                    {
                        controllo1.setText("Riempi i campi vuoti!!!");
                    }
                    else
                    {
                        if (inserisciUtente1.getText().isEmpty() == true)
                        {
                            controllo1.setText("Inserisci username!!!");
                        }
                        else if (jPasswordField1.getText().isEmpty() == true)
                        {
                            controllo1.setText("Inserisci password!!!");
                        }
                    }
                    // Campi G2 vuoti.
                    if (inserisciUtente2.getText().isEmpty() == true && jPasswordField2.getText().isEmpty() == true)
                    {
                        controllo2.setText("Riempi i campi vuoti!!!");
                    }
                    else
                    {
                        if (inserisciUtente2.getText().isEmpty() == true)
                        {
                             controllo2.setText("Inserisci username!!!");
                        }
                        else if (jPasswordField2.getText().isEmpty() == true)
                        {
                             controllo2.setText("Inserisci password!!!");
                        }
                    }
                }
            }
            
            /*
             * Se NON vengono lasciati campi vuoti viene eseguito il controllo delle credenziali.
             * Il controllo viene fatto verificando se i dati sono presenti anche in database.txt.
             * Il tutto viene eseguito da due scanner, uno per G1 e un altro per G2.
             */
            else
            {
                try
                {
                    String query = "select * from classifica.giocatori where nome = '" + inserisciUtente1.getText() + "' AND pass = '"+ jPasswordField1.getText() + "';";
                   
                    if (ottieniBoolean(query))
                    {
                        g1 = true;
                    }
                    
                    String query2 = "select * from classifica.giocatori where nome = '" + inserisciUtente2.getText() + "' AND pass = '"+ jPasswordField2.getText() + "';";
                    
                    if(ottieniBoolean(query2))
                    {
                        g2 = true;
                    }
                    
                    // Se l'accesso di ambedue i giocatori è avvenuto correttamente si può procedere con la scelta del livello.
                    if(g1 == true && g2 == true )
                    {
                        idG1 = inserisciUtente1.getText();
                        idG2 = inserisciUtente2.getText();

                        Livello livello = new Livello();
                        livello.setVisible(true);
                    }
                    else // Altrimenti si passa alla ricerca delle credenziali errate.
                    {
                        if (g1 == false && g2 == false) // Se entrambi i giocatori hanno inserito le credenziali errate.
                        {
                            controllo1.setText("Username e/o password errati");
                            controllo2.setText("Username e/o password errati");
                        }
                        else
                        {
                            if (g1 == false) // Se il G1 ha inserito le credenziali errate.
                            {
                                controllo1.setText("Username e/o password errati");
                            }
                            else // [g2 == false] Se il G2 ha inserito le credenziali errate.
                            {
                                controllo2.setText("Username e/o password errati");
                            }
                        }
                    }
                }
                catch (Exception ex)
                {
                    System.out.println("!!! Il file non è stato trovato !!!" + ex);
                    System.err.println();
                }
            }
        }
    }
    
    /**
     * Metodo che manda la query al Database e riporta il risultato.
     */
    public static boolean ottieniBoolean(String selectSQL)
    {
        boolean prova = false;
        try
        {
            PreparedStatement preparedStatement = null;
            preparedStatement = conn.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next())
            {
                prova = true;
            }
            else
            {
                preparedStatement.close();
            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex);
        }
        return prova;
    }
}
