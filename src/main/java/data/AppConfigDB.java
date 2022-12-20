/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import logic.classes.AppConfig;

/**
 *
 * @author Alex
 */
public class AppConfigDB {

    /**
     * Carga los datos de la base de datos al objeto
     *
     * @param con
     * @return
     * @throws SQLException
     */
    public static AppConfig carregarAppConfig(Connection con) throws SQLException {
        AppConfig appConfig = new AppConfig();

        Statement sentencia;
        sentencia = con.createStatement();
        // Comprovem si existeixen entrades a la taula
        checkTable(sentencia);
        
        // Carreguem les dates del mysql
        sentencia.executeQuery("SELECT * FROM appconfig");
        // Guardem les dades carregades a un fitxer amb el que treballarem
        ResultSet rs = sentencia.getResultSet();
        while (rs.next()) {
            
             //Cargamos los datos que hay en la base de datos y las asignamos a
             //un objeto ya que solo se creara un registro de appconfig a la
             //base de datos
            
            appConfig = new AppConfig(rs.getDouble("defaultCreditLimit"),
                    rs.getInt("defaultQuantityInStock"),
                    rs.getInt("defaultQuantityOrdered"),
                    rs.getInt("defaultProductBenefit"),
                    rs.getInt("minShippingHours"),
                    rs.getInt("minCustomerAge"),
                    rs.getInt("maxLinesPerOrder"),
                    rs.getDouble("maxOrderAmount"));
        }
        return appConfig;
    }
    
    /**
     * Comprueba si existen entradas en la tabla appconfig. Si no existen, no
     * se abrira el programa
     * 
     * @param conn
     * @throws SQLException
     * @author Aitor
     */
    private static void checkTable(Statement query) throws SQLException {
        // Crear sentencia
        query.executeQuery("SELECT * FROM appconfig");
        
        // Tancar l'aplicació si no existeixen entrades
        ResultSet rs = query.getResultSet();
        if (rs.next() == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setContentText("No existeixen entrades a la taula appconfig, tancant aplicació.");
            alert.showAndWait();
            
            Platform.exit();
        }
    }
}
