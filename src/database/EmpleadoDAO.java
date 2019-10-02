package database;

import model.Empleado;

import java.sql.*;
import java.util.ArrayList;

public class EmpleadoDAO {

    /**
     * Objetos para JDBC
     */
    private static Statement stmt;
    private static PreparedStatement ps;
    private static ResultSet rs;
    private static String sql;
    private static Connection conn = ConnectMySQL.getConnection();

    private static Empleado empleado;


    public static ArrayList<Empleado> getEmpleados() {

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

    public static void createEmpleado(String nombre, String apellido) {
        empleado = new Empleado(nombre, apellido);

        sql = "INSERT INTO EMPLEADO (NOMBRE, APELLIDO) VALUES (?, ?);";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellido);

            int afectados = ps.executeUpdate();

            if (afectados == 1) {
                System.out.println("Éxito");
            } else {
                System.out.println("Error");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
