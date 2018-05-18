package modelo.pojos;

import java.sql.Date;

public class MesPeriodo {
    private Integer idMes;
    private Integer idPeriodo;
    private Date diaInicio;
    private Date diaFin;

    public MesPeriodo() {
    }

    public MesPeriodo(Integer idMes, Integer idPeriodo, Date diaInicio, Date diaFin) {
        this.idMes = idMes;
        this.idPeriodo = idPeriodo;
        this.diaInicio = diaInicio;
        this.diaFin = diaFin;
    }

    public Integer getIdMes() {
        return idMes;
    }

    public void setIdMes(Integer idMes) {
        this.idMes = idMes;
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
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
}
