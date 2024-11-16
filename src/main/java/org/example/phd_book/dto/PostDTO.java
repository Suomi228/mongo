package org.example.phd_book.dto;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;

public class PostDTO {
    private String id;
    private String author;
    private String title;
    private List<String> tags;
    private String description;
    private int views;
    private List<Map<String,String>> comments;

    public PostDTO() {
    }

    public PostDTO(String author, String title, List<String> tags, String description, int views, List<Map<String,String>> comments) {
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

    public List<Map<String,String>>getComments() {
        return comments;
    }

    public void setComments(List<Map<String,String>> comments) {
        this.comments = comments;
    }
}
