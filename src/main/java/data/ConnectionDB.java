package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Kiwi
 */
public class ConnectionDB {

    /* Empleamos el patrón singleton para tener solo una conexión a bbdd y no estar creando muchas conexiones.
    Así aseguramos que existe SOLO una instancia de una clase. Y además disponemos de una accesibilidad global, es decir,
    tenemos acceso desde cualquier parte de nuestro programa.
    1 - Para ello hacemos private el constructor de ConnectionDB
    2 - Añadimos un atributo static de la propia ConnectionDB
    3 - Añadimos un método estático público que nos devolverá la ConnectionDB. Si no hay connectionDB creada la creara, 
    en caso contario, nos devolverá la que ya estaba creada.*/
    private static ConnectionDB connectionDB;

    private final Connection conn;

    /**
     *
     * @throws SQLException
     */
    private ConnectionDB() throws SQLException {

        String db = "m03uf6_22_23";
        String user = "root";
        String password = "211121";
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db + "?useUnicode=true&"
                + "useJDBCCompliantTimezoneShift=true&"
                + "useLegacyDatetimeCode=false&serverTimezone=UTC", user, password);
    }

    /**
     *
     * @return @throws SQLException
     */
    public static ConnectionDB getInstance() throws SQLException {
        if (connectionDB != null) {
            return connectionDB;
        } else {
            connectionDB = new ConnectionDB();
            return connectionDB;
        }
    }

    /**
     *
     * @return
     */
    public Connection getConnection() {
        return conn;
    }
}
