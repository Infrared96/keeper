package client.Client.OpenOrder;

import client.Model.Order.Order;
import client.facade.Facade;

import javax.swing.*;

public class OpenOrderFrame extends JFrame {
    public OpenOrderFrame(Facade facade, Order or) {
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setBounds(200,200,1200,700);
        this.add(new OpenOrderPanel(facade, or));
        this.setVisible(true);
    }
}
