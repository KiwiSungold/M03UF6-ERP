package logic;

import data.ConnectionDB;
import data.OrderDetailsDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.classes.OrderDetails;

/**
 *
 * @author Ztudo
 */
public class OrderDetailsLogic {

    Connection conn;
    ObservableList<OrderDetails> orderDetailsOList;
    OrderDetailsDB orderDetailsDB;

    public OrderDetailsLogic() throws SQLException {
        // inicialitzem connexió amb BD pero passant per la capa d'aplicació
        conn = ConnectionDB.getInstance().getConnection();

        // inicialitzem col.lecció
        orderDetailsOList = FXCollections.<OrderDetails>observableArrayList();

        orderDetailsDB = new OrderDetailsDB();
    }

    /**
     * Rellena los registros de la tabla con los productos del nº de pedido.
     *
     * @param orderNum
     * @throws SQLException
     */
    public void setData(int orderNum) throws SQLException {

        this.orderDetailsOList.setAll(orderDetailsDB.orderDetailsToList(conn, orderNum));

    }

    /**
     * Obtiene la lista observable
     *
     * @return
     */
    public ObservableList<OrderDetails> getOrderDetailsObservableList() {
        return orderDetailsOList;
    }

    /**
     * Añade un producto en el pedido.
     *
     * @param detail
     * @throws SQLException
     */
    public void insertOrderDetail(OrderDetails detail) throws SQLException {

        detail.setOrderLineNumber(OrderDetailsDB.insertOrderDetail(conn, detail));

        orderDetailsOList.add(detail);
    }

    /**
     * Añade todos los productos en el pedido.
     *
     * @param details
     * @throws SQLException
     */
    public void insertAllOrderDetails(ArrayList<OrderDetails> details) throws SQLException {

        OrderDetailsDB.insertAllOrderDetails(conn, details);

        //orderDetailsOList.add(details);
    }

    /**
     * Actualiza un registro de la BBDD.
     *
     * @param detail
     * @throws SQLException
     */
    public void updateOrderDetail(OrderDetails detail) throws SQLException {

        OrderDetailsDB.updateOrderDetail(conn, detail);

    }

    /**
     * Elimina un registro de la BBDD
     *
     * @param detail
     * @throws SQLException
     */
    public void deleteOrderDetail(OrderDetails detail) throws SQLException {

        OrderDetailsDB.deleteOrderDetail(conn, detail);

        orderDetailsOList.remove(detail);
    }

    /**
     * Elimina todos los registros de la BBDD.
     *
     * @param orderId
     * @throws SQLException
     */
    public void deleteAllOrderDetail(int orderId) throws SQLException {

        OrderDetailsDB.deleteAllOrderDetail(conn, orderId);

    }

}
