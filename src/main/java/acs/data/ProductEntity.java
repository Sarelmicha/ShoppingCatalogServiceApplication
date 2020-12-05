package acs.data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node(labels="PRODUCT")
public class ProductEntity {
    @Id @GeneratedValue private Long id;

    //    @NotEmpty(message="Name can not be empty")
    private String name;

    //    @NotEmpty(message="Price can not be empty")
    private Float price;

    //    @NotEmpty(message="Image can not be empty")
    private String image;

//    @Convert(acs.logic.utils.MapToJsonConverter.class)
    @Relationship(type = "belongs", direction = Relationship.Direction.OUTGOING) private Set<ProductDetailEntity> productDetails;
    @Relationship(type = "belongsToCategory", direction = Relationship.Direction.OUTGOING) private CategoryEntity categoryEntity;

    public ProductEntity() {
        this.productDetails = new HashSet<>();
    }

    public ProductEntity(String name, Float price, String image,  Set<ProductDetailEntity> productDetailsElements) {
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
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public Set<ProductDetailEntity> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(Set<ProductDetailEntity> productDetails) {
        this.productDetails = productDetails;
    }

}