package com.nghiait.test.nghiait.test.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nghiait.test.nghiait.test.model.Product;
import com.nghiait.test.nghiait.test.model.ResponseObject;
import com.nghiait.test.nghiait.test.repositories.ProductRepository;

@RestController
@RequestMapping(path = "/api/v1/Products")
public class ProductController {

	// OI = Dependency Infection (Tương tự Singletion partern)
	@Autowired
	private ProductRepository repository;

	@GetMapping("")
	// this request is http://localhost:8080/api/v1/Products
	List<Product> getAllProducts() {

//		return List.of(
//				new Product(1L, "nghia", 1998, 20.0, ""),
//				new Product(2L, "lan anh", 1999, 20.0, "") );
//	}

		return repository.findAll();

		// save Database, now have H2 DB = in-serory Database
		// can also send request using Postman

	}

	@GetMapping("/{id}")
	// return status, data, message
	ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
		Optional<Product> foundProduct = repository.findById(id);
//		if(foundProduct.isPresent()) {
//			return ResponseEntity.status(HttpStatus.OK).body(
//					new ResponseObject("ok", "Query product successfully", foundProduct)
//					);
//		}else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//					new ResponseObject("false", "Cannot find product with id =  " + id, foundProduct)
//					);
//		}

		// change if else to return
		return foundProduct.isPresent() ? ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("ok", "Query product successfully", foundProduct)) :

				ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject("failed", "Cannot find product with id =  " + id, foundProduct));

	}

	// insert new product with post method
	// Postman : Raw, JSON
	@PostMapping("/insert")
	ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {

		// 2 product must not have same Name
		List<Product> foundProducts = repository.findByProductName(newProduct.getProductName().trim());

		// neu trung name thi bao loi
		if (foundProducts.size() > 0) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Product name already taken", ""));
		}

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("ok", "Insert Product successfully", repository.save(newProduct)));
	}

	// update , upsert = update if found, otherwise insert
	@PutMapping("/{id}")
	ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
		Product updatedProduct = repository.findById(id).map(product -> {
			product.setProductName(newProduct.getProductName());
			product.setYear(newProduct.getYear());
			product.setPrice(newProduct.getPrice());
			return repository.save(product);
		}).orElseGet(() -> {
			newProduct.setId(id);
			return repository.save(newProduct);

		});
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("ok", "update product successfully", updatedProduct));

	}
	//delete product
	@DeleteMapping("/{id}")
	ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id){
		boolean exists = repository.existsById(id);
		if(exists) {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("ok", "Delete successfully", "")
					);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ResponseObject("failed", "Cannot delete product", ""));
	}

}
