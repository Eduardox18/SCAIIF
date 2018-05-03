package modelo.pojos;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 *
 * @author lalo
 */
public class Calendario {
    private Integer idCalendario;
    private Date fechaLimiteExamen;
    private Date inicioVacaciones;
    private Date finVacaciones;
    private Integer nrc;
    private Integer idPeriodo;


    public Calendario() { }


    public Calendario(Integer idCalendario, Date fechaLimiteExamen, Date inicioVacaciones, Date finVacaciones, Integer nrc, Integer idPeriodo) {
        this.idCalendario = idCalendario;
        this.fechaLimiteExamen = fechaLimiteExamen;
        this.inicioVacaciones = inicioVacaciones;
        this.finVacaciones = finVacaciones;
        this.nrc = nrc;
        this.idPeriodo = idPeriodo;
    }

    public Integer getIdCalendario() {
        return idCalendario;
    }

    public void setIdCalendario(Integer idCalendario) {
        this.idCalendario = idCalendario;
    }

    public Date getFechaLimiteExamen() {
        return fechaLimiteExamen;
    }

    public void setFechaLimiteExamen(Date fechaLimiteExamen) {
        this.fechaLimiteExamen = fechaLimiteExamen;
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
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd 'de' MMMM 'de' yyyy", new Locale("ES"));
        String inicio = sdf.format(inicioVacaciones);
        String fin = sdf.format(finVacaciones);
        String cadenaVacaciones = inicio + " - " + fin;
        return cadenaVacaciones;
    }

    public Integer getNrc() {
        return nrc;
    }

    public void setNrc(Integer nrc) {
        this.nrc = nrc;
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }
}
