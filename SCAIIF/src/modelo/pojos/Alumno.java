package modelo.pojos;

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
    private Integer vigente;

    public Alumno() {}

    public Alumno(String matricula, String nombre, String apPaterno, String apMaterno, 
            String correo, boolean lenguaIndigena, Integer vigente) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.correo = correo;
        this.lenguaIndigena = lenguaIndigena;
        this.vigente = vigente;
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

    public Integer getVigente() {
        return vigente;
    }

    public void setVigente(Integer vigente) {
        this.vigente = vigente;
    }

    @Override
    public String toString() {
        return matricula;
    }
    
    

}
