package client.Client.OpenOrder;

import client.facade.Facade;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CalculatePanel extends JPanel {
    private Facade facade;
    private JPanel calc;
    private JTextField input;
    private String[] buttonMas = {"7","8","9","4","5","6","1","2","3","0",".","Удл"};

    public CalculatePanel(Facade facade) {
        this.facade = facade;
        setLayout(new BorderLayout());
        initCalc();
    }

    private void initCalc() {
        this.input = new JTextField();
        this.calc = new JPanel();

        this.calc.setLayout(new GridLayout(4,3));
        for (int i = 0; i < buttonMas.length; i++) {
            JButton button = new JButton(buttonMas[i]);
            this.calc.add(button);
        }

        this.add(input,BorderLayout.NORTH);
        this.add(calc,BorderLayout.CENTER);
    }
}
