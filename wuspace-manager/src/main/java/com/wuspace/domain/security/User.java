package com.wuspace.domain.security;

import com.wuspace.domain.Blog;
import com.wuspace.domain.Comment;
import com.wuspace.domain.Reply;
import com.wuspace.domain.shared.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

	@Column(name = "username", unique = true, nullable = false)
	private String username;

	@Column(name = "email", length = 50)
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "password")
	private String password;

	@Column(name = "avatar", length = 200)
	private String avatar;

	@Column(name = "nickname", length = 20)
	private String nickname;

	@Column(name = "description", length = 255)
	private String description;

	@Column(name = "enabled")
	private boolean enabled;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Authority> authorities = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	@OrderBy("createTime desc")
	private Set<Blog> blogs;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	@OrderBy("createTime desc")
	private Set<Comment> comments;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "replyTo")
	private Set<Reply> repliesOfReplyTo;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Reply> repliesOfUser;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "collectUsers")
	private Set<Blog> collectBlogs = new HashSet<Blog>();

	@ManyToMany
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "user_care", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "cared_user_id"))
	private Set<User> careUsers = new HashSet<User>();

	public User() {}

	public boolean validatePassword(String password) {
		if (this.password.equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	public void updatePassword(String password) {
		this.password = password;
	}

	public boolean existsCareUser(User user) {
		if (this.careUsers.contains(user)) {
			return true;
		} else {
			return false;
		}
	}

	public void addCareUser(User user) {
		this.careUsers.add(user);
	}

	public void removeCareUser(User user) {
		this.careUsers.remove(user);
	}

	public void updateInfo(String nickname, String email, String phone, String description) {
		this.nickname = nickname;
		this.email = email;
		this.phone = phone;
		this.description = description;
	}
}