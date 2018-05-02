package client.Client.OpenOrder;

import client.Model.NewOrder.NewOrder;
import client.Model.NewOrder.NewOrderList;
import client.Model.Order.Order;
import client.Model.Order.OrderList;
import client.facade.Facade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NewOldOrder extends JPanel {
    private NewOrderTable not;
    private NewOrderTable oot;
    private Facade facade;
    private ArrayList<NewOrder> arrayOrders;
    private ControlPanel controlPanel;

//    public ArrayList<NewOrder> getArrayOrders() {
//        return arrayOrders;
//    }
//
//    public void setArrayOrders(ArrayList<NewOrder> arrayOrders) {
//        this.arrayOrders = arrayOrders;
//    }

    private Order or;
    public NewOldOrder(Facade facade, Order or, ControlPanel controlPanel) {
        this.facade = facade;
        this.or = or;
        this.controlPanel = controlPanel;
        setLayout(new GridLayout(2,1));

        TableModelNewOrder tableNew = new TableModelNewOrder(this.arrayOrders, this.facade);
        not = new NewOrderTable(tableNew);
        facade.setTableModelNewOrder(tableNew);
        facade.setNewOrderTable(not);
        JScrollPane scrollPane = new JScrollPane(not);
/////////////////////////////////////////////////////////////////////
        ////PANEL WITH BUTTONS TABLE///////////////
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BorderLayout());
        newPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new GridLayout(1,2,10, 10));
        JButton bAdd = new JButton("Добавить");
        JButton bDel = new JButton("Удалить");

        buttons.add(bAdd);
        buttons.add(bDel);
        newPanel.add(buttons, BorderLayout.SOUTH);
        add(newPanel);
/////////////////////////////////////////////////////
        ArrayList<NewOrder> oldOrders = NewOrderList.parseNewOrders(facade.getMessageManager().getNewOrdersId("{\"order_id\":" + or.getId() + "}"));
        oot = new NewOrderTable(new TableModelNewOrder(oldOrders, this.facade));
        JScrollPane oldScrollPane = new JScrollPane(oot);
        add(oldScrollPane);

        bAdd.addActionListener(new addNewOrders(oot));
    }

    private class addNewOrders implements ActionListener {
        NewOrderTable oot;
        addNewOrders( NewOrderTable oot) {
            this.oot = oot;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            arrayOrders = facade.getActualOrder();
            facade.setActualOrder(new ArrayList<>());
            facade.setTableModelNewOrder(new TableModelNewOrder(facade.getActualOrder(), facade));
            facade.setModel_NewOrderTable(facade.getTableModelNewOrder());
            //ArrayList<NewOrder> oldOrders = NewOrderList.parseNewOrders(facade.getMessageManager().getNewOrdersId("{\"order_id\":" + or.getId() + "}"));

            facade.getMessageManager().updateNewOrders(NewOrderList.parseString(arrayOrders));
            ArrayList<NewOrder> oldOrders = NewOrderList.parseNewOrders(facade.getMessageManager().getNewOrdersId("{\"order_id\":" + or.getId() + "}"));
            oot.setModel(new TableModelNewOrder(oldOrders, facade));
            or.setPrice(facade, NewOrderList.sumPriceOrder(oldOrders));
            controlPanel.getActionNewOrderPanel().updateSumm();
            //this.facade.getMessageManager().updateOrderPrice("{\"order_id\":" + or.getId() + ",\"price\":" + NewOrderList.sumPriceOrder(oldOrders) + "}")
        }
    }
}
