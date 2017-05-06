package ScreenIniziale;

import Database.ConnessioneDB;
import Database.ConnessioneInterfaccia;
import HomePage.Forza4;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.InputStream;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Unica classe con il MAIN, quella da dove parte l'esecuzione del gioco.
 * Qui viene gestito lo screen iniziale e l'avvio della Home Page dopo tre secondi.
 * @author Antonio Faienza e Riccardo Zandegiacomo
 */
public class ScreenIniziale extends JFrame
{
    InputStream in;
    
    URL url = getClass().getResource("/Immagini/ScreenFinale.jpg");
    JScrollPane scorri;
    ImageIcon immagine;
    ImageIcon icona = new ImageIcon(url);
    
    public ScreenIniziale()
    {
        setTitle("Forza4");
        setLookAndFeel();
        setIconImage(icona.getImage());
        inizia();
    }
    
    /**
     * Metodo che definisce l'aspetto della finestra "ScreenIniziale".
     */
    public void inizia()
    {
        Dimension dimensione = Toolkit.getDefaultToolkit().getScreenSize();
        int x = dimensione.width;
        int y = dimensione.height;
        setBounds(x/4, y/4, x/2, y/2);
        
        // Imposta l'immagine nella finestra.
        immagine = new ImageIcon(url);
        JPanel pannello = new JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                Dimension dimensione = getSize();// Definisce le dimensioni dell'immagine nello schermo.
                g.drawImage(immagine.getImage(), 0, 0, dimensione.width, dimensione.height, null);
            }
        };
        
        scorri = new JScrollPane(pannello);
        getContentPane().add(scorri);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
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
            System.out.println("!!!Attenzione Nimbus non ha avuto successo!!!");
            System.err.println(ex);
        }
    }
    
    /**
     * Unico MAIN di tutto l'applicativo.
     * Contiene una gestione di Thread per far trascorrere 3 secondi tra lo screen iniziale e l'home page.
     */
    public static void main(String[] args)
    {
        Thread t = Thread.currentThread();
        
        // Compare lo screen iniziale.
        ScreenIniziale x = new ScreenIniziale();
        x.setVisible(true);
        x.setResizable(false);
        
        // Ciclo for che ad ogni incremento aspetta 1 secondo.
        try
        {
            for(int i = 0; i <= 3; i++)
            {
                t.sleep(1000);
            }
        }
        catch(InterruptedException ex)
        {
            System.out.println("!!! Thread interrotto !!!");
            System.err.println();
        } 
        
        // Chiusura "ScreenIniziale" e apertura di "Forza4".
        x.dispose();
        new ConnessioneInterfaccia().setVisible(true);
    }
}


