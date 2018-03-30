package client.facade;

import client.Client.Client;
import client.Client.OpenOrder.DishesTable;
import client.Client.OpenOrder.NewOrderTable;
import client.Client.OpenOrder.TableModelDishes;
import client.Client.OpenOrder.TableModelNewOrder;
import client.Client.OrderTable;
import client.Client.TableModel;
import client.Model.NewOrder.NewOrder;
import client.facade.MessageManager.MessageManager;
import client.facade.RequestManager.RequestManager;

import java.util.ArrayList;

public class Facade {
    private RequestManager requestManager = null;
    private MessageManager messageManager = null;
    public boolean isLogin = false;

    private Client client = null;

    private TableModel tm = null;
    private TableModelNewOrder tableModelNewOrder = null;
    private TableModelDishes tableModelDishes = null;

    private OrderTable ot = null;
    private DishesTable dishesTable = null;
    private NewOrderTable newOrderTable = null;


    private ArrayList<NewOrder> actualOrders = new ArrayList<>();

    public Facade() {
        this.requestManager = new RequestManager(this);
        this.messageManager = new MessageManager(this.requestManager);
    }

    public void setModel_DishesTable(TableModelDishes table) {
        this.dishesTable.setModel(table);
    }

    public void setModel_NewOrderTable(TableModelNewOrder table) {
        this.newOrderTable.setModel(table);
    }

    public DishesTable getDishesTable() {
        return dishesTable;
    }

    public void setDishesTable(DishesTable dishesTable) {
        this.dishesTable = dishesTable;
    }

    public NewOrderTable getNewOrderTable() {
        return newOrderTable;
    }

    public void setNewOrderTable(NewOrderTable newOrderTable) {
        this.newOrderTable = newOrderTable;
    }

    public void addActualOrder(NewOrder newOrder) {
        this.actualOrders.add(newOrder);
    }

    public void setActualOrder(ArrayList<NewOrder> actualOrders) {
       this.actualOrders = actualOrders;
    }

    public ArrayList<NewOrder> getActualOrder() {
        return this.actualOrders;
    }

    public TableModelNewOrder getTableModelNewOrder() {
        return tableModelNewOrder;
    }

    public void setTableModelNewOrder(TableModelNewOrder tableModelNewOrder) {
        this.tableModelNewOrder = tableModelNewOrder;
    }

    public TableModelDishes getTableModelDishes() {
        return tableModelDishes;
    }

    public void setTableModelDishes(TableModelDishes tableModelDishes) {
        this.tableModelDishes = tableModelDishes;
    }

    public RequestManager getRequestManager() {
        return this.requestManager;
    }

    public MessageManager getMessageManager() {
        return this.messageManager;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return this.client;
    }

    public TableModel getTableModel() {
        return tm;
    }

    public void setTableModel(TableModel tm) {
        this.tm = tm;
    }

    public OrderTable getOrderTable() {
        return ot;
    }

    public void setOrderTable(OrderTable ot) {
        this.ot = ot;
    }
}
