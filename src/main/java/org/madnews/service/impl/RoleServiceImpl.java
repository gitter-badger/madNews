package org.madnews.service.impl;

import org.madnews.entity.Role;
import org.madnews.repository.RoleRepository;
import org.madnews.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void createRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role readRole(Long id) {
        return roleRepository.findOne(id);
    }

    @Override
    public void updateRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public Iterable<Role> getRoles() {
        return roleRepository.findAll();
    }
}
