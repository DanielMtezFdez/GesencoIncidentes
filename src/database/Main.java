package database;

import model.Empleado;

import java.sql.*;

public class Main {
    /**
     * Objetos para JDBC
     */
    static Statement stmt;
    static PreparedStatement ps;
    static ResultSet rs;
    static String sql;
    static Connection conn = ConnectAccess.getConnection();

    public static void main(String[] args) {
        getFecha();
        updateFecha();
        getFecha();

    }


    public static void getFecha(){

        sql = "SELECT * FROM fechafin";

        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void updateFecha() {
        sql = "UPDATE fechafin SET fechafin=? WHERE Id=1";

        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        try{
            ps = conn.prepareStatement(sql);
            ps.setDate(1, sqlDate);
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
}
