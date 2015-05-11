package org.madnews.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table( name = "REF_TAGS")
public class Tag {

    @Id
    @Column
    @GeneratedValue
    private  int id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tags")
    private Set<Post> posts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

}
