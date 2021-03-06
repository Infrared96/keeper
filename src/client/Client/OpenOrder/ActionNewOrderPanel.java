package client.Client.OpenOrder;

import client.Model.Order.Order;
import client.facade.Facade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        summ = new JLabel("Сумма: " + or.getPrice());

        print.addActionListener(new printCheck());

        add(print);
        add(payCard);
        add(pay);
        add(summ);

        if(or.isPrint()) {
            print.setEnabled(false);
        }
    }

    private class printCheck implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            or.setPrint(facade, true);
            print.setEnabled(false);
        }
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
