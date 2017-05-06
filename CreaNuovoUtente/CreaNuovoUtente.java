package CreaNuovoUtente;

import Database.ConnessioneDB;
import NuovaPartita.NuovaPartita;
import com.mysql.jdbc.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Classe principale per la creazione della finestra che registra e salva un nuovo utente.
 * @author Antonio Faienza, Riccardo Zandegiacomo e Alessandro Coppola
 */
public class CreaNuovoUtente extends JFrame
{
    // Definizione oggetti necessari.
    private JButton Crea;
    private JLabel Utente;
    private JLabel Password1;
    private JLabel VerificaPossibilita;
    private JLabel Password2;
    private JPasswordField CreaPassword1;
    private JPasswordField CreaPassword2;
    private JTextField InserisciUtente;
    
    private String id;
    private String psw;    
    
    private static Connection conn = ConnessioneDB.conn;
    private static PreparedStatement ps;
    
    // Booleane di verifica
    private boolean verId;
    private boolean verPsw;
    private boolean verifica;
    
    URL url = getClass().getResource("/Immagini/ScreenFinale.jpg");
    ImageIcon icona = new ImageIcon(url);
    
    public CreaNuovoUtente()
    {
        setTitle("Forza 4");
        setResizable(false);   
        setIconImage(icona.getImage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        componenti();
        setLocationRelativeTo(null);
    }
    
    /**
     * Metodo che definisce la posizione e il compito dei componenti della finestra.
     */
    private void componenti()
    {
        // Oggetto della classe KeyListener
        KeyListener enter = new KeyListener()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                
            }

            @Override
            public void keyTyped(KeyEvent e)
            {
                
            }

            // Consente di confermare la creazione del nuovo utente attraverso la pressione del tasto "Enter".
            @Override
            public void keyReleased(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    try
                    {
                        Controllo c = new Controllo();
                    }
                    catch (SQLException ex)
                    {
                        Logger.getLogger(CreaNuovoUtente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        
        InserisciUtente = new JTextField();
        InserisciUtente.addKeyListener(enter);
        
        CreaPassword1 = new JPasswordField("");
        CreaPassword1.addKeyListener(enter);
        
        Crea = new javax.swing.JButton("Crea");
        CreaNuovoUtente.Crea crea = new CreaNuovoUtente.Crea();
        Crea.addActionListener(crea);
        
        CreaPassword2 = new JPasswordField("");
        CreaPassword2.addKeyListener(enter);
        
        Utente = new JLabel("Utente");
        Password1 = new JLabel("Immetti la password");
        Password2 = new JLabel("Conferma la password");
        VerificaPossibilita = new JLabel("");
        VerificaPossibilita.setForeground(new java.awt.Color(255, 0, 0));
        
        // Codice che dispone e setta i componenti della finestra
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(VerificaPossibilita, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(Crea, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InserisciUtente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(CreaPassword1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                .addComponent(CreaPassword2))
                            .addComponent(Utente, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Password2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Password1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Utente, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(InserisciUtente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Password1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(CreaPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Password2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(CreaPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Crea, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VerificaPossibilita, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        pack(); 
    }
    
    /**
     * Implementa e abilita il pulsante Crea.
     */
    private class Crea implements ActionListener
    { 
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(("Crea").equals(e.getActionCommand()))
            {
                try
                {
                    Controllo c = new Controllo();
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(CreaNuovoUtente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**
     * Contiene il codice che esegue il controllo per la creazione del nuovo utente.
     * È inserito anche il controllo per i messaggi d'errore relativi ai campi lasciati vuoti.
     */
    private class Controllo
    {
        private Controllo() throws SQLException
        {
            // Inizializzo le variabili booleane a "true" e la jLabel vuota per il controllo.
            verId = true;
            verPsw = true;
            verifica = true;
            VerificaPossibilita.setText("");

            // Messaggi di output nel caso in cui vengano lasciati spazi vuoti.
            if (InserisciUtente.getText().isEmpty() == true || CreaPassword1.getText().isEmpty() == true || CreaPassword2.getText().isEmpty() == true)
            {
               // Tutto vuoto. 
               if (InserisciUtente.getText().isEmpty() == true && CreaPassword1.getText().isEmpty() == true && CreaPassword2.getText().isEmpty() == true)
               {
                   VerificaPossibilita.setText("Nome utente e password vuoti!!!");
               }
               else
               {
                   // Campo utente vuoto.
                   if (InserisciUtente.getText().isEmpty() == true)
                   {
                       VerificaPossibilita.setText("Inserisci un nome utente!!!");
                   }
                   else
                   {
                       // Campi password vuoti.
                       if (CreaPassword1.getText().isEmpty() == true && CreaPassword2.getText().isEmpty() == true)
                       {
                           VerificaPossibilita.setText("Inserisci una password!!!");
                       }
                       else
                       {
                           VerificaPossibilita.setText("Ripeti la password!!!");
                           VerificaPossibilita.setForeground(new java.awt.Color(255, 0, 0));
                       }
                   }
               }
            }
            else
            {
               
                // Controllo sul nome utente (deve essere al massimo composto da 10 caratteri).
                if (InserisciUtente.getText().length() > 10)
                {
                    verId = false;
                }
                else
                {
                    id = InserisciUtente.getText();
                    String query = "select nome from classifica.giocatori where nome = '" + id + " '";
                    if(exist(query).first())
                    {
                        verifica = false;
                    }
                }

                // Controllo sulla password (deve essere di almeno 6 caratteri e la ripetizione dev'essere corretta).
                if (CreaPassword1.getText().length() >= 6 && CreaPassword1.getText().equals(CreaPassword2.getText()))
                {
                    psw = CreaPassword1.getText();
                }
                else
                {
                    verPsw = false;
                }

                // Scrittura su "database.txt" una volta superate le condizioni precedenti.
                if(verId == true && verPsw == true && verifica == true)
                {  
                    // Metodo che inserisce un nuovo utente nel Database.
                    inserisciGiocatore();

                    // Chiusura finestra.
                    dispose();

                }
                else if (verId == true && verPsw == true && verifica == false)
                {
                    VerificaPossibilita.setText("ID già esistente");
                }
                else
                {
                    VerificaPossibilita.setText("Nome utente e/o password errati");
                }
            }
        }
    }
    
    /**
     * Metodo che permette l'inserimento di un nuovo utente.
     */
    private void inserisciGiocatore()
    {
        try
        {
            String sql = "INSERT INTO classifica.giocatori(nome,pass) VALUES (?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "" + id + "");
            ps.setString(2, "" + psw + ""); 
            ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CreaNuovoUtente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo che restituisce il risultato contenuto nel Database.
     */
    private ResultSet exist(String query) throws SQLException
    {
        ResultSet set = null;
        ps = conn.prepareStatement(query);
        set = ps.executeQuery();
        return set;
    }
}
    
    
