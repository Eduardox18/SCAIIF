/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.pojos;

/**
 *
 * @author esmeralda
 */
public class Seccion {
    private Integer idSeccion;
    private Integer noSeccion;

    public Seccion() {
    }

    public Seccion(Integer idSeccion, Integer noSeccion) {
        this.idSeccion = idSeccion;
        this.noSeccion = noSeccion;
    }

    public Integer getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Integer idSeccion) {
        this.idSeccion = idSeccion;
    }

    public Integer getNoSeccion() {
        return noSeccion;
    }

    public void setNoSeccion(Integer noSeccion) {
        this.noSeccion = noSeccion;
    }
    
    
    
    
}
