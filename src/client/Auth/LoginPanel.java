package client.Auth;

import client.Client.FrameClient;
import client.Model.User.User;
import client.Model.User.UserList;
import client.facade.Facade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoginPanel extends JPanel {
    String path = "src/img/logo.jpg";
    private Facade facade;

    JTextField inputLogin;
    JPasswordField inputPassword;
    JLabel labelError;

    LoginPanel(Facade facade, JFrame frame){
        this.facade = facade;

        setLayout(new GridBagLayout());
        setBackground(Color.white);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5,3,5,3);

        JLabel img = new JLabel(new ImageIcon(path));
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        this.add(img,c);

        JLabel labelLogin = new JLabel("Имя: ");
        c.gridwidth = 1;
        c.weighty = 0.0;
        c.gridx = 0;
        c.gridy = 2;
        this.add(labelLogin,c);

        inputLogin = new JTextField();
        inputLogin.setHorizontalAlignment(JTextField.CENTER);
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 2;
        this.add(inputLogin,c);

        JLabel labelPassword = new JLabel("Пароль: ");
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        this.add(labelPassword,c);

        inputPassword = new JPasswordField();
        inputPassword.setHorizontalAlignment(JPasswordField.CENTER);
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 3;
        this.add(inputPassword,c);

        JButton logIn = new JButton("Войти");
        logIn.addActionListener(new Login(frame));
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 4;
        this.add(logIn,c);

        labelError = new JLabel("", JLabel.CENTER);
        c.gridwidth = 0;
        c.gridx = 0;
        c.gridy = 6;
        this.add(labelError,c);

    }

    private class Login implements ActionListener {
        JFrame frame;
        Login(JFrame frame) {
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                String login = inputLogin.getText();
                String password = String.valueOf(inputPassword.getPassword());
                //FrameClient fClient = facade.getRequestManager().createClient(login, password);
                if (!facade.isLogin) {
                    String userStr = facade.getMessageManager().getUserLogin(UserList.forLoginJSON(login,password));
                    if (userStr != null) {
                        User user = new User(userStr);
                        new FrameClient(facade, user);
                        this.frame.dispose();
                    } else {
                        System.out.println("Error Login!");
                        labelError.setText("Not correct login or password!");
                        labelError.setForeground (Color.red);
                    }
                } else {
                    System.out.println("Error: Login connection complite!");
                    labelError.setText("Error: Login connection complite!");
                    labelError.setForeground (Color.red);
                }
            }
            catch (Exception e1)
            {
                e1.printStackTrace();
            }
        }
    }
}
