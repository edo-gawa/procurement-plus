package com.procurementplus.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.procurementplus.demo.auditrail.Auditable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
@Table(name = "m_user")
public class UserEntity extends Auditable<String>{
	
	@Column(name = "user_name")
	private String username;

	@Id
	@Column(name = "social_security_number")
	private Integer socialSecurityNumber;

	@Column(name = "date_od_birth")
	private Date dateOfBirth;

	@Column(name = "is_deleted")
	private boolean deleted;

}
