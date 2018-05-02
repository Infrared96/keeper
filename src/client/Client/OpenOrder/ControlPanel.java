package client.Client.OpenOrder;

import client.Model.Order.Order;
import client.facade.Facade;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private Facade facade;
    private Order or;
    private ActionNewOrderPanel actionNewOrderPanel;
    public ControlPanel(Facade facade, Order or) {
        this.facade = facade;
        this.or = or;

        setLayout(new GridLayout(0,1, 1, 10));
        init();
    }

    private void init() {
        InfoPanel ip = new InfoPanel(this.facade, this.or);
        ip.setSize(0, 30);
        this.actionNewOrderPanel = new ActionNewOrderPanel(this.facade, this.or);
        this.add(ip);
        this.add(new CalculatePanel(this.facade));
        this.add(this.actionNewOrderPanel);

    }

    public ActionNewOrderPanel getActionNewOrderPanel() {
        return actionNewOrderPanel;
    }

    public void setActionNewOrderPanel(ActionNewOrderPanel actionNewOrderPanel) {
        this.actionNewOrderPanel = actionNewOrderPanel;
    }
}
