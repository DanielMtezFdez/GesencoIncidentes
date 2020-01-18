package database;

import model.TipoReparacion;

import java.sql.*;
import java.util.ArrayList;

public class TipoReparacionDAO {
    /**
    * Objetos para JDBC
    */

    private static Statement stmt;
    private static PreparedStatement ps;
    private static ResultSet rs;
    private static String sql;
    private static Connection conn = ConnectAccess.getConnection();

    private static TipoReparacion tipoReparacion;

    public static ArrayList<TipoReparacion> getTiposReparaciones() {

        ArrayList<TipoReparacion> tiposComunicados = new ArrayList<>();

        sql = "SELECT * FROM TipoReparacion ORDER BY Id;";

        tipoReparacion = null;

        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                tipoReparacion = new TipoReparacion(rs.getInt(1), rs.getString(2));
                tiposComunicados.add(tipoReparacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tiposComunicados;
    }

    public static String getTipoById(String tipoReparacion) {

        String resultado = "";

        sql = String.format("SELECT Reparacion FROM TipoReparacion WHERE Id = %s;", tipoReparacion);

        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                resultado = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }


    public static int getIdByTipo(String selectedItem) {
        int resultado = 0;

        sql = String.format("SELECT Id FROM TipoReparacion WHERE TipoReparacion = '%s';", selectedItem);

        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                resultado = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }
}
