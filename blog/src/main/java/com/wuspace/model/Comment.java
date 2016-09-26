package com.wuspace.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comment")
@Setter
@Getter
public class Comment implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "blog_id", nullable = false)
	private Blog blog;

	@Column(name = "content", length = 65535, nullable = false)
	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", columnDefinition = "TIMESTAMP")
	private Date createTime;

	@Column(name = "is_delete", nullable = false)
	private Integer isDelete = 0;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "comment")
	@OrderBy("createTime asc")
	private List<Reply> replies;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "comment_zan", joinColumns = @JoinColumn(name = "comment_id"), inverseJoinColumns = @JoinColumn(name = "zan_user_id"))
	private Set<User> zanUsers = new HashSet<User>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "comment_cai", joinColumns = @JoinColumn(name = "comment_id"), inverseJoinColumns = @JoinColumn(name = "cai_user_id"))
	private Set<User> caiUsers = new HashSet<User>();

	/** default constructor */
	public Comment() {
	}

	/** minimal constructor */
	public Comment(User user, Blog blog, String content) {
		this.user = user;
		this.blog = blog;
		this.content = content;
	}

	public Comment(User user, Blog blog, String content, Date createTime) {
		this.user = user;
		this.blog = blog;
		this.content = content;
		this.createTime = createTime;
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