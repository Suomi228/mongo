package org.example.phd_book.dto;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PostDTO {
    private String id;
    private String author;
    private String title;
    private String flatTags;
    private String description;
    private int views;
    private String flatComments;

    public PostDTO() {
    }

    public PostDTO(String author, String title, String flatTags, String description, int views, String flatComments) {
        this.author = author;
        this.title = title;
        this.flatTags = flatTags;
        this.description = description;
        this.views = views;
        this.flatComments = flatComments;
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

    public String getFlatTags() {
        return flatTags;
    }

    public void setFlatTags(String flatTags) {
        this.flatTags = flatTags;
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

    public String getFlatComments() {
        return flatComments;
    }

    public void setFlatComments(String flatComments) {
        this.flatComments = flatComments;
    }

}
