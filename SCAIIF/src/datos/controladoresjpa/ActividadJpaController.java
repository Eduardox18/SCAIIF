/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.controladoresjpa;

import controlador.exceptions.IllegalOrphanException;
import controlador.exceptions.NonexistentEntityException;
import datos.entidades.Actividad;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import datos.entidades.Reservacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author andres
 */
public class ActividadJpaController implements Serializable {

    public ActividadJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SCAIIFPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Actividad actividad) {
        if (actividad.getReservacionList() == null) {
            actividad.setReservacionList(new ArrayList<Reservacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Reservacion> attachedReservacionList = new ArrayList<Reservacion>();
            for (Reservacion reservacionListReservacionToAttach : actividad.getReservacionList()) {
                reservacionListReservacionToAttach = em.getReference(reservacionListReservacionToAttach.getClass(), reservacionListReservacionToAttach.getReservacionPK());
                attachedReservacionList.add(reservacionListReservacionToAttach);
            }
            actividad.setReservacionList(attachedReservacionList);
            em.persist(actividad);
            for (Reservacion reservacionListReservacion : actividad.getReservacionList()) {
                Actividad oldActividadOfReservacionListReservacion = reservacionListReservacion.getActividad();
                reservacionListReservacion.setActividad(actividad);
                reservacionListReservacion = em.merge(reservacionListReservacion);
                if (oldActividadOfReservacionListReservacion != null) {
                    oldActividadOfReservacionListReservacion.getReservacionList().remove(reservacionListReservacion);
                    oldActividadOfReservacionListReservacion = em.merge(oldActividadOfReservacionListReservacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Actividad actividad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actividad persistentActividad = em.find(Actividad.class, actividad.getNoActividad());
            List<Reservacion> reservacionListOld = persistentActividad.getReservacionList();
            List<Reservacion> reservacionListNew = actividad.getReservacionList();
            List<String> illegalOrphanMessages = null;
            for (Reservacion reservacionListOldReservacion : reservacionListOld) {
                if (!reservacionListNew.contains(reservacionListOldReservacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Reservacion " + reservacionListOldReservacion + " since its actividad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Reservacion> attachedReservacionListNew = new ArrayList<Reservacion>();
            for (Reservacion reservacionListNewReservacionToAttach : reservacionListNew) {
                reservacionListNewReservacionToAttach = em.getReference(reservacionListNewReservacionToAttach.getClass(), reservacionListNewReservacionToAttach.getReservacionPK());
                attachedReservacionListNew.add(reservacionListNewReservacionToAttach);
            }
            reservacionListNew = attachedReservacionListNew;
            actividad.setReservacionList(reservacionListNew);
            actividad = em.merge(actividad);
            for (Reservacion reservacionListNewReservacion : reservacionListNew) {
                if (!reservacionListOld.contains(reservacionListNewReservacion)) {
                    Actividad oldActividadOfReservacionListNewReservacion = reservacionListNewReservacion.getActividad();
                    reservacionListNewReservacion.setActividad(actividad);
                    reservacionListNewReservacion = em.merge(reservacionListNewReservacion);
                    if (oldActividadOfReservacionListNewReservacion != null && !oldActividadOfReservacionListNewReservacion.equals(actividad)) {
                        oldActividadOfReservacionListNewReservacion.getReservacionList().remove(reservacionListNewReservacion);
                        oldActividadOfReservacionListNewReservacion = em.merge(oldActividadOfReservacionListNewReservacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = actividad.getNoActividad();
                if (findActividad(id) == null) {
                    throw new NonexistentEntityException("The actividad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actividad actividad;
            try {
                actividad = em.getReference(Actividad.class, id);
                actividad.getNoActividad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actividad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Reservacion> reservacionListOrphanCheck = actividad.getReservacionList();
            for (Reservacion reservacionListOrphanCheckReservacion : reservacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Actividad (" + actividad + ") cannot be destroyed since the Reservacion " + reservacionListOrphanCheckReservacion + " in its reservacionList field has a non-nullable actividad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(actividad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Actividad> findActividadEntities() {
        return findActividadEntities(true, -1, -1);
    }

    public List<Actividad> findActividadEntities(int maxResults, int firstResult) {
        return findActividadEntities(false, maxResults, firstResult);
    }

    private List<Actividad> findActividadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Actividad.class));
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

    public Actividad findActividad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Actividad.class, id);
        } finally {
            em.close();
        }
    }

    public int getActividadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Actividad> rt = cq.from(Actividad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
