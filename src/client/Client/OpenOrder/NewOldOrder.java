package client.Client.OpenOrder;

import client.Model.NewOrder.NewOrder;
import client.Model.NewOrder.NewOrderList;
import client.Model.Order.Order;
import client.facade.Facade;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class NewOldOrder extends JPanel {
    //private TableModelNewOrder tmno;
    //private TableModelOldOrder tmoo;
    private NewOrderTable not;
    private NewOrderTable oot;
    private Facade facade;
    private ArrayList<NewOrder> arrayOrders;

    public ArrayList<NewOrder> getArrayOrders() {
        return arrayOrders;
    }

    public void setArrayOrders(ArrayList<NewOrder> arrayOrders) {
        this.arrayOrders = arrayOrders;
    }

    private Order or;
    public NewOldOrder(Facade facade, Order or) {
        this.facade = facade;
        this.or = or;
        setLayout(new GridLayout(2,1));

        TableModelNewOrder tableNew = new TableModelNewOrder(this.arrayOrders, this.facade);
        facade.setTableModelNewOrder(tableNew);
        not = new NewOrderTable(tableNew);
        facade.setNewOrderTable(not);
        JScrollPane scrollPane = new JScrollPane(not);
        add(scrollPane);

        ArrayList<NewOrder> oldOrders = NewOrderList.parseNewOrders(facade.getMessageManager().getNewOrdersId("{\"order_id\":" + or.getId() + "}"));
        oot = new NewOrderTable(new TableModelNewOrder(oldOrders, this.facade));
        JScrollPane oldScrollPane = new JScrollPane(oot);
        add(oldScrollPane);

    }
}
