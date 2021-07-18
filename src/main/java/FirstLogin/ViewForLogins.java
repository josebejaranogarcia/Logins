package FirstLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewForLogins extends JFrame {
    private JPanel mainPane;
    private JTextField emailTxt;
    private JPasswordField passwordTxt;
    private JButton loginButton;
    private JButton registerButton;
    private String email;
    private JLabel emailLb;
    private ButtonGroup buttonGroup;
    char[] pass;
    private String passEncrypted;
    private Register register;
    //String pass;
    Connection connection;
    // private static final String statement= "INSERT INTO account (user, pass) VALUES $user, $pass";

    public ViewForLogins() {
        setContentPane(mainPane);
        setTitle("First Loguer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
        setVisible(true);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (e.getActionCommand()) {
                    case "login":
                        systemAccess();
                        break;
                    case "register":
                        register();
                        break;
                }
                MyConnections posgres = new PostgresConnection();
                connection = posgres.connect();
            }
        };
        registerButton.addActionListener(listener);
        loginButton.addActionListener(listener);
    }


    private void register() {
        register = new Register();
        System.out.println(">>>>>>>>> Se tiene que abrir la ventana para registrar");
        //   setContentPane(register.registerPane);
        // insertNewUser();
    }

    void systemAccess() {
        if (checkFields() && getPass().equals(pass))
            System.out.println(">>>>>>>>>> Estamos en el el sistema");
        else
            System.out.println(">>>>>>>>>>>>>>>> Acceso denegado");
        close();
    }


    private boolean checkFields() {
        email = emailTxt.getText().trim();
        pass = passwordTxt.getPassword();
        if (!email.isEmpty() && pass.length >= 3)
            return true;
        System.out.println(">>>>>>>>>>>>>> Rellene los campos");
        return false;
    }

    private void insertNewUser() {
        try {
            passEncrypted = EncrypterPass.encript(pass);
            Statement stm = connection.createStatement();
            stm.executeUpdate("insert into accounts(user_name, password) values('" + email + "','" + passEncrypted + "')");
            stm.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String getPass() {
        String decryptedPass = null;
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select password from accounts where user_name='" + email + "';");
            rs.next();
            passEncrypted = rs.getString("password");
            decryptedPass = EncrypterPass.decrypt(passEncrypted);
            System.out.println("La palabra secreta era: " + decryptedPass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedPass;
    }

    private void close() {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }
}
