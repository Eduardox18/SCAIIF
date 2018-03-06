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
@Table(name = "Seccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seccion.findAll", query = "SELECT s FROM Seccion s")
    , @NamedQuery(name = "Seccion.findByIdSeccion", query = "SELECT s FROM Seccion s WHERE s.idSeccion = :idSeccion")
    , @NamedQuery(name = "Seccion.findByNoSeccion", query = "SELECT s FROM Seccion s WHERE s.noSeccion = :noSeccion")})
public class Seccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSeccion")
    private Integer idSeccion;
    @Basic(optional = false)
    @Column(name = "noSeccion")
    private int noSeccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSeccion")
    private List<Calendario> calendarioList;

    public Seccion() {
    }

    public Seccion(Integer idSeccion) {
        this.idSeccion = idSeccion;
    }

    public Seccion(Integer idSeccion, int noSeccion) {
        this.idSeccion = idSeccion;
        this.noSeccion = noSeccion;
    }

    public Integer getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Integer idSeccion) {
        this.idSeccion = idSeccion;
    }

    public int getNoSeccion() {
        return noSeccion;
    }

    public void setNoSeccion(int noSeccion) {
        this.noSeccion = noSeccion;
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
        hash += (idSeccion != null ? idSeccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seccion)) {
            return false;
        }
        Seccion other = (Seccion) object;
        if ((this.idSeccion == null && other.idSeccion != null) || (this.idSeccion != null && !this.idSeccion.equals(other.idSeccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Entidades.Seccion[ idSeccion=" + idSeccion + " ]";
    }
    
}
