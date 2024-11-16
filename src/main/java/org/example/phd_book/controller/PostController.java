package org.example.phd_book.controller;

import org.example.phd_book.dto.PostDTO;
import org.example.phd_book.model.Post;
import org.example.phd_book.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDTO createPost(@RequestBody PostDTO postDTO) {
        return postService.createPost(postDTO);
    }
    @GetMapping("/get/{id}")
    public PostDTO getPost(@PathVariable String id) {
        return postService.getPost(id);
    }

    @GetMapping("/getViews/{views}")
    public List<Post> findPostsByViews(@PathVariable int views) {
        return postService.findPostsByViews(views);
    }

    @GetMapping("/get")
    public List<PostDTO> getAll() {
        List<PostDTO> postDTOS = postService.findAll();
        return postDTOS;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable String id) {
        try {
            postService.deletePost(id);
            return ResponseEntity.ok("Post deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<PostDTO> updatePost(
            @PathVariable String id,
            @RequestBody PostDTO updatedPostDTO) {
        PostDTO updatedPost = postService.updatePost(id, updatedPostDTO);
        return ResponseEntity.ok(updatedPost);
    }

}
