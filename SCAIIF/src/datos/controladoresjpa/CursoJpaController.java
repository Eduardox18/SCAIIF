/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.controladoresjpa;

import controlador.exceptions.IllegalOrphanException;
import controlador.exceptions.NonexistentEntityException;
import controlador.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import datos.entidades.Usuario;
import datos.entidades.Induccion;
import java.util.ArrayList;
import java.util.List;
import datos.entidades.Calificacion;
import datos.entidades.Calendario;
import datos.entidades.Curso;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author andres
 */
public class CursoJpaController implements Serializable {

    public CursoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SCAIIFPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Curso curso) throws PreexistingEntityException, Exception {
        if (curso.getInduccionList() == null) {
            curso.setInduccionList(new ArrayList<Induccion>());
        }
        if (curso.getCalificacionList() == null) {
            curso.setCalificacionList(new ArrayList<Calificacion>());
        }
        if (curso.getCalendarioList() == null) {
            curso.setCalendarioList(new ArrayList<Calendario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario noPersonal = curso.getNoPersonal();
            if (noPersonal != null) {
                noPersonal = em.getReference(noPersonal.getClass(), noPersonal.getNoPersonal());
                curso.setNoPersonal(noPersonal);
            }
            List<Induccion> attachedInduccionList = new ArrayList<Induccion>();
            for (Induccion induccionListInduccionToAttach : curso.getInduccionList()) {
                induccionListInduccionToAttach = em.getReference(induccionListInduccionToAttach.getClass(), induccionListInduccionToAttach.getInduccionPK());
                attachedInduccionList.add(induccionListInduccionToAttach);
            }
            curso.setInduccionList(attachedInduccionList);
            List<Calificacion> attachedCalificacionList = new ArrayList<Calificacion>();
            for (Calificacion calificacionListCalificacionToAttach : curso.getCalificacionList()) {
                calificacionListCalificacionToAttach = em.getReference(calificacionListCalificacionToAttach.getClass(), calificacionListCalificacionToAttach.getCalificacionPK());
                attachedCalificacionList.add(calificacionListCalificacionToAttach);
            }
            curso.setCalificacionList(attachedCalificacionList);
            List<Calendario> attachedCalendarioList = new ArrayList<Calendario>();
            for (Calendario calendarioListCalendarioToAttach : curso.getCalendarioList()) {
                calendarioListCalendarioToAttach = em.getReference(calendarioListCalendarioToAttach.getClass(), calendarioListCalendarioToAttach.getIdCalendario());
                attachedCalendarioList.add(calendarioListCalendarioToAttach);
            }
            curso.setCalendarioList(attachedCalendarioList);
            em.persist(curso);
            if (noPersonal != null) {
                noPersonal.getCursoList().add(curso);
                noPersonal = em.merge(noPersonal);
            }
            for (Induccion induccionListInduccion : curso.getInduccionList()) {
                Curso oldCursoOfInduccionListInduccion = induccionListInduccion.getCurso();
                induccionListInduccion.setCurso(curso);
                induccionListInduccion = em.merge(induccionListInduccion);
                if (oldCursoOfInduccionListInduccion != null) {
                    oldCursoOfInduccionListInduccion.getInduccionList().remove(induccionListInduccion);
                    oldCursoOfInduccionListInduccion = em.merge(oldCursoOfInduccionListInduccion);
                }
            }
            for (Calificacion calificacionListCalificacion : curso.getCalificacionList()) {
                Curso oldCursoOfCalificacionListCalificacion = calificacionListCalificacion.getCurso();
                calificacionListCalificacion.setCurso(curso);
                calificacionListCalificacion = em.merge(calificacionListCalificacion);
                if (oldCursoOfCalificacionListCalificacion != null) {
                    oldCursoOfCalificacionListCalificacion.getCalificacionList().remove(calificacionListCalificacion);
                    oldCursoOfCalificacionListCalificacion = em.merge(oldCursoOfCalificacionListCalificacion);
                }
            }
            for (Calendario calendarioListCalendario : curso.getCalendarioList()) {
                Curso oldNrcOfCalendarioListCalendario = calendarioListCalendario.getNrc();
                calendarioListCalendario.setNrc(curso);
                calendarioListCalendario = em.merge(calendarioListCalendario);
                if (oldNrcOfCalendarioListCalendario != null) {
                    oldNrcOfCalendarioListCalendario.getCalendarioList().remove(calendarioListCalendario);
                    oldNrcOfCalendarioListCalendario = em.merge(oldNrcOfCalendarioListCalendario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCurso(curso.getNrc()) != null) {
                throw new PreexistingEntityException("Curso " + curso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Curso curso) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Curso persistentCurso = em.find(Curso.class, curso.getNrc());
            Usuario noPersonalOld = persistentCurso.getNoPersonal();
            Usuario noPersonalNew = curso.getNoPersonal();
            List<Induccion> induccionListOld = persistentCurso.getInduccionList();
            List<Induccion> induccionListNew = curso.getInduccionList();
            List<Calificacion> calificacionListOld = persistentCurso.getCalificacionList();
            List<Calificacion> calificacionListNew = curso.getCalificacionList();
            List<Calendario> calendarioListOld = persistentCurso.getCalendarioList();
            List<Calendario> calendarioListNew = curso.getCalendarioList();
            List<String> illegalOrphanMessages = null;
            for (Induccion induccionListOldInduccion : induccionListOld) {
                if (!induccionListNew.contains(induccionListOldInduccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Induccion " + induccionListOldInduccion + " since its curso field is not nullable.");
                }
            }
            for (Calificacion calificacionListOldCalificacion : calificacionListOld) {
                if (!calificacionListNew.contains(calificacionListOldCalificacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Calificacion " + calificacionListOldCalificacion + " since its curso field is not nullable.");
                }
            }
            for (Calendario calendarioListOldCalendario : calendarioListOld) {
                if (!calendarioListNew.contains(calendarioListOldCalendario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Calendario " + calendarioListOldCalendario + " since its nrc field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (noPersonalNew != null) {
                noPersonalNew = em.getReference(noPersonalNew.getClass(), noPersonalNew.getNoPersonal());
                curso.setNoPersonal(noPersonalNew);
            }
            List<Induccion> attachedInduccionListNew = new ArrayList<Induccion>();
            for (Induccion induccionListNewInduccionToAttach : induccionListNew) {
                induccionListNewInduccionToAttach = em.getReference(induccionListNewInduccionToAttach.getClass(), induccionListNewInduccionToAttach.getInduccionPK());
                attachedInduccionListNew.add(induccionListNewInduccionToAttach);
            }
            induccionListNew = attachedInduccionListNew;
            curso.setInduccionList(induccionListNew);
            List<Calificacion> attachedCalificacionListNew = new ArrayList<Calificacion>();
            for (Calificacion calificacionListNewCalificacionToAttach : calificacionListNew) {
                calificacionListNewCalificacionToAttach = em.getReference(calificacionListNewCalificacionToAttach.getClass(), calificacionListNewCalificacionToAttach.getCalificacionPK());
                attachedCalificacionListNew.add(calificacionListNewCalificacionToAttach);
            }
            calificacionListNew = attachedCalificacionListNew;
            curso.setCalificacionList(calificacionListNew);
            List<Calendario> attachedCalendarioListNew = new ArrayList<Calendario>();
            for (Calendario calendarioListNewCalendarioToAttach : calendarioListNew) {
                calendarioListNewCalendarioToAttach = em.getReference(calendarioListNewCalendarioToAttach.getClass(), calendarioListNewCalendarioToAttach.getIdCalendario());
                attachedCalendarioListNew.add(calendarioListNewCalendarioToAttach);
            }
            calendarioListNew = attachedCalendarioListNew;
            curso.setCalendarioList(calendarioListNew);
            curso = em.merge(curso);
            if (noPersonalOld != null && !noPersonalOld.equals(noPersonalNew)) {
                noPersonalOld.getCursoList().remove(curso);
                noPersonalOld = em.merge(noPersonalOld);
            }
            if (noPersonalNew != null && !noPersonalNew.equals(noPersonalOld)) {
                noPersonalNew.getCursoList().add(curso);
                noPersonalNew = em.merge(noPersonalNew);
            }
            for (Induccion induccionListNewInduccion : induccionListNew) {
                if (!induccionListOld.contains(induccionListNewInduccion)) {
                    Curso oldCursoOfInduccionListNewInduccion = induccionListNewInduccion.getCurso();
                    induccionListNewInduccion.setCurso(curso);
                    induccionListNewInduccion = em.merge(induccionListNewInduccion);
                    if (oldCursoOfInduccionListNewInduccion != null && !oldCursoOfInduccionListNewInduccion.equals(curso)) {
                        oldCursoOfInduccionListNewInduccion.getInduccionList().remove(induccionListNewInduccion);
                        oldCursoOfInduccionListNewInduccion = em.merge(oldCursoOfInduccionListNewInduccion);
                    }
                }
            }
            for (Calificacion calificacionListNewCalificacion : calificacionListNew) {
                if (!calificacionListOld.contains(calificacionListNewCalificacion)) {
                    Curso oldCursoOfCalificacionListNewCalificacion = calificacionListNewCalificacion.getCurso();
                    calificacionListNewCalificacion.setCurso(curso);
                    calificacionListNewCalificacion = em.merge(calificacionListNewCalificacion);
                    if (oldCursoOfCalificacionListNewCalificacion != null && !oldCursoOfCalificacionListNewCalificacion.equals(curso)) {
                        oldCursoOfCalificacionListNewCalificacion.getCalificacionList().remove(calificacionListNewCalificacion);
                        oldCursoOfCalificacionListNewCalificacion = em.merge(oldCursoOfCalificacionListNewCalificacion);
                    }
                }
            }
            for (Calendario calendarioListNewCalendario : calendarioListNew) {
                if (!calendarioListOld.contains(calendarioListNewCalendario)) {
                    Curso oldNrcOfCalendarioListNewCalendario = calendarioListNewCalendario.getNrc();
                    calendarioListNewCalendario.setNrc(curso);
                    calendarioListNewCalendario = em.merge(calendarioListNewCalendario);
                    if (oldNrcOfCalendarioListNewCalendario != null && !oldNrcOfCalendarioListNewCalendario.equals(curso)) {
                        oldNrcOfCalendarioListNewCalendario.getCalendarioList().remove(calendarioListNewCalendario);
                        oldNrcOfCalendarioListNewCalendario = em.merge(oldNrcOfCalendarioListNewCalendario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = curso.getNrc();
                if (findCurso(id) == null) {
                    throw new NonexistentEntityException("The curso with id " + id + " no longer exists.");
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
            Curso curso;
            try {
                curso = em.getReference(Curso.class, id);
                curso.getNrc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The curso with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Induccion> induccionListOrphanCheck = curso.getInduccionList();
            for (Induccion induccionListOrphanCheckInduccion : induccionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Curso (" + curso + ") cannot be destroyed since the Induccion " + induccionListOrphanCheckInduccion + " in its induccionList field has a non-nullable curso field.");
            }
            List<Calificacion> calificacionListOrphanCheck = curso.getCalificacionList();
            for (Calificacion calificacionListOrphanCheckCalificacion : calificacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Curso (" + curso + ") cannot be destroyed since the Calificacion " + calificacionListOrphanCheckCalificacion + " in its calificacionList field has a non-nullable curso field.");
            }
            List<Calendario> calendarioListOrphanCheck = curso.getCalendarioList();
            for (Calendario calendarioListOrphanCheckCalendario : calendarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Curso (" + curso + ") cannot be destroyed since the Calendario " + calendarioListOrphanCheckCalendario + " in its calendarioList field has a non-nullable nrc field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario noPersonal = curso.getNoPersonal();
            if (noPersonal != null) {
                noPersonal.getCursoList().remove(curso);
                noPersonal = em.merge(noPersonal);
            }
            em.remove(curso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Curso> findCursoEntities() {
        return findCursoEntities(true, -1, -1);
    }

    public List<Curso> findCursoEntities(int maxResults, int firstResult) {
        return findCursoEntities(false, maxResults, firstResult);
    }

    private List<Curso> findCursoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Curso.class));
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

    public Curso findCurso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Curso.class, id);
        } finally {
            em.close();
        }
    }

    public int getCursoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Curso> rt = cq.from(Curso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
