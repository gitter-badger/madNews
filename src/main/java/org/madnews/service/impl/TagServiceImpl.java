package org.madnews.service.impl;

import org.madnews.entity.Tag;
import org.madnews.repository.TagRepository;
import org.madnews.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Tag tagFromDB = tagRepository.findOne(tag.getId());
        tagFromDB.setName(tag.getName());
        tagFromDB.setPosts(tag.getPosts());
        tagRepository.save(tagFromDB);
    }

    @Override
    public void deleteTag(Tag tag) {
        tagRepository.delete(tag);
    }

    @Override
    public Iterable<Tag> getTags() {
        return tagRepository.findAll();
    }
}
