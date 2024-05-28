package org.example.javalt.Services;

import org.example.javalt.Entities.Role;
import org.example.javalt.Entities.User;
import org.example.javalt.Repositories.RoleRepository;
import org.example.javalt.RequestEntities.RequestCreateRole;
import org.example.javalt.RequestEntities.RequestDeleteRole;
import org.example.javalt.RequestEntities.RequestDeleteUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    public Role getRoleById(String id) {
        return roleRepository.findById(id).orElse(null);
    }
    public Role createRole(RequestCreateRole requestCreateRole) {
        try {
            Role role = new Role();
            role.setRole_name(requestCreateRole.getRole_name());
            roleRepository.save(role);
            return role;
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public Role updateRole(String id, RequestCreateRole requestCreateRole) {
        try {
            Role role = getRoleById(id);
            role.setRole_name(requestCreateRole.getRole_name());
            roleRepository.save(role);
            return role;
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public void deleteRoleById(RequestDeleteRole requestDeleteRole) {
        Role role = roleRepository.findById(requestDeleteRole.getRole_id()).orElseThrow(() -> new RuntimeException("Role not found"));
        role.setIsDeleted(true);
        roleRepository.save(role);
    }
    public List<Role> findAllActiveRoles() {
        return roleRepository.findActiveRole();
    }

}
