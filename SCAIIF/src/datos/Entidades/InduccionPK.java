/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author andres
 */
@Embeddable
public class InduccionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    @Basic(optional = false)
    @Column(name = "nrc")
    private int nrc;
    @Basic(optional = false)
    @Column(name = "idInduccion")
    private int idInduccion;

    public InduccionPK() {
    }

    public InduccionPK(String matricula, int nrc, int idInduccion) {
        this.matricula = matricula;
        this.nrc = nrc;
        this.idInduccion = idInduccion;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getNrc() {
        return nrc;
    }

    public void setNrc(int nrc) {
        this.nrc = nrc;
    }

    public int getIdInduccion() {
        return idInduccion;
    }

    public void setIdInduccion(int idInduccion) {
        this.idInduccion = idInduccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricula != null ? matricula.hashCode() : 0);
        hash += (int) nrc;
        hash += (int) idInduccion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InduccionPK)) {
            return false;
        }
        InduccionPK other = (InduccionPK) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        if (this.nrc != other.nrc) {
            return false;
        }
        if (this.idInduccion != other.idInduccion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Entidades.InduccionPK[ matricula=" + matricula + ", nrc=" + nrc + ", idInduccion=" + idInduccion + " ]";
    }
    
}
