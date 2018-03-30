package client.Client.OpenOrder;

import client.facade.Facade;

import javax.swing.*;
import java.awt.*;

public class ActionNewOrderPanel extends JPanel {
    private Facade facade;
    private JButton print;
    private JButton payCard;
    private JButton pay;
    private JLabel summ;

    public ActionNewOrderPanel(Facade facade) {
        this.facade = facade;
        setLayout(new GridLayout(0,1));
        initActPanal();
    }

    private void initActPanal() {
        print = new JButton("Печатать чек");
        payCard = new JButton("Оплатить картой");
        pay = new JButton("Оплатить наличными");
        summ = new JLabel("Сумма: " + facade.getClient().getTotalSum());

        add(print);
        add(payCard);
        add(pay);
        add(summ);
    }
}
