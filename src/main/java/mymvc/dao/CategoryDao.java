package mymvc.dao;

import mymvc.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CategoryDao {
    public Category findOne(int id);

    public void persist(Category givenCategory);
}
