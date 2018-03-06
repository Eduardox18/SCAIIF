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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "Usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByNoPersonal", query = "SELECT u FROM Usuario u WHERE u.noPersonal = :noPersonal")
    , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuario.findByApPaterno", query = "SELECT u FROM Usuario u WHERE u.apPaterno = :apPaterno")
    , @NamedQuery(name = "Usuario.findByApMaterno", query = "SELECT u FROM Usuario u WHERE u.apMaterno = :apMaterno")
    , @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo")
    , @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "noPersonal")
    private Integer noPersonal;
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
    @Column(name = "password")
    private String password;
    @JoinColumn(name = "idCargo", referencedColumnName = "idCargo")
    @ManyToOne(optional = false)
    private Cargo idCargo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Observacion> observacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "noPersonal")
    private List<Curso> cursoList;

    public Usuario() {
    }

    public Usuario(Integer noPersonal) {
        this.noPersonal = noPersonal;
    }

    public Usuario(Integer noPersonal, String nombre, String apPaterno, String correo, String password) {
        this.noPersonal = noPersonal;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.correo = correo;
        this.password = password;
    }

    public Integer getNoPersonal() {
        return noPersonal;
    }

    public void setNoPersonal(Integer noPersonal) {
        this.noPersonal = noPersonal;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cargo getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Cargo idCargo) {
        this.idCargo = idCargo;
    }

    @XmlTransient
    public List<Observacion> getObservacionList() {
        return observacionList;
    }

    public void setObservacionList(List<Observacion> observacionList) {
        this.observacionList = observacionList;
    }

    @XmlTransient
    public List<Curso> getCursoList() {
        return cursoList;
    }

    public void setCursoList(List<Curso> cursoList) {
        this.cursoList = cursoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noPersonal != null ? noPersonal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.noPersonal == null && other.noPersonal != null) || (this.noPersonal != null && !this.noPersonal.equals(other.noPersonal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Entidades.Usuario[ noPersonal=" + noPersonal + " ]";
    }
    
}
