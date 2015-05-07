package org.madnews.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "POSTS")
public class Post implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "SMALLIMG")
    private String smallImg;

    @Column(name = "BIGIMG")
    private String bigImg;

    @Column(name = "RATING")
    private int rating;

    @Column(name = "TIMESTAMP")
    private int timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSmallImg() {
        return smallImg;
    }

    public void setSmallImg(String smallImg) {
        this.smallImg = smallImg;
    }

    public String getBigImg() {
        return bigImg;
    }

    public void setBigImg(String bigImg) {
        this.bigImg = bigImg;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
