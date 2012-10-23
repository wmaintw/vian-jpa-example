package mymvc.dao;

import junit.framework.TestCase;
import mymvc.domain.Category;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class JpaCategoryDaoTest extends TestCase {

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Autowired
    private CategoryDao categoryDao;

    @Test
    public void testFindOne() throws Exception {
        Category givenCategory = prepareCategory("test title", "test description");
        Category category = categoryDao.findOne(givenCategory.getId());

        assertEquals(givenCategory.getId(), category.getId());
    }

    @Test
    public void shouldSaveOneNewCategory() {
        Category givenCategory = prepareCategory("test title", "test description");

        Category persistedCategory = categoryDao.findOne(givenCategory.getId());

        assertEquals(givenCategory.getTitle(), persistedCategory.getTitle());
        assertEquals(givenCategory.getDescription(), persistedCategory.getDescription());
    }

    @Test
    public void notSqlInjectionable() throws Exception {
        prepareCategory("test title", "test description");

        Category category;
        String title = "' or 'a'='a";
        category = categoryDao.findByTitle(title);

        assertNull(category);
    }

    @Test
    public void notSqlInjectionableWithNativeQuery() throws Exception {
        prepareCategory("test title", "test description");

        Category category;
        String title = "' or 'a'='a";
        category = categoryDao.findByTitleUsingNativeQuery(title);

        assertNull(category);
    }

    @Test
    public void sqlInjectionableWithStringQuery() throws Exception {
        prepareCategory("test title", "test description");
        prepareCategory("admin", "admin's description");

        Category category;
        String title = "' or 'a'='a";
        category = categoryDao.findByTitleUsingStringQuery(title);

        assertNotNull(category);
        assertEquals("test title", category.getTitle());
        assertEquals("test description", category.getDescription());
    }

    private Category prepareCategory(String title, String description) {
        Category existingCategory = new Category();
        existingCategory.setTitle(title);
        existingCategory.setDescription(description);
        categoryDao.persist(existingCategory);

        return existingCategory;
    }

    @After
    public void cleanUp() {
        categoryDao.removeAll();
    }
}
