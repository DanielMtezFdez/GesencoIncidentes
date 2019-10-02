package database;

import model.Incidente;

import java.sql.*;
import java.util.ArrayList;

public class IncidenteDAO {

    /**
     * Objetos para JDBC
     */
    private static Statement stmt;
    private static PreparedStatement ps;
    private static ResultSet rs;
    private static String sql;
    private static Connection conn = ConnectMySQL.getConnection();

    private static Incidente incidente;


//    public static ArrayList<Incidente> getComunidades() {
//
//        ArrayList<Incidente> incidentes = new ArrayList<>();
//
//        String sql = "SELECT CODIGO, NOMBRE FROM COMUNID ORDER BY CODIGO;";
//
//        incidente = null;
//
//        try{
//            stmt = conn.createStatement();
//            rs = stmt.executeQuery(sql);
//            while(rs.next()){
//                incidente = new Incidente(rs.getString(1), rs.getString(2));
//                incidentes.add(incidente);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return incidentes;
//    }
}
