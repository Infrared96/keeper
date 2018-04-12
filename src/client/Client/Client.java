package client.Client;

import client.Model.Order.Order;
import client.Model.Order.OrderList;
import client.Model.User.User;
import client.facade.Facade;

import java.io.*;
import java.util.ArrayList;


public class Client
{
    private Facade facade;
    private User user = null;
    private ArrayList<Order> orders = null;
    private double totalSum;

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public User getUser() {
        return this.user;
    }

    public void setOrders( ArrayList<Order> orders) {
        this.orders = orders;
    }

    public double getTotalSum() {
        if (orders.size() == 0) {
            totalSum = 0.0;
        } else if(totalSum == 0.0) {
            setTotalSum();
        }
        return totalSum;
    }

    public Client(Facade facade, User user) throws  IOException {
        this.facade = facade;
        this.user = user;
        this.totalSum = 0.0;
        this.orders = initOrders();
    }

    private void setTotalSum() {
        this.totalSum = 0;
        for(int i = 0; i < orders.size(); i++) {
            this.totalSum+=orders.get(i).getPrice();
        }
    }

    private ArrayList<Order> initOrders() {
        ArrayList<Order> orders = null;
        String ordersJSON = facade.getMessageManager().getOrdersUser("{\"user_id\":" + this.user.getId()+"}");
        if (ordersJSON != null) {
            orders = OrderList.parseUserOrders(ordersJSON);
        }
        return orders;
    }

}