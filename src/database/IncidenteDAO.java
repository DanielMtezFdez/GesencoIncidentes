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
                String tipoJunta = rs.getString(3);
                String titulo = rs.getString(4);
                String descripcion = rs.getString(5);

                String fechaAlta;
                if(rs.getTimestamp(6) == null){
                    fechaAlta = "";
                } else {
                    fechaAlta = rs.getTimestamp(6).toString();
                }

                String fechaJunta;
                if(rs.getTimestamp(7) == null){
                    fechaJunta = "";
                } else {
                    fechaJunta = rs.getTimestamp(7).toString();
                }

                String fechaFin;
                if(rs.getTimestamp(8) == null){
                    fechaFin = "";
                } else {
                    fechaFin = rs.getTimestamp(8).toString();
                }

                String tipoIncidente = rs.getString(9);
                String tipoComunicado = rs.getString(10);
                String completo = rs.getString(11);
                incidente = new Incidente(codEmpleado, tipoJunta, titulo, descripcion, fechaAlta, fechaJunta, fechaFin, tipoIncidente, tipoComunicado, completo);
                incidentes.add(incidente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return incidentes;
    }
}
