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
public class Modulo {
    private Integer idModulo;
    private Integer noModulo;

    public Modulo() {
    }

    public Modulo(Integer idModulo, Integer noModulo) {
        this.idModulo = idModulo;
        this.noModulo = noModulo;
    }

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public Integer getNoModulo() {
        return noModulo;
    }

    public void setNoModulo(Integer noModulo) {
        this.noModulo = noModulo;
    }
    
    
    
    
}
