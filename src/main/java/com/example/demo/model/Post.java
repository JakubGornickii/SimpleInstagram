package com.example.demo.model;
import org.hibernate.validator.constraints.URL;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private int id;
    @URL(message = "*Proszę wpisać poprawny link do zdjęcia")
    private String url;
    @NotEmpty(message = "*Proszę wpisać nazwę")
    private String name;
    @NotEmpty(message = "*Proszę wpisać opis")
    private String description;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "post_hashtags", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "hash_id"))
    private List<Hashtag> hashtags;

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }
}
