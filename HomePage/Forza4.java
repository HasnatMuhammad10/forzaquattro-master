package HomePage;

import Classifica.Classifica;
import Help.Help;
import NuovaPartita.NuovaPartita;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Classe che definisce l'aspetto e le funzioni dell'Home Page.
 * @author Antonio Faienza e Riccardo Zandegiacomo
 */
public class Forza4 extends JFrame 
{
    private ButtonGroup tutti;
    private JButton NuovaPartita;
    private JButton Help; 
    private JButton Classifica;
    private JButton Esci;
    
    URL url = getClass().getResource("/Immagini/ScreenFinale.jpg");
    URL urlHome = getClass().getResource("/Immagini/SfondoScreen2.jpg");
    private ImageIcon icona = new ImageIcon(url);
    
    public Forza4()
    {
        setTitle("Forza4");
        setLookAndFeel();
        setIconImage(icona.getImage());
        setSize(700, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        button();
    }
    
    /**
     * Metodo che definisce i bottoni nell'interfaccia principale di gioco.
     */
    private void button()
    {
        // Definizione dei pulsanti.
        PannelloImmagini pannello = new PannelloImmagini();
        tutti = new ButtonGroup();
        
        NuovaPartita = new JButton("Nuova Partita");
        NuovaPartita.setToolTipText("Inizia una nuova Partita");
        NuovaPartita.setBackground(Color.WHITE);
        tutti.add(NuovaPartita);
        this.add(NuovaPartita);
        Forza4.PartitaNuova newGame = new Forza4.PartitaNuova();
        NuovaPartita.addActionListener(newGame);
        
        Help = new JButton("Help");
        Help.setToolTipText("Come si gioca");
        Help.setBackground(Color.WHITE);
        tutti.add(Help);
        this.add(Help);
        Forza4.Aiuto aiuto = new Forza4.Aiuto();
        Help.addActionListener(aiuto);
        
        Classifica = new JButton("Classifica");
        Classifica.setToolTipText("Visualizza la Classifica");
        Classifica.setBackground(Color.WHITE);
        tutti.add(Classifica);
        this.add(Classifica);
        Forza4.Record record = new Forza4.Record();
        Classifica.addActionListener(record);
        
        Esci = new JButton("Esci");
        Esci.setToolTipText("Esci dal gioco");
        Esci.setBackground(Color.WHITE);
        tutti.add(Esci);
        this.add(Esci);        
        Forza4.Uscita exit = new Forza4.Uscita();
        Esci.addActionListener(exit);
        
        // Layout pannello e pulsanti.
        GroupLayout jPanel1Layout = new GroupLayout(pannello);
        pannello.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Esci, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(NuovaPartita, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Help, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(Classifica, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(73, 73, 73))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NuovaPartita, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Classifica, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(Help, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addComponent(Esci, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pannello, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pannello, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
    }
    
    /**
     * Metodo che definisce il LAF.
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
     * Metodo che definisce il jButton "Nuova Partita".
     */
    private class PartitaNuova implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent evento)
        {
            if(("Nuova Partita").equals(evento.getActionCommand()))
            {
                dispose();
                NuovaPartita game = new NuovaPartita();
                game.setVisible(true);
            }
        }
    }
    
    /**
     * Metodo che definisce il jButton di "Esci".
     */
    private class Uscita implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            System.exit(0);
        }
    }
    
    /**
     * Metodo che definisce il jButton "Classifica".
     */
    private class Record implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getActionCommand().equals("Classifica"))
            {
                Classifica record = new Classifica();
                record.setVisible(true);
            }
        }
    }
    
    /**
     * Metodo che definisce il jButton "Help".
     */
    private class Aiuto implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(("Help").equals(e.getActionCommand()))
            {
                Help help = new Help();
                help.setVisible(true);
            }
        }
    }    

    /**
     * Classe che gestisce le immagini sul pannello.
     */
    class PannelloImmagini extends JPanel
    {
       private Image image;

       public PannelloImmagini()
       {
           try
           {
               image = ImageIO.read(urlHome);
           }
           catch (IOException e)
           {
               e.printStackTrace();
           }
       }
       
       public void paintComponent(Graphics g)
       {
           super.paintComponent(g);
           if (image == null)
               return;
           
          // Disegno l'immagine dall'angolo superiore sinistro.
          g.drawImage(image,0,0,null);
       }
    }
}
