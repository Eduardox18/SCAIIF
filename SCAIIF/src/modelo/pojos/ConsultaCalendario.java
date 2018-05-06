package modelo.pojos;

import java.sql.Date;

public class ConsultaCalendario {
    private String mes;
    private String diaInicio;
    private String diaFin;
    private Integer noModulo;
    private Integer noSeccion;
    private Date fechaLimiteExamen;
    private String nombreMaterial;
    private Integer noConversacion;

    public ConsultaCalendario() {
    }

    public ConsultaCalendario(String mes, String diaInicio, String diaFin, Integer noModulo, Integer noSeccion,
                              Date fechaLimiteExamen, String nombreMaterial, Integer noConversacion) {
        this.mes = mes;
        this.diaInicio = diaInicio;
        this.diaFin = diaFin;
        this.noModulo = noModulo;
        this.noSeccion = noSeccion;
        this.fechaLimiteExamen = fechaLimiteExamen;
        this.nombreMaterial = nombreMaterial;
        this.noConversacion = noConversacion;
    }

    public String getMes() {
        return mes;
    }

    public String getDiaInicio() {
        String dias = diaInicio + " - " + diaFin;
        return dias;
    }

    public void setDiaInicio(String diaInicio) {
        this.diaInicio = diaInicio;
    }

    public String getDiaFin() {
        String dias = diaInicio + " - " + diaFin;
        return dias;
    }

    public void setDiaFin(String diaFin) {
        this.diaFin = diaFin;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Integer getNoModulo() {
        return noModulo;
    }

    public void setNoModulo(Integer noModulo) {
        this.noModulo = noModulo;
    }

    public Integer getNoSeccion() {
        return noSeccion;
    }

    public void setNoSeccion(Integer noSeccion) {
        this.noSeccion = noSeccion;
    }

    public Date getFechaLimiteExamen() {
        return fechaLimiteExamen;
    }

    public void setFechaLimiteExamen(Date fechaLimiteExamen) {
        this.fechaLimiteExamen = fechaLimiteExamen;
    }

    public String getNombreMaterial() {
        return nombreMaterial;
    }

    public void setNombreMaterial(String nombreMaterial) {
        this.nombreMaterial = nombreMaterial;
    }

    public Integer getNoConversacion() {
        return noConversacion;
    }

    public void setNoConversacion(Integer noConversacion) {
        this.noConversacion = noConversacion;
    }
}
