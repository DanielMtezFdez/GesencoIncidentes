package database;


import model.Empleado;
import model.TipoComunicado;

import java.sql.*;
import java.util.ArrayList;

public class TipoComunicadoDAO {

    /**
     * Objetos para JDBC
     */
    private static Statement stmt;
    private static PreparedStatement ps;
    private static ResultSet rs;
    private static String sql;
    private static Connection conn = ConnectAccess.getConnection();

    private static TipoComunicado tipoComunicado;

    public static ArrayList<TipoComunicado> getTiposComunicados() {

        ArrayList<TipoComunicado> tiposComunicados = new ArrayList<>();

        sql = "SELECT * FROM TIPOCOMUNICADO ORDER BY ID;";

        tipoComunicado = null;

        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                tipoComunicado = new TipoComunicado(rs.getInt(1), rs.getString(2));
                tiposComunicados.add(tipoComunicado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tiposComunicados;
    }

    public static String getTipoById(int tipoComunicado) {

        String resultado = "";

        sql = String.format("SELECT TipoComunicado FROM TIPOCOMUNICADO WHERE Id = %s;", tipoComunicado);

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

        sql = String.format("SELECT Id FROM TIPOCOMUNICADO WHERE TipoComunicado = %s;", selectedItem);

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
