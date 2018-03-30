package client.Client.ModalAdd;

import client.Model.User.User;
import client.facade.Facade;

import javax.swing.*;

public class ModalAddTab extends JFrame {
    public ModalAddTab(Facade facade, User user) {
        this.setBounds(800,200,300,200);
        AddOrderPanel addOrder = new AddOrderPanel(facade,user, this);
        add(addOrder);
        this.setVisible(true);
    }

}
