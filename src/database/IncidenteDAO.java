package database;

import model.Incidente;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.Date;

public class IncidenteDAO {

    /**
     * Objetos para JDBC
     */
    private static Statement stmt;
    private static PreparedStatement ps;
    private static ResultSet rs;
    private static String sql;
    private static Connection conn = ConnectAccess.getConnection();

    private static Incidente incidente;


    public static boolean registerIncident(Incidente incidente) {

        sql = "INSERT INTO incidente (Empleado, Titulo, Descripcion, FechaJunta, NivelUrgencia, TipoComunicado, Completo, Comunidad) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
            ps.setTimestamp(4, incidente.getFechaJunta());
            ps.setInt(5, incidente.getNivelUrgencia());
            ps.setInt(6, incidente.getTipoComunicado());
            ps.setString(7, incidente.getCompleto());
            ps.setString(8, incidente.getCodComunidad());

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

        sql = "SELECT * FROM INCIDENTE ORDER BY FECHAJUNTA;";

        incidente = null;

        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                int idIncidente = rs.getInt(1);
                String codEmpleado = rs.getString(2);
                String titulo = rs.getString(3);
                String descripcion = rs.getString(4);
                Timestamp fechaAlta = rs.getTimestamp(5);

                Timestamp fechaJunta;
                if(rs.getTimestamp(6) != null){
                    fechaJunta = rs.getTimestamp(6);
                } else {
                    fechaJunta = null;
                }

                Timestamp fechaFin;
                if(rs.getTimestamp(7) != null){
                    fechaFin = rs.getTimestamp(7);
                } else {
                    fechaFin = null;
                }

                int nivelUrgencia = rs.getInt(8);
                int tipoComunicado = rs.getInt(9);
                String completo = rs.getString(10);
                String comunidad = rs.getString(11);

                incidente = new Incidente(idIncidente, codEmpleado, titulo, descripcion, fechaAlta, fechaJunta, fechaFin, nivelUrgencia, tipoComunicado, completo, comunidad);
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
        sql = String.format("UPDATE INCIDENTE SET Descripcion = '%s' WHERE IdIncidente = %d", descripcionModificada, idIncidente);

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

    /**
     * Created 16/10/2019 by dmartinez
     *
     * Establece el campo en la base de datos de completo a "si"
     *
     * @param idIncidente int con el id del incidente seleccionado
     */
    public static void completarIncidente(int idIncidente) {

        Date currentDate = new Date();

        SimpleDateFormat format = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");

        System.out.println(format.format(currentDate));

        sql = String.format("UPDATE INCIDENTE SET FechaFin = %s, Completo = 'si' WHERE IdIncidente = %s", currentDate, idIncidente);

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

    public static ArrayList<Incidente> getIncidentesConFiltro(Map<String,?> listaFiltros) {

        ArrayList<Incidente> incidentes = new ArrayList<>();
        sql = "SELECT * FROM INCIDENTE ";

        // Obtenemos todas las llaves del Map
        Set<String> mapKeys = listaFiltros.keySet();
        int iterations = 0;

        for(String key : mapKeys) {
            iterations +=1;
            if (iterations == 1) {
                if(key.equals("fechajuntaantesde")){
                    sql += " WHERE FechaJunta <= '" + listaFiltros.get(key) + "' ";
                } else if (key.equals("fechajuntadespuesde")) {
                    sql += " WHERE FechaJunta >= '" + listaFiltros.get(key) + "' ";
                } else {
                    sql += " WHERE " + key + " = '" + listaFiltros.get(key) + "' ";
                }
            } else {
                if(key.equals("fechajuntaantes_e")){
                    sql += " AND FechaJunta <= '" + listaFiltros.get(key) + "' ";
                } else if (key.equals("fechajuntadespuesde")) {
                    sql += " AND FechaJunta >= '" + listaFiltros.get(key) + "' ";
                }else {
                    sql += " AND " + key + " = '" + listaFiltros.get(key) + "' ";
                }

            }

        }

        sql += " ORDER BY FECHAJUNTA;";

        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                int idIncidente = rs.getInt(1);
                String codEmpleado = rs.getString(2);
                String titulo = rs.getString(3);
                String descripcion = rs.getString(4);
                Timestamp fechaAlta = rs.getTimestamp(5);

                Timestamp fechaJunta;
                if(rs.getTimestamp(6) != null){
                    fechaJunta = rs.getTimestamp(6);
                } else {
                    fechaJunta = null;
                }

                Timestamp fechaFin;
                if(rs.getTimestamp(7) != null){
                    fechaFin = rs.getTimestamp(7);
                } else {
                    fechaFin = null;
                }

                int nivelUrgencia = rs.getInt(8);
                int tipoComunicado = rs.getInt(9);
                String completo = rs.getString(10);
                String comunidad = rs.getString(11);

                incidente = new Incidente(idIncidente, codEmpleado, titulo, descripcion, fechaAlta, fechaJunta, fechaFin, nivelUrgencia, tipoComunicado, completo, comunidad);
                incidentes.add(incidente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return incidentes;
    }
}
