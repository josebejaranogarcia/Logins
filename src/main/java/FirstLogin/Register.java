package FirstLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame {

    private JButton registerButton;
    private JLabel emailLb;
    private JTextField emailTxt;
    private JPasswordField passwordTxt;
    private JButton backButton1;
     JPanel registerPane;




    public Register() {
        setContentPane(registerPane);
        setTitle("Register");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
        setVisible(true);


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
