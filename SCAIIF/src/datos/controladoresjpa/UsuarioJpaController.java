/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.controladoresjpa;

import controlador.exceptions.IllegalOrphanException;
import controlador.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import datos.entidades.Cargo;
import datos.entidades.Observacion;
import java.util.ArrayList;
import java.util.List;
import datos.entidades.Curso;
import datos.entidades.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author andres
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SCAIIFPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getObservacionList() == null) {
            usuario.setObservacionList(new ArrayList<Observacion>());
        }
        if (usuario.getCursoList() == null) {
            usuario.setCursoList(new ArrayList<Curso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cargo idCargo = usuario.getIdCargo();
            if (idCargo != null) {
                idCargo = em.getReference(idCargo.getClass(), idCargo.getIdCargo());
                usuario.setIdCargo(idCargo);
            }
            List<Observacion> attachedObservacionList = new ArrayList<Observacion>();
            for (Observacion observacionListObservacionToAttach : usuario.getObservacionList()) {
                observacionListObservacionToAttach = em.getReference(observacionListObservacionToAttach.getClass(), observacionListObservacionToAttach.getObservacionPK());
                attachedObservacionList.add(observacionListObservacionToAttach);
            }
            usuario.setObservacionList(attachedObservacionList);
            List<Curso> attachedCursoList = new ArrayList<Curso>();
            for (Curso cursoListCursoToAttach : usuario.getCursoList()) {
                cursoListCursoToAttach = em.getReference(cursoListCursoToAttach.getClass(), cursoListCursoToAttach.getNrc());
                attachedCursoList.add(cursoListCursoToAttach);
            }
            usuario.setCursoList(attachedCursoList);
            em.persist(usuario);
            if (idCargo != null) {
                idCargo.getUsuarioList().add(usuario);
                idCargo = em.merge(idCargo);
            }
            for (Observacion observacionListObservacion : usuario.getObservacionList()) {
                Usuario oldUsuarioOfObservacionListObservacion = observacionListObservacion.getUsuario();
                observacionListObservacion.setUsuario(usuario);
                observacionListObservacion = em.merge(observacionListObservacion);
                if (oldUsuarioOfObservacionListObservacion != null) {
                    oldUsuarioOfObservacionListObservacion.getObservacionList().remove(observacionListObservacion);
                    oldUsuarioOfObservacionListObservacion = em.merge(oldUsuarioOfObservacionListObservacion);
                }
            }
            for (Curso cursoListCurso : usuario.getCursoList()) {
                Usuario oldNoPersonalOfCursoListCurso = cursoListCurso.getNoPersonal();
                cursoListCurso.setNoPersonal(usuario);
                cursoListCurso = em.merge(cursoListCurso);
                if (oldNoPersonalOfCursoListCurso != null) {
                    oldNoPersonalOfCursoListCurso.getCursoList().remove(cursoListCurso);
                    oldNoPersonalOfCursoListCurso = em.merge(oldNoPersonalOfCursoListCurso);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getNoPersonal());
            Cargo idCargoOld = persistentUsuario.getIdCargo();
            Cargo idCargoNew = usuario.getIdCargo();
            List<Observacion> observacionListOld = persistentUsuario.getObservacionList();
            List<Observacion> observacionListNew = usuario.getObservacionList();
            List<Curso> cursoListOld = persistentUsuario.getCursoList();
            List<Curso> cursoListNew = usuario.getCursoList();
            List<String> illegalOrphanMessages = null;
            for (Observacion observacionListOldObservacion : observacionListOld) {
                if (!observacionListNew.contains(observacionListOldObservacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Observacion " + observacionListOldObservacion + " since its usuario field is not nullable.");
                }
            }
            for (Curso cursoListOldCurso : cursoListOld) {
                if (!cursoListNew.contains(cursoListOldCurso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Curso " + cursoListOldCurso + " since its noPersonal field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCargoNew != null) {
                idCargoNew = em.getReference(idCargoNew.getClass(), idCargoNew.getIdCargo());
                usuario.setIdCargo(idCargoNew);
            }
            List<Observacion> attachedObservacionListNew = new ArrayList<Observacion>();
            for (Observacion observacionListNewObservacionToAttach : observacionListNew) {
                observacionListNewObservacionToAttach = em.getReference(observacionListNewObservacionToAttach.getClass(), observacionListNewObservacionToAttach.getObservacionPK());
                attachedObservacionListNew.add(observacionListNewObservacionToAttach);
            }
            observacionListNew = attachedObservacionListNew;
            usuario.setObservacionList(observacionListNew);
            List<Curso> attachedCursoListNew = new ArrayList<Curso>();
            for (Curso cursoListNewCursoToAttach : cursoListNew) {
                cursoListNewCursoToAttach = em.getReference(cursoListNewCursoToAttach.getClass(), cursoListNewCursoToAttach.getNrc());
                attachedCursoListNew.add(cursoListNewCursoToAttach);
            }
            cursoListNew = attachedCursoListNew;
            usuario.setCursoList(cursoListNew);
            usuario = em.merge(usuario);
            if (idCargoOld != null && !idCargoOld.equals(idCargoNew)) {
                idCargoOld.getUsuarioList().remove(usuario);
                idCargoOld = em.merge(idCargoOld);
            }
            if (idCargoNew != null && !idCargoNew.equals(idCargoOld)) {
                idCargoNew.getUsuarioList().add(usuario);
                idCargoNew = em.merge(idCargoNew);
            }
            for (Observacion observacionListNewObservacion : observacionListNew) {
                if (!observacionListOld.contains(observacionListNewObservacion)) {
                    Usuario oldUsuarioOfObservacionListNewObservacion = observacionListNewObservacion.getUsuario();
                    observacionListNewObservacion.setUsuario(usuario);
                    observacionListNewObservacion = em.merge(observacionListNewObservacion);
                    if (oldUsuarioOfObservacionListNewObservacion != null && !oldUsuarioOfObservacionListNewObservacion.equals(usuario)) {
                        oldUsuarioOfObservacionListNewObservacion.getObservacionList().remove(observacionListNewObservacion);
                        oldUsuarioOfObservacionListNewObservacion = em.merge(oldUsuarioOfObservacionListNewObservacion);
                    }
                }
            }
            for (Curso cursoListNewCurso : cursoListNew) {
                if (!cursoListOld.contains(cursoListNewCurso)) {
                    Usuario oldNoPersonalOfCursoListNewCurso = cursoListNewCurso.getNoPersonal();
                    cursoListNewCurso.setNoPersonal(usuario);
                    cursoListNewCurso = em.merge(cursoListNewCurso);
                    if (oldNoPersonalOfCursoListNewCurso != null && !oldNoPersonalOfCursoListNewCurso.equals(usuario)) {
                        oldNoPersonalOfCursoListNewCurso.getCursoList().remove(cursoListNewCurso);
                        oldNoPersonalOfCursoListNewCurso = em.merge(oldNoPersonalOfCursoListNewCurso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getNoPersonal();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getNoPersonal();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Observacion> observacionListOrphanCheck = usuario.getObservacionList();
            for (Observacion observacionListOrphanCheckObservacion : observacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Observacion " + observacionListOrphanCheckObservacion + " in its observacionList field has a non-nullable usuario field.");
            }
            List<Curso> cursoListOrphanCheck = usuario.getCursoList();
            for (Curso cursoListOrphanCheckCurso : cursoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Curso " + cursoListOrphanCheckCurso + " in its cursoList field has a non-nullable noPersonal field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cargo idCargo = usuario.getIdCargo();
            if (idCargo != null) {
                idCargo.getUsuarioList().remove(usuario);
                idCargo = em.merge(idCargo);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
