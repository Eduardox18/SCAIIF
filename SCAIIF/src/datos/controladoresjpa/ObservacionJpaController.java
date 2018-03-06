/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.controladoresjpa;

import controlador.exceptions.NonexistentEntityException;
import controlador.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import datos.entidades.Alumno;
import datos.entidades.Observacion;
import datos.entidades.ObservacionPK;
import datos.entidades.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author andres
 */
public class ObservacionJpaController implements Serializable {

    public ObservacionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SCAIIFPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Observacion observacion) throws PreexistingEntityException, Exception {
        if (observacion.getObservacionPK() == null) {
            observacion.setObservacionPK(new ObservacionPK());
        }
        observacion.getObservacionPK().setNoPersonal(observacion.getUsuario().getNoPersonal());
        observacion.getObservacionPK().setMatricula(observacion.getAlumno().getMatricula());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno alumno = observacion.getAlumno();
            if (alumno != null) {
                alumno = em.getReference(alumno.getClass(), alumno.getMatricula());
                observacion.setAlumno(alumno);
            }
            Usuario usuario = observacion.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getNoPersonal());
                observacion.setUsuario(usuario);
            }
            em.persist(observacion);
            if (alumno != null) {
                alumno.getObservacionList().add(observacion);
                alumno = em.merge(alumno);
            }
            if (usuario != null) {
                usuario.getObservacionList().add(observacion);
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findObservacion(observacion.getObservacionPK()) != null) {
                throw new PreexistingEntityException("Observacion " + observacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Observacion observacion) throws NonexistentEntityException, Exception {
        observacion.getObservacionPK().setNoPersonal(observacion.getUsuario().getNoPersonal());
        observacion.getObservacionPK().setMatricula(observacion.getAlumno().getMatricula());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Observacion persistentObservacion = em.find(Observacion.class, observacion.getObservacionPK());
            Alumno alumnoOld = persistentObservacion.getAlumno();
            Alumno alumnoNew = observacion.getAlumno();
            Usuario usuarioOld = persistentObservacion.getUsuario();
            Usuario usuarioNew = observacion.getUsuario();
            if (alumnoNew != null) {
                alumnoNew = em.getReference(alumnoNew.getClass(), alumnoNew.getMatricula());
                observacion.setAlumno(alumnoNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getNoPersonal());
                observacion.setUsuario(usuarioNew);
            }
            observacion = em.merge(observacion);
            if (alumnoOld != null && !alumnoOld.equals(alumnoNew)) {
                alumnoOld.getObservacionList().remove(observacion);
                alumnoOld = em.merge(alumnoOld);
            }
            if (alumnoNew != null && !alumnoNew.equals(alumnoOld)) {
                alumnoNew.getObservacionList().add(observacion);
                alumnoNew = em.merge(alumnoNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getObservacionList().remove(observacion);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getObservacionList().add(observacion);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ObservacionPK id = observacion.getObservacionPK();
                if (findObservacion(id) == null) {
                    throw new NonexistentEntityException("The observacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ObservacionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Observacion observacion;
            try {
                observacion = em.getReference(Observacion.class, id);
                observacion.getObservacionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The observacion with id " + id + " no longer exists.", enfe);
            }
            Alumno alumno = observacion.getAlumno();
            if (alumno != null) {
                alumno.getObservacionList().remove(observacion);
                alumno = em.merge(alumno);
            }
            Usuario usuario = observacion.getUsuario();
            if (usuario != null) {
                usuario.getObservacionList().remove(observacion);
                usuario = em.merge(usuario);
            }
            em.remove(observacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Observacion> findObservacionEntities() {
        return findObservacionEntities(true, -1, -1);
    }

    public List<Observacion> findObservacionEntities(int maxResults, int firstResult) {
        return findObservacionEntities(false, maxResults, firstResult);
    }

    private List<Observacion> findObservacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Observacion.class));
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

    public Observacion findObservacion(ObservacionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Observacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getObservacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Observacion> rt = cq.from(Observacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
