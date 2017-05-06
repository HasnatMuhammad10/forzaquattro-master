package Classifica;

import Database.ConnessioneDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 * Classe principale per creazione della connessione col database e creazione classifica.
 * @author Antonio Faienza e Riccardo Zandegiacomo
 */
public class Classifica extends JFrame
{
    // Dichiarazione variabili e oggetti necessari.
    static PreparedStatement ps;
    static ResultSet rs;
    static Connection conn = ConnessioneDB.conn;
    
    public static DefaultTableModel model;
    private Object[][] lista;
    private Object[] colonneTabella;
    
    private JButton Indietro;
    private JLabel jLabel1;
    private JScrollPane jScrollPane2;
    private JTable tabella;
    
    URL url = getClass().getResource("/Immagini/ScreenFinale.jpg");
    ImageIcon icona = new ImageIcon(url);
    
    public Classifica() 
    {
        // Disegno della finestra.
        setTitle("Forza4");
        setLookAndFeel();
        setIconImage(icona.getImage());
        componenti();
        setLocationRelativeTo(null);
        setResizable(false); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // CHiamata metodi di connessione e aggiornamento.
        aggiornaTabella();
        connetti();
    }

    /**
     * Metodo che stabilisce la connessione col Database
     */
    private void connetti() 
    {
        try 
        {
            // Stabilisce la connessione col Database.
            String str =  "select * from graduatoria ORDER BY differenza DESC limit 10";
            ps = Database.ConnessioneDB.conn.prepareStatement(str);
            rs = ps.executeQuery();
           
            int riga = 0;
            int colonna = 0;
            while (rs.next())
            {
                lista[riga][colonna] = rs.getString("nomeutente");
                colonna++;
                lista[riga][colonna] = rs.getString("partitevinte");
                colonna++;
                lista[riga][colonna] = rs.getString("partiteperse");
                colonna++;
                lista[riga][colonna] = rs.getString("differenza");
                colonna = 0;
                riga++;
                
                if(riga == 10)
                {                    
                    break;
                }
            }
            model.setDataVector(lista, colonneTabella);
            tabella.setModel(model);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Classifica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo che aggiorna la tabella "Classifica".
     */
    private void aggiornaTabella()
    {
        colonneTabella = new String[]{"Nome Utente", "Partite Vinte", "Partite Perse", "Punteggio"};
        
        int riga = 0;
        int colonna = 0;
        
        // Sovrascrive la tabella generata da NetBeans con la tabella che fa al caso nostro.
        model = new DefaultTableModel()
        {
           public boolean isCellEditable(int rows, int columns)
           {
               return false;
           }
        };
        
        tabella.setEnabled(false);
        tabella.setModel(model);
        
        model.isCellEditable(riga, 4);
        
        // Nmero righe e colonne.
        lista = new Object[10][4];
    }
    
    /**
     * Metodo che richiama e ordina i componenti della finestra.
     */
    private void componenti() 
    {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        // Definizione di "jLabel1".
        jLabel1 = new JLabel();
        jLabel1.setFont(new java.awt.Font("Perpetua Titling MT", 1, 14));
        jLabel1.setText(" CLASSIFICA");
        
        // Definizione pulsante "Indietro".
        Indietro = new JButton("Indietro");
        Classifica.Ritorna ritorna = new Classifica.Ritorna();
        Indietro.addActionListener(ritorna);
        
        // definizione "Tabella".
        tabella = new JTable();
        tabella.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null}
        },new String [] {
            "Title 1", "Title 2", "Title 3", "Title 4"
        }
                ));
        
         // Definizione "jScrollPane2"
        jScrollPane2 = new JScrollPane();
        jScrollPane2.setViewportView(tabella);
        
        // Definizione posizione dei componenti nella finestra.
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Indietro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(256, 256, 256))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Indietro, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        pack();
    }
    
    /**
     * Metodo che cerca e imposta il tema "Nimbus".
     */
    private void setLookAndFeel()
    {
        try
        {
            // Indaghiamo su tutti i LookAndFeel installati recuperabili grazie alla classe UIMAnager.
            for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
                // Controllo presenza Nimbus.
                if("Nimbus".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break; // Arrestiamo il ciclo una volta trovato Nimbus.
                }
            }
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
            System.out.println("!!! Attenzione Nimbus non ha avuto successo!!!");
            System.err.println(ex); // Scrittura del report delle ecezioni.
        }
    }
    
    /**
     * Metodo che definisce e abilita il punlasnte "Indietro".
     */
    private class Ritorna implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(("Indietro").equals(e.getActionCommand()))
            {
                dispose();
            }
        }
    }
}
