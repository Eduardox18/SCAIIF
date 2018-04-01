package modelo.pojos;

/**
 *
 * @author lalo
 */
public class MaterialReportar {
    private Integer noMaterial;
    private String nombreMaterial;

    public MaterialReportar() {
    }

    public MaterialReportar(Integer noMaterial, String nombreMaterial) {
        this.noMaterial = noMaterial;
        this.nombreMaterial = nombreMaterial;
    }

    public Integer getNoMaterial() {
        return noMaterial;
    }

    public void setNoMaterial(Integer noMaterial) {
        this.noMaterial = noMaterial;
    }

    public String getNombreMaterial() {
        return nombreMaterial;
    }

    public void setNombreMaterial(String nombreMaterial) {
        this.nombreMaterial = nombreMaterial;
    }
}
