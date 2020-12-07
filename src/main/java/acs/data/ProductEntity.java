package acs.data;
import org.springframework.data.neo4j.core.schema.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Node(labels="PRODUCT")
public class ProductEntity {
    @Id private Long id;

    //    @NotEmpty(message="Name can not be empty")
    private String name;

    //    @NotEmpty(message="Price can not be empty")
    private Float price;

    //    @NotEmpty(message="Image can not be empty")
    private String image;

//    @Convert(acs.logic.utils.MapToJsonConverter.class)
    @CompositeProperty
    private Map<String, Object> productDetails;
    @Relationship(type = "belongsToCategory", direction = Relationship.Direction.OUTGOING) private CategoryEntity parentCategory;

    public ProductEntity() {
        this.productDetails = new HashMap<>();
    }

    public ProductEntity(String name, Float price, String image,  Map<String, Object> productDetailsElements) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.productDetails = productDetailsElements;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
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