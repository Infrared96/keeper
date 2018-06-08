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

    public ArrayList<Order> getNotCloseOrders() {
        ArrayList<Order> dontCloseOrders = new ArrayList<>();
        for(Order order: this.orders) {
            if(!order.isClose()) {
                dontCloseOrders.add(order);
            }
        }
        return dontCloseOrders;
    }
    public ArrayList<Order> getCloseOrders() {
        ArrayList<Order> closeOrders = new ArrayList<>();
        for(Order order: this.orders) {
            if(order.isClose()) {
                closeOrders.add(order);
            }
        }
        return closeOrders;
    }

    public User getUser() {
        return this.user;
    }

    public void setOrders( ArrayList<Order> orders) {
        this.orders = orders;
    }

    public double getTotalSum() {
        return setSum(this.totalSum, this.getOrders());
    }

    public double getNotTotalSum() {
        return setSum(0, this.getNotCloseOrders());
    }

    public Client(Facade facade, User user) throws  IOException {
        this.facade = facade;
        this.user = user;
        this.totalSum = 0.0;
        this.orders = initOrders();
    }

    private double setSum(double sum, ArrayList<Order> orders) {
        if(orders.size() > 0) {
            for(int i = 0; i < orders.size(); i++) {
                sum += orders.get(i).getPrice();
            }
        }
        return sum;
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