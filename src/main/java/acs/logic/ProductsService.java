package acs.logic;
import acs.boundary.ProductBoundary;

import java.util.List;

public interface ProductsService {

    ProductBoundary createProduct(ProductBoundary productBoundary);

    ProductBoundary getProduct(String productId);

    void deleteAllProducts();

    List<ProductBoundary> getAllProducts(String filterType, String filterValue, String sortBy, String sortOrder, int page, int size);

    //List<ProductBoundary> getAllProductsByCriteria(String criteriaType, String criteriaValue,
                                             //      int size, int page, String sortBy, String sortOrder);
}
