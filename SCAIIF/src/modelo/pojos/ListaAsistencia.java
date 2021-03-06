package modelo.pojos;

/**
 *
 * @author lalo
 */
public class ListaAsistencia {
    
    private Integer noActividad;
    private String matricula;
    private String nombre;
    private String apPaterno;
    private String apMaterno;

    public ListaAsistencia() {
    }

    public ListaAsistencia(Integer noActividad, String matricula, String nombre, String apPaterno, String apMaterno) {
        this.noActividad = noActividad;
        this.matricula = matricula;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
    }

    public Integer getNoActividad() {
        return noActividad;
    }

    public void setNoActividad(Integer noActividad) {
        this.noActividad = noActividad;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        String nombreCompleto;
        if (this.apMaterno == null) {
            nombreCompleto = this.apPaterno + " " + this.nombre;
        } else {
            nombreCompleto = this.apPaterno + " " + this.apMaterno + " " + this.nombre;
        }
        
        return nombreCompleto;
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
    
    public String getNombreCompleto() {
        String nombreCompleto;
        if (this.apMaterno == null) {
            nombreCompleto = this.apPaterno + " " + this.nombre;
        } else {
            nombreCompleto = this.apPaterno + " " + this.apMaterno + " " + this.nombre;
        }
        
        return nombreCompleto;
    }
}
