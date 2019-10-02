package database_firebird;

import model.Comunidad;

import java.sql.*;

public class ComunidadDAO {

    /**
     * Objetos para JDBC
     */
    private static Statement stm;
    private static PreparedStatement ps;
    private static ResultSet rs;
    private static String sql;
    private static Connection conn = ConnectFireBird.getConnection();

    private static Comunidad comunidad;

//    public static void obtenerCodigos(){
//        Connection con = ConnectFireBird.ConnectionFireBird(url, user, pass);
//
//        try {
//            if(con == null){
//                System.out.println("Conexión fallida");
//            }
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery("SELECT * FROM COMUNID WHERE NOMBRE='C.P. Julio Camba 10'");
//
//            System.out.println("Empezamos RS");
//            while(rs.next()){
//                System.out.println(rs.getString(1));
//                System.out.println(rs.getString(2));
//            }
//            System.out.println("Terminado");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }


}
