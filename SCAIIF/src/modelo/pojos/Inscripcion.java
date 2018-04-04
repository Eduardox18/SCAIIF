package modelo.pojos;

public class Inscripcion {

    private String matricula;
    private Integer nrc;
    private Double calificacionFinal;

    public Inscripcion() {
    }

    public Inscripcion(String matricula, Integer nrc, Double calificacionFinal) {
        this.matricula = matricula;
        this.nrc = nrc;
        this.calificacionFinal = calificacionFinal;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getNrc() {
        return nrc;
    }

    public void setNrc(Integer nrc) {
        this.nrc = nrc;
    }

    public Double getCalificacionFinal() {
        return calificacionFinal;
    }

    public void setCalificacionFinal(Double calificacionFinal) {
        this.calificacionFinal = calificacionFinal;
    }
}
