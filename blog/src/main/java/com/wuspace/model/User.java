package com.wuspace.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "user")
@Setter
@Getter
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "address", length = 30)
	private String address;

	@Column(name = "age")
	private Integer age;

	@Temporal(TemporalType.DATE)
	@Column(name = "birthday", length = 10)
	private Date birthday;

	@Column(name = "create_time", length = 19)
	private Timestamp createTime;

	@Column(name = "email", length = 50)
	private String email;

	@Column(name = "avatar", length = 200)
	private String avatar;

	@Column(name = "is_admin")
	private Integer isAdmin = 0;

	@Column(name = "is_delete")
	private Integer isDelete = 0;

	@Column(name = "nickname", length = 20)
	private String nickname;

	@Column(name = "password", length = 16)
	private String password;

	@Column(name = "phone")
	private String phone;

	@Column(name = "sex")
	private Integer sex;

	@Column(name = "update_time", length = 19)
	private Timestamp updateTime;

	@Column(name = "username", length = 10)
	private String username;

	@Column(name = "description", length = 255)
	private String description;

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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

}