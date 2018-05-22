package client.Client.OpenOrder;

import client.Model.Category.Category;
import client.Model.Category.CategoryList;
import client.Model.Dish.Dish;
import client.Model.Order.Order;
import client.facade.Facade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ButtonPanel extends JPanel {
    Facade facade;
    JPanel mainPanel;
    Order or;
    DishesTable dt;
    public ButtonPanel(Facade facade, JPanel mainPanel, Order or, DishesTable dt) {
        this.facade = facade;
        this.mainPanel = mainPanel;
        this.or = or;
        this.dt = dt;
        setLayout(new GridLayout(2,6));
        init();
    }

    private void init() {

        ArrayList<Category> categories = CategoryList.parseCategorys(facade.getMessageManager().getCategorys());
        if(categories.size() <= 12) {
            for(int i = 0; i < categories.size(); i++) {
                JButton button = new JButton(categories.get(i).getName());
                button.setPreferredSize(new Dimension(60,60));
                button.addActionListener(new OpenCategory(i+1));
                add(button);
            }
            JButton button = new JButton("Все блюда");
            button.setPreferredSize(new Dimension(60,60));
            button.addActionListener(new OpenAll());
            add(button);
        }
    }

    private  class OpenCategory implements ActionListener {
        int count;
        OpenCategory(int i) {
            count = i;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Dish> dishes = facade.getDishes();
            ArrayList<Dish> dishesCategory = new ArrayList<>();
            for(int i = 0; i < dishes.size(); i++) {
                if(dishes.get(i).getCategory() == count) {
                    dishesCategory.add(dishes.get(i));
                }
            }
            facade.setTableModelDishes(new TableModelDishes(dishesCategory));
            facade.getDishesTable().setModel(facade.getTableModelDishes());
        }
    }

    private  class OpenAll implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            facade.setTableModelDishes(new TableModelDishes(facade.getDishes()));
            facade.getDishesTable().setModel(facade.getTableModelDishes());
        }
    }
}
