package com.app.propertyValuatorBE.db.repository;

import com.app.propertyValuatorBE.db.entities.Role;
import com.app.propertyValuatorBE.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(UserRole userRole);
}
