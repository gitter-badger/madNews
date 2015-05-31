package org.madnews.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;
import java.util.Set;

/**
 * Entity Аннотация говорит о том, что данный класс должен быть отображен в базу данных.
 */
@Entity
@Table(name = "REF_PERMISSIONS")
public class Permission {

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy="permissions")
    @JsonBackReference("users-permissions")
    private Set<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
