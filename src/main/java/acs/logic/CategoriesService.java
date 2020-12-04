package acs.logic;

import acs.boundary.CategoryBoundary;

import java.util.List;

public interface CategoriesService {

	CategoryBoundary createCategory(CategoryBoundary categoryBoundary);

	List<CategoryBoundary> getAllCategories(int size, int page, String sortBy, String sortOrder);

//	CategoryBoundary getUser(String email);
////
//	List<CategoryBoundary> getAllUsers(String criteriaType, String criteriaValue, int size, int page,
//									   String sortBy, String sortOrder);

}
