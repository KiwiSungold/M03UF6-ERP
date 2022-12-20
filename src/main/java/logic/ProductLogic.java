package logic;

import data.ConnectionDB;
import data.ProductDB;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.classes.Product;

/**
 *
 * @author Aitor
 */
public class ProductLogic {
    
    Connection conn;
    ObservableList<Product> productsOList;
    
    /**
     * Inicializar conexion i coleccion
     * 
     * @throws SQLException 
     */
    public ProductLogic() throws SQLException {
        // Inicialitzar connexió
        conn = ConnectionDB.getInstance().getConnection();
        //Inicialitzar col·lecció
        productsOList = FXCollections.<Product>observableArrayList();
    }
    
    /**
     * Pasar los contenidos de la tabla products a la observableList
     * 
     * @throws SQLException 
     */
    public void setData() throws SQLException {
        this.productsOList.setAll(ProductDB.productsToList(conn));
    }
    
    /**
     * Obtener la observableList
     * 
     * @return 
     */
    public ObservableList<Product> getProductObservableList() {
        return productsOList;
    }
    
    /**
     * Envia un nuevo producto a la capa BBDD
     * 
     * @param product
     * @throws SQLException 
     */
    public void addProduct(Product product) throws SQLException {
        ProductDB.insertProduct(conn, product);
    }
    
    /**
     * Envia a la capa BBDD la entrada a eliminar i tambien elimina esta de la
     * observableList
     * 
     * @param product
     * @throws SQLException 
     */
    public void removeProduct(Product product) throws SQLException {
        ProductDB.deleteProduct(conn, product);
        productsOList.remove(product);
    }
    
    /**
     * Envia a la capa BBDD el producto modificado
     * 
     * @param product
     * @throws SQLException 
     */
    public void editProduct(Product product) throws SQLException {
        ProductDB.modifyProduct(conn, product);
    }
    
    /**
     * Verifica que el nombre i la descripcion del producto no sean nulos
     * 
     * @param product
     * @throws Exception 
     */
    public void checkProductEmptyFields(Product product) throws Exception {
        if (product.getProductName().equals("")) {
            throw new Exception("El nombre no puede estar en blanco.");
        } else if (product.getProductDescription().equals("")) {
            throw new Exception("La descripción no puede estar en blanco.");
        }
    }
    
    /**
     * Llama al metodo de la capa BD para comprobar si existen entradas en la 
     * tabla products i devuelve el resultado
     * 
     * @return
     * @throws SQLException 
     */
    public boolean productExists() throws SQLException {
        return ProductDB.checkTable(conn);
    }
    
    /**
     * Cierra la conexion con la BBDD
     * 
     * @throws SQLException 
     */
    public void closeConn() throws SQLException {
        this.conn.close();
    }
}
