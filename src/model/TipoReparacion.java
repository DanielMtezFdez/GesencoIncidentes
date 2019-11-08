package model;

public class TipoReparacion {
    private int id;
    private String tipoReparacion;

    public TipoReparacion() {

    }

    public TipoReparacion(int id, String tipoReparacion) {
        this.id = id;
        this.tipoReparacion = tipoReparacion;
    }


    @Override
    public String toString() {
        return "TipoReparacion{" +
                "id=" + id +
                ", tipoReparacion='" + tipoReparacion + '\'' +
                '}';
    }


    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String gettipoReparacion() {
        return tipoReparacion;
    }

    public void settipoReparacion(String tipoReparacion) {
        this.tipoReparacion = tipoReparacion;
    }
}
