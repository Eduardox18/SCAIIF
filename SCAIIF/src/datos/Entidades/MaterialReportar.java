/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "MaterialReportar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaterialReportar.findAll", query = "SELECT m FROM MaterialReportar m")
    , @NamedQuery(name = "MaterialReportar.findByNoMaterial", query = "SELECT m FROM MaterialReportar m WHERE m.noMaterial = :noMaterial")
    , @NamedQuery(name = "MaterialReportar.findByNombreMaterial", query = "SELECT m FROM MaterialReportar m WHERE m.nombreMaterial = :nombreMaterial")})
public class MaterialReportar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "noMaterial")
    private Integer noMaterial;
    @Basic(optional = false)
    @Column(name = "nombreMaterial")
    private String nombreMaterial;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "noMaterial")
    private List<Calendario> calendarioList;

    public MaterialReportar() {
    }

    public MaterialReportar(Integer noMaterial) {
        this.noMaterial = noMaterial;
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

    @XmlTransient
    public List<Calendario> getCalendarioList() {
        return calendarioList;
    }

    public void setCalendarioList(List<Calendario> calendarioList) {
        this.calendarioList = calendarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noMaterial != null ? noMaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaterialReportar)) {
            return false;
        }
        MaterialReportar other = (MaterialReportar) object;
        if ((this.noMaterial == null && other.noMaterial != null) || (this.noMaterial != null && !this.noMaterial.equals(other.noMaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Entidades.MaterialReportar[ noMaterial=" + noMaterial + " ]";
    }
    
}
