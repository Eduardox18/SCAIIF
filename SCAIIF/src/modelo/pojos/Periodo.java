package modelo.pojos;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Periodo {
    private Integer idPeriodo;
    private Date fechaInicio;
    private Date fechaFin;
    private Date inicioVacaciones;
    private Date finVacaciones;

    public Periodo() {
    }

    public Periodo(Integer idPeriodo, Date fechaInicio, Date fechaFin, Date inicioVacaciones, Date finVacaciones) {
        this.idPeriodo = idPeriodo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.inicioVacaciones = inicioVacaciones;
        this.finVacaciones = finVacaciones;
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getInicioVacaciones() {
        return inicioVacaciones;
    }

    public void setInicioVacaciones(Date inicioVacaciones) {
        this.inicioVacaciones = inicioVacaciones;
    }

    public Date getFinVacaciones() {
        return finVacaciones;
    }

    public void setFinVacaciones(Date finVacaciones) {
        this.finVacaciones = finVacaciones;
    }

    public String getVacaciones() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM", new Locale("ES"));
        String inicio = sdf.format(inicioVacaciones);
        String fin = sdf.format(finVacaciones);
        String cadenaVacaciones = inicio + " - " + fin;
        return cadenaVacaciones;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", new Locale("ES"));
        String inicio = sdf.format(fechaInicio);
        String fin = sdf.format(fechaFin);
        String cadenaPeriodo = inicio + " - " + fin;
        return cadenaPeriodo;
    }
}
