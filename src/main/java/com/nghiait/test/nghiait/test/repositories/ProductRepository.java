package com.nghiait.test.nghiait.test.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nghiait.test.nghiait.test.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	List<Product> findByProductName(String name);

}
