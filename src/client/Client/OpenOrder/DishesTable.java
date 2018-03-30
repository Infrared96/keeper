package client.Client.OpenOrder;

import client.Model.Dish.Dish;
import client.Model.NewOrder.NewOrder;
import client.Model.Order.Order;
import client.facade.Facade;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DishesTable extends JTable {
    Facade facade;
    ArrayList<Dish> dishes;
    //ArrayList<NewOrder> arrayNewOrders;
    //public DishesTable(Facade facade, Order or, ArrayList<Dish> dishes, ArrayList<NewOrder> arrayNewOrders) {
    public DishesTable(Facade facade,ArrayList<Dish> dishes) {
        this.facade = facade;
        this.dishes = dishes;
       // this.arrayNewOrders = arrayNewOrders;

        this.setModel(new TableModelDishes(this.dishes));
        this.getTableHeader().setPreferredSize(new Dimension(0, 24));
        this.rowHeight = 24;

        this.getTableHeader().setFont(new Font(this.getName(), Font.BOLD, 20));
        this.setFont(new Font(this.getName(), Font.PLAIN, 16));

        //Выравнивание по центру текста
        this.setDefaultRenderer(this.getColumnClass(1), new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.setHorizontalAlignment(SwingConstants.CENTER);
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                return this;
            }

        });

        //this.addMouseListener(new addNewOrder( this, dishes, arrayNewOrders, or));
    }



//    private class addNewOrder extends MouseAdapter {
//        ArrayList<Dish> dishes;
//        JTable table;
//        ArrayList<NewOrder> arrayNewOrders;
//        Order or;
//
//        public addNewOrder(JTable table, ArrayList<Dish> dishes, ArrayList<NewOrder> arrayNewOrders, Order or) {
//            this.dishes = dishes;
//            this.table = table;
//            this.arrayNewOrders = arrayNewOrders;
//            this.or = or;
//        }
//
//        @Override
//        public void mouseClicked(MouseEvent e) {
//            System.out.println( "click = " + e.getClickCount() + "mouse: " +  e.getModifiersEx() );
//
//            if(e.getClickCount() == 2 && e.getModifiersEx() == 0) {
//                int row = table.rowAtPoint(e.getPoint());
//                Dish dish = this.dishes.get(row);
//                arrayNewOrders.add(new NewOrder(0,dish.getId(), or.getId(), 1.0, false));
//            }
//           // this.no.add()
//        }
//    }
}
