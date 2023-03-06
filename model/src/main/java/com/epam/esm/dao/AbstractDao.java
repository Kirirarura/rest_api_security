package com.epam.esm.dao;

import com.epam.esm.dao.query.QueryBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

/**
 * Class designed to implement basic operations with database.
 */
@Repository
public abstract class AbstractDao<T> {
    @PersistenceContext
    protected EntityManager entityManager;
    protected final Class<T> entityType;

    protected abstract QueryBuilder<T> getQueryCreator();

    protected AbstractDao(Class<T> entityType) {
        this.entityType = entityType;
    }

    public Optional<T> findById(Long id) {
        return Optional.ofNullable(entityManager.find(entityType, id));
    }

    public List<T> findAll(Pageable pageable) {
        return entityManager.createQuery("SELECT e FROM " + entityType.getSimpleName() + " e", entityType)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

    public void removeById(Long id) {
        T entity = entityManager.find(entityType, id);
        entityManager.remove(entity);
    }

    /**
     * Method that inserts given entity into corresponding table in database.
     * Uses entityManager to persist an entity.
     *
     * @param entity Entity to be inserted in database.
     * @return Entity that was inserted in database.
     */
    public T insert(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public Optional<T> findByName(String name) {
        return entityManager.createQuery("SELECT e FROM " + entityType.getSimpleName() +
                        " e WHERE e.name = :name", entityType)
                .setParameter("name", name)
                .getResultList().stream()
                .findFirst();
    }

    /**
     * Method that returns filtered List of entities.
     * Accepts MultiValueMap with filtering parameters and creates CriteriaQuery.
     *
     * @param fields MultiValueMap with filtering parameters.
     * @param pageable PageRequest with size and page parameters.
     * @return List of entities.
     */
    public List<T> findWithFilters(MultiValueMap<String, String> fields, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = getQueryCreator().createGetQuery(fields, criteriaBuilder);

        return entityManager.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }
}
