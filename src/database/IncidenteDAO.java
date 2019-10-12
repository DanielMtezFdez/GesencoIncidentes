package database;

import model.Incidente;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

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


    public static ArrayList<Incidente> getIncidentes() {

        ArrayList<Incidente> incidentes = new ArrayList<>();

        String sql = "SELECT * FROM INCIDENTE ORDER BY IDINCIDENTE;";

        incidente = null;

        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                String codEmpleado = rs.getString(2);
                String titulo = rs.getString(3);
                String descripcion = rs.getString(4);

                String fechaAlta;
                if(rs.getTimestamp(5) == null){
                    fechaAlta = "";
                } else {
                    fechaAlta = rs.getTimestamp(5).toString();
                }

                String fechaJunta;
                if(rs.getTimestamp(6) == null){
                    fechaJunta = "";
                } else {
                    fechaJunta = rs.getTimestamp(6).toString();
                }

                String fechaFin;
                if(rs.getTimestamp(7) == null){
                    fechaFin = "";
                } else {
                    fechaFin = rs.getTimestamp(7).toString();
                }

                String nivelUrgencia = rs.getString(8);
                String tipoComunicado = rs.getString(9);
                String completo = rs.getString(10);
                incidente = new Incidente(codEmpleado, titulo, descripcion, fechaAlta, fechaJunta, fechaFin, nivelUrgencia, tipoComunicado, completo);
                incidentes.add(incidente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return incidentes;
    }
}
