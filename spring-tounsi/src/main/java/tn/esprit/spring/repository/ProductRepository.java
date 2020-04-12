



package tn.esprit.spring.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.spring.entity.Product;
import tn.esprit.spring.entity.Ray;


public interface ProductRepository extends CrudRepository<Product, Long> {
	List<Product> findByRay(Ray ray);
}