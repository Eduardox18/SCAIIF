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
public class ObservacionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    @Basic(optional = false)
    @Column(name = "noPersonal")
    private int noPersonal;
    @Basic(optional = false)
    @Column(name = "idObservacion")
    private int idObservacion;

    public ObservacionPK() {
    }

    public ObservacionPK(String matricula, int noPersonal, int idObservacion) {
        this.matricula = matricula;
        this.noPersonal = noPersonal;
        this.idObservacion = idObservacion;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getNoPersonal() {
        return noPersonal;
    }

    public void setNoPersonal(int noPersonal) {
        this.noPersonal = noPersonal;
    }

    public int getIdObservacion() {
        return idObservacion;
    }

    public void setIdObservacion(int idObservacion) {
        this.idObservacion = idObservacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricula != null ? matricula.hashCode() : 0);
        hash += (int) noPersonal;
        hash += (int) idObservacion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObservacionPK)) {
            return false;
        }
        ObservacionPK other = (ObservacionPK) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        if (this.noPersonal != other.noPersonal) {
            return false;
        }
        if (this.idObservacion != other.idObservacion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Entidades.ObservacionPK[ matricula=" + matricula + ", noPersonal=" + noPersonal + ", idObservacion=" + idObservacion + " ]";
    }
    
}
