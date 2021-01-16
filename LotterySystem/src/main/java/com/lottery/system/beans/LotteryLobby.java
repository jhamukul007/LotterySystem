package com.lottery.system.beans;

import java.io.Serializable;
import java.util.SortedSet;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public @Data class LotteryLobby implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1989445996448718877L;

	@Id
	@GenericGenerator(name = "uuid",strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	@Column(nullable = false,length = 40)
	@EqualsAndHashCode.Include
	private String id;
	
	@Column
	private String name;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable = false)
	private SortedSet<User> users;
	
	@OneToOne
	@JoinColumn(name="lobbyinfo_id",nullable = false)
	private LobbyInfo info;
	
	@Column(columnDefinition = "BIT")
	private boolean active;
	
}
