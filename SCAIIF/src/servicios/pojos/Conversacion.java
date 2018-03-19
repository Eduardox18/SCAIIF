/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.pojos;

/**
 *
 * @author Esmeralda
 */
public class Conversacion {
    private Integer idConversacion;
    private Integer noConversacion;
    
    public Conversacion (){}

    public Conversacion(Integer idConversacion, Integer noConversacion) {
        this.idConversacion = idConversacion;
        this.noConversacion = noConversacion;
    }

    public Integer getIdConversacion() {
        return idConversacion;
    }

    public void setIdConversacion(Integer idConversacion) {
        this.idConversacion = idConversacion;
    }

    public Integer getNoConversacion() {
        return noConversacion;
    }

    public void setNoConversacion(Integer noConversacion) {
        this.noConversacion = noConversacion;
    }
    
    
    
}
