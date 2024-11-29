package com.springboot.SpringSecurity.securityApp.services;


import com.springboot.SpringSecurity.securityApp.dto.PostDTO;

import java.util.List;

public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);
}
