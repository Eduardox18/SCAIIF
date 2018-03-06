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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Induccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Induccion.findAll", query = "SELECT i FROM Induccion i")
    , @NamedQuery(name = "Induccion.findByMatricula", query = "SELECT i FROM Induccion i WHERE i.induccionPK.matricula = :matricula")
    , @NamedQuery(name = "Induccion.findByNrc", query = "SELECT i FROM Induccion i WHERE i.induccionPK.nrc = :nrc")
    , @NamedQuery(name = "Induccion.findByIdInduccion", query = "SELECT i FROM Induccion i WHERE i.induccionPK.idInduccion = :idInduccion")
    , @NamedQuery(name = "Induccion.findByCursoInduccion", query = "SELECT i FROM Induccion i WHERE i.cursoInduccion = :cursoInduccion")
    , @NamedQuery(name = "Induccion.findByPrimeraAsesoria", query = "SELECT i FROM Induccion i WHERE i.primeraAsesoria = :primeraAsesoria")
    , @NamedQuery(name = "Induccion.findByFecha", query = "SELECT i FROM Induccion i WHERE i.fecha = :fecha")})
public class Induccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InduccionPK induccionPK;
    @Basic(optional = false)
    @Column(name = "cursoInduccion")
    private short cursoInduccion;
    @Basic(optional = false)
    @Column(name = "primeraAsesoria")
    private short primeraAsesoria;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Alumno alumno;
    @JoinColumn(name = "nrc", referencedColumnName = "nrc", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Curso curso;

    public Induccion() {
    }

    public Induccion(InduccionPK induccionPK) {
        this.induccionPK = induccionPK;
    }

    public Induccion(InduccionPK induccionPK, short cursoInduccion, short primeraAsesoria, Date fecha) {
        this.induccionPK = induccionPK;
        this.cursoInduccion = cursoInduccion;
        this.primeraAsesoria = primeraAsesoria;
        this.fecha = fecha;
    }

    public Induccion(String matricula, int nrc, int idInduccion) {
        this.induccionPK = new InduccionPK(matricula, nrc, idInduccion);
    }

    public InduccionPK getInduccionPK() {
        return induccionPK;
    }

    public void setInduccionPK(InduccionPK induccionPK) {
        this.induccionPK = induccionPK;
    }

    public short getCursoInduccion() {
        return cursoInduccion;
    }

    public void setCursoInduccion(short cursoInduccion) {
        this.cursoInduccion = cursoInduccion;
    }

    public short getPrimeraAsesoria() {
        return primeraAsesoria;
    }

    public void setPrimeraAsesoria(short primeraAsesoria) {
        this.primeraAsesoria = primeraAsesoria;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (induccionPK != null ? induccionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Induccion)) {
            return false;
        }
        Induccion other = (Induccion) object;
        if ((this.induccionPK == null && other.induccionPK != null) || (this.induccionPK != null && !this.induccionPK.equals(other.induccionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Entidades.Induccion[ induccionPK=" + induccionPK + " ]";
    }
    
}
