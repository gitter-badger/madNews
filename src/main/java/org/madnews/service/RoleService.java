package org.madnews.service;

import org.madnews.entity.Role;

public interface RoleService {
    void createRole(Role role);
    Role readRole(Long id);
    void updateRole(Role role);
    void deleteRole(Role role);
    Iterable<?> getRoles();
}
