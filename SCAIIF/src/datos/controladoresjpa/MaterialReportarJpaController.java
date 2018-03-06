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
import datos.entidades.MaterialReportar;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author andres
 */
public class MaterialReportarJpaController implements Serializable {

    public MaterialReportarJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SCAIIFPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MaterialReportar materialReportar) {
        if (materialReportar.getCalendarioList() == null) {
            materialReportar.setCalendarioList(new ArrayList<Calendario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Calendario> attachedCalendarioList = new ArrayList<Calendario>();
            for (Calendario calendarioListCalendarioToAttach : materialReportar.getCalendarioList()) {
                calendarioListCalendarioToAttach = em.getReference(calendarioListCalendarioToAttach.getClass(), calendarioListCalendarioToAttach.getIdCalendario());
                attachedCalendarioList.add(calendarioListCalendarioToAttach);
            }
            materialReportar.setCalendarioList(attachedCalendarioList);
            em.persist(materialReportar);
            for (Calendario calendarioListCalendario : materialReportar.getCalendarioList()) {
                MaterialReportar oldNoMaterialOfCalendarioListCalendario = calendarioListCalendario.getNoMaterial();
                calendarioListCalendario.setNoMaterial(materialReportar);
                calendarioListCalendario = em.merge(calendarioListCalendario);
                if (oldNoMaterialOfCalendarioListCalendario != null) {
                    oldNoMaterialOfCalendarioListCalendario.getCalendarioList().remove(calendarioListCalendario);
                    oldNoMaterialOfCalendarioListCalendario = em.merge(oldNoMaterialOfCalendarioListCalendario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MaterialReportar materialReportar) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MaterialReportar persistentMaterialReportar = em.find(MaterialReportar.class, materialReportar.getNoMaterial());
            List<Calendario> calendarioListOld = persistentMaterialReportar.getCalendarioList();
            List<Calendario> calendarioListNew = materialReportar.getCalendarioList();
            List<String> illegalOrphanMessages = null;
            for (Calendario calendarioListOldCalendario : calendarioListOld) {
                if (!calendarioListNew.contains(calendarioListOldCalendario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Calendario " + calendarioListOldCalendario + " since its noMaterial field is not nullable.");
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
            materialReportar.setCalendarioList(calendarioListNew);
            materialReportar = em.merge(materialReportar);
            for (Calendario calendarioListNewCalendario : calendarioListNew) {
                if (!calendarioListOld.contains(calendarioListNewCalendario)) {
                    MaterialReportar oldNoMaterialOfCalendarioListNewCalendario = calendarioListNewCalendario.getNoMaterial();
                    calendarioListNewCalendario.setNoMaterial(materialReportar);
                    calendarioListNewCalendario = em.merge(calendarioListNewCalendario);
                    if (oldNoMaterialOfCalendarioListNewCalendario != null && !oldNoMaterialOfCalendarioListNewCalendario.equals(materialReportar)) {
                        oldNoMaterialOfCalendarioListNewCalendario.getCalendarioList().remove(calendarioListNewCalendario);
                        oldNoMaterialOfCalendarioListNewCalendario = em.merge(oldNoMaterialOfCalendarioListNewCalendario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = materialReportar.getNoMaterial();
                if (findMaterialReportar(id) == null) {
                    throw new NonexistentEntityException("The materialReportar with id " + id + " no longer exists.");
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
            MaterialReportar materialReportar;
            try {
                materialReportar = em.getReference(MaterialReportar.class, id);
                materialReportar.getNoMaterial();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materialReportar with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Calendario> calendarioListOrphanCheck = materialReportar.getCalendarioList();
            for (Calendario calendarioListOrphanCheckCalendario : calendarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MaterialReportar (" + materialReportar + ") cannot be destroyed since the Calendario " + calendarioListOrphanCheckCalendario + " in its calendarioList field has a non-nullable noMaterial field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(materialReportar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MaterialReportar> findMaterialReportarEntities() {
        return findMaterialReportarEntities(true, -1, -1);
    }

    public List<MaterialReportar> findMaterialReportarEntities(int maxResults, int firstResult) {
        return findMaterialReportarEntities(false, maxResults, firstResult);
    }

    private List<MaterialReportar> findMaterialReportarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MaterialReportar.class));
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

    public MaterialReportar findMaterialReportar(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MaterialReportar.class, id);
        } finally {
            em.close();
        }
    }

    public int getMaterialReportarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MaterialReportar> rt = cq.from(MaterialReportar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
