package org.madnews.dao;

import org.madnews.entity.Post;
import org.madnews.entity.Tag;

import java.util.List;
import java.util.Set;

public interface TagDAO extends GenericDAO<Tag, Long> {
    Set<Post> getPostsByTagId(Long id);
    List getTags();
}
