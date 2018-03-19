package servicios.pojos;

/**
 *
 * @author lalo
 */
public class Observacion {
    
    private int idObservacion;
    private String matricula;
    private int noPersonal;
    private String asunto;
    private String comentario;
    
    public Observacion() {}

    public Observacion(int idObservacion, String matricula, int noPersonal, String asunto, String comentario) {
        this.idObservacion = idObservacion;
        this.matricula = matricula;
        this.noPersonal = noPersonal;
        this.asunto = asunto;
        this.comentario = comentario;
    }

    public int getIdObservacion() {
        return idObservacion;
    }

    public void setIdObservacion(int idObservacion) {
        this.idObservacion = idObservacion;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getNoPersonal() {
        return noPersonal;
    }

    public void setNoPersonal(int noPersonal) {
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
