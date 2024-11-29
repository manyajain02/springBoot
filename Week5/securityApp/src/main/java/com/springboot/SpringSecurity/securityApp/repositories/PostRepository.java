package com.springboot.SpringSecurity.securityApp.repositories;


import com.springboot.SpringSecurity.securityApp.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
