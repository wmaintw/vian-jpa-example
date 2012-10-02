package mymvc.dao;

import mymvc.domain.Category;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("CategoryDao")
@Transactional
public class JpaCategoryDao implements CategoryDao {

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Category findOne(int id) {
        return entityManager.find(Category.class, id);
    }

    @Override
    public void persist(Category givenCategory) {
        entityManager.persist(givenCategory);
    }
}
