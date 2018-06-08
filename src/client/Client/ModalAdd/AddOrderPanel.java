package client.Client.ModalAdd;

import client.Client.TableModel;
import client.Model.Order.Order;
import client.Model.Order.OrderList;
import client.Model.User.User;
import client.facade.Facade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddOrderPanel extends JPanel {
    private Facade facade;
    private JTextField inputTable;
    private JTextField inputCount;
    private JLabel labelError;
    public AddOrderPanel(Facade facade, User user, ModalAddTab modalAddTab) {
        this.facade = facade;

        setLayout(new GridBagLayout());
        setBackground(Color.white);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5,3,5,3);

        JLabel labelTable = new JLabel("Стол: ");
        c.gridwidth = 1;
        c.weighty = 0.0;
        c.gridx = 0;
        c.gridy = 2;
        this.add(labelTable,c);

        inputTable = new JTextField();
        inputTable.setHorizontalAlignment(JTextField.CENTER);
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 2;
        this.add(inputTable,c);

        JLabel labelCount = new JLabel("Количество людей: ");
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        this.add(labelCount,c);

        inputCount = new JTextField();
        inputCount.setHorizontalAlignment(JTextField.CENTER);
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 3;
        this.add(inputCount,c);

        JButton addOrder = new JButton("Добавить");
        addOrder.addActionListener(new AddOrder(facade, user, modalAddTab));
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 4;
        this.add(addOrder,c);

        JButton cancelOrder = new JButton("Отмена");
        cancelOrder.addActionListener(new CancelAction(modalAddTab));
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 4;
        this.add(cancelOrder,c);

        labelError = new JLabel("", JLabel.CENTER);
        c.gridwidth = 0;
        c.gridx = 0;
        c.gridy = 6;
        this.add(labelError,c);
    }

    private class CancelAction implements ActionListener {
        JFrame modalAddTab;
        CancelAction(JFrame modalAddTab) {
            this.modalAddTab = modalAddTab;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                this.modalAddTab.dispose();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    private class AddOrder implements ActionListener {
        Facade facade;
        User user;
        JFrame modalAddTab;
        AddOrder(Facade facade, User user, JFrame modalAddTab) {
            this.facade = facade;
            this.user = user;
            this.modalAddTab = modalAddTab;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(!String.valueOf(inputTable.getText()).equals("") && !String.valueOf(inputCount.getText()).equals("")) {
                    int table = Integer.parseInt(String.valueOf(inputTable.getText()));
                    int count = Integer.parseInt(String.valueOf(inputCount.getText()));

                    ArrayList<Order> orders = null;
                    String ordersJSON = facade.getMessageManager().createOrder(OrderList.forCreateOrder(table, count, this.user.getId()));
                    if (ordersJSON != null || !ordersJSON.equals("")) {
                        orders = OrderList.parseUserOrders(ordersJSON);
                        //обновление таблицы
                        this.facade.getClient().setOrders(orders);
                        this.facade.setTableModel(new TableModel(orders));
                        this.facade.getOrderTable().setModel(this.facade.getTableModel());
                    }
                    this.modalAddTab.dispose();
                } else {
                    System.out.println("Error: Не заполнено(-ы) поле(-я)");
                    labelError.setText("Error: Не заполнено(-ы) поле(-я)");
                    labelError.setForeground (Color.red);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
