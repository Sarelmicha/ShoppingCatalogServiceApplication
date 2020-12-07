package acs.logic.db;
import acs.boundary.ProductBoundary;
import acs.dao.CategoryDao;
import acs.dao.ProductDao;
import acs.data.CategoryEntity;
import acs.data.ProductEntity;
import acs.exceptions.NotFoundException;
import acs.logic.ProductsService;
import acs.logic.utils.FilterType;
import acs.logic.utils.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DatabaseProductsService implements ProductsService {
    private final ProductDao productDao; // Data access object
    private final ProductConverter converter;
    private final CategoryDao categoryDao;

    @Autowired
    public DatabaseProductsService(ProductDao productDao, CategoryDao categoryDao, ProductConverter converter) {
        this.productDao = productDao;
        this.converter = converter;
        this.categoryDao = categoryDao;
    }

    @Override
    @Transactional
    public ProductBoundary createProduct(ProductBoundary productBoundary) {

        ProductEntity productEntity = this.converter.toEntity(productBoundary);
        Optional<ProductEntity> productInDB = this.productDao.findById(productEntity.getId());

        if(productInDB.isPresent()){
            throw new RuntimeException("Product with categorical number " + productEntity.getId() + " already exist");
        }
        CategoryEntity categoryInDB = this.categoryDao.findOneByName(productBoundary.getCategory().getName());
        if(categoryInDB == null){
            throw  new RuntimeException("Category for product doesn't exist");
        }
        categoryInDB.getProductEntitySet().add(productEntity);
        productEntity.setCategoryEntity(categoryInDB);

        this.productDao.save(productEntity);
        this.categoryDao.save(categoryInDB);

        return this.converter.fromEntity(productEntity);
    }

    @Override
    public ProductBoundary getProduct(String productId) {
        return this.converter.fromEntity(this.productDao.findById(Long.parseLong(productId)).
                orElseThrow(()-> new NotFoundException("Product does not exists")));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductBoundary> getAllProducts(String filterType, String filterValue, String sortBy, String sortOrder, int page, int size) {

        if (filterType != null && filterValue != null) {
            if (filterType.equals(FilterType.NAME.toString())) {
                return this.productDao
                        .findAllByNameLikeIgnoreCase(filterValue,
                                PageRequest.of(page, size, Sort.Direction.valueOf(sortOrder), sortBy))
                        .stream().map(this.converter::fromEntity).collect(Collectors.toList());
            } else if (filterType.equals(FilterType.MIN_PRICE.toString())) {
                return this.productDao
                        .findAllByPriceGreaterThan(Float.parseFloat(filterValue),
                                PageRequest.of(page, size, Sort.Direction.valueOf(sortOrder), sortBy))
                        .stream().map(this.converter::fromEntity).collect(Collectors.toList());
            } else if (filterType.equals(FilterType.MAX_PRICE.toString())) {
                return productDao.findAllByPriceLessThan(Float.parseFloat(filterValue),
                        PageRequest.of(page, size, Sort.Direction.valueOf(sortOrder), sortBy))
                        .stream().map(this.converter::fromEntity).collect(Collectors.toList());
            }
            else if (filterType.equals(FilterType.CATEGORY.toString())) {
/*                 CategoryEntity categoryEntity = categoryDao.findOneByName(filterValue);
                 Set<ProductEntity> set = new HashSet<>();
                 findAllProducts(categoryEntity, set);
                 List<ProductBoundary> allProducts = set.stream().map((value) -> this.converter.fromEntity(value)).collect(Collectors.toList());*/
                 return productDao.findAllByParentCategory_Name(filterValue, PageRequest.of(page, size, Sort.Direction.valueOf(sortOrder), sortBy))
                         .stream().map(this.converter::fromEntity).collect(Collectors.toList());
            }
        }

        return this.productDao.findAll(
                PageRequest.of(page, size, Sort.Direction.valueOf(sortOrder), sortBy)).getContent()
                .stream().map(this.converter::fromEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteAllProducts() {
        this.productDao.deleteAll();
    }


  /*  public void findAllProducts(CategoryEntity categoryEntity, Set<ProductEntity> productEntitySet){
//        if(categoryEntity.getCategoryEntitySet().isEmpty()){
//            return;
//        }
        for(ProductEntity productEntity: categoryEntity.getProductEntitySet()){
            productEntitySet.add(productEntity);
        }
        for(CategoryEntity category: categoryEntity.getCategoryEntitySet()){
            findAllProducts(category, productEntitySet);
        }
    }
*/
}
