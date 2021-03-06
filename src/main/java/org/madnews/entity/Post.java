package org.madnews.entity;

import com.fasterxml.jackson.annotation.JsonView;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import org.madnews.utils.View;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.JoinTable;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Entity
@Indexed
@AnalyzerDef(name = "customanalyzer",
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
  @TokenFilterDef(factory = LowerCaseFilterFactory.class),
  @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
    @Parameter(name = "language", value = "English")
  })
})
@Table(name = "POSTS")
public class Post {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonView(View.SimplePost.class)
    private Long id;

	@Field
	@Analyzer(definition = "customanalyzer")
    @Column
    @JsonView(View.SimplePost.class)
    private String title;

	@Field
	@Analyzer(definition = "customanalyzer")
    @Column
    @JsonView(View.SimplePost.class)
    private String shortText;

	@Field
	@Analyzer(definition = "customanalyzer")
    @Column
    @JsonView(View.SimplePost.class)
    private String html;

    @Column
    @JsonView(View.SimplePost.class)
    private String mainImg;

    @Column
    @JsonView(View.SimplePost.class)
    private int position;

    @Column
    @JsonView(View.EditablePost.class)
    private boolean isTopNews;

    @Column
    @JsonView(View.SimplePost.class)
    private boolean isFeatured;

    @Column
    @JsonView(View.SimplePost.class)
    private boolean isShowOnMain;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "userid", nullable = false)
    @JsonView(View.EditablePost.class)
    private User user;

    @Column
    @JsonView(View.SimplePost.class)
    private Timestamp timestamp;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "POSTS_TAGS",
            joinColumns={@JoinColumn(name="POSTID")},
            inverseJoinColumns={@JoinColumn(name="TAGID")})
    @JsonView(View.FullPost.class)
    private Set<Tag> tags;

    @PrePersist
    void preInsert() {
    	if (position == 0) position = -1;
        timestamp = new Timestamp(new Date().getTime());
    }

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

    public boolean getIsTopNews() {
        return isTopNews;
    }

    public void setIsTopNews(boolean isTopNews) {
        this.isTopNews = isTopNews;
    }

    public boolean getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public boolean getIsShowOnMain() {
        return isShowOnMain;
    }

    public void setIsShowOnMain(boolean isShowOnMain) {
        this.isShowOnMain = isShowOnMain;
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

