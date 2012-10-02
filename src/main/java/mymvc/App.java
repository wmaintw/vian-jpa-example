package mymvc;

import mymvc.bo.CategoryService;
import mymvc.bo.CategoryServiceImpl;
import mymvc.domain.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 10/2/12
 * Time: 12:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class App {
    public static void main(String[] args) {
        Category category;

//        category = tranditionalGetCategory();

        category = jpaGetCategory();

        System.out.println(category.getId() + "/" + category.getTitle());
    }

    private static Category jpaGetCategory() {
        CategoryService categoryService = new CategoryServiceImpl();
        return categoryService.findFirst();
    }

    private static Category tranditionalGetCategory() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceNamePU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Category.class, 1);
    }
}
