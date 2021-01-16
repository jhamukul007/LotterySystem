package com.lottery.system.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public @Data class LobbyInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3739826101611012775L;

	@Id
	@GenericGenerator(name = "uuid",strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	@Column(nullable = false,length = 40)
	@EqualsAndHashCode.Include
	private String id;
	
	//Currenly MaxSize=100;
	@Column(name = "max_size",nullable = false)
	private Integer maxSize;
	
	@Column(name="total_person",nullable = false)
	private Integer totalPerson;
	
	@Column(nullable = false)
	private Integer price;
	
	@Column(name="start_time",nullable = true)
	private Date startTime;
	
	@Column(name="end_time",nullable = true)
	private Date endTime;
	
	@OneToOne
	@JoinColumn(name ="lobby_id", nullable = true)
	private LotteryLobby lobby;
	
	@OneToOne
	@JoinColumn(name="user_id",nullable = false)
	private User winner;	
}
