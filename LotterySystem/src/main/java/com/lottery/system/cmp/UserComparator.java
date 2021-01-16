package com.lottery.system.cmp;

import java.util.Comparator;

import com.lottery.system.beans.User;

import lombok.ToString;

@ToString
public class UserComparator implements Comparator<User> {

	@Override
	public int compare(User user1, User user2) {
		return user1.getName().compareTo(user2.getName());
	}

}
