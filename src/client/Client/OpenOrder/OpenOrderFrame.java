package client.Client.OpenOrder;

import client.Client.TableModel;
import client.Model.Order.Order;
import client.Model.Order.OrderList;
import client.Model.User.User;
import client.facade.Facade;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class OpenOrderFrame extends JFrame {
    Facade facade;
    public OpenOrderFrame(Facade facade, Order or) {
        this.facade = facade;
        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new CloseWindow());
        this.setBounds(200,200,1200,700);
        this.add(new OpenOrderPanel(facade, or, this));
        this.setVisible(true);
    }

    private class CloseWindow implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {
        }

        //обновление окна с заказами
        @Override
        public void windowClosing(WindowEvent e) {
            User user = facade.getClient().getUser();
            ArrayList<Order> orders = OrderList.parseUserOrders(facade.getMessageManager().getOrders(user.getType(), user.getId()));
            facade.getClient().setOrders(orders);
            facade.setTableModel(new TableModel(orders));
            facade.getOrderTable().setModel(facade.getTableModel());
            facade.getClient().getClientPanel().updateStatusbar();
            e.getWindow().setVisible(false);
        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    }
}

