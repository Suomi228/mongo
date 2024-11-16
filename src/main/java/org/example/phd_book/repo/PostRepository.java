package org.example.phd_book.repo;

import org.example.phd_book.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findPostByAuthor(String author);
    List<Post> findPostsByViews(int views);
    List<Post> findAll();
    Optional<Post> findById(String id);
}
