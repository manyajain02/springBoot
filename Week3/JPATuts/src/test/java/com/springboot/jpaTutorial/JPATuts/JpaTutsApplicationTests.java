package com.springboot.jpaTutorial.JPATuts;

import com.springboot.jpaTutorial.JPATuts.entities.ProductEntity;
import com.springboot.jpaTutorial.JPATuts.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.springboot.jpaTutorial.JPATuts.repositories.ProductRepository.*;

@SpringBootTest
class JpaTutsApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testRepository(){
		ProductEntity productEntity = ProductEntity.builder()
				.sku("PaperBoat1234")
				.title("Paper Boat Beverages")
				.price(BigDecimal.valueOf(123.10))
				.quantity(19)
				.build();
		ProductEntity savedProductRepository = productRepository.save(productEntity);
		System.out.println(savedProductRepository);
	}

	@Test
	void getRepository(){
//		List<ProductEntity> entities = productRepository.findByTitle("Pepsi");
				List<ProductEntity> findByTitles = productRepository.findByTitle("Paper Boat Beverages");
//				List<ProductEntity> entities = productRepository.findByCreatedAtAfter(
//						LocalDateTime.of(2024, 1 ,1,0,0,0));
//				System.out.println("findByCreatedAtAfter" + entities);

//				List<ProductEntity> entities = productRepository.findByQuantityAndPrice(19, BigDecimal.valueOf(123.10));
//				System.out.println("findByQuantityAndPrice" + entities);
		List<ProductEntity> findbytitle = productRepository.findByTitleLike("%P%");
		System.out.println("findByTitleContaining" + findbytitle);

		List<ProductEntity> findByTitleContainingIgnoreCase = productRepository.findByTitleContainingIgnoreCase("pepsi"); // uppercase or lower case
		System.out.println("findByTitleContainingIgnoreCase" + findByTitleContainingIgnoreCase);
		Optional<ProductEntity> findByTitleAndPrice = productRepository.findByTitleAndPrice("Paper Boat Beverages" ,BigDecimal.valueOf(123.10)); // uppercase or lower case
		System.out.println("findByTitleAndPrice" + findByTitleAndPrice);

	}
}
