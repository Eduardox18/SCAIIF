package modelo.pojos;

import java.sql.Date;

public class Induccion {
    private Integer idInduccion;
    private String matricula;
    private Integer nrc;
    private Date cursoInduccion;
    private Date primeraAsesoria;
    private Integer noPersonal;

    public Induccion() {
    }

    public Induccion(String matricula, Integer nrc, Date cursoInduccion, Date primeraAsesoria, Integer noPersonal) {
        this.matricula = matricula;
        this.nrc = nrc;
        this.cursoInduccion = cursoInduccion;
        this.primeraAsesoria = primeraAsesoria;
        this.noPersonal = noPersonal;
    }

    public Integer getIdInduccion() {
        return idInduccion;
    }

    public void setIdInduccion(Integer idInduccion) {
        this.idInduccion = idInduccion;
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

    public Date getCursoInduccion() {
        return cursoInduccion;
    }

    public void setCursoInduccion(Date cursoInduccion) {
        this.cursoInduccion = cursoInduccion;
    }

    public Date getPrimeraAsesoria() {
        return primeraAsesoria;
    }

    public void setPrimeraAsesoria(Date primeraAsesoria) {
        this.primeraAsesoria = primeraAsesoria;
    }

    public Integer getNoPersonal() {
        return noPersonal;
    }

    public void setNoPersonal(Integer noPersonal) {
        this.noPersonal = noPersonal;
    }
}
