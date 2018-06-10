package client.Client;

import client.Client.ModalAdd.ModalAddTab;
import client.Model.Order.Order;
import client.Model.User.User;
import client.facade.Facade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ClientPanel extends JPanel {
    private Facade facade;
    private User user;
    private TableModel tm;
    private Client client;
    private JLabel statusbar;
    private JPanel panel;
    private OrderTable table;

    public ClientPanel(Facade facade, User user) {
        this.facade = facade;
        this.user = user;

        setLayout(new BorderLayout());
        setBackground(Color.white);

        panel = new JPanel();
        add(panel,BorderLayout.SOUTH);
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);

        initClient();
        ArrayList<Order> userOrders = this.user.getType().equals("admin") ? this.client.getOrders() : this.client.getNotCloseOrders();
        tm = new TableModel(userOrders);
        table = new OrderTable(tm, facade);
        this.facade.setTableModel(tm);
        this.facade.setOrderTable(table);

        JScrollPane scr = new JScrollPane(table);
        add(scr, BorderLayout.CENTER);
    }

    private void initClient() {
        try {
            System.out.println("User: " + this.user.getLogin() + " created!" );
            this.client = new Client(this.facade, this.user, this);
            this.facade.setClient(this.client);

            statusbar = new JLabel("Пользователь: " + this.user.getName()+  "  Общая касса: " + this.client.getTotalSum()  + "  Сумма не закрытых счетов: " + this.client.getNotTotalSum());
            statusbar.setFont(new Font(statusbar.getName(), Font.BOLD, 14));
            panel.add(statusbar, BorderLayout.SOUTH);
            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new FlowLayout());
            panel.add(buttonsPanel, BorderLayout.CENTER);

            Button addOrder = new Button("Добавить заказ");
            addOrder.setFont(new Font(this.getName(), Font.BOLD, 20));
            addOrder.setBackground(Color.white);
            addOrder.setSize(600,32);
            addOrder.addActionListener(new addTable());
            buttonsPanel.add(addOrder);

            if(client.getUser().getType().equals("admin")) {
                Button closeChange = new Button("Закрыть смену");
                closeChange.setFont(new Font(this.getName(), Font.BOLD, 20));
                closeChange.setBackground(Color.white);
                closeChange.setSize(600,32);
                closeChange.addActionListener(new addTable());
                buttonsPanel.add(closeChange);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateStatusbar() {
        panel.remove(statusbar);
        statusbar = new JLabel("Пользователь: " + this.user.getName()+  "  Общая касса: " + this.client.getTotalSum()  + "  Сумма не закрытых счетов: " + this.client.getNotTotalSum());
        statusbar.setFont(new Font(statusbar.getName(), Font.BOLD, 14));
        panel.add(statusbar, BorderLayout.SOUTH);
        this.updateUI();
    }

    private class addTable implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            try {
                new ModalAddTab(facade, user);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
