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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c")
    , @NamedQuery(name = "Curso.findByNrc", query = "SELECT c FROM Curso c WHERE c.nrc = :nrc")
    , @NamedQuery(name = "Curso.findByNombreCurso", query = "SELECT c FROM Curso c WHERE c.nombreCurso = :nombreCurso")
    , @NamedQuery(name = "Curso.findByNoCreditos", query = "SELECT c FROM Curso c WHERE c.noCreditos = :noCreditos")})
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nrc")
    private Integer nrc;
    @Basic(optional = false)
    @Column(name = "nombreCurso")
    private String nombreCurso;
    @Basic(optional = false)
    @Column(name = "noCreditos")
    private int noCreditos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso")
    private List<Induccion> induccionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso")
    private List<Calificacion> calificacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nrc")
    private List<Calendario> calendarioList;
    @JoinColumn(name = "noPersonal", referencedColumnName = "noPersonal")
    @ManyToOne(optional = false)
    private Usuario noPersonal;

    public Curso() {
    }

    public Curso(Integer nrc) {
        this.nrc = nrc;
    }

    public Curso(Integer nrc, String nombreCurso, int noCreditos) {
        this.nrc = nrc;
        this.nombreCurso = nombreCurso;
        this.noCreditos = noCreditos;
    }

    public Integer getNrc() {
        return nrc;
    }

    public void setNrc(Integer nrc) {
        this.nrc = nrc;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public int getNoCreditos() {
        return noCreditos;
    }

    public void setNoCreditos(int noCreditos) {
        this.noCreditos = noCreditos;
    }

    @XmlTransient
    public List<Induccion> getInduccionList() {
        return induccionList;
    }

    public void setInduccionList(List<Induccion> induccionList) {
        this.induccionList = induccionList;
    }

    @XmlTransient
    public List<Calificacion> getCalificacionList() {
        return calificacionList;
    }

    public void setCalificacionList(List<Calificacion> calificacionList) {
        this.calificacionList = calificacionList;
    }

    @XmlTransient
    public List<Calendario> getCalendarioList() {
        return calendarioList;
    }

    public void setCalendarioList(List<Calendario> calendarioList) {
        this.calendarioList = calendarioList;
    }

    public Usuario getNoPersonal() {
        return noPersonal;
    }

    public void setNoPersonal(Usuario noPersonal) {
        this.noPersonal = noPersonal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nrc != null ? nrc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.nrc == null && other.nrc != null) || (this.nrc != null && !this.nrc.equals(other.nrc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Entidades.Curso[ nrc=" + nrc + " ]";
    }
    
}
