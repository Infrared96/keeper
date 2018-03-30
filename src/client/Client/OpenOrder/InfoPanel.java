package client.Client.OpenOrder;

import client.Client.Client;
import client.Model.Order.Order;
import client.facade.Facade;

import javax.swing.*;
import java.awt.*;


public class InfoPanel extends JPanel {
    private Client client;
    private Order or;
    private Facade facade;

    public InfoPanel(Facade facade, Order or) {
        this.facade = facade;
        this.client = facade.getClient();
        this.or = or;

        setLayout(new GridLayout(0,1));

        initPanel();
    }

    private void initPanel() {
       JLabel name = new JLabel("Имя: " + this.client.getUser().getName());
       JLabel type = new JLabel("Должность: " + this.client.getUser().getType());
       JLabel table = new JLabel("Стол: " + this.or.getTable_num());

       this.add(name);
       this.add(type);
       this.add(table);
    }

}
