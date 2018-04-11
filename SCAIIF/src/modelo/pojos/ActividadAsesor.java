/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojos;

import java.sql.Time;
import java.sql.Date;

/**
 *
 * @author andres
 */
public class ActividadAsesor {
    
    private Integer noActividad;
    private String nombre;
    private Time horaInicio;
    private Time horaFin;
    private Date fecha;
    private String nombreAsesor;
    private String apPaterno;
    private String apMaterno;

    public ActividadAsesor() {
    }

    public ActividadAsesor(Integer noActividad, String nombre, Time horaInicio, Time horaFin,
            Date fecha, String nombreAsesor) {
        this.noActividad = noActividad;
        this.nombre = nombre;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fecha = fecha;
        this.nombreAsesor = nombreAsesor;
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
    
    public String getNombreAsesor() {
        String nombreCompleto;
        if (this.apMaterno == null) {
            nombreCompleto = this.apPaterno + " " + this.nombreAsesor;
        } else {
            nombreCompleto = this.apPaterno + " " + this.apMaterno + " " 
                    + this.nombreAsesor;
        }
        
        return nombreCompleto;
    }

    public void setNombreAsesor(String nombreAsesor) {
        this.nombreAsesor = nombreAsesor;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }
}
