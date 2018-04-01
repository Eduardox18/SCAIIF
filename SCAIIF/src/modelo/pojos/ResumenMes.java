package modelo.pojos;

import java.sql.Date;

public class ResumenMes {

    private Integer idResumenMes;
    private Integer idCalendario;
    private Date diaInicio;
    private Date diaFin;
    private Integer idMes;
    private Integer idSeccion;
    private Integer noMaterial;
    private Integer idModulo;
    private Integer idConversacion;

    public ResumenMes() { }

    public ResumenMes(Integer idResumenMes, Integer idCalendario, Date diaInicio, Date diaFin, Integer idMes, Integer idSeccion, Integer noMaterial, Integer idModulo, Integer idConversacion) {
        this.idResumenMes = idResumenMes;
        this.idCalendario = idCalendario;
        this.diaInicio = diaInicio;
        this.diaFin = diaFin;
        this.idMes = idMes;
        this.idSeccion = idSeccion;
        this.noMaterial = noMaterial;
        this.idModulo = idModulo;
        this.idConversacion = idConversacion;
    }

    public Integer getIdResumenMes() {
        return idResumenMes;
    }

    public void setIdResumenMes(Integer idResumenMes) {
        this.idResumenMes = idResumenMes;
    }

    public Integer getIdCalendario() {
        return idCalendario;
    }

    public void setIdCalendario(Integer idCalendario) {
        this.idCalendario = idCalendario;
    }

    public Date getDiaInicio() {
        return diaInicio;
    }

    public void setDiaInicio(Date diaInicio) {
        this.diaInicio = diaInicio;
    }

    public Date getDiaFin() {
        return diaFin;
    }

    public void setDiaFin(Date diaFin) {
        this.diaFin = diaFin;
    }

    public Integer getIdMes() {
        return idMes;
    }

    public void setIdMes(Integer idMes) {
        this.idMes = idMes;
    }

    public Integer getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Integer idSeccion) {
        this.idSeccion = idSeccion;
    }

    public Integer getNoMaterial() {
        return noMaterial;
    }

    public void setNoMaterial(Integer noMaterial) {
        this.noMaterial = noMaterial;
    }

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public Integer getIdConversacion() {
        return idConversacion;
    }

    public void setIdConversacion(Integer idConversacion) {
        this.idConversacion = idConversacion;
    }
}
