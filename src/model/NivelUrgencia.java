package model;

public class NivelUrgencia {

    private int id;
    private String nivelUrgencia;

    public NivelUrgencia() {

    }

    public NivelUrgencia(int id, String nivelUrgencia) {
        this.id = id;
        this.nivelUrgencia = nivelUrgencia;
    }


    @Override
    public String toString() {
        return "NivelUrgencia{" +
                "id=" + id +
                ", nivelUrgencia='" + nivelUrgencia + '\'' +
                '}';
    }


    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNivelUrgencia() {
        return nivelUrgencia;
    }

    public void setNivelUrgencia(String nivelUrgencia) {
        this.nivelUrgencia = nivelUrgencia;
    }
}
