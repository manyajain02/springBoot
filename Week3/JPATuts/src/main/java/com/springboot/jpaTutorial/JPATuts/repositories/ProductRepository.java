package com.springboot.jpaTutorial.JPATuts.repositories;

import com.springboot.jpaTutorial.JPATuts.entities.ProductEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByTitle(String pepsi);
    List<ProductEntity> findBy(Sort sort);
    List<ProductEntity> findByCreatedAtAfter(LocalDateTime after);

    List<ProductEntity> findByQuantityAndPrice(int quantity, BigDecimal price);

    List<ProductEntity> findByTitleLike(String title);

    List<ProductEntity> findByTitleContaining(String pepsi);

    List<ProductEntity> findByTitleContainingIgnoreCase(String title1);

//    Optional<ProductEntity> findByTitleAndPrice(String title , BigDecimal price);
   // creating my own query here
    @Query("select e from ProductEntity e where e.title=?1 and e.price=?2")
    Optional<ProductEntity> findByTitleAndPrice(String title ,BigDecimal price);

    List<ProductEntity> findByOrderByPrice();
}
