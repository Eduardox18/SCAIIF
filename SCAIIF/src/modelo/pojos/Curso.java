package modelo.pojos;

/**
 *
 * @author lalo
 */
public class Curso {

    private Integer nrc;
    private String nombreCurso;
    private Integer noCreditos;
    private Integer noPersonal;

    public Curso() {}

    public Curso(Integer nrc, String nombreCurso, Integer noCreditos, Integer noPersonal) {
        this.nrc = nrc;
        this.nombreCurso = nombreCurso;
        this.noCreditos = noCreditos;
        this.noPersonal = noPersonal;
    }

    public Integer getNrc() {
        return nrc;
    }

    public void setNrc(Integer nrc) {
        this.nrc = nrc;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public Integer getNoCreditos() {
        return noCreditos;
    }

    public void setNoCreditos(Integer noCreditos) {
        this.noCreditos = noCreditos;
    }

    public Integer getNoPersonal() {
        return noPersonal;
    }

    public void setNoPersonal(Integer noPersonal) {
        this.noPersonal = noPersonal;
    }

}
