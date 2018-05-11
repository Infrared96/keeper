package client.Client.OpenOrder;

import client.Model.Dish.Dish;
import client.Model.Dish.DishesList;
import client.Model.Order.Order;
import client.facade.Facade;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BigOrderPanel extends JPanel {
    private Facade facade;
    private Order or;
    private DishesTable dt;
    public BigOrderPanel(Facade facade, Order or) {
        setLayout(new BorderLayout());
        setBackground(Color.white);
        this.facade = facade;
        this.or = or;
        init();
    }

    private void init() {
        this.add(new ButtonPanel(this.facade,this, this.or, dt), BorderLayout.SOUTH);
        facade.setDishesTable(new DishesTable(this.facade, facade.getDishes(), this.or));
        dt = facade.getDishesTable();

        JScrollPane dishScroll = new JScrollPane(dt);
        add(dishScroll, BorderLayout.CENTER);
    }


}
