package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectFireBird {
    private Connection conn;

    public ConnectFireBird (String url, String user, String pass) {
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            System.out.println("Driver registrado");
        } catch (ClassNotFoundException e) {
            System.out.println("Error registrando el Driver");
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexión exitosa");
        } catch (SQLException e) {
            System.out.println("Error al realizar la conexión");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String url = "jdbc:firebirdsql:localhost/3050:D:\\DISTR K\\HORIZONT\\Database";
        String user = "";
        String pass = "";
    }
}
