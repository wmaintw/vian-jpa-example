package mymvc.bo;

import mymvc.dao.CategoryDao;
import mymvc.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("CategoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Autowired
    private CategoryDao categoryDao;

    public Category findFirst() {
        Category category;

        category = categoryDao.findOne(1);

        return category;
    }

}
