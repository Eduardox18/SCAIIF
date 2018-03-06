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
@Table(name = "Modulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modulo.findAll", query = "SELECT m FROM Modulo m")
    , @NamedQuery(name = "Modulo.findByIdModulo", query = "SELECT m FROM Modulo m WHERE m.idModulo = :idModulo")
    , @NamedQuery(name = "Modulo.findByNoModulo", query = "SELECT m FROM Modulo m WHERE m.noModulo = :noModulo")})
public class Modulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idModulo")
    private Integer idModulo;
    @Basic(optional = false)
    @Column(name = "noModulo")
    private int noModulo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idModulo")
    private List<Calendario> calendarioList;

    public Modulo() {
    }

    public Modulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public Modulo(Integer idModulo, int noModulo) {
        this.idModulo = idModulo;
        this.noModulo = noModulo;
    }

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public int getNoModulo() {
        return noModulo;
    }

    public void setNoModulo(int noModulo) {
        this.noModulo = noModulo;
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
        hash += (idModulo != null ? idModulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modulo)) {
            return false;
        }
        Modulo other = (Modulo) object;
        if ((this.idModulo == null && other.idModulo != null) || (this.idModulo != null && !this.idModulo.equals(other.idModulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Entidades.Modulo[ idModulo=" + idModulo + " ]";
    }
    
}
