package database;

import model.Comunidad;
import model.Empleado;
import model.Incidente;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    /**
     * Objetos para JDBC
     */
    static Statement stmt;
    static PreparedStatement ps;
    static ResultSet rs;
    static String sql;
    static Connection conn = ConnectAccess.getConnection();

    static Empleado empleado;
    public static void main(String[] args) {

//        ArrayList<Comunidad> comunidades = ComunidadDAO.getComunidades();
//        for (Comunidad c : comunidades){
//            System.out.println(c);
//        }

//        ArrayList<Empleado> empleados = EmpleadoDAO.getEmpleados();
//        for (Empleado e : empleados){
//            System.out.println(e);
//        }

//        EmpleadoDAO.createEmpleado("Test", "Idea");

//        ArrayList<Incidente> incidentes = IncidenteDAO.getIncidentes();
////        for (Incidente i : incidentes){
////            System.out.println(i);
////        }


        ArrayList<Empleado> empleados = getEmpleados();

        for (Empleado e : empleados) {
            System.out.println(e);
        }


    }


    public static ArrayList<Empleado> getEmpleados(){

        ArrayList<Empleado> empleados = new ArrayList<>();

        sql = "SELECT * FROM EMPLEADO ORDER BY CODEMPLEADO;";

        empleado = null;

        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                empleado = new Empleado(rs.getString(2), rs.getString(3), rs.getString(4));
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empleados;
    }
}
