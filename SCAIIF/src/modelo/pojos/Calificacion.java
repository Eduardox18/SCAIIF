package modelo.pojos;

/**
 *
 * @author lalo
 */
public class Calificacion {

    private Integer nrc;
    private String matricula;
    private double calificacion;

    public Calificacion() {}

    public Calificacion(Integer nrc, String matricula, double calificacion) {
        this.nrc = nrc;
        this.matricula = matricula;
        this.calificacion = calificacion;
    }

    public Integer getNrc() {
        return nrc;
    }

    public void setNrc(Integer nrc) {
        this.nrc = nrc;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

}
