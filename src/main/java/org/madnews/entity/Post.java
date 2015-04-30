package org.madnews.entity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by andrey.bereza on 29.04.2015.
 */

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy="increment")
    private int id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String big_img;

    @Column
    private String small_img;

    @Column
    private int rating;

    @Column
    private int timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getTimestamp() {
        return (int) (System.currentTimeMillis() / 1000L);
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getBig_img() {
        return big_img;
    }

    public void setBig_img(String big_img) {
        this.big_img = big_img;
    }

    public String getSmall_img() {
        return small_img;
    }

    public void setSmall_img(String small_img) {
        this.small_img = small_img;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
