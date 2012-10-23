package mymvc.dao;

import mymvc.domain.Category;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

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

    public Category findByTitle(String title) {
        Category category = null;
        Query query = entityManager.createQuery("select c from Category c where c.title = :title");
        query.setParameter("title", title);
        List<Category> categories = query.getResultList();

        if (categories != null && categories.size() > 0) {
            category = categories.get(0);
        }
        return category;
    }

    public Category findByTitleUsingNativeQuery (String title) {
        Category category = null;
        Query query = entityManager.createNativeQuery("select * from Category where title = :title");
        query.setParameter("title", title);
        List<Category> categories = query.getResultList();

        if (categories != null && categories.size() > 0) {
            category = categories.get(0);
        }
        return category;
    }

    public Category findByTitleUsingStringQuery (String title) {
        Category category = null;
        Query query = entityManager.createNativeQuery("select * from Category where title = '" + title + "'", Category.class);
        List<Category> categories = query.getResultList();

        if (categories != null && categories.size() > 0) {
            category = categories.get(0);
        }
        return category;
    }

    @Override
    public void removeAll() {
        Query query = entityManager.createQuery("select c from Category c");
        List<Category> categories = query.getResultList();
        for (Category category : categories) {
            entityManager.remove(category);
        }
    }

    @Override
    public void persist(Category givenCategory) {
        entityManager.persist(givenCategory);
    }
}
