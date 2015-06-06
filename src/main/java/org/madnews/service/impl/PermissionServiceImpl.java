package org.madnews.service.impl;

import org.madnews.entity.Permission;
import org.madnews.repository.PermissionRepository;
import org.madnews.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public void createPermission(Permission permission) {
        permissionRepository.save(permission);
    }

    @Override
    public Permission readPermission(Long id) {
        return permissionRepository.findOne(id);
    }

    @Override
    public void updatePermission(Permission permission) {
        Permission permissionFromDB = permissionRepository.findOne(permission.getId());
        permissionFromDB.setName(permission.getName());
        permissionFromDB.setUsers(permission.getUsers());
        permissionRepository.save(permissionFromDB);
    }

    @Override
    public void deletePermission(Permission permission) {
        permissionRepository.delete(permission);
    }

    @Override
    public Iterable<Permission> getPermissions() {
        return permissionRepository.findAll();
    }
}
