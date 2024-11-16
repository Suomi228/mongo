package org.example.phd_book.service;

import org.example.phd_book.dto.PostDTO;
import org.example.phd_book.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PostService {
    List<Post> findPostByAuthor(String author);
    List<Post> findPostsByViews(int views);
    void deletePost(String id);
    PostDTO createPost(PostDTO postDTO);
    PostDTO getPost(String id);
    List<PostDTO> findAll();
    PostDTO updatePost(String id, PostDTO postDTO);
}
