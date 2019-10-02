package database_firebird;

public class Main {

    private static String url = "jdbc:firebirdsql:localhost/3050:D:\\DISTR K\\HORIZONT\\Database\\HORIZONT.FDB";
    private static String user = "SYSDBA";
    private static String pass = "masterkey";

    public static void main(String[] args) {
       ConnectFireBird.obtenerCodigos();
    }

}
