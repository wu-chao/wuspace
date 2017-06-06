package com.wuspace.domain;

import com.wuspace.domain.security.User;
import com.wuspace.domain.shared.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Comment extends BaseEntity {

	private User user;

	private User atUser;

	private List<Reply> replies;
	
	private Set<User> zanUsers = new HashSet<User>();
	
	private Set<User> caiUsers = new HashSet<User>();

}