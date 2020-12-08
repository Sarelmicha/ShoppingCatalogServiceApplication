package acs.logic.utils;

import acs.boundary.ProductBoundary;
import acs.data.ProductEntity;
import org.neo4j.driver.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProductConverter {
    private CategoryConverter categoryConverter;

    public ProductConverter() {
        this.categoryConverter = new CategoryConverter();
    }

    public ProductBoundary fromEntity(ProductEntity entity) {
        ProductBoundary rv = new ProductBoundary();
        rv.setId(entity.getId());
        rv.setImage(entity.getImage());
        rv.setName(entity.getName());
        rv.setPrice(entity.getPrice());
        try{
            rv.setProductDetails(this.setToProductDetailEntityMap(entity.getProductDetails()));
        }
        catch(ClassCastException e){
            rv.setProductDetails(entity.getProductDetails());
        }
        rv.setCategory(categoryConverter.fromEntity(entity.getCategoryEntity()));
        return rv;
    }

    public ProductEntity toEntity(ProductBoundary boundary) {
        ProductEntity rv = new ProductEntity();
        rv.setId(boundary.getId());
        rv.setImage(boundary.getImage());
        rv.setName(boundary.getName());
        rv.setPrice(boundary.getPrice());
        rv.setCategoryEntity(categoryConverter.toEntity(boundary.getCategory()));
        rv.setProductDetails(boundary.getProductDetails());
        return rv;
    }


    public Map<String, Object> setToProductDetailEntityMap(Map<String, Object>  entityProductDetails) {
        Map<String, Object> temp = new HashMap<>();
        for (String key : entityProductDetails.keySet()) {
            temp.put(key, ((Value)(entityProductDetails.get(key))).asObject());
        }
        return temp;
    }
}