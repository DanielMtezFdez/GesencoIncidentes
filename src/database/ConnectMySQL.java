package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectMySQL {
    // Propiedades
    private static Connection conn = null;
    private String driver;
    private String url;
    private String user;
    private String pass;

    // Constructor
    private ConnectMySQL() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/gesenco";
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
            new ConnectMySQL();
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
