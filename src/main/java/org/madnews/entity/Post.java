package org.madnews.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import org.madnews.utils.View;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.JoinTable;
import javax.persistence.Version;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Entity Аннотация говорит о том, что данный класс должен быть отображен в базу данных.
 */
@Entity
@Table(name = "POSTS")
public class Post {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    @JsonView(View.ShortPost.class)
    private String title;

    @Column
    @JsonView(View.ShortPost.class)
    private String shortText;

    @Column
    @JsonView(View.FullPost.class)
    private String html;

    @Column
    @JsonView(View.ShortPost.class)
    private String mainImg;

    @Column
    @JsonView(View.ShortPost.class)
    private int position;

    @Column
    @JsonIgnore
    private boolean isTopNews;

    @Column
    @JsonView(View.ShortPost.class)
    @JsonIgnore
    private boolean isFeatured;

    @Column
    @JsonView(View.ShortPost.class)
    @JsonIgnore
    private boolean IsShowOnMain;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "userid", nullable = false)
//    @JsonManagedReference("user-posts")
    @JsonView(View.FullPost.class)
    private User user;

    @Column
    @Version
    @JsonView(View.ShortPost.class)
    private Timestamp timestamp;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "POSTS_TAGS",
            joinColumns={@JoinColumn(name="POSTID")},
            inverseJoinColumns={@JoinColumn(name="TAGID")})
//    @JsonManagedReference("posts-tags")
    @JsonView(View.FullPost.class)
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

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isTopNews() {
        return isTopNews;
    }

    public void setIsTopNews(boolean isTopNews) {
        this.isTopNews = isTopNews;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public boolean isShowOnMain() {
        return IsShowOnMain;
    }

    public void setIsShowOnMain(boolean isShowOnMain) {
        IsShowOnMain = isShowOnMain;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}

