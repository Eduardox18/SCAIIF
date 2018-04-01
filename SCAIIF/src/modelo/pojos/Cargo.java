package modelo.pojos;

/**
 *
 * @author lalo
 */
public class Cargo {

    private Integer idCargo;
    private String cargo;

    public Cargo() {}

    public Cargo(Integer idCargo, String cargo) {
        this.idCargo = idCargo;
        this.cargo = cargo;
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

}
