package com.lottery.system.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public @Data class Wallet implements Serializable{
	
	@Id
	@GenericGenerator(name = "uuid",strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	@Column(updatable = false, length = 36)
	@EqualsAndHashCode.Include
	private String id;
	
	@Column(name = "wallet_name", nullable = true)
	private String name;
	
	@Column(name = "amount")
	private Long amount=0l;
	
	@JoinColumn(name="user_id", nullable = false)
	@OneToOne
	private User user;
}
