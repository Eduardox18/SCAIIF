package modelo.pojos;

/**
 *
 * @author lalo
 */
public class CalificacionModulo {

    private Integer idCalificacionModulo;
    private String matricula;
    private Integer nrc;
    private Integer idModulo;
    private Double calificacion;

    public CalificacionModulo() {
    }

    public CalificacionModulo(Integer idCalificacionModulo, String matricula, Integer nrc, Integer idModulo, Double calificacion) {
        this.idCalificacionModulo = idCalificacionModulo;
        this.matricula = matricula;
        this.nrc = nrc;
        this.idModulo = idModulo;
        this.calificacion = calificacion;
    }

    public Integer getIdCalificacionModulo() {
        return idCalificacionModulo;
    }

    public void setIdCalificacionModulo(Integer idCalificacionModulo) {
        this.idCalificacionModulo = idCalificacionModulo;
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

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }
}
