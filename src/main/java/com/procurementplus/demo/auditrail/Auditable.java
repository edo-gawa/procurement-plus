package com.procurementplus.demo.auditrail;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {
	@Column(name = "created_by", nullable = false, updatable = false)	
	@CreatedBy
	protected U createdBy;
	@Column(name = "created_date", nullable = false, updatable = false)	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createDate;
	@LastModifiedBy
	protected U updatedBy;
	
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date updateDate;
}
