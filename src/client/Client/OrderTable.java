package client.Client;

import client.Client.OpenOrder.OpenOrderFrame;
import client.Model.Order.Order;
import client.Model.Order.OrderList;
import client.facade.Facade;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderTable extends JTable{
    Facade facade;
    public OrderTable(TableModel dm, Facade facade) {
        super(dm);
        this.facade = facade;

        this.getTableHeader().setPreferredSize(new Dimension(0, 32));
        this.rowHeight = 32;

        this.getTableHeader().setFont(new Font(this.getName(), Font.BOLD, 20));
        this.setFont(new Font(this.getName(), Font.PLAIN, 20));

        //Выравнивание по центру текста
        this.setDefaultRenderer(this.getColumnClass(1), new DefaultTableCellRenderer(){
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.setHorizontalAlignment(SwingConstants.CENTER);
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                return this;
            }

        });

        this.addMouseListener(new OpenNewOrder(this));
    }

    private class OpenNewOrder extends MouseAdapter {
        JTable table;

        public OpenNewOrder(JTable table) {
            this.table = table;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
           System.out.println( "click = " + e.getClickCount() + "mouse: " +  e.getModifiersEx() );

            if(e.getClickCount() == 2 && e.getModifiersEx() == 0) {
                int row = table.rowAtPoint(e.getPoint());
                int id = Integer.parseInt(String.valueOf(table.getValueAt(row,0)));
                Order or = OrderList.parseOneOrder(facade.getMessageManager().getOrderId("{\"id\":" + id + "}"));
                new OpenOrderFrame(facade, or);
            }
        }
    }
}
