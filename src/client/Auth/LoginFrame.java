package client.Auth;

import client.facade.Facade;

import javax.swing.*;

public class LoginFrame extends JFrame {
    public LoginFrame(Facade facade) {
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setBounds(800,200,400,600);
        LoginPanel loginPanel = new LoginPanel(facade, this);
        this.add(loginPanel);
        this.setVisible(true);
    }
}
