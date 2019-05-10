package connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Marine V
 */
public class SQLConnection {

    // objet de type connection permettant la connection à la base de donnée
    private static Connection connection = null;

    private SQLConnection() {
    }

    ;

    public static Connection getInstance() {
        if (connection == null) {
            Properties config = new Properties();
            try (InputStream in = SQLConnection.class.getResourceAsStream("/ressource/config.properties")) {
                config.load(in);
            } catch (IOException ex) {
                Logger.getLogger(SQLConnection.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(1);
            }

            String url = "jdbc:" + config.getProperty("sgbdr")
                    + "://" + config.getProperty("host")
                    + ":" + config.getProperty("port")
                    + "/" + config.getProperty("database");

            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url, config);
            } catch (SQLException ex) {
                Logger.getLogger(SQLConnection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SQLConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(connection);
        return connection;
    }
}
