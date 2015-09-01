package com.jsf22.html5.app.persistence.dao.generic;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import static com.jsf22.html5.app.util.ValidacaoUtil.possuiValor;

public abstract class GenericDAO<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "SAMPLE_PU")
	private EntityManager em;

	private Class<T> entity;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.entity = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected EntityManager getEntityManager() {
		return em;
	}

	public void joinTransaction() {
		em.joinTransaction();
	}

	public void save(T t) {
		em.persist(t);
	}

	public void delete(Long id) {
		T entityToBeRemoved = findReferenceOnly(id);
		em.remove(entityToBeRemoved);
	}

	public T update(T t) {
		return em.merge(t);
	}

	public T find(long id) {
		return em.find(entity, id);
	}

	public T findReferenceOnly(Long entityID) {
		return em.getReference(entity, entityID);
	}

	public List<T> findAll() {
		CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(entity);
		cq.select(cq.from(entity));
		return em.createQuery(cq).getResultList();
	}
	
	protected T findOneResult(String sql, Map<String, Object> parameters) {
		T result = null;

		try {
			TypedQuery<T> query = em.createQuery(sql, entity);
			if (possuiValor(parameters)) {
				populateQueryParameters(query, parameters);
			}

			result = (T) query.getSingleResult();

		} catch (NoResultException e) {
			System.out.println("No result found for query: " + sql);
		} catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<T> findWithNamedQuery(String namedQueryName) {
		return this.em.createNamedQuery(namedQueryName).getResultList();
	}

	public List<T> findWithNamedQuery(String namedQueryName, Map<String, Object> parameters) {
		return findWithNamedQuery(namedQueryName, parameters, 0);
	}

	@SuppressWarnings("unchecked")
	public List<T> findWithNamedQuery(String queryName, int resultLimit) {
		return this.em.createNamedQuery(queryName).setMaxResults(resultLimit).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByNativeQuery(String sql) {
		return this.em.createNativeQuery(sql, entity).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> findWithNamedQuery(String namedQueryName, Map<String, Object> parameters, int resultLimit) {
		Query query = this.em.createNamedQuery(namedQueryName);
		if (resultLimit > 0) {
			query.setMaxResults(resultLimit);
		}
		populateQueryParameters(query, parameters);
		return query.getResultList();
	}

	private void populateQueryParameters(Query query, Map<String, Object> parameters) {
		for (Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}
}
