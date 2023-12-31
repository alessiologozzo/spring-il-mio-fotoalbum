package com.org.springilmiofotoalbum.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.springilmiofotoalbum.auth.pojos.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    public Role findByName(String name);
}
