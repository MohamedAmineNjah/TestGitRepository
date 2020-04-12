
package tn.esprit.spring.controller;

import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Product;
import tn.esprit.spring.service.interfaces.IRayInfoService;



/**
 * 
 * Cette classe sert a implémenter les méthodes qui permettent de gérer un projet.
 * @author Walid YAICH
 *
 */
@RestController
@RequestMapping("/aminepro")
public class ProductController  {

	@Autowired
	private IRayInfoService rayInfoService;
	
	private Logger logger = LoggerFactory.getLogger(ProductController.class);

	/**
	 * Ajouter un projet et l'affecter a un client
	 * @param projet
	 * @param clientId
	 */
	@RequestMapping(value = "/product", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addProduct(@RequestBody Product product, 
    									  @RequestParam("rayId") Long rayId){
    	logger.debug("Invocation de la resource : POST /project/");
    	rayInfoService.addProductAndAssignToRay(product, rayId);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	/**
	 * Mettre a jour les informations du projet
	 * @param projet
	 * @param clientId
	 */
	@RequestMapping(value = "/product/{productId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateProject(@RequestBody Product product, 
    											@PathVariable("productId") Long productId){
    	logger.debug("Invocation de la resource : PUT /product/{productId}");
    	try{
        	rayInfoService.updateProduct(productId, product);
    	}catch (NoResultException e) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    	return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	/**
	 * Retourner le projet s'il existe dans la base
	 * @param projectId
	 * @return Project project
	 */
    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable("productId") Long productId) {
    	logger.debug("Invocation de la resource : GET /product/{productId}");
    	
    	Product product = rayInfoService.getProductById(productId);
    	if(product == null){
    		logger.info("Impossible de trouver le projet");
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
	
	
    /**
     * Récupérer tous les projets
     */
	@RequestMapping(value = "/product", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getAllProjects(){
    	logger.debug("Invocation de la resource : GET /product");
    	List<Product> products = rayInfoService.getAllProducts();
    	if(products.isEmpty()){
        	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	}
    	return new ResponseEntity<>(products, HttpStatus.OK);
    }
	
	
    /**
     * Supprimer tous les projets
     */
	@RequestMapping(value = "/product", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllProducts(){
    	logger.debug("Invocation de la resource : DELETE /project");
    	rayInfoService.deleteAllProducts();
    	return new ResponseEntity<>(HttpStatus.OK);
    }
    	
}
