package modelo.pojos;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Periodo {
    private Integer idPeriodo;
    private Date fechaInicio;
    private Date fechaFin;

    public Periodo() {
    }

    public Periodo(Integer idPeriodo, Date fechaInicio, Date fechaFin) {
        this.idPeriodo = idPeriodo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
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

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", new Locale("ES"));
        String inicio = sdf.format(fechaInicio);
        String fin = sdf.format(fechaFin);
        String cadenaPeriodo = inicio + " - " + fin;
        return cadenaPeriodo;
    }
}
