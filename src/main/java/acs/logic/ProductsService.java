package acs.logic;
import acs.boundary.ProductBoundary;

import java.util.List;

public interface ProductsService {
    ProductBoundary createProduct(ProductBoundary productBoundary);

    ProductBoundary getProduct(String productId);

    List<ProductBoundary> getAllProducts(int size, int page, String sortBy, String sortOrder);

    //List<ProductBoundary> getAllProductsByCriteria(String criteriaType, String criteriaValue,
                                             //      int size, int page, String sortBy, String sortOrder);
}
