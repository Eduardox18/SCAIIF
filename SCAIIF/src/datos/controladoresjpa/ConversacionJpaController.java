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
import datos.entidades.Calendario;
import datos.entidades.Conversacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author andres
 */
public class ConversacionJpaController implements Serializable {

    public ConversacionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SCAIIFPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Conversacion conversacion) {
        if (conversacion.getCalendarioList() == null) {
            conversacion.setCalendarioList(new ArrayList<Calendario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Calendario> attachedCalendarioList = new ArrayList<Calendario>();
            for (Calendario calendarioListCalendarioToAttach : conversacion.getCalendarioList()) {
                calendarioListCalendarioToAttach = em.getReference(calendarioListCalendarioToAttach.getClass(), calendarioListCalendarioToAttach.getIdCalendario());
                attachedCalendarioList.add(calendarioListCalendarioToAttach);
            }
            conversacion.setCalendarioList(attachedCalendarioList);
            em.persist(conversacion);
            for (Calendario calendarioListCalendario : conversacion.getCalendarioList()) {
                Conversacion oldIdConversacionOfCalendarioListCalendario = calendarioListCalendario.getIdConversacion();
                calendarioListCalendario.setIdConversacion(conversacion);
                calendarioListCalendario = em.merge(calendarioListCalendario);
                if (oldIdConversacionOfCalendarioListCalendario != null) {
                    oldIdConversacionOfCalendarioListCalendario.getCalendarioList().remove(calendarioListCalendario);
                    oldIdConversacionOfCalendarioListCalendario = em.merge(oldIdConversacionOfCalendarioListCalendario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Conversacion conversacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Conversacion persistentConversacion = em.find(Conversacion.class, conversacion.getIdConversacion());
            List<Calendario> calendarioListOld = persistentConversacion.getCalendarioList();
            List<Calendario> calendarioListNew = conversacion.getCalendarioList();
            List<String> illegalOrphanMessages = null;
            for (Calendario calendarioListOldCalendario : calendarioListOld) {
                if (!calendarioListNew.contains(calendarioListOldCalendario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Calendario " + calendarioListOldCalendario + " since its idConversacion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Calendario> attachedCalendarioListNew = new ArrayList<Calendario>();
            for (Calendario calendarioListNewCalendarioToAttach : calendarioListNew) {
                calendarioListNewCalendarioToAttach = em.getReference(calendarioListNewCalendarioToAttach.getClass(), calendarioListNewCalendarioToAttach.getIdCalendario());
                attachedCalendarioListNew.add(calendarioListNewCalendarioToAttach);
            }
            calendarioListNew = attachedCalendarioListNew;
            conversacion.setCalendarioList(calendarioListNew);
            conversacion = em.merge(conversacion);
            for (Calendario calendarioListNewCalendario : calendarioListNew) {
                if (!calendarioListOld.contains(calendarioListNewCalendario)) {
                    Conversacion oldIdConversacionOfCalendarioListNewCalendario = calendarioListNewCalendario.getIdConversacion();
                    calendarioListNewCalendario.setIdConversacion(conversacion);
                    calendarioListNewCalendario = em.merge(calendarioListNewCalendario);
                    if (oldIdConversacionOfCalendarioListNewCalendario != null && !oldIdConversacionOfCalendarioListNewCalendario.equals(conversacion)) {
                        oldIdConversacionOfCalendarioListNewCalendario.getCalendarioList().remove(calendarioListNewCalendario);
                        oldIdConversacionOfCalendarioListNewCalendario = em.merge(oldIdConversacionOfCalendarioListNewCalendario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = conversacion.getIdConversacion();
                if (findConversacion(id) == null) {
                    throw new NonexistentEntityException("The conversacion with id " + id + " no longer exists.");
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
            Conversacion conversacion;
            try {
                conversacion = em.getReference(Conversacion.class, id);
                conversacion.getIdConversacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The conversacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Calendario> calendarioListOrphanCheck = conversacion.getCalendarioList();
            for (Calendario calendarioListOrphanCheckCalendario : calendarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Conversacion (" + conversacion + ") cannot be destroyed since the Calendario " + calendarioListOrphanCheckCalendario + " in its calendarioList field has a non-nullable idConversacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(conversacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Conversacion> findConversacionEntities() {
        return findConversacionEntities(true, -1, -1);
    }

    public List<Conversacion> findConversacionEntities(int maxResults, int firstResult) {
        return findConversacionEntities(false, maxResults, firstResult);
    }

    private List<Conversacion> findConversacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Conversacion.class));
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

    public Conversacion findConversacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Conversacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getConversacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Conversacion> rt = cq.from(Conversacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
