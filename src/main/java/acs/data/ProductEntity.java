package acs.data;
import org.neo4j.ogm.annotation.typeconversion.Convert;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
  @Relationship(type = "belongs", direction = Relationship.Direction.INCOMING) private Set<ProductDetailEntity> productDetailsElements;

    private CategoryEntity category;

    public ProductEntity() {
        this.productDetailsElements = new HashSet<>();
    }

    public ProductEntity(Long id, String name, Float price, String image,  Set<ProductDetailEntity> productDetailsElements) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.productDetailsElements = productDetailsElements;

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

    public Set<ProductDetailEntity> getProductDetailsElements() {
        return productDetailsElements;
    }

    public void setProductDetailsElements(Set<ProductDetailEntity> productDetailsElements) {
        this.productDetailsElements = productDetailsElements;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}