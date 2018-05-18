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
    private Integer nrc;


    public Calendario() { }


    public Calendario(Integer idCalendario, Date fechaLimiteExamen, Integer nrc) {
        this.idCalendario = idCalendario;
        this.fechaLimiteExamen = fechaLimiteExamen;
        this.nrc = nrc;
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

    public Integer getNrc() {
        return nrc;
    }

    public void setNrc(Integer nrc) {
        this.nrc = nrc;
    }
}
