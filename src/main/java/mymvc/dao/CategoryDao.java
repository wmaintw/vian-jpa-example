package mymvc.dao;

import mymvc.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CategoryDao {
    public Category findOne(int id);

    public Category findByTitle(String title);
    public Category findByTitleUsingNativeQuery(String title);
    public Category findByTitleUsingStringQuery(String title);

    public void persist(Category givenCategory);

    public void removeAll();
}
