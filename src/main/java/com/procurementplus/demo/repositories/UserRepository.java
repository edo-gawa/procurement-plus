package com.procurementplus.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.procurementplus.demo.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	UserEntity findBySocialSecurityNumber(Integer ssn);
	List<UserEntity> findByDeleted(Boolean isDeleted);
}
