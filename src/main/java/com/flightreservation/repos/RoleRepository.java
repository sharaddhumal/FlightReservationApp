package com.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightreservation.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
