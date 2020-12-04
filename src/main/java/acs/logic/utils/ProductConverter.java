package acs.logic.utils;
import acs.boundary.ProductBoundary;
import acs.data.ProductEntity;
import org.springframework.stereotype.Component;

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
        //rv.setProductDetails(entity.getProductDetails());
        rv.setCategory(categoryConverter.fromEntity(entity.getCategory()));
        return rv;
    }

    public ProductEntity toEntity(ProductBoundary boundary) {
        ProductEntity rv = new ProductEntity();
        rv.setId(Long.parseLong(boundary.getId()));
        rv.setImage(boundary.getImage());
        rv.setName(boundary.getName());
        rv.setPrice(boundary.getPrice());
        rv.setCategory(categoryConverter.toEntity(boundary.getCategory()));
        //rv.setProductDetails(boundary.getProductDetails());
        return rv;
    }
}