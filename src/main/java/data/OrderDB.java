package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import logic.classes.Customer;
import logic.classes.Order;

/**
 *
 * @author Kiwi
 */
public class OrderDB {

    OrderDetailsDB orderDetailsDB = new OrderDetailsDB();

    /**
     * Retorna el contenido de la tabla en una colección de "Orders"
     *
     * @param conn
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList<Order> ordersToList(Connection conn) throws SQLException {

        ArrayList<Order> ordersList = new ArrayList<>();
        Customer customer;

        Statement query;
        query = conn.createStatement();
        query.executeQuery("SELECT * FROM orders");

        ResultSet rs = query.getResultSet();
        while (rs.next()) {
            customer = getCustomer(conn, rs.getString("customers_customerEmail"));
            ordersList.add(new Order(rs.getInt("orderNumber"), rs.getTimestamp("orderDate"), rs.getTimestamp("requiredDate"), rs.getTimestamp("shippedDate"), customer, orderDetailsDB.orderDetailsToList(conn, rs.getInt("orderNumber"))));
        }
        return ordersList;
    }

    /**
     * Retorna el contenido de la tabla en una colección de "Orders" filtrado
     * por fecha.
     *
     * @param conn
     * @param p_fecha_Desde
     * @param p_fecha_Hasta
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList<Order> ordersToListFiltered(Connection conn, Timestamp p_fecha_Desde, Timestamp p_fecha_Hasta) throws SQLException {

        ArrayList<Order> ordersListFiltered = new ArrayList<>();
        Customer customer;

        Statement query;
        query = conn.createStatement();
        query.executeQuery("SELECT * FROM orders WHERE requiredDate BETWEEN " + "'" + p_fecha_Desde + "' AND '" + p_fecha_Hasta + "'");

        ResultSet rs = query.getResultSet();
        while (rs.next()) {
            customer = getCustomer(conn, rs.getString("customers_customerEmail"));
            ordersListFiltered.add(new Order(rs.getInt("orderNumber"), rs.getTimestamp("orderDate"), rs.getTimestamp("requiredDate"), rs.getTimestamp("shippedDate"), customer, orderDetailsDB.orderDetailsToList(conn, rs.getInt("orderNumber"))));
        }
        return ordersListFiltered;
    }

    /**
     * Inserta un nuevo pedido y retorna su orderNumber
     *
     * @param conn
     * @param order
     * @return
     * @throws SQLException
     */
    public static int insertOrder(Connection conn, Order order) throws SQLException {

        Statement query;
        query = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, Statement.RETURN_GENERATED_KEYS);
        query.executeQuery("SELECT * FROM orders");

        ResultSet rs = query.getResultSet();

        rs.moveToInsertRow();

        rs.updateTimestamp("orderDate", order.getOrderDate());
        rs.updateTimestamp("requiredDate", order.getRequiredDate());
        rs.updateTimestamp("shippedDate", order.getShippedDate());
        rs.updateString("customers_customerEmail", order.getCustomer().getCustomerEmail());

        rs.insertRow();

        ResultSet generatedKeys = query.getGeneratedKeys();
        int orderNumber = 0;

        if (generatedKeys.next()) {
            orderNumber = generatedKeys.getInt("orderNumber");
        }
        return orderNumber;
    }

    /**
     * Retorna un customer cuyo customerEmail coincida con el pasado por
     * parámetro.
     *
     * @param conn
     * @param customerEmail
     * @return
     * @throws SQLException
     */
    private static Customer getCustomer(Connection conn, String customerEmail) throws SQLException {
        Customer customer = null;

        Statement query;
        query = conn.createStatement();
        query.executeQuery("SELECT * FROM customers WHERE customerEmail = '" + customerEmail + "'");

        ResultSet rs = query.getResultSet();

        if (rs.next()) {
            customer = new Customer(rs.getString("customerEmail"), rs.getString("idCard"), rs.getString("customerName"), rs.getString("phone"), rs.getDouble("creditLimit"), rs.getString("birthDate"));
        }
        return customer;
    }

    /**
     * Actualiza un nuevo pedido
     *
     * @param conn
     * @param order
     * @throws SQLException
     */
    public static void updateOrder(Connection conn, Order order) throws SQLException {

        Statement query;
        query = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        query.executeQuery("SELECT * FROM orders WHERE orderNumber = " + order.getOrderNumber());

        ResultSet rs = query.getResultSet();

        if (rs.next()) {
            rs.updateTimestamp("orderDate", order.getOrderDate());
            rs.updateTimestamp("requiredDate", order.getRequiredDate());
            rs.updateTimestamp("shippedDate", order.getShippedDate());

            rs.updateRow();
        }
    }

    /**
     * Elimina el registro Order recibido como parámetro de la BBDD.
     *
     * @param conn
     * @param order
     * @throws SQLException
     */
    public static void deleteOrder(Connection conn, Order order) throws SQLException {

        Statement query;
        query = conn.createStatement();

        String sqlStr = "DELETE FROM orders WHERE orderNumber = " + order.getOrderNumber();

        query.executeUpdate(sqlStr);
    }
}
