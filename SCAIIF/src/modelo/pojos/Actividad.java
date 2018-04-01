package modelo.pojos;


import java.sql.Time;
import java.util.Date;

/**
 *
 * @author lalo
 */
public class Actividad {

    private Integer noActividad;
    private String nombre;
    private Time horaInicio;
    private Time horaFin;
    private Date fecha;
    private Integer cupo;
    private Integer noPersonal;

    public Actividad() {
    }

    public Actividad(Integer noActividad, String nombre, Time horaInicio, Time horaFin, Date fecha, Integer cupo, Integer noPersonal) {
        this.noActividad = noActividad;
        this.nombre = nombre;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fecha = fecha;
        this.cupo = cupo;
        this.noPersonal = noPersonal;
    }

    public Integer getNoActividad() {
        return noActividad;
    }

    public void setNoActividad(Integer noActividad) {
        this.noActividad = noActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getCupo() {
        return cupo;
    }

    public void setCupo(Integer cupo) {
        this.cupo = cupo;
    }

    public Integer getNoPersonal() {
        return noPersonal;
    }

    public void setNoPersonal(Integer noPersonal) {
        this.noPersonal = noPersonal;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
