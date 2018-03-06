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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "Calendario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calendario.findAll", query = "SELECT c FROM Calendario c")
    , @NamedQuery(name = "Calendario.findByIdCalendario", query = "SELECT c FROM Calendario c WHERE c.idCalendario = :idCalendario")
    , @NamedQuery(name = "Calendario.findByMes", query = "SELECT c FROM Calendario c WHERE c.mes = :mes")
    , @NamedQuery(name = "Calendario.findByPeriodo", query = "SELECT c FROM Calendario c WHERE c.periodo = :periodo")
    , @NamedQuery(name = "Calendario.findByDiaInicio", query = "SELECT c FROM Calendario c WHERE c.diaInicio = :diaInicio")
    , @NamedQuery(name = "Calendario.findByDiaFin", query = "SELECT c FROM Calendario c WHERE c.diaFin = :diaFin")
    , @NamedQuery(name = "Calendario.findByDiasFestivos", query = "SELECT c FROM Calendario c WHERE c.diasFestivos = :diasFestivos")
    , @NamedQuery(name = "Calendario.findByFechaLimiteExamen", query = "SELECT c FROM Calendario c WHERE c.fechaLimiteExamen = :fechaLimiteExamen")
    , @NamedQuery(name = "Calendario.findByInicioVacaciones", query = "SELECT c FROM Calendario c WHERE c.inicioVacaciones = :inicioVacaciones")
    , @NamedQuery(name = "Calendario.findByFinVacaciones", query = "SELECT c FROM Calendario c WHERE c.finVacaciones = :finVacaciones")})
public class Calendario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCalendario")
    private Integer idCalendario;
    @Basic(optional = false)
    @Column(name = "mes")
    private String mes;
    @Basic(optional = false)
    @Column(name = "periodo")
    private String periodo;
    @Basic(optional = false)
    @Column(name = "diaInicio")
    private int diaInicio;
    @Basic(optional = false)
    @Column(name = "diaFin")
    private int diaFin;
    @Basic(optional = false)
    @Column(name = "diasFestivos")
    private int diasFestivos;
    @Basic(optional = false)
    @Column(name = "fechaLimiteExamen")
    @Temporal(TemporalType.DATE)
    private Date fechaLimiteExamen;
    @Basic(optional = false)
    @Column(name = "inicioVacaciones")
    @Temporal(TemporalType.DATE)
    private Date inicioVacaciones;
    @Basic(optional = false)
    @Column(name = "finVacaciones")
    @Temporal(TemporalType.DATE)
    private Date finVacaciones;
    @JoinColumn(name = "idConversacion", referencedColumnName = "idConversacion")
    @ManyToOne(optional = false)
    private Conversacion idConversacion;
    @JoinColumn(name = "nrc", referencedColumnName = "nrc")
    @ManyToOne(optional = false)
    private Curso nrc;
    @JoinColumn(name = "noMaterial", referencedColumnName = "noMaterial")
    @ManyToOne(optional = false)
    private MaterialReportar noMaterial;
    @JoinColumn(name = "idModulo", referencedColumnName = "idModulo")
    @ManyToOne(optional = false)
    private Modulo idModulo;
    @JoinColumn(name = "idSeccion", referencedColumnName = "idSeccion")
    @ManyToOne(optional = false)
    private Seccion idSeccion;

    public Calendario() {
    }

    public Calendario(Integer idCalendario) {
        this.idCalendario = idCalendario;
    }

    public Calendario(Integer idCalendario, String mes, String periodo, int diaInicio, int diaFin, int diasFestivos, Date fechaLimiteExamen, Date inicioVacaciones, Date finVacaciones) {
        this.idCalendario = idCalendario;
        this.mes = mes;
        this.periodo = periodo;
        this.diaInicio = diaInicio;
        this.diaFin = diaFin;
        this.diasFestivos = diasFestivos;
        this.fechaLimiteExamen = fechaLimiteExamen;
        this.inicioVacaciones = inicioVacaciones;
        this.finVacaciones = finVacaciones;
    }

    public Integer getIdCalendario() {
        return idCalendario;
    }

    public void setIdCalendario(Integer idCalendario) {
        this.idCalendario = idCalendario;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getDiaInicio() {
        return diaInicio;
    }

    public void setDiaInicio(int diaInicio) {
        this.diaInicio = diaInicio;
    }

    public int getDiaFin() {
        return diaFin;
    }

    public void setDiaFin(int diaFin) {
        this.diaFin = diaFin;
    }

    public int getDiasFestivos() {
        return diasFestivos;
    }

    public void setDiasFestivos(int diasFestivos) {
        this.diasFestivos = diasFestivos;
    }

    public Date getFechaLimiteExamen() {
        return fechaLimiteExamen;
    }

    public void setFechaLimiteExamen(Date fechaLimiteExamen) {
        this.fechaLimiteExamen = fechaLimiteExamen;
    }

    public Date getInicioVacaciones() {
        return inicioVacaciones;
    }

    public void setInicioVacaciones(Date inicioVacaciones) {
        this.inicioVacaciones = inicioVacaciones;
    }

    public Date getFinVacaciones() {
        return finVacaciones;
    }

    public void setFinVacaciones(Date finVacaciones) {
        this.finVacaciones = finVacaciones;
    }

    public Conversacion getIdConversacion() {
        return idConversacion;
    }

    public void setIdConversacion(Conversacion idConversacion) {
        this.idConversacion = idConversacion;
    }

    public Curso getNrc() {
        return nrc;
    }

    public void setNrc(Curso nrc) {
        this.nrc = nrc;
    }

    public MaterialReportar getNoMaterial() {
        return noMaterial;
    }

    public void setNoMaterial(MaterialReportar noMaterial) {
        this.noMaterial = noMaterial;
    }

    public Modulo getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Modulo idModulo) {
        this.idModulo = idModulo;
    }

    public Seccion getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Seccion idSeccion) {
        this.idSeccion = idSeccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCalendario != null ? idCalendario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calendario)) {
            return false;
        }
        Calendario other = (Calendario) object;
        if ((this.idCalendario == null && other.idCalendario != null) || (this.idCalendario != null && !this.idCalendario.equals(other.idCalendario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Entidades.Calendario[ idCalendario=" + idCalendario + " ]";
    }
    
}
