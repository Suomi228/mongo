package org.example.phd_book.controller;

import org.example.phd_book.dto.PostDTO;
import org.example.phd_book.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class PostController {

    private PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public Page<PostDTO> getPostsPage(
            @RequestParam(defaultValue = "0") Integer offset,
            @RequestParam(defaultValue = "10") Integer limit
    ) {
        Page<PostDTO> postsPage = postService.getPosts(PageRequest.of(offset, limit));
        return postsPage;
    }
    @PostMapping("/posts")
    public PostDTO createPost(@RequestBody PostDTO postDTO) {
        return postService.createPost(postDTO);
    }
    @GetMapping("/posts/{id}")
    public PostDTO getPost(@PathVariable String id) {
        return postService.getPost(id);
    }
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable String id) {
        try {
            postService.deletePost(id);
            return ResponseEntity.ok("Post deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/posts/{id}")
    public PostDTO updatePost(@RequestBody PostDTO updatedPostDTO, @PathVariable String id) {
        updatedPostDTO.setId(id);
        return postService.updatePost(id, updatedPostDTO);
    }
}
