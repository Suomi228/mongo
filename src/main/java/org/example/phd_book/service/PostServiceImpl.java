package org.example.phd_book.service;

import org.example.phd_book.dto.PostDTO;
import org.example.phd_book.model.Post;
import org.example.phd_book.repo.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        // Преобразование тегов из строки в список
        List<String> tags = postDTO.getFlatTags() != null
                ? List.of(postDTO.getFlatTags().split(","))
                : List.of();

        // Преобразование комментариев из строки в список объектов
        List<Map<String, String>> comments = postDTO.getFlatComments() != null
                ? List.of(postDTO.getFlatComments().split(";")).stream()
                .map(flatComment -> {
                    String[] parts = flatComment.split(":", 2);
                    return Map.of(
                            "name", parts[0].trim(),
                            "comment", parts.length > 1 ? parts[1].trim() : ""
                    );
                }).toList()
                : List.of();

        // Создание сущности Post
        Post post = modelMapper.map(postDTO, Post.class);
        post.setTags(tags);
        post.setComments(comments);

        // Сохранение в базе данных
        Post savedPost = postRepo.save(post);

        // Преобразование обратно в DTO
        PostDTO savedPostDTO = modelMapper.map(savedPost, PostDTO.class);
        savedPostDTO.setFlatTags(String.join(", ", savedPost.getTags()));

        if (savedPost.getComments() != null && !savedPost.getComments().isEmpty()) {
            String flatComments = savedPost.getComments().stream()
                    .map(comment -> comment.get("name") + ": " + comment.get("comment"))
                    .collect(Collectors.joining("; "));
            savedPostDTO.setFlatComments(flatComments);
        }

        return savedPostDTO;
    }



    @Override
    public PostDTO getPost(String id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public Page<Post> getPosts(Pageable pageable) {
        Page<Post> post = postRepo.findAll(pageable);
        return post;
    }

    @Override
    public PostDTO updatePost(String id, PostDTO postDTO) {
        Post existingPost = postRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post with ID " + id + " not found"));

        // Обновляем основные данные
        existingPost.setAuthor(postDTO.getAuthor());
        existingPost.setTitle(postDTO.getTitle());
        existingPost.setDescription(postDTO.getDescription());
        existingPost.setViews(postDTO.getViews());

        // Обновляем теги
        List<String> tags = postDTO.getFlatTags() != null
                ? List.of(postDTO.getFlatTags().split(","))
                : List.of();
        existingPost.setTags(tags);

        // Обновляем комментарии
        if (postDTO.getFlatComments() != null && !postDTO.getFlatComments().isBlank()) {
            List<Map<String, String>> comments = List.of(postDTO.getFlatComments().split(";")).stream()
                    .map(flatComment -> {
                        String[] parts = flatComment.split(":", 2);
                        return Map.of(
                                "name", parts[0].trim(),
                                "comment", parts.length > 1 ? parts[1].trim() : ""
                        );
                    }).toList();
            existingPost.setComments(comments);
        } else {
            existingPost.setComments(null);
        }

        // Сохранение изменений
        Post savedPost = postRepo.save(existingPost);

        // Преобразование обратно в DTO
        PostDTO updatedPostDTO = modelMapper.map(savedPost, PostDTO.class);
        updatedPostDTO.setFlatTags(String.join(", ", savedPost.getTags()));

        if (savedPost.getComments() != null && !savedPost.getComments().isEmpty()) {
            String flatComments = savedPost.getComments().stream()
                    .map(comment -> comment.get("name") + ": " + comment.get("comment"))
                    .collect(Collectors.joining("; "));
            updatedPostDTO.setFlatComments(flatComments);
        }

        return updatedPostDTO;
    }


}
