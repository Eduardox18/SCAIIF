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
import datos.entidades.Calificacion;
import datos.entidades.CalificacionPK;
import datos.entidades.Curso;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author andres
 */
public class CalificacionJpaController implements Serializable {

    public CalificacionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SCAIIFPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Calificacion calificacion) throws PreexistingEntityException, Exception {
        if (calificacion.getCalificacionPK() == null) {
            calificacion.setCalificacionPK(new CalificacionPK());
        }
        calificacion.getCalificacionPK().setMatricula(calificacion.getAlumno().getMatricula());
        calificacion.getCalificacionPK().setNrc(calificacion.getCurso().getNrc());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno alumno = calificacion.getAlumno();
            if (alumno != null) {
                alumno = em.getReference(alumno.getClass(), alumno.getMatricula());
                calificacion.setAlumno(alumno);
            }
            Curso curso = calificacion.getCurso();
            if (curso != null) {
                curso = em.getReference(curso.getClass(), curso.getNrc());
                calificacion.setCurso(curso);
            }
            em.persist(calificacion);
            if (alumno != null) {
                alumno.getCalificacionList().add(calificacion);
                alumno = em.merge(alumno);
            }
            if (curso != null) {
                curso.getCalificacionList().add(calificacion);
                curso = em.merge(curso);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCalificacion(calificacion.getCalificacionPK()) != null) {
                throw new PreexistingEntityException("Calificacion " + calificacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Calificacion calificacion) throws NonexistentEntityException, Exception {
        calificacion.getCalificacionPK().setMatricula(calificacion.getAlumno().getMatricula());
        calificacion.getCalificacionPK().setNrc(calificacion.getCurso().getNrc());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Calificacion persistentCalificacion = em.find(Calificacion.class, calificacion.getCalificacionPK());
            Alumno alumnoOld = persistentCalificacion.getAlumno();
            Alumno alumnoNew = calificacion.getAlumno();
            Curso cursoOld = persistentCalificacion.getCurso();
            Curso cursoNew = calificacion.getCurso();
            if (alumnoNew != null) {
                alumnoNew = em.getReference(alumnoNew.getClass(), alumnoNew.getMatricula());
                calificacion.setAlumno(alumnoNew);
            }
            if (cursoNew != null) {
                cursoNew = em.getReference(cursoNew.getClass(), cursoNew.getNrc());
                calificacion.setCurso(cursoNew);
            }
            calificacion = em.merge(calificacion);
            if (alumnoOld != null && !alumnoOld.equals(alumnoNew)) {
                alumnoOld.getCalificacionList().remove(calificacion);
                alumnoOld = em.merge(alumnoOld);
            }
            if (alumnoNew != null && !alumnoNew.equals(alumnoOld)) {
                alumnoNew.getCalificacionList().add(calificacion);
                alumnoNew = em.merge(alumnoNew);
            }
            if (cursoOld != null && !cursoOld.equals(cursoNew)) {
                cursoOld.getCalificacionList().remove(calificacion);
                cursoOld = em.merge(cursoOld);
            }
            if (cursoNew != null && !cursoNew.equals(cursoOld)) {
                cursoNew.getCalificacionList().add(calificacion);
                cursoNew = em.merge(cursoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CalificacionPK id = calificacion.getCalificacionPK();
                if (findCalificacion(id) == null) {
                    throw new NonexistentEntityException("The calificacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CalificacionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Calificacion calificacion;
            try {
                calificacion = em.getReference(Calificacion.class, id);
                calificacion.getCalificacionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The calificacion with id " + id + " no longer exists.", enfe);
            }
            Alumno alumno = calificacion.getAlumno();
            if (alumno != null) {
                alumno.getCalificacionList().remove(calificacion);
                alumno = em.merge(alumno);
            }
            Curso curso = calificacion.getCurso();
            if (curso != null) {
                curso.getCalificacionList().remove(calificacion);
                curso = em.merge(curso);
            }
            em.remove(calificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Calificacion> findCalificacionEntities() {
        return findCalificacionEntities(true, -1, -1);
    }

    public List<Calificacion> findCalificacionEntities(int maxResults, int firstResult) {
        return findCalificacionEntities(false, maxResults, firstResult);
    }

    private List<Calificacion> findCalificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Calificacion.class));
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

    public Calificacion findCalificacion(CalificacionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Calificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getCalificacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Calificacion> rt = cq.from(Calificacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
