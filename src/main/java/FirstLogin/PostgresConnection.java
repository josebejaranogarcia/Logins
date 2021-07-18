package FirstLogin;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresConnection  implements MyConnections{

    private static final String db = "accounts";
    private static final String url= "jdbc:postgresql://localhost:5432/" + db;
    private static final String user= "postgres";
    private static final String pass= "root";

    public Connection connect(){
        Connection conection=null;
        try{
            conection = DriverManager.getConnection(this.url, this.user, this.pass);
             System.out.println(">>>>>>>>>Realizada la conexion");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return conection;
    }
}

