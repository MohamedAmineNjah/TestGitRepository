
package tn.esprit.spring.service.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entity.Product;
import tn.esprit.spring.entity.Ray;
import tn.esprit.spring.repository.ProductRepository;
import tn.esprit.spring.repository.RayRepository;
import tn.esprit.spring.service.interfaces.IRayInfoService;

/**
 * Cette classe permet de gérer les informations client.
 * @author Walid YAICH
 * 
 */
@Component
public class RayMySQLServiceImpl implements IRayInfoService{
	
	@Autowired
	private RayRepository rayRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	
	/**
	 * Ajouter un projet et l'affecter a un client
	 * @param projet
	 * @param clientId
	 */
	@Override
	public void addProductAndAssignToRay(Product product, Long rayId) {
		product.setRay(new Ray(rayId));
		productRepository.save(product);
	}
	
	
	/**
	 * Trouver un projet en ayant son Id
	 */
	@Override
	public Product getProductById(Long productId){
		return productRepository.findById(productId).get();
	}
	
	
	/**
	 * Supprimer tous les projets
	 */
	@Override
	public void deleteAllProducts() {
		productRepository.deleteAll();
	}
	
	
	/**
	 * Récupérer tous les projets
	 */
	@Override
	public List<Product> getAllProducts() {
		//Ce cast n'est pas une bonne pratique ?
		return (List<Product>) productRepository.findAll();
	}
	
	
	/**
	 * Récupérer les données client en ayant son Id
	 * @param clientId l'identifiant du client
	 * @return Client client
	 */
	@Override
	public Ray getRayById(Long rayId){////
		return rayRepository.findById(rayId).get();
	}
	
	
	/**
	 * Ajouter un client dans la base
	 */
	@Override
	public void addRay(Ray ray) {
		rayRepository.save(ray);
	}
	
	
	/**
	 * Mettre a jour les informations client
	 */
	@Override
	public void updateRayInfoById(Ray ray, Long rayId) {
		rayRepository.updateRayInfoById(ray.getTyperay(), ray.getCategoryray(),rayId);
	}

	
	@Override
	@Transactional
	public void updateProduct(Long productId, Product product){
		Product projectManagedEntity = productRepository.findById(productId).get();
		if(projectManagedEntity == null){
			throw new NoResultException();
		}
		projectManagedEntity.setBarCode(product.getBarCode());
		projectManagedEntity.setCategory(product.getCategory());
		projectManagedEntity.setExprdate(product.getExprdate());
		projectManagedEntity.setName(product.getName());
		projectManagedEntity.setPrice(product.getPrice());
		projectManagedEntity.setType(product.getType());
		
		// Il faut faire attention lorsqu'on utilise save pour mettre a jour un enregistrement dans
		// la base de données.
		// Dans ce cas si on utilise save, tout l'objet va etre sauvegardé dans la base,
		// y compris la valeur null de "client_id".
		//project.setId(projectId);
		//productRepository.save(project);
	}
	
	
	/**
	 * Supprimer un client
	 */
	@Override
	public void deleteRay(Ray ray) {
		rayRepository.delete(ray);
	}

	
	/**
	 * Calculer le nombre total des clients
	 */
	@Override
	public Long countRays() {
		return rayRepository.count();
	}
	
}
