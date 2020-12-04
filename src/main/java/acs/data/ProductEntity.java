package acs.data;


import org.neo4j.ogm.annotation.typeconversion.Convert;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.HashMap;
import java.util.Map;

@Node(labels="PRODUCT")
public class ProductEntity {

    @Id
    @GeneratedValue
    private long id;        // ID PK VARCHAR(255)

//    @NotEmpty(message="Name can not be empty")
    private String name;    // NAME VARCHAR(255)

//    @NotEmpty(message="Price can not be empty")
    private Float price;

//    @NotEmpty(message="Image can not be empty")
    private String image;    // IMAGE VARCHAR(255)

    @Convert(acs.logic.utils.MapToJsonConverter.class)
    private Map<Long, Object> productDetails; // ELEMENT_ATTRIBUTES CLOB

    private CategoryEntity category;

    public ProductEntity() {
        this.productDetails = new HashMap<>();
    }

    public ProductEntity(long id, String name, Float price, String image, Map<Long, Object> productDetails) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.productDetails = productDetails;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Map<Long, Object> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(Map<Long, Object> productDetails) {
        this.productDetails = productDetails;
    }


    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
