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
@Table(name = "Alumno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alumno.findAll", query = "SELECT a FROM Alumno a")
    , @NamedQuery(name = "Alumno.findByMatricula", query = "SELECT a FROM Alumno a WHERE a.matricula = :matricula")
    , @NamedQuery(name = "Alumno.findByNombre", query = "SELECT a FROM Alumno a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Alumno.findByApPaterno", query = "SELECT a FROM Alumno a WHERE a.apPaterno = :apPaterno")
    , @NamedQuery(name = "Alumno.findByApMaterno", query = "SELECT a FROM Alumno a WHERE a.apMaterno = :apMaterno")
    , @NamedQuery(name = "Alumno.findByCorreo", query = "SELECT a FROM Alumno a WHERE a.correo = :correo")
    , @NamedQuery(name = "Alumno.findByLenguaIndigena", query = "SELECT a FROM Alumno a WHERE a.lenguaIndigena = :lenguaIndigena")})
public class Alumno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apPaterno")
    private String apPaterno;
    @Column(name = "apMaterno")
    private String apMaterno;
    @Basic(optional = false)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @Column(name = "lenguaIndigena")
    private short lenguaIndigena;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumno")
    private List<Reservacion> reservacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumno")
    private List<Induccion> induccionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumno")
    private List<Calificacion> calificacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumno")
    private List<Observacion> observacionList;

    public Alumno() {
    }

    public Alumno(String matricula) {
        this.matricula = matricula;
    }

    public Alumno(String matricula, String nombre, String apPaterno, String correo, short lenguaIndigena) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.correo = correo;
        this.lenguaIndigena = lenguaIndigena;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public short getLenguaIndigena() {
        return lenguaIndigena;
    }

    public void setLenguaIndigena(short lenguaIndigena) {
        this.lenguaIndigena = lenguaIndigena;
    }

    @XmlTransient
    public List<Reservacion> getReservacionList() {
        return reservacionList;
    }

    public void setReservacionList(List<Reservacion> reservacionList) {
        this.reservacionList = reservacionList;
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
    public List<Observacion> getObservacionList() {
        return observacionList;
    }

    public void setObservacionList(List<Observacion> observacionList) {
        this.observacionList = observacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricula != null ? matricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Entidades.Alumno[ matricula=" + matricula + " ]";
    }
    
}
