package modelo.pojos;

import java.util.Date;

/**
 *
 * @author lalo
 */
public class Reservacion {
    
    private Integer noReservacion;
    private Integer noActividad;
    private String matricula;
    private Date fecha;
    private boolean asistencia;

    public Reservacion() {}

    public Reservacion(Integer noReservacion, Integer noActividad, String matricula, Date fecha, boolean asistencia) {
        this.noReservacion = noReservacion;
        this.noActividad = noActividad;
        this.matricula = matricula;
        this.fecha = fecha;
        this.asistencia = asistencia;
    }

    public Integer getNoReservacion() {
        return noReservacion;
    }

    public void setNoReservacion(Integer noReservacion) {
        this.noReservacion = noReservacion;
    }

    public Integer getNoActividad() {
        return noActividad;
    }

    public void setNoActividad(Integer noActividad) {
        this.noActividad = noActividad;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isAsistencia() {
        return asistencia;
    }

    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }
    
    
    
}
