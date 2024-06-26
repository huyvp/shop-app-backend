package com.app.shop.repo;

import com.app.shop.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepo extends JpaRepository<Permission, String> {
}
