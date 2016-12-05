package com.wuspace.domain;

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
@Table(name = "comments")
public class Comment extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "blog_id", nullable = false)
	private Blog blog;

	@Column(name = "content", length = 512, nullable = false)
	private String content;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "comment")
	@OrderBy("createTime asc")
	private List<Reply> replies;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "comment_zan", joinColumns = @JoinColumn(name = "comment_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> zanUsers = new HashSet<User>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "comment_cai", joinColumns = @JoinColumn(name = "comment_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> caiUsers = new HashSet<User>();

	protected Comment() {
	}

	public Comment(User user, Blog blog, String content, Timestamp timestamp) {
		super();
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