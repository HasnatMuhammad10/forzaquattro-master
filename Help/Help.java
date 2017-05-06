package Help;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Classe che crea la finestra di Help.
 * @author Alessandro Coppola
 */
public class Help extends JFrame
{
    private javax.swing.JButton Indietro;
    private javax.swing.JLabel Regole;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea Area;
    
    URL url = getClass().getResource("/Immagini/ScreenFinale.jpg");
    ImageIcon icona = new ImageIcon(url);
    
    
    /**
     * Nel costruttore sono spiegate tutte le componenti utilizzate per la realizzazione del frame.
     */
    public Help()
    {
        setTitle("Forza 4");
        setLookAndFeel();
        setIconImage(icona.getImage());  
       
        /*
         * Definita la dimensione della schermata attraverso la classe Dimension del pacchetto awt e 
         * le sue dimensioni attraverso la classe Toolkit.
         */
        Dimension dimensione = Toolkit.getDefaultToolkit().getScreenSize();
        
        int x = dimensione.width;
        int y = dimensione.height;
        
        /*
         * Utilizziamo un metodo di JFrame che ci permette di definire la posizione: prima coppia la posizione
         * seconda coppia la dimensione.
         */
        setBounds(x/4, y/4, x/2, y/2);
        
        setResizable(false); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        Regole = new javax.swing.JLabel("   REGOLE");
        Regole.setFont(new java.awt.Font("Perpetua Titling MT", 1, 14));
        
        Indietro = new JButton("Indietro");
        Indietro.setToolTipText("Torna alla schermata precedente");
        Help.Chiudi chiudi = new Help.Chiudi();
        Indietro.addActionListener(chiudi);
        
        jScrollPane1 = new JScrollPane();
        Area = new JTextArea();
        Area.setEditable(false); // Evitiamo che qualcuno possa cambiare da "fuori" il contenuto dell'area.
        Area.setBackground(new java.awt.Color(204, 204, 255));
        Area.setColumns(20);
        Area.setForeground(new java.awt.Color(51, 0, 51));
        Area.setRows(10);
        Area.setText("L'applicativo in questione prevede che due utenti si sfidino a turni alterni sulla stessa macchina. L'obiettivo del \ngioco è quello di riuscire a mettere in fila quattro pedine, non importa in che ordine esse siano disposte \n(verticale, orizzontale, o obliquo), l'importante è che vengano posizionate in successione. L'elemento \nfondamentale del gioco, che lo rende del tutto originale, è la forza di gravità: la scacchiera infatti è posta in verticale\nfra i giocatori, e le pedine vengono fatte cadere lungo la griglia in modo tale che una pedina inserita\nin una certa colonna vada sempre ad occupare la posizione libera situata più in basso nella colonna\nstessa.\nPuoi scegliere fra tre livelli di gioco: facile, medio e difficile. Con il livello facile ci sono 8 secondi per pensare ed\nagire, con il livello medio che ne sono 5, mentre con il livello difficile sono a disposizione solo 3 secondi.\nGiocare è semplice, basta registrarsi all'applicativo con un nome utente che non superi i 10 caratteri e una\npassword di almeno 6 caratteri alfanumerici. \n\n\nAutori del gioco: ANTONIO FAIENZA, RICCARDO ZANDEGIACOMO e ALESSANDRO COPPOLA");
        jScrollPane1.setViewportView(Area);

        // Dimensioni della finestra.
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Regole, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(287, 287, 287))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Indietro, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Regole, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Indietro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        pack();
    }
    
    /**
     * Metodo che definisce l'aspetto della finestra.
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
     * Definisce il compito del pulsante "Indietro".
     */
    private class Chiudi implements ActionListener
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

