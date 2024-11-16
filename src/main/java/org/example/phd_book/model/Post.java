package org.example.phd_book.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Document(collection = "blog")
public class Post {
    @Id
    private String id;
    @Field(name="author")
    @Indexed(unique = false, sparse = true, direction = IndexDirection.ASCENDING, background = false)
    private String author;
    @Field(name="title")
    @Indexed(unique = false, sparse = true, direction = IndexDirection.ASCENDING, background = false)
    private String title;
    @Field(name="tags")
    @Indexed(unique = false, sparse = true, direction = IndexDirection.ASCENDING, background = false)
    private List<String> tags;
    @Field(name="description")
    @Indexed(unique = false, sparse = true, direction = IndexDirection.ASCENDING, background = false)
    private String description;
    @Field(name="views")
    @Indexed(unique = false, sparse = true, direction = IndexDirection.ASCENDING, background = false)
    private int views;
    @Field(name="comments")
    @Indexed(unique = false, sparse = true, direction = IndexDirection.ASCENDING, background = false)
    private List<Map<String,String>> comments;

    public Post() {
    }

    public Post(String id, String author, String title, List<String> tags, String description, int views, List<Map<String, String>> comments) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.tags = tags;
        this.description = description;
        this.views = views;
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public List<Map<String,String>> getComments() {
        return comments;
    }

    public void setComments(List<Map<String,String>> comments) {
        this.comments = comments;
    }
}
