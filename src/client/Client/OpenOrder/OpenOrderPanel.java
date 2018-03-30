package client.Client.OpenOrder;

import client.Model.Order.Order;
import client.facade.Facade;

import javax.swing.*;
import java.awt.*;

public class OpenOrderPanel extends JPanel {
    private Facade facade;
    private Order or;

    public OpenOrderPanel(Facade facade, Order or) {
        this.facade = facade;
        this.or = or;

        setLayout(new BorderLayout());
        setBackground(Color.white);
        initOpenOrder();
    }

    private void initOpenOrder() {
        this.add(new ControlPanel(this.facade, this.or), BorderLayout.EAST);
        this.add(new BigOrderPanel(this.facade, this.or),BorderLayout.CENTER);
        this.add(new NewOldOrder(this.facade, this.or), BorderLayout.WEST);
       // new ControlPanel();
//        new CategoryPanel();
//        new ListDish();
//        new OrderPanel();
    }
}
