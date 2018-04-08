package modelo.pojos;

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

    @Override
    public String toString() {
        return String.valueOf(noModulo);
    }
}
