package client.Client;

import client.Client.ModalAdd.ModalAddTab;
import client.Model.User.User;
import client.facade.Facade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientPanel extends JPanel {
    private Facade facade;
    private User user;
    private TableModel tm;
    private Client client;
    JLabel statusbar;
    private OrderTable table;

    public ClientPanel(Facade facade, User user) {
        this.facade = facade;
        this.user = user;

        setLayout(new BorderLayout());
        setBackground(Color.white);

        initClient();

        tm = new TableModel(this.client.getOrders());
        table = new OrderTable(tm, facade);
        this.facade.setTableModel(tm);
        this.facade.setOrderTable(table);

        JScrollPane scr = new JScrollPane(table);
        add(scr, BorderLayout.NORTH);

        Button addOrder = new Button("Добавить");
        addOrder.setFont(new Font(this.getName(), Font.BOLD, 20));
        addOrder.setBackground(Color.white);
        addOrder.setSize(1200,32);
        addOrder.addActionListener(new addTable(this.facade,this.user));
        add(addOrder);
    }

    private void initClient() {
        try {
            System.out.println("User: " + this.user.getLogin() + " created!" );
            this.client = new Client(this.facade, this.user);
            this.facade.setClient(this.client);

            statusbar = new JLabel("Пользователь: " + this.user.getName()+  "Общая касса :"  + "  Сумма не закрытых счетов: " + this.client.getTotalSum());
            statusbar.setFont(new Font(statusbar.getName(), Font.BOLD, 14));
            this.add(statusbar, BorderLayout.SOUTH);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class addTable implements ActionListener {
        Facade facade;
        User user;
        addTable(Facade facade, User user) {
            this.facade = facade;
            this.user = user;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                new ModalAddTab(this.facade, this.user);
            }
            catch (Exception e1)
            {
                e1.printStackTrace();
            }
        }
    }
}
