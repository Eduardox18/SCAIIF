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
@Table(name = "Reservacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservacion.findAll", query = "SELECT r FROM Reservacion r")
    , @NamedQuery(name = "Reservacion.findByNoActividad", query = "SELECT r FROM Reservacion r WHERE r.reservacionPK.noActividad = :noActividad")
    , @NamedQuery(name = "Reservacion.findByMatricula", query = "SELECT r FROM Reservacion r WHERE r.reservacionPK.matricula = :matricula")
    , @NamedQuery(name = "Reservacion.findByNoReservacion", query = "SELECT r FROM Reservacion r WHERE r.reservacionPK.noReservacion = :noReservacion")
    , @NamedQuery(name = "Reservacion.findByFecha", query = "SELECT r FROM Reservacion r WHERE r.fecha = :fecha")
    , @NamedQuery(name = "Reservacion.findByAsistencia", query = "SELECT r FROM Reservacion r WHERE r.asistencia = :asistencia")})
public class Reservacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReservacionPK reservacionPK;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "asistencia")
    private short asistencia;
    @JoinColumn(name = "noActividad", referencedColumnName = "noActividad", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Actividad actividad;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Alumno alumno;

    public Reservacion() {
    }

    public Reservacion(ReservacionPK reservacionPK) {
        this.reservacionPK = reservacionPK;
    }

    public Reservacion(ReservacionPK reservacionPK, Date fecha, short asistencia) {
        this.reservacionPK = reservacionPK;
        this.fecha = fecha;
        this.asistencia = asistencia;
    }

    public Reservacion(int noActividad, String matricula, int noReservacion) {
        this.reservacionPK = new ReservacionPK(noActividad, matricula, noReservacion);
    }

    public ReservacionPK getReservacionPK() {
        return reservacionPK;
    }

    public void setReservacionPK(ReservacionPK reservacionPK) {
        this.reservacionPK = reservacionPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public short getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(short asistencia) {
        this.asistencia = asistencia;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservacionPK != null ? reservacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservacion)) {
            return false;
        }
        Reservacion other = (Reservacion) object;
        if ((this.reservacionPK == null && other.reservacionPK != null) || (this.reservacionPK != null && !this.reservacionPK.equals(other.reservacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Entidades.Reservacion[ reservacionPK=" + reservacionPK + " ]";
    }
    
}
