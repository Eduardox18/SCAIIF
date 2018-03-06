/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.pojos;

/**
 *
 * @author lalo
 */
public class Calificacion {

    private int nrc;
    private String matricula;
    private double calificacion;

    public Calificacion() {}

    public Calificacion(int nrc, String matricula, double calificacion) {
        this.nrc = nrc;
        this.matricula = matricula;
        this.calificacion = calificacion;
    }

    public int getNrc() {
        return nrc;
    }

    public void setNrc(int nrc) {
        this.nrc = nrc;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

}
