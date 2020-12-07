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
        rv.setProductDetails(this.setToProductDetailEntityMap(entity.getProductDetails()));
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
        rv.setProductDetails(boundary.getProductDetails());
        return rv;
    }

//    public Set<ProductDetailEntity> mapToProductDetailEntitySet(Map<String, Object> productDetails) {
//        Set<ProductDetailEntity> productDetailsEntitySet = new HashSet<>();
//
//        for (Map.Entry<String, Object> entry : productDetails.entrySet()) {
//            productDetailsEntitySet.add(new ProductDetailEntity(entry.getKey(), entry.getValue()));
//        }
//        return productDetailsEntitySet;
//    }

    public Map<String, Object> setToProductDetailEntityMap(Map<String, Object>  entityProductDetails) {
        Map<String, Object> temp = new HashMap<>();
        for (String key : entityProductDetails.keySet()) {
            Class myClass = entityProductDetails.get(key).getClass();
            if (myClass == Integer.class) {
                int t = (int) entityProductDetails.get(key);
                temp.put(key, t);
            } else if (myClass == Boolean.class) {
                boolean t = (boolean) entityProductDetails.get(key);
                temp.put(key, t);
            } else if (myClass == Float.class) {
                float t = (float) entityProductDetails.get(key);
                temp.put(key, t);
            } else {
                String t = entityProductDetails.get(key).toString().trim();
                temp.put(key, t);
            }
        }
        return temp;
    }
}