package org.madnews.repository;

import org.madnews.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.sql.Timestamp;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
    Post findByIsTopNewsTrueAndTimestampGreaterThan(Timestamp lastChanged);
    Page<Post> findAllByTagsIdOrderByTimestampDesc(Long tagId, Pageable pageable);
}
