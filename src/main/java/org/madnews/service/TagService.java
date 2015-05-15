package org.madnews.service;

import org.madnews.entity.Post;
import org.madnews.entity.Tag;

import java.util.Set;

public interface TagService {
    void createTag(Tag tag);
    Tag readTag(Long id);
    void updateTag(Tag tag);
    void deleteTag(Tag tag);
    Set<Post> getPostsByTagId(Long id);
    Iterable<Tag> getTags();
}
