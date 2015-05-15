package org.madnews.repository;

import org.madnews.entity.Post;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface PostRepository extends CrudRepository<Post, Long> {
    Post findByIsTopNewsTrueAndTimestampGreaterThan(LocalDateTime timestamp);
}
