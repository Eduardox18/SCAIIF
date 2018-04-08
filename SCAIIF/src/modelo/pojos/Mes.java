package modelo.pojos;

public class Mes {

    private Integer idMes;
    private String mes;

    public Mes() {
    }

    public Mes(Integer idMes, String mes) {
        this.idMes = idMes;
        this.mes = mes;
    }

    public Integer getIdMes() {
        return idMes;
    }

    public void setIdMes(Integer idMes) {
        this.idMes = idMes;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    @Override
    public String toString() {
        return mes;
    }
}
