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
public class CalificacionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "nrc")
    private int nrc;
    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;

    public CalificacionPK() {
    }

    public CalificacionPK(int nrc, String matricula) {
        this.nrc = nrc;
        this.matricula = matricula;
    }

    public int getNrc() {
        return nrc;
    }

    public void setNrc(int nrc) {
        this.nrc = nrc;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) nrc;
        hash += (matricula != null ? matricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalificacionPK)) {
            return false;
        }
        CalificacionPK other = (CalificacionPK) object;
        if (this.nrc != other.nrc) {
            return false;
        }
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Entidades.CalificacionPK[ nrc=" + nrc + ", matricula=" + matricula + " ]";
    }
    
}
