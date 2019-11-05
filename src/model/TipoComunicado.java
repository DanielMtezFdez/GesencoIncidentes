package model;

public class TipoComunicado {

    private int id;
    private String tipoComunicado;

    public TipoComunicado() {

    }

    public TipoComunicado(int id, String tipoComunicado) {
        this.id = id;
        this.tipoComunicado = tipoComunicado;
    }



    @Override
    public String toString() {
        return "TipoComunicado{" +
                "id=" + id +
                ", tipoComunicado='" + tipoComunicado + '\'' +
                '}';
    }


    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoComunicado() {
        return tipoComunicado;
    }

    public void setTipoComunicado(String tipoComunicado) {
        this.tipoComunicado = tipoComunicado;
    }
}
