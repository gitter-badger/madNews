package org.madnews.service.impl;

import org.madnews.entity.Post;
import org.madnews.entity.Tag;
import org.madnews.repository.TagRepository;
import org.madnews.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public void createTag(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public Tag readTag(Long id) {
        return tagRepository.findOne(id);
    }

    @Override
    public void updateTag(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public void deleteTag(Tag tag) {
        tagRepository.delete(tag);
    }

    @Override
    public Set<Post> getPostsByTagId(Long id) {
        return tagRepository.findOne(id).getPosts();
    }

    @Override
    public Iterable<Tag> getTags() {
        return tagRepository.findAll();
    }
}
