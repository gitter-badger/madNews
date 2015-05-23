package org.madnews.service;

import org.madnews.entity.Tag;

public interface TagService {
    void createTag(Tag tag);
    Tag readTag(Long id);
    void updateTag(Tag tag);
    void deleteTag(Tag tag);
    Iterable<Tag> getTags();
}
