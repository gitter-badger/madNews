package org.madnews.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "POSTS")
public class Post implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String smallImg;

    @Column
    private String bigImg;

    @Column
    private int rating;

    @Column
    private boolean isTopNews;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @Column
    private int timestamp;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="posts_tags",
            joinColumns = @JoinColumn(name="postid", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="tagid", referencedColumnName="id")
    )
    private Set<Tag> tags;

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

    public boolean isTopNews() {
        return isTopNews;
    }

    public void setIsTopNews(boolean isTopNews) {
        this.isTopNews = isTopNews;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }


}
