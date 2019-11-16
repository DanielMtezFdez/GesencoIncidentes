package database;

import model.Incidente;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

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


        sql = "INSERT INTO incidente (Empleado, Titulo, Descripcion, FechaJunta, NivelUrgencia, TipoComunicado, Completo, Comunidad, TipoReparacion, EmpresaReparadora) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);

            // formateo de fecha

            Timestamp fechaJunta = incidente.getFechaJunta();
            java.util.Date date = new Date(fechaJunta.getTime());
            System.out.println(date);

            //

            ps.setString(1, incidente.getCodEmpleado());
            ps.setString(2, incidente.getTitulo());
            ps.setString(3, incidente.getDescripcion());
            ps.setDate(4, new java.sql.Date(date.getTime()));
            ps.setInt(5, incidente.getNivelUrgencia());
            ps.setInt(6, incidente.getTipoComunicado());
            ps.setString(7, incidente.getCompleto());
            ps.setString(8, incidente.getCodComunidad());
            ps.setInt(9, incidente.getTipoReparacion());
            ps.setString(10, incidente.getEmpresaReparadora());

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
                int tipoReparacion = rs.getInt(12);
                String empresaReparadora = rs.getString(13);

                incidente = new Incidente(idIncidente, codEmpleado, titulo, descripcion, fechaAlta, fechaJunta, fechaFin,
                        nivelUrgencia, tipoComunicado, completo, comunidad, tipoReparacion, empresaReparadora);
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

        sql = "UPDATE incidente SET fechafin=?, Completo=? WHERE IdIncidente=?";

        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        try{
            ps = conn.prepareStatement(sql);
            ps.setDate(1, sqlDate);
            ps.setString(2, "si");
            ps.setInt(3, idIncidente);
            int res = ps.executeUpdate();

            if(res == 0) {
                System.out.println("error");
            } else {
                System.out.println(res);
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
                int tipoReparacion = rs.getInt(12);
                String empresaReparadora = rs.getString(13);

                incidente = new Incidente(idIncidente, codEmpleado, titulo, descripcion, fechaAlta, fechaJunta, fechaFin,
                        nivelUrgencia, tipoComunicado, completo, comunidad, tipoReparacion, empresaReparadora);
                incidentes.add(incidente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return incidentes;
    }
}
