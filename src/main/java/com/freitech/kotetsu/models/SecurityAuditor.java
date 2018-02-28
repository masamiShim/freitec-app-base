package com.freitech.kotetsu.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@EntityListeners(value = AuditingEntityListener.class)
@MappedSuperclass
public abstract class SecurityAuditor implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Version
	protected long version;

	@CreatedBy
	@Column(name = "CreatedBy")
	protected String createdBy;

	@CreatedDate
	@Column(name = "Created")
	protected LocalDateTime created;

	@LastModifiedBy
	@Column(name = "ModifiedBy")
	protected String modifiedBy;

	@LastModifiedDate
	@Column(name = "Modified")
	protected LocalDateTime modified;

	@Column(name = "DeletedBy")
	protected String deletedBy;

	@Column(name = "Deleted")
	protected LocalDateTime deleted;

}
