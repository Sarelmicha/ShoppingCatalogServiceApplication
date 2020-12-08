package acs.boundary;


public class CategoryBoundary {

	private String name;
	private String description;
	private String parentCategory;


	public CategoryBoundary() {
	}

	public CategoryBoundary(String name, String description, String parentCategory) {
		this.name = name;
		this.description = description;
		this.parentCategory = parentCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(String parentCategory) {
		this.parentCategory = parentCategory;
	}
}
