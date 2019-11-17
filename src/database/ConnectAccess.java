package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectAccess {

    // Propiedades
    private static Connection conn = null;
    private String driver;
    private String url;
    private String user;
    private String pass;

    // Constructor
    private ConnectAccess() {
        String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
        String url = "jdbc:ucanaccess://C:\\BD\\BDGsco\\BDIncidencias.accdb";
//        String url = "jdbc:ucanaccess://C:\\BD\\BDGsco\\BDDani.accdb";
        String user = "gesenco";
        String pass = "gesenco";

        try {
            Class.forName(driver);
            System.out.println("Driver conectado con éxito");
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexión establecida con éxito");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Created by dmartinez on 02/10/2019
     *
     * Método para obtener la conexión singleton
     *
     * @return Connection de la conexión establecida
     */
    public static Connection getConnection() {
        if (conn == null) {
            new ConnectAccess();
        }
        return conn;
    }


    /**
     * Método para cerrar la conexión establecida
     */
    public static void closeConnection(){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

}
