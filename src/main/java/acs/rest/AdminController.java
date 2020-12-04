package acs.rest;

import acs.logic.EnhancedCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
	
	private EnhancedCategoriesService enhancedCategoriesService;
	
	@Autowired
	public AdminController(EnhancedCategoriesService enhancedCategoriesService) {
		this.enhancedCategoriesService = enhancedCategoriesService;
	}
	
	/*--------------------- DELETE APIS ------------------- */

	@RequestMapping(path = "/shopping",
			method = RequestMethod.DELETE)
	public void deleteAllShopping() {
		this.enhancedCategoriesService.deleteAllShopping();
	}

}
