package acs.rest;

import acs.logic.EnhancedCategoriesService;
import acs.logic.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
	
	private EnhancedCategoriesService enhancedCategoriesService;
	private ProductsService productService;
	
	@Autowired
	public AdminController(EnhancedCategoriesService enhancedCategoriesService, ProductsService productService) {
		this.enhancedCategoriesService = enhancedCategoriesService;
		this.productService = productService;
	}
	
	/*--------------------- DELETE APIS ------------------- */

	@RequestMapping(path = "/shopping",
			method = RequestMethod.DELETE)
	public void deleteAllShopping() {
		this.productService.deleteAllProducts();
		this.enhancedCategoriesService.deleteAllShopping();
	}

}
