package org.example.phd_book.service;

import org.example.phd_book.dto.PostDTO;
import org.example.phd_book.model.Post;
import org.example.phd_book.repo.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepo;
    private final ModelMapper modelMapper;
    public PostServiceImpl(PostRepository postRepo, ModelMapper modelMapper) {
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Post> findPostByAuthor(String author) {
        return postRepo.findPostByAuthor(author);
    }

    @Override
    public List<Post> findPostsByViews(int views) {
        return postRepo.findPostsByViews(views);
    }

    @Override
    public void deletePost(String id) {
        if (postRepo.existsById(id)) { // Проверяем, существует ли пост с данным id
            postRepo.deleteById(id);
        } else {
            throw new RuntimeException("Post with id " + id + " not found.");
        }
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = modelMapper.map(postDTO, Post.class);
        Post savedPost = postRepo.save(post);
        return modelMapper.map(savedPost, PostDTO.class);
    }

    @Override
    public PostDTO getPost(String id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public List<PostDTO> findAll() {
        List<Post> post = postRepo.findAll();
        List<PostDTO> bookingDTOS = post.stream()
                .map(booking -> modelMapper.map(booking, PostDTO.class))
                .toList();
        return bookingDTOS;
    }

    @Override
    public PostDTO updatePost(String id, PostDTO postDTO) {
        Post existingPost = postRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post with ID " + id + " not found"));

        // Обновляем данные сущности
        existingPost.setAuthor(postDTO.getAuthor());
        existingPost.setTitle(postDTO.getTitle());
        existingPost.setTags(postDTO.getTags());
        existingPost.setDescription(postDTO.getDescription());
        existingPost.setViews(postDTO.getViews());
        existingPost.setComments(postDTO.getComments());

        // Сохраняем изменения
        Post savedPost = postRepo.save(existingPost);

        // Конвертируем сохраненную сущность обратно в DTO
        return modelMapper.map(savedPost, PostDTO.class);
    }
}
