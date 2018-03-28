package modelo.pojos;

import java.sql.Date;

/**
 *
 * @author lalo
 */
public class Aviso {
    private Integer noAviso;
    private String asunto;
    private String mensaje;
    private Date fechaCreacion;
    private Date fechaLimite;

    public Aviso() {
    }

    public Aviso(Integer noAviso, String asunto, String mensaje, Date fechaCreacion, Date fechaLimite) {
        this.noAviso = noAviso;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.fechaLimite = fechaLimite;
    }

    public Integer getNoAviso() {
        return noAviso;
    }

    public void setNoAviso(Integer noAviso) {
        this.noAviso = noAviso;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }
}
