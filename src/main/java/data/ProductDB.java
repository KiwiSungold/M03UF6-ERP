package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import logic.classes.Product;

/**
 *
 * @author Aitor
 */
public class ProductDB {
    
    /**
     * Crea un ArrayList con todos los productos de la BBDD
     * 
     * @param conn
     * @return 
     * @throws SQLException 
     */
    public static ArrayList<Product> productsToList(Connection conn) throws SQLException {
        
        ArrayList<Product> productsList = new ArrayList<>();
        
        // Crear query
        Statement query = conn.createStatement();
        query.executeQuery("SELECT * FROM products");
        
        ResultSet rs = query.getResultSet();
        
        // Pasar todos los resultados al ArrayList i retornar
        while (rs.next()) {
            productsList.add(new Product(
                    rs.getInt("productCode"), rs.getString("productName"), 
                    rs.getString("productDescription"), rs.getInt("quantityInStock"), 
                    rs.getDouble("buyPrice")));
        }
        return productsList;
    }
    
    /**
     * Inserta un nuevo producto en la BBDD
     * 
     * @param conn
     * @param product
     * @throws SQLException 
     */
    public static void insertProduct(Connection conn, Product product) throws SQLException {
        // Preparamos el comando sql i cargamos los datos de la BBDD para trabajar con esta
        Statement query = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        query.executeQuery("SELECT * FROM products");
        ResultSet rs = query.getResultSet();

        // Nos movemos a la ultima posicion de la tabla e insertamos los nuevos datos
        rs.moveToInsertRow();
        rs.updateString("productName", product.getProductName());
        rs.updateString("productDescription", product.getProductDescription());
        rs.updateInt("quantityInStock", product.getQuantityInStock());
        rs.updateDouble("buyPrice", product.getBuyPrice());
        
        // Añadimos los datos a la tabla
        rs.insertRow();
    }
    
    /**
     * Modifica la entrada de la tabla seleccionada desde la observableList
     * 
     * @param conn
     * @param product
     * @throws SQLException 
     */
    public static void modifyProduct(Connection conn, Product product) throws SQLException {
        /**
         * Preparamos un comando sql que buscara el producto con la misma ID que
         * la obtenida desde la observableList
         */
        Statement query = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        query.executeQuery("SELECT * FROM products WHERE productCode = " + String.valueOf(product.getProductCode()));
        ResultSet rs = query.getResultSet();
        
        // Enviamos los nuevos valores a la BBDD
        if (rs.next()) {
            rs.updateString("productName", product.getProductName());
            rs.updateString("productDescription", product.getProductDescription());
            rs.updateInt("quantityInStock", product.getQuantityInStock());
            rs.updateDouble("buyPrice", product.getBuyPrice());
            
            rs.updateRow();
        }
    }
    
    /**
     * Elimina la entrada de la tabla correspondiente con la id proporcionada
     * 
     * @param conn
     * @param product
     * @throws SQLException 
     */
    public static void deleteProduct(Connection conn, Product product) throws SQLException {
        Statement query = conn.createStatement();
        
        String sqlStr = "DELETE FROM products WHERE productCode = " + String.valueOf(product.getProductCode());
        query.executeUpdate(sqlStr);
    }
    
    /**
     * Comprueba si existen entradas en la tabla products
     * 
     * @param conn
     * @return
     * @throws SQLException 
     */
    public static boolean checkTable(Connection conn) throws SQLException {
        // Crear sentencia
        Statement query = conn.createStatement();
        query.executeQuery("SELECT * FROM products");
        
        // Si hay productos, devuelve true. De lo contrario, false.
        ResultSet rs = query.getResultSet();
        return rs.next();
    }
}
