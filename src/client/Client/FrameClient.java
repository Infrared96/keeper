package client.Client;

import client.Model.User.User;
import client.facade.Facade;

import javax.swing.*;

public class FrameClient extends JFrame {
    public FrameClient(Facade facade, User user) {
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setBounds(200,200,1200,700);
        this.add(new ClientPanel(facade, user));
        this.setVisible(true);
    }
}
