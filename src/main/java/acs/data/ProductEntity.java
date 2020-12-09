package acs.data;
import acs.exceptions.BadRequestException;
import org.neo4j.driver.internal.shaded.reactor.util.annotation.NonNull;
import org.neo4j.driver.internal.shaded.reactor.util.annotation.Nullable;
import org.springframework.data.neo4j.core.schema.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Node(labels="PRODUCT")
public class ProductEntity {

    @Id private String id;
    private String name;
    private Double price;
    private String image;

    @CompositeProperty private Map<String, Object> productDetails;

    @Relationship(type = "belongsToCategory", direction = Relationship.Direction.OUTGOING) private CategoryEntity parentCategory;

    public ProductEntity() {
        this.productDetails = new HashMap<>();
    }

    public ProductEntity(String name, Double price, String image,  Map<String, Object> productDetailsElements) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.productDetails = productDetailsElements;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.isEmpty()){
            throw new BadRequestException("Name of product can not be empty or null");
        }
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        if(price == null){
            throw new BadRequestException("Price can not be null.");
        }
        if(price < 0){
            throw new BadRequestException("Price can not be negative.");
        }
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CategoryEntity getCategoryEntity() {
        return parentCategory;
    }

    public void setCategoryEntity(CategoryEntity category) {
        this.parentCategory = category;
    }

    public Map<String, Object> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(Map<String, Object> productDetails) {
        this.productDetails = productDetails;
    }

}