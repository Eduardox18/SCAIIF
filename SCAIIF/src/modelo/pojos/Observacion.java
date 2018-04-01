package modelo.pojos;

/**
 *
 * @author lalo
 */
public class Observacion {
    
    private Integer idObservacion;
    private String matricula;
    private Integer noPersonal;
    private String asunto;
    private String comentario;
    
    public Observacion() {}

    public Observacion(Integer idObservacion, String matricula, Integer noPersonal, String asunto, String comentario) {
        this.idObservacion = idObservacion;
        this.matricula = matricula;
        this.noPersonal = noPersonal;
        this.asunto = asunto;
        this.comentario = comentario;
    }

    public Integer getIdObservacion() {
        return idObservacion;
    }

    public void setIdObservacion(Integer idObservacion) {
        this.idObservacion = idObservacion;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getNoPersonal() {
        return noPersonal;
    }

    public void setNoPersonal(Integer noPersonal) {
        this.noPersonal = noPersonal;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    
    
}
