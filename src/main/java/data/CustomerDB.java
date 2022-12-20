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
import logic.classes.Customer;

/**
 *
 * @author Alex
 */
public class CustomerDB {

    /**
     * Carga los objetos de tipo Customer desde una base de datos MySQL.
     *
     * @param con La conexión a la base de datos MySQL.
     * @return Una lista de objetos Customer cargados desde la base de datos.
     * @throws SQLException Si ocurre algún error al acceder a la base de datos.
     */
    public static ArrayList<Customer> carregarCustomer(Connection con) throws SQLException {
        ArrayList<Customer> ret = new ArrayList<>();

        Statement sentencia;

        sentencia = con.createStatement();

        sentencia.executeQuery("SELECT * FROM customers");

        ResultSet rs = sentencia.getResultSet();
        while (rs.next()) {
            ret.add(new Customer(rs.getString("customerEmail"), rs.getString("idCard"),
                    rs.getString("customerName"), rs.getString("phone"), rs.getInt("creditLimit"), rs.getString("birthDate")));
        }
        return ret;
    }

    /**
     * Inserta un nuevo objeto de tipo Customer en una base de datos MySQL.
     *
     * @param con La conexión a la base de datos MySQL.
     * @param customer El objeto Customer a insertar en la base de datos.
     * @throws SQLException Si ocurre algún error al acceder a la base de datos.
     */
    public static void insereixNouCustomer(Connection con, Customer customer) throws SQLException {

        Statement sentencia;
        sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        sentencia.executeQuery("SELECT * FROM customers");
        ResultSet rs = sentencia.getResultSet();
        rs.moveToInsertRow();

        rs.updateString("customerEmail", customer.getCustomerEmail());
        rs.updateString("idCard", customer.getIdCard());
        rs.updateString("customerName", customer.getCustomerName());
        rs.updateString("phone", customer.getPhoneNumber());
        rs.updateDouble("creditLimit", customer.getCreditLimit());
        rs.updateString("birthDate", customer.getBirthDate());

        rs.insertRow();

    }

    /**
     * Modifica un objeto de tipo Customer en una base de datos MySQL.
     *
     * @param con La conexión a la base de datos MySQL.
     * @param customer El objeto Customer a modificar en la base de datos.
     * @throws SQLException Si ocurre algún error al acceder a la base de datos.
     */
    public static void modificaCustomer(Connection con, Customer customer) throws SQLException {
        Statement sentencia;

        sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        sentencia.executeQuery("SELECT * FROM customers WHERE customerEmail = " + "'" + customer.getCustomerEmail() + "'");
        ResultSet rs = sentencia.getResultSet();

        if (rs.next()) {
            rs.updateString("customerEmail", customer.getCustomerEmail());
            rs.updateString("idCard", customer.getIdCard());
            rs.updateString("customerName", customer.getCustomerName());
            rs.updateString("phone", customer.getPhoneNumber());
            rs.updateDouble("creditLimit", customer.getCreditLimit());
            rs.updateString("birthDate", customer.getBirthDate());

            rs.updateRow();
        }
    }

    /**
     * Elimina un objeto de tipo Customer de una base de datos MySQL.
     *
     * @param con La conexión a la base de datos MySQL.
     * @param customer El objeto Customer a eliminar de la base de datos.
     * @throws SQLException Si ocurre algún error al acceder a la base de datos.
     */
    public static void eliminaCustomer(Connection con, Customer customer) throws SQLException {
        Statement sentencia;

        sentencia = con.createStatement();
        String sqlStr = "DELETE FROM customers WHERE customerEmail = " + "'" + customer.getCustomerEmail() + "'";
        sentencia.executeUpdate(sqlStr);
    }
    
    /**
     * Comprueba si existen entradas en la tabla customers
     * 
     * @param conn
     * @return
     * @throws SQLException 
     * @author Aitor
     */
    public static boolean checkTable(Connection conn) throws SQLException {
        // Crear sentencia
        Statement query = conn.createStatement();
        query.executeQuery("SELECT * FROM customers");
        
        // Si hay clientes, devuelve true. De lo contrario, false.
        ResultSet rs = query.getResultSet();
        return rs.next();
    }
}
