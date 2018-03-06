/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.controladoresjpa;

import controlador.exceptions.IllegalOrphanException;
import controlador.exceptions.NonexistentEntityException;
import controlador.exceptions.PreexistingEntityException;
import datos.entidades.Alumno;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import datos.entidades.Reservacion;
import java.util.ArrayList;
import java.util.List;
import datos.entidades.Induccion;
import datos.entidades.Calificacion;
import datos.entidades.Observacion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author andres
 */
public class AlumnoJpaController implements Serializable {

    public AlumnoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SCAIIFPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Alumno alumno) throws PreexistingEntityException, Exception {
        if (alumno.getReservacionList() == null) {
            alumno.setReservacionList(new ArrayList<Reservacion>());
        }
        if (alumno.getInduccionList() == null) {
            alumno.setInduccionList(new ArrayList<Induccion>());
        }
        if (alumno.getCalificacionList() == null) {
            alumno.setCalificacionList(new ArrayList<Calificacion>());
        }
        if (alumno.getObservacionList() == null) {
            alumno.setObservacionList(new ArrayList<Observacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Reservacion> attachedReservacionList = new ArrayList<Reservacion>();
            for (Reservacion reservacionListReservacionToAttach : alumno.getReservacionList()) {
                reservacionListReservacionToAttach = em.getReference(reservacionListReservacionToAttach.getClass(), reservacionListReservacionToAttach.getReservacionPK());
                attachedReservacionList.add(reservacionListReservacionToAttach);
            }
            alumno.setReservacionList(attachedReservacionList);
            List<Induccion> attachedInduccionList = new ArrayList<Induccion>();
            for (Induccion induccionListInduccionToAttach : alumno.getInduccionList()) {
                induccionListInduccionToAttach = em.getReference(induccionListInduccionToAttach.getClass(), induccionListInduccionToAttach.getInduccionPK());
                attachedInduccionList.add(induccionListInduccionToAttach);
            }
            alumno.setInduccionList(attachedInduccionList);
            List<Calificacion> attachedCalificacionList = new ArrayList<Calificacion>();
            for (Calificacion calificacionListCalificacionToAttach : alumno.getCalificacionList()) {
                calificacionListCalificacionToAttach = em.getReference(calificacionListCalificacionToAttach.getClass(), calificacionListCalificacionToAttach.getCalificacionPK());
                attachedCalificacionList.add(calificacionListCalificacionToAttach);
            }
            alumno.setCalificacionList(attachedCalificacionList);
            List<Observacion> attachedObservacionList = new ArrayList<Observacion>();
            for (Observacion observacionListObservacionToAttach : alumno.getObservacionList()) {
                observacionListObservacionToAttach = em.getReference(observacionListObservacionToAttach.getClass(), observacionListObservacionToAttach.getObservacionPK());
                attachedObservacionList.add(observacionListObservacionToAttach);
            }
            alumno.setObservacionList(attachedObservacionList);
            em.persist(alumno);
            for (Reservacion reservacionListReservacion : alumno.getReservacionList()) {
                Alumno oldAlumnoOfReservacionListReservacion = reservacionListReservacion.getAlumno();
                reservacionListReservacion.setAlumno(alumno);
                reservacionListReservacion = em.merge(reservacionListReservacion);
                if (oldAlumnoOfReservacionListReservacion != null) {
                    oldAlumnoOfReservacionListReservacion.getReservacionList().remove(reservacionListReservacion);
                    oldAlumnoOfReservacionListReservacion = em.merge(oldAlumnoOfReservacionListReservacion);
                }
            }
            for (Induccion induccionListInduccion : alumno.getInduccionList()) {
                Alumno oldAlumnoOfInduccionListInduccion = induccionListInduccion.getAlumno();
                induccionListInduccion.setAlumno(alumno);
                induccionListInduccion = em.merge(induccionListInduccion);
                if (oldAlumnoOfInduccionListInduccion != null) {
                    oldAlumnoOfInduccionListInduccion.getInduccionList().remove(induccionListInduccion);
                    oldAlumnoOfInduccionListInduccion = em.merge(oldAlumnoOfInduccionListInduccion);
                }
            }
            for (Calificacion calificacionListCalificacion : alumno.getCalificacionList()) {
                Alumno oldAlumnoOfCalificacionListCalificacion = calificacionListCalificacion.getAlumno();
                calificacionListCalificacion.setAlumno(alumno);
                calificacionListCalificacion = em.merge(calificacionListCalificacion);
                if (oldAlumnoOfCalificacionListCalificacion != null) {
                    oldAlumnoOfCalificacionListCalificacion.getCalificacionList().remove(calificacionListCalificacion);
                    oldAlumnoOfCalificacionListCalificacion = em.merge(oldAlumnoOfCalificacionListCalificacion);
                }
            }
            for (Observacion observacionListObservacion : alumno.getObservacionList()) {
                Alumno oldAlumnoOfObservacionListObservacion = observacionListObservacion.getAlumno();
                observacionListObservacion.setAlumno(alumno);
                observacionListObservacion = em.merge(observacionListObservacion);
                if (oldAlumnoOfObservacionListObservacion != null) {
                    oldAlumnoOfObservacionListObservacion.getObservacionList().remove(observacionListObservacion);
                    oldAlumnoOfObservacionListObservacion = em.merge(oldAlumnoOfObservacionListObservacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAlumno(alumno.getMatricula()) != null) {
                throw new PreexistingEntityException("Alumno " + alumno + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Alumno alumno) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno persistentAlumno = em.find(Alumno.class, alumno.getMatricula());
            List<Reservacion> reservacionListOld = persistentAlumno.getReservacionList();
            List<Reservacion> reservacionListNew = alumno.getReservacionList();
            List<Induccion> induccionListOld = persistentAlumno.getInduccionList();
            List<Induccion> induccionListNew = alumno.getInduccionList();
            List<Calificacion> calificacionListOld = persistentAlumno.getCalificacionList();
            List<Calificacion> calificacionListNew = alumno.getCalificacionList();
            List<Observacion> observacionListOld = persistentAlumno.getObservacionList();
            List<Observacion> observacionListNew = alumno.getObservacionList();
            List<String> illegalOrphanMessages = null;
            for (Reservacion reservacionListOldReservacion : reservacionListOld) {
                if (!reservacionListNew.contains(reservacionListOldReservacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Reservacion " + reservacionListOldReservacion + " since its alumno field is not nullable.");
                }
            }
            for (Induccion induccionListOldInduccion : induccionListOld) {
                if (!induccionListNew.contains(induccionListOldInduccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Induccion " + induccionListOldInduccion + " since its alumno field is not nullable.");
                }
            }
            for (Calificacion calificacionListOldCalificacion : calificacionListOld) {
                if (!calificacionListNew.contains(calificacionListOldCalificacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Calificacion " + calificacionListOldCalificacion + " since its alumno field is not nullable.");
                }
            }
            for (Observacion observacionListOldObservacion : observacionListOld) {
                if (!observacionListNew.contains(observacionListOldObservacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Observacion " + observacionListOldObservacion + " since its alumno field is not nullable.");
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
            alumno.setReservacionList(reservacionListNew);
            List<Induccion> attachedInduccionListNew = new ArrayList<Induccion>();
            for (Induccion induccionListNewInduccionToAttach : induccionListNew) {
                induccionListNewInduccionToAttach = em.getReference(induccionListNewInduccionToAttach.getClass(), induccionListNewInduccionToAttach.getInduccionPK());
                attachedInduccionListNew.add(induccionListNewInduccionToAttach);
            }
            induccionListNew = attachedInduccionListNew;
            alumno.setInduccionList(induccionListNew);
            List<Calificacion> attachedCalificacionListNew = new ArrayList<Calificacion>();
            for (Calificacion calificacionListNewCalificacionToAttach : calificacionListNew) {
                calificacionListNewCalificacionToAttach = em.getReference(calificacionListNewCalificacionToAttach.getClass(), calificacionListNewCalificacionToAttach.getCalificacionPK());
                attachedCalificacionListNew.add(calificacionListNewCalificacionToAttach);
            }
            calificacionListNew = attachedCalificacionListNew;
            alumno.setCalificacionList(calificacionListNew);
            List<Observacion> attachedObservacionListNew = new ArrayList<Observacion>();
            for (Observacion observacionListNewObservacionToAttach : observacionListNew) {
                observacionListNewObservacionToAttach = em.getReference(observacionListNewObservacionToAttach.getClass(), observacionListNewObservacionToAttach.getObservacionPK());
                attachedObservacionListNew.add(observacionListNewObservacionToAttach);
            }
            observacionListNew = attachedObservacionListNew;
            alumno.setObservacionList(observacionListNew);
            alumno = em.merge(alumno);
            for (Reservacion reservacionListNewReservacion : reservacionListNew) {
                if (!reservacionListOld.contains(reservacionListNewReservacion)) {
                    Alumno oldAlumnoOfReservacionListNewReservacion = reservacionListNewReservacion.getAlumno();
                    reservacionListNewReservacion.setAlumno(alumno);
                    reservacionListNewReservacion = em.merge(reservacionListNewReservacion);
                    if (oldAlumnoOfReservacionListNewReservacion != null && !oldAlumnoOfReservacionListNewReservacion.equals(alumno)) {
                        oldAlumnoOfReservacionListNewReservacion.getReservacionList().remove(reservacionListNewReservacion);
                        oldAlumnoOfReservacionListNewReservacion = em.merge(oldAlumnoOfReservacionListNewReservacion);
                    }
                }
            }
            for (Induccion induccionListNewInduccion : induccionListNew) {
                if (!induccionListOld.contains(induccionListNewInduccion)) {
                    Alumno oldAlumnoOfInduccionListNewInduccion = induccionListNewInduccion.getAlumno();
                    induccionListNewInduccion.setAlumno(alumno);
                    induccionListNewInduccion = em.merge(induccionListNewInduccion);
                    if (oldAlumnoOfInduccionListNewInduccion != null && !oldAlumnoOfInduccionListNewInduccion.equals(alumno)) {
                        oldAlumnoOfInduccionListNewInduccion.getInduccionList().remove(induccionListNewInduccion);
                        oldAlumnoOfInduccionListNewInduccion = em.merge(oldAlumnoOfInduccionListNewInduccion);
                    }
                }
            }
            for (Calificacion calificacionListNewCalificacion : calificacionListNew) {
                if (!calificacionListOld.contains(calificacionListNewCalificacion)) {
                    Alumno oldAlumnoOfCalificacionListNewCalificacion = calificacionListNewCalificacion.getAlumno();
                    calificacionListNewCalificacion.setAlumno(alumno);
                    calificacionListNewCalificacion = em.merge(calificacionListNewCalificacion);
                    if (oldAlumnoOfCalificacionListNewCalificacion != null && !oldAlumnoOfCalificacionListNewCalificacion.equals(alumno)) {
                        oldAlumnoOfCalificacionListNewCalificacion.getCalificacionList().remove(calificacionListNewCalificacion);
                        oldAlumnoOfCalificacionListNewCalificacion = em.merge(oldAlumnoOfCalificacionListNewCalificacion);
                    }
                }
            }
            for (Observacion observacionListNewObservacion : observacionListNew) {
                if (!observacionListOld.contains(observacionListNewObservacion)) {
                    Alumno oldAlumnoOfObservacionListNewObservacion = observacionListNewObservacion.getAlumno();
                    observacionListNewObservacion.setAlumno(alumno);
                    observacionListNewObservacion = em.merge(observacionListNewObservacion);
                    if (oldAlumnoOfObservacionListNewObservacion != null && !oldAlumnoOfObservacionListNewObservacion.equals(alumno)) {
                        oldAlumnoOfObservacionListNewObservacion.getObservacionList().remove(observacionListNewObservacion);
                        oldAlumnoOfObservacionListNewObservacion = em.merge(oldAlumnoOfObservacionListNewObservacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = alumno.getMatricula();
                if (findAlumno(id) == null) {
                    throw new NonexistentEntityException("The alumno with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno alumno;
            try {
                alumno = em.getReference(Alumno.class, id);
                alumno.getMatricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alumno with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Reservacion> reservacionListOrphanCheck = alumno.getReservacionList();
            for (Reservacion reservacionListOrphanCheckReservacion : reservacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Alumno (" + alumno + ") cannot be destroyed since the Reservacion " + reservacionListOrphanCheckReservacion + " in its reservacionList field has a non-nullable alumno field.");
            }
            List<Induccion> induccionListOrphanCheck = alumno.getInduccionList();
            for (Induccion induccionListOrphanCheckInduccion : induccionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Alumno (" + alumno + ") cannot be destroyed since the Induccion " + induccionListOrphanCheckInduccion + " in its induccionList field has a non-nullable alumno field.");
            }
            List<Calificacion> calificacionListOrphanCheck = alumno.getCalificacionList();
            for (Calificacion calificacionListOrphanCheckCalificacion : calificacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Alumno (" + alumno + ") cannot be destroyed since the Calificacion " + calificacionListOrphanCheckCalificacion + " in its calificacionList field has a non-nullable alumno field.");
            }
            List<Observacion> observacionListOrphanCheck = alumno.getObservacionList();
            for (Observacion observacionListOrphanCheckObservacion : observacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Alumno (" + alumno + ") cannot be destroyed since the Observacion " + observacionListOrphanCheckObservacion + " in its observacionList field has a non-nullable alumno field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(alumno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Alumno> findAlumnoEntities() {
        return findAlumnoEntities(true, -1, -1);
    }

    public List<Alumno> findAlumnoEntities(int maxResults, int firstResult) {
        return findAlumnoEntities(false, maxResults, firstResult);
    }

    private List<Alumno> findAlumnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Alumno.class));
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

    public Alumno findAlumno(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alumno.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlumnoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Alumno> rt = cq.from(Alumno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
