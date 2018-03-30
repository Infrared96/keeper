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
    public DishesTable(Facade facade,ArrayList<Dish> dishes, Order or) {
        this.facade = facade;
        this.dishes = dishes;
       // this.arrayNewOrders = arrayNewOrders;

        TableModelDishes table = new TableModelDishes(this.dishes);
        this.setModel(table);
        this.facade.setTableModelDishes(table);
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
        this.addMouseListener(new addNewOrder( this, dishes, or));
    }



    private class addNewOrder extends MouseAdapter {
        ArrayList<Dish> dishes;
        JTable table;
        Order or;

        public addNewOrder(JTable table, ArrayList<Dish> dishes, Order or) {
            this.dishes = dishes;
            this.table = table;
            this.or = or;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println( "click = " + e.getClickCount() + "mouse: " +  e.getModifiersEx() );

            if(e.getClickCount() == 2 && e.getModifiersEx() == 0) {
                int row = table.rowAtPoint(e.getPoint());
                Dish dish = this.dishes.get(row);
                facade.addActualOrder(new NewOrder(dish, this.or, 1));
                facade.setTableModelNewOrder(new TableModelNewOrder(facade.getActualOrder(), facade));
                facade.setModel_NewOrderTable(facade.getTableModelNewOrder());
            }
           // this.no.add()
        }
    }
}
