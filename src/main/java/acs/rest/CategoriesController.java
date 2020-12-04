package acs.rest;

import acs.boundary.CategoryBoundary;
import acs.logic.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoriesController {
	private CategoriesService categoriesService;

	@Autowired
	public CategoriesController(CategoriesService categoriesService) {
		this.categoriesService = categoriesService;
	}

	/*--------------------- GET APIS ------------------- */

	// Get user
//	@RequestMapping(path = "/users/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public CategoryBoundary getUser(@PathVariable("email") String email) {
//		return userService.getUser(email);
//	}


	  // Get all users with pagination

	  @RequestMapping(path = "/shopping/categories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	  public CategoryBoundary[] getAllCategories(
	  		@RequestParam(name = "size", required = false, defaultValue = "10") int size,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "sortBy", required = false, defaultValue = "name") String sortBy,
			@RequestParam(name = "sortOrder", required = false, defaultValue = "ASC") String sortOrder) {

	  	return categoriesService.getAllCategories(size, page, sortBy, sortOrder).toArray(new CategoryBoundary[0]);
	  }


	// Get all users by criteria type with pagination
//	@RequestMapping(path = "/users/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public CategoryBoundary[] getAllUsers(
//			@RequestParam(name = "criteriaType", required = false) String criteriaType,
//			@RequestParam(name = "criteriaValue", required = false) String criteriaValue,
//			@RequestParam(name = "size", required = false, defaultValue = "10") int size,
//			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
//			@RequestParam(name = "sortBy", required = false, defaultValue = "email") String sortBy,
//			@RequestParam(name = "sortOrder", required = false, defaultValue = "ASC") String sortOrder) {
//		return userService.getAllUsers(criteriaType, criteriaValue, size, page, sortBy, sortOrder).toArray(new CategoryBoundary[0]);
//	}

	/*--------------------- POST APIS ------------------- */

	// Store category
	@RequestMapping(path = "/shopping/categories", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CategoryBoundary createCategory(@RequestBody CategoryBoundary categoryBoundary) {
		return categoriesService.createCategory(categoryBoundary);
	}
}
