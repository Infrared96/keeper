package client.Client.OpenOrder;

import client.Model.Category.Category;
import client.Model.Category.CategoryList;
import client.facade.Facade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ButtonPanel extends JPanel {
    Facade facade;
    JPanel mainPanel;
    public ButtonPanel(Facade facade, JPanel mainPanel) {
        this.facade = facade;
        this.mainPanel = mainPanel;
        setLayout(new GridLayout(2,6));
        init();
    }

    private void init() {

        ArrayList<Category> categories = CategoryList.parseCategorys(facade.getMessageManager().getCategorys());
        if(categories.size() <= 12) {
            for(int i = 0; i < categories.size(); i++) {
                JButton button = new JButton(categories.get(i).getName());
                button.setPreferredSize(new Dimension(60,60));
                button.addActionListener(new OpenCategory());
                add(button);
            }
        }
    }

    private  class OpenCategory implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
