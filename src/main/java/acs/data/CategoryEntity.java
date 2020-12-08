package acs.data;
import acs.exceptions.BadRequestException;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import java.util.HashSet;
import java.util.Set;

@Node(labels = "CATEGORY")
public class CategoryEntity {

    @Id @GeneratedValue private Long id;
    private String name;
    private String description ;

    @Relationship(type = "belongsToCategory", direction = Relationship.Direction.OUTGOING) private CategoryEntity parentCategory;
    @Relationship(type = "categoryChildren", direction = Relationship.Direction.OUTGOING) private Set<CategoryEntity> categoryEntitySet;
    @Relationship(type = "productsChildren", direction = Relationship.Direction.OUTGOING) private Set<ProductEntity> productEntitySet;

    public CategoryEntity() {
        this.categoryEntitySet = new HashSet<>();
        this.productEntitySet = new HashSet<>();
    }

    public CategoryEntity(String name, String description, CategoryEntity parentCategory) {
        super();
        this.name = name;
        this.description = description;
        this.parentCategory = parentCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.isEmpty()) {
            throw new BadRequestException("Name of category can not be empty or null");
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryEntity getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryEntity parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Set<CategoryEntity> getCategoryEntitySet() {
        return categoryEntitySet;
    }

    public void setCategoryEntitySet(Set<CategoryEntity> categoryEntitySet) {
        this.categoryEntitySet = categoryEntitySet;
    }

    public Set<ProductEntity> getProductEntitySet() {
        return productEntitySet;
    }

    public void setProductEntitySet(Set<ProductEntity> productEntitySet) {
        this.productEntitySet = productEntitySet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
