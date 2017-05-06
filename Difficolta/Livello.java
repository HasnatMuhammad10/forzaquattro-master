package Difficolta;

import GiocoEffettivo.GiocoForza4;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Classe che definisce la finestra del livello.
 * @author Antonio Faienza e Riccardo Zandegiacomo
 */
public class Livello extends JFrame 
{
    private JComboBox scelta;
    private JLabel difficoltà;
    
    URL url = getClass().getResource("/Immagini/ScreenFinale.jpg");
    ImageIcon icona = new ImageIcon(url);
    
    public Livello() 
    {
        setTitle("Forza 4");
        setLookAndFeel();
        setIconImage(icona.getImage());
        pulsanti();
        setLocationRelativeTo(null);
        setResizable(false); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    /**
     * Metodo che si occupa di definisre i pulsanti della finestra.
     */
    private void pulsanti()
    {
        scelta = new JComboBox();
        scelta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Facile", "Medio", "Difficile" }));
        Livello.Graduale x = new Livello.Graduale();
        scelta.addActionListener(x);
        
        difficoltà = new JLabel("Difficoltà");
        difficoltà.setToolTipText("Scegli il livello con cui giocare");
        
        // Definisce la posizione.
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(difficoltà, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scelta, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(difficoltà, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scelta, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
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
            for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) // indaghiamo su tutti i lookAndFeel installati recuperabili grazie alla classe UIMAnager
            {
                if("Nimbus".equals(info.getName())) // vediamo se c'è Nimbus
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break; // arrestiamo il ciclo una volta trovato
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
     * Metodo che chiude tutte le finestre precedentemente aperte.
     */
    private void chiudi()
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
     * Definizione della finestra a tendina e attivazione dei suoi pulsanti.
     */
    private class Graduale implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String x = (String)scelta.getSelectedItem();
            
            // Livello Facile
            if(("Facile").equals(x))
            {
                //Metodo che chiude tutte le finestre precedentemente aperte.
                chiudi();
                
                // Ripristino variabili booleane.
                GiocoEffettivo.Griglia.end = false;
                GiocoEffettivo.Griglia.parita = true;
                GiocoForza4.endTimer = false;
                GiocoEffettivo.Griglia.turno = 0;
                
                // Definizione del valore da cui deve partire il CountDown.
                GiocoEffettivo.GiocoForza4.CountDown.valoreTimer = 8;
                GiocoEffettivo.GiocoForza4.CountDown.valoreTimer2 = 8;
                
                // Chiamata di una nuova istanza di "GiocoForza4".
                GiocoForza4 g = new GiocoForza4(8);
                g.setVisible(true);
            }
            
            // Livello Medio
            if(("Medio").equals(x))
            {
                chiudi();
                GiocoEffettivo.Griglia.end = false;
                GiocoEffettivo.Griglia.parita = true;
                GiocoForza4.endTimer = false;
                GiocoEffettivo.Griglia.turno = 0;
                GiocoEffettivo.GiocoForza4.CountDown.valoreTimer = 5;
                GiocoEffettivo.GiocoForza4.CountDown.valoreTimer2 = 5;
                
                GiocoForza4 g = new GiocoForza4(5);
                g.setVisible(true);
            }
            
            // Livello Difficile
            if(("Difficile").equals(x))
            {
                chiudi();
                GiocoEffettivo.Griglia.end = false;
                GiocoEffettivo.Griglia.parita = true;
                GiocoForza4.endTimer = false;
                GiocoEffettivo.Griglia.turno = 0;
                GiocoEffettivo.GiocoForza4.CountDown.valoreTimer = 3;
                GiocoEffettivo.GiocoForza4.CountDown.valoreTimer2 = 3;
                
                GiocoForza4 g = new GiocoForza4(3);
                g.setVisible(true);
            }
        }
    }
}
