package com.nghiait.test.nghiait.test.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nghiait.test.nghiait.test.model.Product;
import com.nghiait.test.nghiait.test.repositories.ProductRepository;

/*
 * Using Docker Hub
 * 
docker run -d --rm --name mysql-spring-boot-tutorial \
-e MYSQL_ROOT_PASSWORD=root \
-e MYSQL_USER=root \
-e MYSQL_PASSWORD=root \
-e MYSQL_DATABASE=test_db \
-p 3309:3306 \
--volume mysql-spring-boot-tutorial-volume:/var/lib/mysql \
mysql:latest

mysql -h localhost -P 3309 --protocol=tcp -u hoangnd -p

* */

@Configuration
public class Database {

	// Loger (when erro or done ==> print loger
	private static final Logger logger = LoggerFactory.getLogger(Database.class);

	@Bean
	CommandLineRunner initDatabase(ProductRepository productRepository) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
//				Product productA = new Product( "nghia12321", 1998, 20.0, "");
//				Product productB = new Product( "lan anh", 1999, 20.0, "");
//				logger.info("insert data " + productRepository.save(productA));
//				logger.info("insert data " + productRepository.save(productB));
			}
		};
	}

}
