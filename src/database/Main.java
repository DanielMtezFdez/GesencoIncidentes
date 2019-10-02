package database;

import model.Comunidad;
import model.Empleado;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

//        ArrayList<Comunidad> comunidades = ComunidadDAO.getComunidades();
//        for (Comunidad c : comunidades){
//            System.out.println(c);
//        }

        ArrayList<Empleado> empleados = EmpleadoDAO.getEmpleados();
        for (Empleado e : empleados){
            System.out.println(e);
        }
    }

}
