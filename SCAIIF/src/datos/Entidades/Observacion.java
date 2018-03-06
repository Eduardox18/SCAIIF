/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "Observacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Observacion.findAll", query = "SELECT o FROM Observacion o")
    , @NamedQuery(name = "Observacion.findByMatricula", query = "SELECT o FROM Observacion o WHERE o.observacionPK.matricula = :matricula")
    , @NamedQuery(name = "Observacion.findByNoPersonal", query = "SELECT o FROM Observacion o WHERE o.observacionPK.noPersonal = :noPersonal")
    , @NamedQuery(name = "Observacion.findByIdObservacion", query = "SELECT o FROM Observacion o WHERE o.observacionPK.idObservacion = :idObservacion")
    , @NamedQuery(name = "Observacion.findByAsunto", query = "SELECT o FROM Observacion o WHERE o.asunto = :asunto")
    , @NamedQuery(name = "Observacion.findByComentario", query = "SELECT o FROM Observacion o WHERE o.comentario = :comentario")})
public class Observacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ObservacionPK observacionPK;
    @Basic(optional = false)
    @Column(name = "asunto")
    private String asunto;
    @Basic(optional = false)
    @Column(name = "comentario")
    private String comentario;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Alumno alumno;
    @JoinColumn(name = "noPersonal", referencedColumnName = "noPersonal", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Observacion() {
    }

    public Observacion(ObservacionPK observacionPK) {
        this.observacionPK = observacionPK;
    }

    public Observacion(ObservacionPK observacionPK, String asunto, String comentario) {
        this.observacionPK = observacionPK;
        this.asunto = asunto;
        this.comentario = comentario;
    }

    public Observacion(String matricula, int noPersonal, int idObservacion) {
        this.observacionPK = new ObservacionPK(matricula, noPersonal, idObservacion);
    }

    public ObservacionPK getObservacionPK() {
        return observacionPK;
    }

    public void setObservacionPK(ObservacionPK observacionPK) {
        this.observacionPK = observacionPK;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (observacionPK != null ? observacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Observacion)) {
            return false;
        }
        Observacion other = (Observacion) object;
        if ((this.observacionPK == null && other.observacionPK != null) || (this.observacionPK != null && !this.observacionPK.equals(other.observacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Entidades.Observacion[ observacionPK=" + observacionPK + " ]";
    }
    
}
