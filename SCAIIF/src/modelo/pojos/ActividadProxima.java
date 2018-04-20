package modelo.pojos;

import java.sql.Date;

public class ActividadProxima {
    private Date fechaActividad;
    private String nombreActividad;
    private Date fechaReservacion;
    private String nombreCurso;
    private String estado;

    public ActividadProxima() {
    }

    public ActividadProxima(Date fechaActividad, String nombreActividad, Date fechaReservacion, String nombreCurso,
                            String estado) {
        this.fechaActividad = fechaActividad;
        this.nombreActividad = nombreActividad;
        this.fechaReservacion = fechaReservacion;
        this.nombreCurso = nombreCurso;
        this.estado = estado;
    }

    public Date getFechaActividad() {
        return fechaActividad;
    }

    public void setFechaActividad(Date fechaActividad) {
        this.fechaActividad = fechaActividad;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public Date getFechaReservacion() {
        return fechaReservacion;
    }

    public void setFechaReservacion(Date fechaReservacion) {
        this.fechaReservacion = fechaReservacion;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
