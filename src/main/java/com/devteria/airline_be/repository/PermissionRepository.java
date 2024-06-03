package com.devteria.airline_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devteria.airline_be.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {}
