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
public class Alumno {

    private String matricula;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String correo;
    private boolean lenguaIndigena;

    public Alumno() {}

    public Alumno(String matricula, String nombre, String apPaterno, String apMaterno, String correo, boolean lenguaIndigena) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.correo = correo;
        this.lenguaIndigena = lenguaIndigena;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isLenguaIndigena() {
        return lenguaIndigena;
    }

    public void setLenguaIndigena(boolean lenguaIndigena) {
        this.lenguaIndigena = lenguaIndigena;
    }

    @Override
    public String toString() {
        return matricula;
    }
    
    

}
