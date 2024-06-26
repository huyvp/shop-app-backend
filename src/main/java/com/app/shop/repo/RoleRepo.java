package com.app.shop.repo;

import com.app.shop.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, String> { }
