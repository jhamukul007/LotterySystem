package com.lottery.system.beans;

import java.io.Serializable;
import java.util.SortedSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import com.lottery.system.cmp.UserComparator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "lobby")
public @Data class LotteryLobby implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1989445996448718877L;

	@Id
	@GenericGenerator(name = "uuid",strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	@Column(nullable = false,length = 36)
	@EqualsAndHashCode.Include
	private String id;
	
	@Column
	private String name;
	
	@OneToMany(cascade =CascadeType.REFRESH, mappedBy = "lobby")
	@Sort(type = SortType.COMPARATOR,comparator = UserComparator.class)
	@Cache(usage = 	CacheConcurrencyStrategy.READ_ONLY)
	private SortedSet<User> users;
	
	@OneToOne
	@JoinColumn(name="lobby_info_id",nullable = false)
	private LobbyInfo info;
	
	@Column(columnDefinition = "BIT")
	private boolean active;
	
}
