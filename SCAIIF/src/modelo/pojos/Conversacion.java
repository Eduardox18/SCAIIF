package modelo.pojos;

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

    @Override
    public String toString() {
        return String.valueOf(noConversacion);
    }
}
