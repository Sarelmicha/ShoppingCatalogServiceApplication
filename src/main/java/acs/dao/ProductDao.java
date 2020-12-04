package acs.dao;

import acs.data.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends PagingAndSortingRepository<ProductEntity, Long> {
    List<ProductEntity> findAllByNameLikeIgnoreCase(@Param("name") String name, Pageable pageable);
    List<ProductEntity> findAllByPriceGreaterThan(@Param("price") Float price, Pageable pageable);
    List<ProductEntity> findAllByPriceLessThan(@Param("price") Float price, Pageable pageable);

}
