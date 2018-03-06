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
@Table(name = "Conversacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conversacion.findAll", query = "SELECT c FROM Conversacion c")
    , @NamedQuery(name = "Conversacion.findByIdConversacion", query = "SELECT c FROM Conversacion c WHERE c.idConversacion = :idConversacion")
    , @NamedQuery(name = "Conversacion.findByNoConversacion", query = "SELECT c FROM Conversacion c WHERE c.noConversacion = :noConversacion")})
public class Conversacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idConversacion")
    private Integer idConversacion;
    @Basic(optional = false)
    @Column(name = "noConversacion")
    private int noConversacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConversacion")
    private List<Calendario> calendarioList;

    public Conversacion() {
    }

    public Conversacion(Integer idConversacion) {
        this.idConversacion = idConversacion;
    }

    public Conversacion(Integer idConversacion, int noConversacion) {
        this.idConversacion = idConversacion;
        this.noConversacion = noConversacion;
    }

    public Integer getIdConversacion() {
        return idConversacion;
    }

    public void setIdConversacion(Integer idConversacion) {
        this.idConversacion = idConversacion;
    }

    public int getNoConversacion() {
        return noConversacion;
    }

    public void setNoConversacion(int noConversacion) {
        this.noConversacion = noConversacion;
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
        hash += (idConversacion != null ? idConversacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conversacion)) {
            return false;
        }
        Conversacion other = (Conversacion) object;
        if ((this.idConversacion == null && other.idConversacion != null) || (this.idConversacion != null && !this.idConversacion.equals(other.idConversacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Entidades.Conversacion[ idConversacion=" + idConversacion + " ]";
    }
    
}
