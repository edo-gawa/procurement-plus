package com.procurementplus.demo.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.procurementplus.demo.dto.UserDto;
import com.procurementplus.demo.entity.UserEntity;
import com.procurementplus.demo.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void addUser(UserDto body) throws Exception {
		if (Optional.ofNullable(userRepository.findBySocialSecurityNumber(body.getSocialNumbErSecurity()))
				.isPresent()) {
			throw new SQLException("SSN Already Exist");
		}

		userRepository.save(UserEntity.builder().username(body.getUserName()).dateOfBirth(body.getDateOfBirth())
				.socialSecurityNumber(body.getSocialNumbErSecurity()).build());
	}

	@Transactional
	public void updateUser(UserDto body) throws Exception {
		UserEntity fromDb = userRepository.findBySocialSecurityNumber(body.getSocialNumbErSecurity());
		if (Optional.ofNullable(fromDb).isEmpty() || fromDb.isDeleted()) {
			throw new SQLException("SSN Not Found");
		}

		fromDb.setUsername(body.getUserName());
		fromDb.setDateOfBirth(body.getDateOfBirth());
	}

	@Transactional
	public void softDelete(Integer ssn) throws Exception {
		UserEntity fromDb = userRepository.findBySocialSecurityNumber(ssn);
		if (Optional.ofNullable(fromDb).isEmpty() || fromDb.isDeleted()) {
			throw new SQLException("SSN Not Found");
		}
		fromDb.setDeleted(true);

	}

	public List<UserDto> findAll() throws Exception {
		List<UserEntity> users = userRepository.findByDeleted(false);
		if (Optional.ofNullable(users).isEmpty()) {
			throw new SQLException("NOT_FOUND");
		}
		List<UserDto> usersDto = new ArrayList<UserDto>();
		users.forEach(user -> {
			UserDto userDto = new UserDto();
			userDto.setUserName(user.getUsername());
			userDto.setSocialNumberSecurity(user.getSocialSecurityNumber().toString());
			userDto.setDateOfBirthFromDate(user.getDateOfBirth());
			userDto.setDeleted(user.isDeleted());
			userDto.setCreateDate(user.getCreateDate());
			userDto.setCreatedBy(user.getCreatedBy());
			userDto.setUpdateDate(user.getUpdateDate());
			userDto.setUpdatedBy(user.getUpdatedBy());
			usersDto.add(userDto);
		});

		return usersDto;

	}

	public UserDto findBySSN(Integer ssn) throws Exception {
		UserEntity user = userRepository.findBySocialSecurityNumber(ssn);
		if (Optional.ofNullable(user).isEmpty() || user.isDeleted()) {
			throw new SQLException("NOT_FOUND");
		}

		UserDto userDto = new UserDto();
		userDto.setUserName(user.getUsername());
		userDto.setSocialNumberSecurity(user.getSocialSecurityNumber().toString());
		userDto.setDateOfBirthFromDate(user.getDateOfBirth());
		userDto.setDeleted(user.isDeleted());
		userDto.setCreateDate(user.getCreateDate());
		userDto.setCreatedBy(user.getCreatedBy());
		userDto.setUpdateDate(user.getUpdateDate());
		userDto.setUpdatedBy(user.getUpdatedBy());

		return userDto;

	}
}
