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
import datos.entidades.Modulo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author andres
 */
public class ModuloJpaController implements Serializable {

    public ModuloJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SCAIIFPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Modulo modulo) {
        if (modulo.getCalendarioList() == null) {
            modulo.setCalendarioList(new ArrayList<Calendario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Calendario> attachedCalendarioList = new ArrayList<Calendario>();
            for (Calendario calendarioListCalendarioToAttach : modulo.getCalendarioList()) {
                calendarioListCalendarioToAttach = em.getReference(calendarioListCalendarioToAttach.getClass(), calendarioListCalendarioToAttach.getIdCalendario());
                attachedCalendarioList.add(calendarioListCalendarioToAttach);
            }
            modulo.setCalendarioList(attachedCalendarioList);
            em.persist(modulo);
            for (Calendario calendarioListCalendario : modulo.getCalendarioList()) {
                Modulo oldIdModuloOfCalendarioListCalendario = calendarioListCalendario.getIdModulo();
                calendarioListCalendario.setIdModulo(modulo);
                calendarioListCalendario = em.merge(calendarioListCalendario);
                if (oldIdModuloOfCalendarioListCalendario != null) {
                    oldIdModuloOfCalendarioListCalendario.getCalendarioList().remove(calendarioListCalendario);
                    oldIdModuloOfCalendarioListCalendario = em.merge(oldIdModuloOfCalendarioListCalendario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Modulo modulo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Modulo persistentModulo = em.find(Modulo.class, modulo.getIdModulo());
            List<Calendario> calendarioListOld = persistentModulo.getCalendarioList();
            List<Calendario> calendarioListNew = modulo.getCalendarioList();
            List<String> illegalOrphanMessages = null;
            for (Calendario calendarioListOldCalendario : calendarioListOld) {
                if (!calendarioListNew.contains(calendarioListOldCalendario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Calendario " + calendarioListOldCalendario + " since its idModulo field is not nullable.");
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
            modulo.setCalendarioList(calendarioListNew);
            modulo = em.merge(modulo);
            for (Calendario calendarioListNewCalendario : calendarioListNew) {
                if (!calendarioListOld.contains(calendarioListNewCalendario)) {
                    Modulo oldIdModuloOfCalendarioListNewCalendario = calendarioListNewCalendario.getIdModulo();
                    calendarioListNewCalendario.setIdModulo(modulo);
                    calendarioListNewCalendario = em.merge(calendarioListNewCalendario);
                    if (oldIdModuloOfCalendarioListNewCalendario != null && !oldIdModuloOfCalendarioListNewCalendario.equals(modulo)) {
                        oldIdModuloOfCalendarioListNewCalendario.getCalendarioList().remove(calendarioListNewCalendario);
                        oldIdModuloOfCalendarioListNewCalendario = em.merge(oldIdModuloOfCalendarioListNewCalendario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = modulo.getIdModulo();
                if (findModulo(id) == null) {
                    throw new NonexistentEntityException("The modulo with id " + id + " no longer exists.");
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
            Modulo modulo;
            try {
                modulo = em.getReference(Modulo.class, id);
                modulo.getIdModulo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The modulo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Calendario> calendarioListOrphanCheck = modulo.getCalendarioList();
            for (Calendario calendarioListOrphanCheckCalendario : calendarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Modulo (" + modulo + ") cannot be destroyed since the Calendario " + calendarioListOrphanCheckCalendario + " in its calendarioList field has a non-nullable idModulo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(modulo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Modulo> findModuloEntities() {
        return findModuloEntities(true, -1, -1);
    }

    public List<Modulo> findModuloEntities(int maxResults, int firstResult) {
        return findModuloEntities(false, maxResults, firstResult);
    }

    private List<Modulo> findModuloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Modulo.class));
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

    public Modulo findModulo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Modulo.class, id);
        } finally {
            em.close();
        }
    }

    public int getModuloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Modulo> rt = cq.from(Modulo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
