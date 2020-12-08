package acs.rest;

import acs.logic.EnhancedCategoriesService;
import acs.logic.EnhancedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
	
	private EnhancedCategoriesService enhancedCategoriesService;
	private EnhancedProductService enhancedProductService;
	
	@Autowired
	public AdminController(EnhancedCategoriesService enhancedCategoriesService, EnhancedProductService enhancedProductService) {
		this.enhancedCategoriesService = enhancedCategoriesService;
		this.enhancedProductService = enhancedProductService;
	}
	
	/*--------------------- DELETE APIS ------------------- */

	@RequestMapping(path = "/shopping",
			method = RequestMethod.DELETE)
	public void deleteAllShopping() {
		this.enhancedProductService.deleteAllProducts();
		this.enhancedCategoriesService.deleteAllCategories();
	}

}
