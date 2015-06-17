package org.madnews.repository;

import org.madnews.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
    Post findByIsTopNewsTrue();
    Page<Post> findAllByTagsIdOrderByTimestampDesc(Long tagId, Pageable pageable);
    List<Post> findByIsShowOnMainTrueOrderByTimestampDesc();
    Page<Post> findByIsShowOnMainFalseOrderByTimestampDesc(Pageable pageable);
}
