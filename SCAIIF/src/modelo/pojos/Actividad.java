package modelo.pojos;


import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    private Integer nrc;
    private Integer noPersonal;
    private Integer idEstado;

    public Actividad() {
    }

    public Actividad(Integer noActividad, String nombre, Time horaInicio, Time horaFin, Date fecha, Integer cupo,
                     Integer nrc, Integer noPersonal, Integer idEstado) {
        this.noActividad = noActividad;
        this.nombre = nombre;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fecha = fecha;
        this.cupo = cupo;
        this.nrc = nrc;
        this.noPersonal = noPersonal;
        this.idEstado = idEstado;
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

    public String getFecha() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM", new Locale("ES"));
        String fechaActividad = sdf.format(fecha);
        return fechaActividad;
    }
    
    public String getFechaFormato() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM", new Locale("ES"));
        String fechaActividad = sdf.format(fecha);
        return fechaActividad;
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

    public Integer getNrc() {
        return nrc;
    }

    public void setNrc(Integer nrc) {
        this.nrc = nrc;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
