package org.madnews.service.impl;

import org.madnews.dao.TagDAO;
import org.madnews.entity.Tag;
import org.madnews.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;

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
}
