package client.Client.OpenOrder;

import client.Model.Order.Order;
import client.facade.Facade;

import javax.swing.*;
import java.awt.*;

public class ActionNewOrderPanel extends JPanel {
    private Facade facade;
    private Order or;
    private JButton print;
    private JButton payCard;
    private JButton pay;
    private JLabel summ;

    public ActionNewOrderPanel(Facade facade, Order or) {
        this.facade = facade;
        this.or = or;
        setLayout(new GridLayout(0,1));
        initActPanal();
    }

    private void initActPanal() {
        print = new JButton("Печатать чек");
        payCard = new JButton("Оплатить картой");
        pay = new JButton("Оплатить наличными");
        summ = new JLabel(this.facade.updateOrderPrice(or.getPrice()));

        add(print);
        add(payCard);
        add(pay);
        add(summ);
    }

    public JLabel getSumm() {
        return summ;
    }

    public void updateSumm() {
        this.removeAll();
        initActPanal();
        this.updateUI();
    }
}
