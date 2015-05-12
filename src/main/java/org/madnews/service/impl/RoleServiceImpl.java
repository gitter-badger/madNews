package org.madnews.service.impl;

import org.madnews.dao.RoleDAO;
import org.madnews.entity.Role;
import org.madnews.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public void createRole(Role role) {
        roleDAO.create(role);
    }

    @Override
    public Role readRole(Long id) {
        return roleDAO.read(id);
    }

    @Override
    public void updateRole(Role role) {
        roleDAO.update(role);
    }

    @Override
    public void deleteRole(Role role) {
        roleDAO.delete(role);
    }
}
