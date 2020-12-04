package acs.rest;

import acs.boundary.ProductBoundary;
import acs.logic.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    /*--------------------- GET APIS ------------------- */
    // Get product
	@RequestMapping(path = "/shopping/products/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductBoundary getProduct(@PathVariable("productId") String productId) {
		return productsService.getProduct(productId);
	}

    @RequestMapping(path = "/shopping/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductBoundary[] getAllProducts(
            @RequestParam(name = "filterType", required = false) String filterType,
            @RequestParam(name = "filterValue", required = false) String filterValue,
            @RequestParam(name = "sortBy", required = false, defaultValue = "price") String sortBy,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "ASC") String sortOrder,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size){
        return productsService.getAllProducts(filterType, filterValue, sortBy, sortOrder, page, size).toArray(new ProductBoundary[0]);
    }


    // Get all products by criteria type with pagination
//	@RequestMapping(path = "/users/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ProductBoundary[] getAllProductsByCriteria(
//			@RequestParam(name = "criteriaType", required = false) String criteriaType,
//			@RequestParam(name = "criteriaValue", required = false) String criteriaValue,
//			@RequestParam(name = "size", required = false, defaultValue = "10") int size,
//			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
//			@RequestParam(name = "sortBy", required = false, defaultValue = "email") String sortBy,
//			@RequestParam(name = "sortOrder", required = false, defaultValue = "ASC") String sortOrder) {
//		return productsService.getAllProductsByCriteria(criteriaType, criteriaValue, size, page, sortBy, sortOrder).toArray(new ProductBoundary[0]);
//	}


    /*--------------------- POST APIS ------------------- */

    // Store product
    @RequestMapping(path = "/shopping/products", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductBoundary createProduct(@RequestBody ProductBoundary productBoundary) {
        return productsService.createProduct(productBoundary);
    }
}
