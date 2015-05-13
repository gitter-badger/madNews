package org.madnews.service.impl;

import org.madnews.dao.TagDAO;
import org.madnews.entity.Post;
import org.madnews.entity.Tag;
import org.madnews.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDAO tagDAO;

    @Override
    public void createTag(Tag tag) {
        tagDAO.create(tag);
    }

    @Override
    public Tag readTag(Long id) {
        return tagDAO.read(id);
    }

    @Override
    public void updateTag(Tag tag) {
        tagDAO.update(tag);
    }

    @Override
    public void deleteTag(Tag tag) {
        tagDAO.delete(tag);
    }

    @Override
    public Set<Post> getPostsByTagId(Long id) {
        return tagDAO.getPostsByTagId(id);
    }

    @Override
    public List getTags() {
        return tagDAO.getTags();
    }
}
