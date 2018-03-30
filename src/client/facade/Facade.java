package client.facade;

import client.Client.Client;
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
    private OrderTable ot = null;

    private ArrayList<NewOrder> actualOrders = new ArrayList<>();

    public Facade() {
        this.requestManager = new RequestManager(this);
        this.messageManager = new MessageManager(this.requestManager);
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
