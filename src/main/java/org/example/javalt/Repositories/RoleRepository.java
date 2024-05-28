package org.example.javalt.Repositories;

import org.example.javalt.Entities.Role;
import org.example.javalt.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    @Query("SELECT r FROM Role r WHERE r.isDeleted = false")
    List<Role> findActiveRole();
}
