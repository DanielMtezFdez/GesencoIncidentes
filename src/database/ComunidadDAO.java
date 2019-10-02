package database;

import model.Comunidad;

import java.sql.*;
import java.util.ArrayList;

public class ComunidadDAO {

    /**
     * Objetos para JDBC
     */
    private static Statement stmt;
    private static PreparedStatement ps;
    private static ResultSet rs;
    private static String sql;
    private static Connection conn = ConnectFireBird.getConnection();

    private static Comunidad comunidad;




    public static ArrayList<Comunidad> getComunidades() {

        ArrayList<Comunidad> comunidades = new ArrayList<>();

        String sql = "SELECT CODIGO, NOMBRE FROM COMUNID ORDER BY CODIGO;";

        Comunidad c = null;

        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                c = new Comunidad(rs.getString(1), rs.getString(2));
                comunidades.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comunidades;
    }



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
