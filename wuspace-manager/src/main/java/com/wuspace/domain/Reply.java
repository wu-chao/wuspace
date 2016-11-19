package com.wuspace.domain;

import com.wuspace.domain.shared.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "reply")
public class Reply extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "comment_id", nullable = false)
	private Comment comment;
	
	@ManyToOne
	@JoinColumn(name = "reply_to_id", nullable = false)
	private User replyTo;
	
	@Column(name = "content", nullable = false, length = 255)
	private String content;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "reply_zan", joinColumns = @JoinColumn(name = "reply_id"), inverseJoinColumns = @JoinColumn(name = "zan_user_id"))
	private Set<User> zanUsers = new HashSet<User>(0);
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "reply_cai", joinColumns = @JoinColumn(name = "reply_id"), inverseJoinColumns = @JoinColumn(name = "cai_user_id"))
	private Set<User> caiUsers = new HashSet<User>(0);

	protected Reply() {
		super();
		// no-args constructor required by JPA spec
		// this one is protected since it shouldn't be used directly
	}

	public boolean existsZanUser(User user) {
		if (this.zanUsers.contains(user)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void addZanUser(User user) {
		this.zanUsers.add(user);
	}

	public boolean existsCaiUser(User user) {
		if (this.caiUsers.contains(user)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void addCaiUser(User user) {
		this.caiUsers.add(user);
	}
}