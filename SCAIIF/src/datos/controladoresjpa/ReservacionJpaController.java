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
import datos.entidades.Actividad;
import datos.entidades.Alumno;
import datos.entidades.Reservacion;
import datos.entidades.ReservacionPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author andres
 */
public class ReservacionJpaController implements Serializable {

    public ReservacionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SCAIIFPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reservacion reservacion) throws PreexistingEntityException, Exception {
        if (reservacion.getReservacionPK() == null) {
            reservacion.setReservacionPK(new ReservacionPK());
        }
        reservacion.getReservacionPK().setNoActividad(reservacion.getActividad().getNoActividad());
        reservacion.getReservacionPK().setMatricula(reservacion.getAlumno().getMatricula());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actividad actividad = reservacion.getActividad();
            if (actividad != null) {
                actividad = em.getReference(actividad.getClass(), actividad.getNoActividad());
                reservacion.setActividad(actividad);
            }
            Alumno alumno = reservacion.getAlumno();
            if (alumno != null) {
                alumno = em.getReference(alumno.getClass(), alumno.getMatricula());
                reservacion.setAlumno(alumno);
            }
            em.persist(reservacion);
            if (actividad != null) {
                actividad.getReservacionList().add(reservacion);
                actividad = em.merge(actividad);
            }
            if (alumno != null) {
                alumno.getReservacionList().add(reservacion);
                alumno = em.merge(alumno);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReservacion(reservacion.getReservacionPK()) != null) {
                throw new PreexistingEntityException("Reservacion " + reservacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reservacion reservacion) throws NonexistentEntityException, Exception {
        reservacion.getReservacionPK().setNoActividad(reservacion.getActividad().getNoActividad());
        reservacion.getReservacionPK().setMatricula(reservacion.getAlumno().getMatricula());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reservacion persistentReservacion = em.find(Reservacion.class, reservacion.getReservacionPK());
            Actividad actividadOld = persistentReservacion.getActividad();
            Actividad actividadNew = reservacion.getActividad();
            Alumno alumnoOld = persistentReservacion.getAlumno();
            Alumno alumnoNew = reservacion.getAlumno();
            if (actividadNew != null) {
                actividadNew = em.getReference(actividadNew.getClass(), actividadNew.getNoActividad());
                reservacion.setActividad(actividadNew);
            }
            if (alumnoNew != null) {
                alumnoNew = em.getReference(alumnoNew.getClass(), alumnoNew.getMatricula());
                reservacion.setAlumno(alumnoNew);
            }
            reservacion = em.merge(reservacion);
            if (actividadOld != null && !actividadOld.equals(actividadNew)) {
                actividadOld.getReservacionList().remove(reservacion);
                actividadOld = em.merge(actividadOld);
            }
            if (actividadNew != null && !actividadNew.equals(actividadOld)) {
                actividadNew.getReservacionList().add(reservacion);
                actividadNew = em.merge(actividadNew);
            }
            if (alumnoOld != null && !alumnoOld.equals(alumnoNew)) {
                alumnoOld.getReservacionList().remove(reservacion);
                alumnoOld = em.merge(alumnoOld);
            }
            if (alumnoNew != null && !alumnoNew.equals(alumnoOld)) {
                alumnoNew.getReservacionList().add(reservacion);
                alumnoNew = em.merge(alumnoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ReservacionPK id = reservacion.getReservacionPK();
                if (findReservacion(id) == null) {
                    throw new NonexistentEntityException("The reservacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ReservacionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reservacion reservacion;
            try {
                reservacion = em.getReference(Reservacion.class, id);
                reservacion.getReservacionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reservacion with id " + id + " no longer exists.", enfe);
            }
            Actividad actividad = reservacion.getActividad();
            if (actividad != null) {
                actividad.getReservacionList().remove(reservacion);
                actividad = em.merge(actividad);
            }
            Alumno alumno = reservacion.getAlumno();
            if (alumno != null) {
                alumno.getReservacionList().remove(reservacion);
                alumno = em.merge(alumno);
            }
            em.remove(reservacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reservacion> findReservacionEntities() {
        return findReservacionEntities(true, -1, -1);
    }

    public List<Reservacion> findReservacionEntities(int maxResults, int firstResult) {
        return findReservacionEntities(false, maxResults, firstResult);
    }

    private List<Reservacion> findReservacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reservacion.class));
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

    public Reservacion findReservacion(ReservacionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reservacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getReservacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reservacion> rt = cq.from(Reservacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
