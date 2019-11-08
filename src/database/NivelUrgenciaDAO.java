package database;

import model.NivelUrgencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

public class NivelUrgenciaDAO {

    /**
     * Objetos para JDBC
     */
    private static Statement stmt;
    private static PreparedStatement ps;
    private static ResultSet rs;
    private static String sql;
    private static Connection conn = ConnectAccess.getConnection();

    private static NivelUrgencia nivelUrgencia;


    public static ArrayList<NivelUrgencia> getNivelesUrgencia() {

        ArrayList<NivelUrgencia> nivelesUrgencia = new ArrayList<>();

        sql = "SELECT * FROM NivelUrgencia ORDER BY Id;";

        nivelUrgencia = null;

        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                nivelUrgencia = new NivelUrgencia(rs.getInt(1), rs.getString(2));
                nivelesUrgencia.add(nivelUrgencia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nivelesUrgencia;
    }


    public static String getNivelUrgenciaByID(int nivelUrgencia) {
        String resultado = "";

        sql = String.format("SELECT NivelUrgencia FROM NIVELURGENCIA WHERE Id = %s;", nivelUrgencia);

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

    public static int getIdByNivelUrgencia(String selectedItem) {
        int resultado = 0;

        sql = String.format("SELECT Id FROM NIVELURGENCIA WHERE NivelUrgencia = '%s;'", selectedItem);

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
