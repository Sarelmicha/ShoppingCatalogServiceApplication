package acs.dao;

import acs.data.CategoryEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends PagingAndSortingRepository<CategoryEntity, Long> {
//    List<CategoryEntity> findAllByLastNameLikeIgnoreCase(@Param("lastName") String lastName, Pageable pageable);
//
//    List<CategoryEntity> findAllByAgeGreaterThan(@Param("age") int age, Pageable pageable);
//
    CategoryEntity findOneByName(@Param("name") String name);

}