package acs.logic.utils;

import acs.boundary.CategoryBoundary;
import acs.data.CategoryEntity;
import org.springframework.stereotype.Component;


@Component
public class CategoryConverter {

	public CategoryBoundary fromEntity(CategoryEntity entity) {
		CategoryBoundary rv = new CategoryBoundary();
		rv.setName(entity.getName());
		rv.setDescription(entity.getDescription());

		return rv;
	}

	public CategoryEntity toEntity(CategoryBoundary boundary) {
		CategoryEntity rv = new CategoryEntity();
		rv.setName(boundary.getName());
		rv.setDescription(boundary.getDescription());
		return rv;
	}
}
