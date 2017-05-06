package Database;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe che si occupa di effettuare una connessione all'interno del database.
 * @author Antonio Faienza
 */
public class ConnessioneDB {
    
    // Dati per la connessione.
    public static Connection conn = null;
    public static String driver = "com.mysql.jdbc.Driver";
    public static String nomeDB = "classifica";
    public static String username = "";
    public static String password = "";
    public static String porta = "";
    private static boolean isConnect = false;

    public static boolean connetti() throws SQLException
    {
        try
        {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://" + "localhost:" + porta + "/" + nomeDB, username, password);
            isConnect = true;
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println(ex);
            boolean isConnect = false;
        }
        return isConnect;
    }
}