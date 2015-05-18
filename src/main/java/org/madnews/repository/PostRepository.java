package org.madnews.repository;

import org.madnews.entity.Post;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;

public interface PostRepository extends CrudRepository<Post, Long> {
    Post findByIsTopNewsTrueAndLastChangedGreaterThan(Timestamp lastChanged);
}
