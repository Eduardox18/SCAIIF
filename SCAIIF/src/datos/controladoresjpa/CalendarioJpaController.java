/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.controladoresjpa;

import controlador.exceptions.NonexistentEntityException;
import datos.entidades.Calendario;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import datos.entidades.Conversacion;
import datos.entidades.Curso;
import datos.entidades.MaterialReportar;
import datos.entidades.Modulo;
import datos.entidades.Seccion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author andres
 */
public class CalendarioJpaController implements Serializable {

    public CalendarioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SCAIIFPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Calendario calendario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Conversacion idConversacion = calendario.getIdConversacion();
            if (idConversacion != null) {
                idConversacion = em.getReference(idConversacion.getClass(), idConversacion.getIdConversacion());
                calendario.setIdConversacion(idConversacion);
            }
            Curso nrc = calendario.getNrc();
            if (nrc != null) {
                nrc = em.getReference(nrc.getClass(), nrc.getNrc());
                calendario.setNrc(nrc);
            }
            MaterialReportar noMaterial = calendario.getNoMaterial();
            if (noMaterial != null) {
                noMaterial = em.getReference(noMaterial.getClass(), noMaterial.getNoMaterial());
                calendario.setNoMaterial(noMaterial);
            }
            Modulo idModulo = calendario.getIdModulo();
            if (idModulo != null) {
                idModulo = em.getReference(idModulo.getClass(), idModulo.getIdModulo());
                calendario.setIdModulo(idModulo);
            }
            Seccion idSeccion = calendario.getIdSeccion();
            if (idSeccion != null) {
                idSeccion = em.getReference(idSeccion.getClass(), idSeccion.getIdSeccion());
                calendario.setIdSeccion(idSeccion);
            }
            em.persist(calendario);
            if (idConversacion != null) {
                idConversacion.getCalendarioList().add(calendario);
                idConversacion = em.merge(idConversacion);
            }
            if (nrc != null) {
                nrc.getCalendarioList().add(calendario);
                nrc = em.merge(nrc);
            }
            if (noMaterial != null) {
                noMaterial.getCalendarioList().add(calendario);
                noMaterial = em.merge(noMaterial);
            }
            if (idModulo != null) {
                idModulo.getCalendarioList().add(calendario);
                idModulo = em.merge(idModulo);
            }
            if (idSeccion != null) {
                idSeccion.getCalendarioList().add(calendario);
                idSeccion = em.merge(idSeccion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Calendario calendario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Calendario persistentCalendario = em.find(Calendario.class, calendario.getIdCalendario());
            Conversacion idConversacionOld = persistentCalendario.getIdConversacion();
            Conversacion idConversacionNew = calendario.getIdConversacion();
            Curso nrcOld = persistentCalendario.getNrc();
            Curso nrcNew = calendario.getNrc();
            MaterialReportar noMaterialOld = persistentCalendario.getNoMaterial();
            MaterialReportar noMaterialNew = calendario.getNoMaterial();
            Modulo idModuloOld = persistentCalendario.getIdModulo();
            Modulo idModuloNew = calendario.getIdModulo();
            Seccion idSeccionOld = persistentCalendario.getIdSeccion();
            Seccion idSeccionNew = calendario.getIdSeccion();
            if (idConversacionNew != null) {
                idConversacionNew = em.getReference(idConversacionNew.getClass(), idConversacionNew.getIdConversacion());
                calendario.setIdConversacion(idConversacionNew);
            }
            if (nrcNew != null) {
                nrcNew = em.getReference(nrcNew.getClass(), nrcNew.getNrc());
                calendario.setNrc(nrcNew);
            }
            if (noMaterialNew != null) {
                noMaterialNew = em.getReference(noMaterialNew.getClass(), noMaterialNew.getNoMaterial());
                calendario.setNoMaterial(noMaterialNew);
            }
            if (idModuloNew != null) {
                idModuloNew = em.getReference(idModuloNew.getClass(), idModuloNew.getIdModulo());
                calendario.setIdModulo(idModuloNew);
            }
            if (idSeccionNew != null) {
                idSeccionNew = em.getReference(idSeccionNew.getClass(), idSeccionNew.getIdSeccion());
                calendario.setIdSeccion(idSeccionNew);
            }
            calendario = em.merge(calendario);
            if (idConversacionOld != null && !idConversacionOld.equals(idConversacionNew)) {
                idConversacionOld.getCalendarioList().remove(calendario);
                idConversacionOld = em.merge(idConversacionOld);
            }
            if (idConversacionNew != null && !idConversacionNew.equals(idConversacionOld)) {
                idConversacionNew.getCalendarioList().add(calendario);
                idConversacionNew = em.merge(idConversacionNew);
            }
            if (nrcOld != null && !nrcOld.equals(nrcNew)) {
                nrcOld.getCalendarioList().remove(calendario);
                nrcOld = em.merge(nrcOld);
            }
            if (nrcNew != null && !nrcNew.equals(nrcOld)) {
                nrcNew.getCalendarioList().add(calendario);
                nrcNew = em.merge(nrcNew);
            }
            if (noMaterialOld != null && !noMaterialOld.equals(noMaterialNew)) {
                noMaterialOld.getCalendarioList().remove(calendario);
                noMaterialOld = em.merge(noMaterialOld);
            }
            if (noMaterialNew != null && !noMaterialNew.equals(noMaterialOld)) {
                noMaterialNew.getCalendarioList().add(calendario);
                noMaterialNew = em.merge(noMaterialNew);
            }
            if (idModuloOld != null && !idModuloOld.equals(idModuloNew)) {
                idModuloOld.getCalendarioList().remove(calendario);
                idModuloOld = em.merge(idModuloOld);
            }
            if (idModuloNew != null && !idModuloNew.equals(idModuloOld)) {
                idModuloNew.getCalendarioList().add(calendario);
                idModuloNew = em.merge(idModuloNew);
            }
            if (idSeccionOld != null && !idSeccionOld.equals(idSeccionNew)) {
                idSeccionOld.getCalendarioList().remove(calendario);
                idSeccionOld = em.merge(idSeccionOld);
            }
            if (idSeccionNew != null && !idSeccionNew.equals(idSeccionOld)) {
                idSeccionNew.getCalendarioList().add(calendario);
                idSeccionNew = em.merge(idSeccionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = calendario.getIdCalendario();
                if (findCalendario(id) == null) {
                    throw new NonexistentEntityException("The calendario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Calendario calendario;
            try {
                calendario = em.getReference(Calendario.class, id);
                calendario.getIdCalendario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The calendario with id " + id + " no longer exists.", enfe);
            }
            Conversacion idConversacion = calendario.getIdConversacion();
            if (idConversacion != null) {
                idConversacion.getCalendarioList().remove(calendario);
                idConversacion = em.merge(idConversacion);
            }
            Curso nrc = calendario.getNrc();
            if (nrc != null) {
                nrc.getCalendarioList().remove(calendario);
                nrc = em.merge(nrc);
            }
            MaterialReportar noMaterial = calendario.getNoMaterial();
            if (noMaterial != null) {
                noMaterial.getCalendarioList().remove(calendario);
                noMaterial = em.merge(noMaterial);
            }
            Modulo idModulo = calendario.getIdModulo();
            if (idModulo != null) {
                idModulo.getCalendarioList().remove(calendario);
                idModulo = em.merge(idModulo);
            }
            Seccion idSeccion = calendario.getIdSeccion();
            if (idSeccion != null) {
                idSeccion.getCalendarioList().remove(calendario);
                idSeccion = em.merge(idSeccion);
            }
            em.remove(calendario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Calendario> findCalendarioEntities() {
        return findCalendarioEntities(true, -1, -1);
    }

    public List<Calendario> findCalendarioEntities(int maxResults, int firstResult) {
        return findCalendarioEntities(false, maxResults, firstResult);
    }

    private List<Calendario> findCalendarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Calendario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Calendario findCalendario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Calendario.class, id);
        } finally {
            em.close();
        }
    }

    public int getCalendarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Calendario> rt = cq.from(Calendario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
