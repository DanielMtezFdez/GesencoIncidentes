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


    public static boolean registerIncident(Incidente incidente) {

        sql = "INSERT INTO incidente (CodEmpleado, Titulo, Descripcion, FechaJunta, NivelUrgencia, TipoComunicado, Completo) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);

            System.out.println(incidente.getCodEmpleado());
            System.out.println(incidente.getTitulo());
            System.out.println(incidente.getDescripcion());
            System.out.println(incidente.getFechaJunta());
            System.out.println(incidente.getNivelUrgencia());
            System.out.println(incidente.getTipoComunicado());
            System.out.println(incidente.getCompleto());

            ps.setString(1, incidente.getCodEmpleado());
            ps.setString(2, incidente.getTitulo());
            ps.setString(3, incidente.getDescripcion());
            ps.setString(4, incidente.getFechaJunta());
            ps.setString(5, incidente.getNivelUrgencia());
            ps.setString(6, incidente.getTipoComunicado());
            ps.setString(7, incidente.getCompleto());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Registro erroneo");
            e.printStackTrace();
            return false;
        }

    }


    public static ArrayList<Incidente> getIncidentes() {

        ArrayList<Incidente> incidentes = new ArrayList<>();

        sql = "SELECT * FROM INCIDENTE ORDER BY IDINCIDENTE;";

        incidente = null;

        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                int idIncidente = rs.getInt(1);
                String codEmpleado = rs.getString(2);
                String titulo = rs.getString(3);
                String descripcion = rs.getString(4);
                String fechaAlta = rs.getTimestamp(5).toString();
                String fechaJunta = rs.getString(6);
                String fechaFin = fechaFin = rs.getString(7);
                String nivelUrgencia = rs.getString(8);
                String tipoComunicado = rs.getString(9);
                String completo = rs.getString(10);

                if(completo.equals("no")) {
                    fechaFin="NO FINALIZADO";
                }

                incidente = new Incidente(idIncidente, codEmpleado, titulo, descripcion, fechaAlta, fechaJunta, fechaFin, nivelUrgencia, tipoComunicado, completo);
                incidentes.add(incidente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return incidentes;
    }

    /**
     * Created 15/10/2019 by dmartinez
     *
     * Recoge la descripcion modificada y el id del incidente y la modifica en la base de datos
     *
     * @param idIncidente int con el id del incidente
     * @param descripcionModificada String con la descripción del incidente modificada
     */
    public static void editarIncidente(int idIncidente, String descripcionModificada) {
        sql = "UPDATE INCIDENTE SET Descripcion = '" + descripcionModificada + "' WHERE IdIncidente = " + idIncidente;

        try {
            stmt = conn.createStatement();
            int filas = stmt.executeUpdate(sql);

            if (filas > 0) {
                System.out.println("Actualizado correctamente");
            } else {
                System.out.println("Problema con la actualización");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
