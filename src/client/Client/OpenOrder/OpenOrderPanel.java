package client.Client.OpenOrder;

import client.Model.Order.Order;
import client.facade.Facade;

import javax.swing.*;
import java.awt.*;

public class OpenOrderPanel extends JPanel {
    private Facade facade;
    private Order or;
    private JFrame frame;

    public OpenOrderPanel(Facade facade, Order or, JFrame frame) {
        this.facade = facade;
        this.or = or;
        this.frame = frame;

        setLayout(new BorderLayout());
        setBackground(Color.white);
        initOpenOrder();
    }

    private void initOpenOrder() {
        ControlPanel controlPanel = new ControlPanel(this.facade, this.or, this.frame);
        this.add(controlPanel, BorderLayout.EAST);
        this.add(new BigOrderPanel(this.facade, this.or),BorderLayout.CENTER);
        this.add(new NewOldOrder(this.facade, this.or, controlPanel), BorderLayout.WEST);
    }
}
