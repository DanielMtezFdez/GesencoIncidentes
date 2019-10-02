package database_firebird;

import model.Comunidad;

import java.util.ArrayList;

public class Main {

    private static String url = "jdbc:firebirdsql:localhost/3050:D:\\DISTR K\\HORIZONT\\Database\\HORIZONT.FDB";
    private static String user = "SYSDBA";
    private static String pass = "masterkey";

    public static void main(String[] args) {
        ArrayList<Comunidad> comunidades = ComunidadDAO.getComunidades();
        for (Comunidad c : comunidades){
            System.out.println(c);
        }
    }

}
