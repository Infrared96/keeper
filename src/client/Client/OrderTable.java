package client.Client;

import client.Client.OpenOrder.OpenOrderFrame;
import client.Model.Order.Order;
import client.Model.Order.OrderList;
import client.facade.Facade;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class OrderTable extends JTable{
    private Facade facade;
    private ArrayList<Order> orders;

    public OrderTable(TableModel dm, Facade facade) {
        super(dm);
        this.facade = facade;
        this.orders = facade.getClient().getOrders();

        this.getTableHeader().setPreferredSize(new Dimension(0, 32));
        this.rowHeight = 32;

        this.getTableHeader().setFont(new Font(this.getName(), Font.BOLD, 20));
        this.setFont(new Font(this.getName(), Font.PLAIN, 20));

        //Выравнивание по центру текста
        this.setDefaultRenderer(this.getColumnClass(1), new DefaultTableCellRenderer(){
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.setHorizontalAlignment(SwingConstants.CENTER);
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if(facade.getClient().getUser().getType().equals("admin")) {
                    if(orders.get(row).isPrint() && orders.get(row).isClose()) {
                        this.setBackground(new Color(12, 12, 12, 57));
                    } else if(orders.get(row).isPrint()) {
                        this.setBackground(new Color(22, 177, 11, 57));
                    } else {
                        this.setBackground(Color.white);
                    }
                }
                return this;
            }});

        this.addMouseListener(new OpenNewOrder(this));
    }
//    class MyTableCellRenderer extends DefaultTableCellRenderer {
//        TableModel dm;
//        public MyTableCellRenderer(TableModel dm) {
//            this.dm = dm;
//        }
//        @Override
//        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//            c.setBackground(dm.getRowColour(row));
//            return c;
//        }
//    }

    private class OpenNewOrder extends MouseAdapter {
        JTable table;

        public OpenNewOrder(JTable table) {
            this.table = table;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            orders = facade.getClient().getOrders();
           System.out.println( "click = " + e.getClickCount() + "mouse: " +  e.getModifiersEx() );

            if(e.getClickCount() == 2 && e.getModifiersEx() == 0) {
                int row = table.rowAtPoint(e.getPoint());
                int id = Integer.parseInt(String.valueOf(table.getValueAt(row,0)));
                int orderId = orders.get(id-1).getId();
                Order or = OrderList.parseOneOrder(facade.getMessageManager().getOrderId("{\"id\":" + orderId + "}"));
                new OpenOrderFrame(facade, or);
            }
        }
    }
}
