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
import datos.entidades.Curso;
import datos.entidades.Induccion;
import datos.entidades.InduccionPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author andres
 */
public class InduccionJpaController implements Serializable {

    public InduccionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SCAIIFPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Induccion induccion) throws PreexistingEntityException, Exception {
        if (induccion.getInduccionPK() == null) {
            induccion.setInduccionPK(new InduccionPK());
        }
        induccion.getInduccionPK().setMatricula(induccion.getAlumno().getMatricula());
        induccion.getInduccionPK().setNrc(induccion.getCurso().getNrc());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno alumno = induccion.getAlumno();
            if (alumno != null) {
                alumno = em.getReference(alumno.getClass(), alumno.getMatricula());
                induccion.setAlumno(alumno);
            }
            Curso curso = induccion.getCurso();
            if (curso != null) {
                curso = em.getReference(curso.getClass(), curso.getNrc());
                induccion.setCurso(curso);
            }
            em.persist(induccion);
            if (alumno != null) {
                alumno.getInduccionList().add(induccion);
                alumno = em.merge(alumno);
            }
            if (curso != null) {
                curso.getInduccionList().add(induccion);
                curso = em.merge(curso);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInduccion(induccion.getInduccionPK()) != null) {
                throw new PreexistingEntityException("Induccion " + induccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Induccion induccion) throws NonexistentEntityException, Exception {
        induccion.getInduccionPK().setMatricula(induccion.getAlumno().getMatricula());
        induccion.getInduccionPK().setNrc(induccion.getCurso().getNrc());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Induccion persistentInduccion = em.find(Induccion.class, induccion.getInduccionPK());
            Alumno alumnoOld = persistentInduccion.getAlumno();
            Alumno alumnoNew = induccion.getAlumno();
            Curso cursoOld = persistentInduccion.getCurso();
            Curso cursoNew = induccion.getCurso();
            if (alumnoNew != null) {
                alumnoNew = em.getReference(alumnoNew.getClass(), alumnoNew.getMatricula());
                induccion.setAlumno(alumnoNew);
            }
            if (cursoNew != null) {
                cursoNew = em.getReference(cursoNew.getClass(), cursoNew.getNrc());
                induccion.setCurso(cursoNew);
            }
            induccion = em.merge(induccion);
            if (alumnoOld != null && !alumnoOld.equals(alumnoNew)) {
                alumnoOld.getInduccionList().remove(induccion);
                alumnoOld = em.merge(alumnoOld);
            }
            if (alumnoNew != null && !alumnoNew.equals(alumnoOld)) {
                alumnoNew.getInduccionList().add(induccion);
                alumnoNew = em.merge(alumnoNew);
            }
            if (cursoOld != null && !cursoOld.equals(cursoNew)) {
                cursoOld.getInduccionList().remove(induccion);
                cursoOld = em.merge(cursoOld);
            }
            if (cursoNew != null && !cursoNew.equals(cursoOld)) {
                cursoNew.getInduccionList().add(induccion);
                cursoNew = em.merge(cursoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                InduccionPK id = induccion.getInduccionPK();
                if (findInduccion(id) == null) {
                    throw new NonexistentEntityException("The induccion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(InduccionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Induccion induccion;
            try {
                induccion = em.getReference(Induccion.class, id);
                induccion.getInduccionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The induccion with id " + id + " no longer exists.", enfe);
            }
            Alumno alumno = induccion.getAlumno();
            if (alumno != null) {
                alumno.getInduccionList().remove(induccion);
                alumno = em.merge(alumno);
            }
            Curso curso = induccion.getCurso();
            if (curso != null) {
                curso.getInduccionList().remove(induccion);
                curso = em.merge(curso);
            }
            em.remove(induccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Induccion> findInduccionEntities() {
        return findInduccionEntities(true, -1, -1);
    }

    public List<Induccion> findInduccionEntities(int maxResults, int firstResult) {
        return findInduccionEntities(false, maxResults, firstResult);
    }

    private List<Induccion> findInduccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Induccion.class));
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

    public Induccion findInduccion(InduccionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Induccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getInduccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Induccion> rt = cq.from(Induccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
