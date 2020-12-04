package acs.logic.db;
import acs.boundary.ProductBoundary;
import acs.dao.CategoryDao;
import acs.dao.ProductDao;
import acs.data.ProductEntity;
import acs.exceptions.NotFoundException;
import acs.logic.ProductsService;
import acs.logic.utils.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DatabaseProductsService implements ProductsService {
    private ProductDao productDao; // Data access object
    private ProductConverter converter;
    private CategoryDao categoryDao;

    @Autowired
    public DatabaseProductsService(ProductDao productDao, CategoryDao categoryDao, ProductConverter converter) {
        this.productDao = productDao;
        this.converter = converter;
        this.categoryDao = categoryDao;
    }


    // TODO check with eyal
    @Override
    @Transactional
    public ProductBoundary createProduct(ProductBoundary productBoundary) {
//        ProductEntity product = this.productDao.findById(uuid).
//        if(product != null){
//            throw new AlreadyExistsException("This product already exists");
//        }
        ProductEntity productEntity = this.converter.toEntity(productBoundary);
        productEntity = this.productDao.save(productEntity);
        return this.converter.fromEntity(productEntity);
    }

    @Override
    public ProductBoundary getProduct(String productId) {
        return this.converter.fromEntity(this.productDao.findById(Long.parseLong(productId)).
                orElseThrow(()-> new NotFoundException("Product does not exists")));
    }

    @Override
    public List<ProductBoundary> getAllProducts(int size, int page, String sortBy, String sortOrder) {
        return this.productDao.findAll(
                PageRequest.of(page, size, Sort.Direction.valueOf(sortOrder), sortBy)).getContent()
                .stream().map(this.converter::fromEntity).collect(Collectors.toList());
    }


}
