package acs.logic.utils;
import acs.boundary.ProductBoundary;
import acs.data.ProductDetailEntity;
import acs.data.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class ProductConverter {
    private CategoryConverter categoryConverter;

    public ProductConverter() {
        this.categoryConverter = new CategoryConverter();
    }

    public ProductBoundary fromEntity(ProductEntity entity) {
        ProductBoundary rv = new ProductBoundary();
        rv.setId(entity.getId() + "");
        rv.setImage(entity.getImage());
        rv.setName(entity.getName());
        rv.setPrice(entity.getPrice());
        rv.setProductDetails(setToProductDetailEntityMap(entity.getProductDetails()));
        rv.setCategory(categoryConverter.fromEntity(entity.getCategoryEntity()));
        return rv;
    }

    public ProductEntity toEntity(ProductBoundary boundary) {
        ProductEntity rv = new ProductEntity();
        rv.setId(Long.parseLong(boundary.getId()));
        rv.setImage(boundary.getImage());
        rv.setName(boundary.getName());
        rv.setPrice(boundary.getPrice());
        rv.setCategoryEntity(categoryConverter.toEntity(boundary.getCategory()));
        rv.setProductDetails(mapToProductDetailEntitySet(boundary.getProductDetails()));
        return rv;
    }

    public Set<ProductDetailEntity> mapToProductDetailEntitySet(Map<String,Object> productDetails) {
        Set<ProductDetailEntity> productDetailsEntitySet = new HashSet<>();

        for (Map.Entry<String,Object> entry : productDetails.entrySet()){
            productDetailsEntitySet.add(new ProductDetailEntity(entry.getKey(),entry.getValue()));
        }
        return productDetailsEntitySet;
    }

    public Map<String,Object> setToProductDetailEntityMap(Set<ProductDetailEntity> productDetails) {
        Map<String,Object> productDetailsEntityMap = new HashMap<>();

        for (ProductDetailEntity productDetailEntity : productDetails) {
            productDetailsEntityMap.put(productDetailEntity.getKey(), productDetailEntity.getValue());
        }

        return productDetailsEntityMap;
    }
}