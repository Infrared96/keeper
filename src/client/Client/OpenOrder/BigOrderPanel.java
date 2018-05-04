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
    public BigOrderPanel(Facade facade, Order or) {
        this.facade = facade;
        this.or = or;

        setLayout(new BorderLayout());
        setBackground(Color.white);
        init();
    }

    private void init() {
        this.add(new ButtonPanel(this.facade,this), BorderLayout.SOUTH);
        ArrayList<Dish> dishes = facade.getDishes();
        DishesTable dt = new DishesTable(this.facade, dishes, this.or);

        JScrollPane dishScroll = new JScrollPane(dt);
        add(dishScroll, BorderLayout.CENTER);
    }
}
