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
public class Curso {

    private int nrc;
    private String nombreCurso;
    private int noCreditos;
    private int noPersonal;

    public Curso() {}

    public Curso(int nrc, String nombreCurso, int noCreditos, int noPersonal) {
        this.nrc = nrc;
        this.nombreCurso = nombreCurso;
        this.noCreditos = noCreditos;
        this.noPersonal = noPersonal;
    }

    public int getNrc() {
        return nrc;
    }

    public void setNrc(int nrc) {
        this.nrc = nrc;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public int getNoCreditos() {
        return noCreditos;
    }

    public void setNoCreditos(int noCreditos) {
        this.noCreditos = noCreditos;
    }

    public int getNoPersonal() {
        return noPersonal;
    }

    public void setNoPersonal(int noPersonal) {
        this.noPersonal = noPersonal;
    }

}
