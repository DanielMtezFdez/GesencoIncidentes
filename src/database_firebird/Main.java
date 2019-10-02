package database_firebird;

import model.Comunidad;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Comunidad> comunidades = ComunidadDAO.getComunidades();
        for (Comunidad c : comunidades){
            System.out.println(c);
        }
    }

}
