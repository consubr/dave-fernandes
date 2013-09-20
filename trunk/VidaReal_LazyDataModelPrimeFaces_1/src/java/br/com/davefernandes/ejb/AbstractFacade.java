/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davefernandes.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.primefaces.model.SortOrder;

/**
 *
 * @author dave
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();
    
        // This method provides a bridge
    // between the session beans and my LazyDataModel.
    public List<T> load(int first, int count, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        javax.persistence.criteria.CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = builder.createQuery();
        Root root = cq.from(entityClass);
        cq.select(root);
        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                cq.orderBy(builder.asc(root.get(sortField)));
            } else if (sortOrder == SortOrder.DESCENDING) {
                cq.orderBy(builder.desc(root.get(sortField)));
            }
        }
        if (filters != null) {
            Set<Map.Entry<String, String>> entries = filters.entrySet();
            ArrayList<Predicate> predicatesList =
                    new ArrayList<Predicate>(entries.size());
            for (Map.Entry<String, String> filter : entries) {
                predicatesList.add(builder.like(root.get(filter.getKey()),
                        filter.getValue() + "%"));
            }
            cq.where(predicatesList.<Predicate>toArray(
                    new Predicate[predicatesList.size()]));
        }
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setFirstResult(first);
        q.setMaxResults(count);
        return q.getResultList();
    }

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
