/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "Aviso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aviso.findAll", query = "SELECT a FROM Aviso a")
    , @NamedQuery(name = "Aviso.findByNoAviso", query = "SELECT a FROM Aviso a WHERE a.noAviso = :noAviso")
    , @NamedQuery(name = "Aviso.findByAsunto", query = "SELECT a FROM Aviso a WHERE a.asunto = :asunto")
    , @NamedQuery(name = "Aviso.findByMensaje", query = "SELECT a FROM Aviso a WHERE a.mensaje = :mensaje")
    , @NamedQuery(name = "Aviso.findByFechaCreacion", query = "SELECT a FROM Aviso a WHERE a.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Aviso.findByFechaLimite", query = "SELECT a FROM Aviso a WHERE a.fechaLimite = :fechaLimite")})
public class Aviso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "noAviso")
    private Integer noAviso;
    @Basic(optional = false)
    @Column(name = "asunto")
    private String asunto;
    @Basic(optional = false)
    @Column(name = "mensaje")
    private String mensaje;
    @Basic(optional = false)
    @Column(name = "fechaCreacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Basic(optional = false)
    @Column(name = "fechaLimite")
    @Temporal(TemporalType.DATE)
    private Date fechaLimite;

    public Aviso() {
    }

    public Aviso(Integer noAviso) {
        this.noAviso = noAviso;
    }

    public Aviso(Integer noAviso, String asunto, String mensaje, Date fechaCreacion, Date fechaLimite) {
        this.noAviso = noAviso;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.fechaLimite = fechaLimite;
    }

    public Integer getNoAviso() {
        return noAviso;
    }

    public void setNoAviso(Integer noAviso) {
        this.noAviso = noAviso;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noAviso != null ? noAviso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aviso)) {
            return false;
        }
        Aviso other = (Aviso) object;
        if ((this.noAviso == null && other.noAviso != null) || (this.noAviso != null && !this.noAviso.equals(other.noAviso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Entidades.Aviso[ noAviso=" + noAviso + " ]";
    }
    
}
