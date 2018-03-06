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
public class ReservacionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "noActividad")
    private int noActividad;
    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    @Basic(optional = false)
    @Column(name = "noReservacion")
    private int noReservacion;

    public ReservacionPK() {
    }

    public ReservacionPK(int noActividad, String matricula, int noReservacion) {
        this.noActividad = noActividad;
        this.matricula = matricula;
        this.noReservacion = noReservacion;
    }

    public int getNoActividad() {
        return noActividad;
    }

    public void setNoActividad(int noActividad) {
        this.noActividad = noActividad;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getNoReservacion() {
        return noReservacion;
    }

    public void setNoReservacion(int noReservacion) {
        this.noReservacion = noReservacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) noActividad;
        hash += (matricula != null ? matricula.hashCode() : 0);
        hash += (int) noReservacion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservacionPK)) {
            return false;
        }
        ReservacionPK other = (ReservacionPK) object;
        if (this.noActividad != other.noActividad) {
            return false;
        }
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        if (this.noReservacion != other.noReservacion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Entidades.ReservacionPK[ noActividad=" + noActividad + ", matricula=" + matricula + ", noReservacion=" + noReservacion + " ]";
    }
    
}
