package servicios.pojos;

import java.util.Date;

/**
 *
 * @author Esmeralda
 */
public class Induccion {
    private String matricula;
    private int nrc;
    private int idInduccion;
    private boolean cursoInduccion;
    private boolean primeraAsesoria;
    private Date fecha;

    public Induccion(String matricula, int nrc, int idInduccion, boolean cursoInduccion, boolean primeraAsesoria, Date fecha) {
        this.matricula = matricula;
        this.nrc = nrc;
        this.idInduccion = idInduccion;
        this.cursoInduccion = cursoInduccion;
        this.primeraAsesoria = primeraAsesoria;
        this.fecha = fecha;
    }

    public Induccion() {
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getNrc() {
        return nrc;
    }

    public void setNrc(int nrc) {
        this.nrc = nrc;
    }

    public int getIdInduccion() {
        return idInduccion;
    }

    public void setIdInduccion(int idInduccion) {
        this.idInduccion = idInduccion;
    }

    public boolean isCursoInduccion() {
        return cursoInduccion;
    }

    public void setCursoInduccion(boolean cursoInduccion) {
        this.cursoInduccion = cursoInduccion;
    }

    public boolean isPrimeraAsesoria() {
        return primeraAsesoria;
    }

    public void setPrimeraAsesoria(boolean primeraAsesoria) {
        this.primeraAsesoria = primeraAsesoria;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    
    
}
