package mymvc.dao;

import junit.framework.TestCase;
import mymvc.domain.Category;
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
        Category category = categoryDao.findOne(1);

        assertEquals(1, category.getId());
    }

    @Test
    public void shouldSaveOneNewCategory() {

        Category givenCategory = new Category();
        givenCategory.setTitle("test title");
        givenCategory.setDescription("test description");
        categoryDao.persist(givenCategory);

        Category persistedCategory = categoryDao.findOne(givenCategory.getId());

        assertEquals(givenCategory.getTitle(), persistedCategory.getTitle());
        assertEquals(givenCategory.getDescription(), persistedCategory.getDescription());
    }
}
