package Database;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Classe che si occupa di inserire i record all'interno del nostro database.
 * @author Antonio Faienza
 */
public class InserisciRecord 
{
    private static Connection conn = ConnessioneDB.conn;
    private static PreparedStatement ps;
    private static int partVinte = 0;
    private static int partPerse = 0;   
    private static int diff;
    private static int sommaPartiteVinte;
    private static int sommaPartitePerse;
    
    // Metodo che inserisce in due stringhe vincitore e sconfitto.
    public InserisciRecord()
    {
        vincitore();
        inserisci("" +  Vincita_Parita.Vittoria.jLabel4.getText() + "");             
        sconfitto();            
        inserisci("" + Vincita_Parita.Vittoria.perdente + "");
    }
    
    /**
     * Metodo che aggiorna il Database.
     */
    public static void inserisci(String nome)
    {
        try
        {
            // Richiesta di connessione.
            String query = "select partitevinte from graduatoria where nomeutente = '" + nome + " '";
            String query2 = "select partiteperse from graduatoria where nomeutente = '" + nome + " '";
                
            // Controlla se la query è andata a buon fine e se il dato è gia esistente.
            if(exist(query).first())
            {
                String valoreVittoria = getDato(query);
                String valoreSconfitta = getDato(query2);
                int valoreVincita = Integer.parseInt(valoreVittoria); // Converte una stringa in intero.
                int valorePartitePerse = Integer.parseInt(valoreSconfitta);

                // Aggiornamento dei dati aggiungendoli a quelli precedenti, se esistenti.
                sommaPartiteVinte = partVinte + valoreVincita ;
                sommaPartitePerse = partPerse + valorePartitePerse ;
                diff = sommaPartiteVinte - sommaPartitePerse;

                // Esegue l'aggiornamento vero e proprio.
                String query3 = "update graduatoria set partitevinte = '" + sommaPartiteVinte + "',partiteperse = '" + sommaPartitePerse + "',differenza = '" + diff + "' where nomeutente = '" + nome + "'";
                ps = conn.prepareStatement(query3);
                ps.executeUpdate();
                
            }
            else // Se il dato non esiste, mi scrive un nuovo utente.
            {
                diff = partVinte - partPerse;
                String sql = "INSERT INTO classifica.graduatoria(nomeutente,partitevinte,partiteperse,differenza) VALUES (?,?,?,?)";
                ps = conn.prepareStatement(sql);            
                ps.setString(1, nome);
                ps.setString(2, " " + partVinte + " ");
                ps.setString(3, " " + partPerse + " ");
                ps.setString(4, " " + diff + " ");
                ps.executeUpdate();
            }    
        }
        catch(Exception e)
        {
            System.out.println("ERRORE " + e);
        }
    }
    
    /**
     * Aggiorna il contatore del vincitore.
     */
    private static void vincitore()
    {
        partVinte = 1;
        partPerse = 0;
    }
    
    /**
     * Aggiorna il contatore del perdente.
     */
    private static void sconfitto()
    {
        partVinte = 0;
        partPerse = 1;
    }
    
    /**
     * Metodo che recupera il dato contenuto nella query.
     */
    public static String getDato(String query) throws SQLException
    {
        String valore = "";
        ps = conn.prepareStatement(query); // Prepara la richiesta.
        ResultSet x = ps.executeQuery();
        ResultSetMetaData meta = x.getMetaData();
        int colonna = meta.getColumnCount();
        while(x.next())
        {
            for(int i = 0; i < colonna; i++)
            {
                valore = x.getString( i + 1);
            }
        }
        return valore;
    }
    
    /**
     * Metodo che verifica se il risultato esiste.
     */
    public static ResultSet exist(String query) throws SQLException
    {
        ResultSet set = null;
        ps = conn.prepareStatement(query); // Prepara l'invio della query.
        set = ps.executeQuery();  // Esegue la query.
        return set;
    }
}
