package database_firebird;

import java.sql.*;

public class ConnectFireBird {

    // Propiedades
    private static Connection conn = null;
    private String driver;
    private String url;
    private String user;
    private String pass;

    // Constructor
    private ConnectFireBird() {
        String driver = "org.firebirdsql.jdbc.FBDriver";
        String url = "jdbc:firebirdsql:localhost/3050:D:\\DISTR K\\HORIZONT\\Database\\HORIZONT.FDB";
        String user = "SYSDBA";
        String pass = "masterkey";

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
            new ConnectFireBird();
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



//    private static Connection ConnectionFireBird(String url, String user, String pass) {
//        try {
//            Class.forName("org.firebirdsql.jdbc.FBDriver");
//            System.out.println("Driver registrado");
//        } catch (ClassNotFoundException e) {
//            System.out.println("Error registrando el Driver");
//            e.printStackTrace();
//        }
//
//        try {
//            con = DriverManager.getConnection(url, user, pass);
//            System.out.println("Conexión exitosa");
//        } catch (SQLException e) {
//            System.out.println("Error al realizar la conexión");
//            e.printStackTrace();
//        }
//
//        return con;
//    }
}
