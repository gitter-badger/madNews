package org.madnews.service;

import org.madnews.entity.Permission;

public interface PermissionService {
    void createPermission(Permission permission);
    Permission readPermission(Long id);
    void updatePermission(Permission permission);
    void deletePermission(Permission permission);
    Iterable<?> getPermissions();
    Permission readPermissionByName(String name);
}
