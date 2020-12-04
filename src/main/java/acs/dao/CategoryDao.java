package acs.dao;

import acs.data.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends JpaRepository<CategoryEntity, String> {
//    List<CategoryEntity> findAllByLastNameLikeIgnoreCase(@Param("lastName") String lastName, Pageable pageable);
//
//    List<CategoryEntity> findAllByAgeGreaterThan(@Param("age") int age, Pageable pageable);
//
    CategoryEntity findByName(@Param("name") String name);

}