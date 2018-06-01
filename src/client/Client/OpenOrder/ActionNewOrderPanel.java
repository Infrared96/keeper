package client.Client.OpenOrder;

import client.Model.Order.Order;
import client.facade.Facade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class ActionNewOrderPanel extends JPanel {
    private Facade facade;
    private Order or;
    private JButton print;
    private JButton payCard;
    private JButton pay;
    private JLabel summ;
    private JFrame frame;

    public ActionNewOrderPanel(Facade facade, Order or, JFrame frame) {
        this.facade = facade;
        this.or = or;
        this.frame = frame;
        setLayout(new GridLayout(0,1));
        initActPanal();
    }

    private void initActPanal() {
        print = new JButton("Печатать чек");
        payCard = new JButton("Оплатить картой");
        pay = new JButton("Оплатить наличными");
        summ = new JLabel("Сумма: " + or.getPrice());

        print.addActionListener(new printCheck());
        pay.addActionListener(new payCash());

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

    private class payCash implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(or.isPrint()) {
                or.closeOrder(facade, true);
                WindowEvent winClosingEvent = new WindowEvent( frame, WindowEvent.WINDOW_CLOSING );
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent( winClosingEvent );
            }
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
