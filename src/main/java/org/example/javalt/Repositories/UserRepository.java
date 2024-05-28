package org.example.javalt.Repositories;

import org.example.javalt.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT u FROM User u WHERE u.isDeleted = false AND u.role.isDeleted = false")
    List<User> findActiveUsersWithActiveRoles();
}
