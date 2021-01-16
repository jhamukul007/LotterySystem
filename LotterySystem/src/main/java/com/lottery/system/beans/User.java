package com.lottery.system.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "user")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public @Data class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6168955536692343052L;

	@Id
	@GenericGenerator(name="uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	@Column(updatable = false,length = 36)
	@EqualsAndHashCode.Include
	private String id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name="phone",nullable = false)
	@EqualsAndHashCode.Include
	private String phone;
	
	@Column(name="email", nullable = false)
	private String email;
	
	@OneToOne
	@JoinColumn(name="wallet_id", nullable = false)
	private Wallet Wallet;
}
