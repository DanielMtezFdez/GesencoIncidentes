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

    public static ArrayList<Comunidad> getComunidades() {

        ArrayList<Comunidad> comunidades = new ArrayList<>();

        sql = "SELECT CODIGO, NOMBRE FROM COMUNID ORDER BY CODIGO;";

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

    public static boolean checkComunidad(String codComunidad) {

        boolean codInDB = false;

        sql = "SELECT * FROM COMUNID";

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if(rs.next()){
                codInDB = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return codInDB;
    }

    public static String getComunidadById (String codComunidad) {

        String comunidad = "";

        if(codComunidad == null) {
            return comunidad;
        }

        sql = "SELECT * FROM COMUNID WHERE CODIGO = \"" + codComunidad + "\"";

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if(rs.next()){
                comunidad = rs.getString("NOMBRE");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comunidad;
    }




}
