package com.wuspace.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "reply")
@Setter
@Getter
public class Reply implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "comment_id", nullable = false)
	private Comment comment;
	
	@ManyToOne
	@JoinColumn(name = "reply_to_id", nullable = false)
	private User replyTo;
	
	@Column(name = "create_time", length = 19)
	private Timestamp createTime;
	
	@Column(name = "is_delete")
	private Integer isDelete = 0;
	
	@Column(name = "content", nullable = false, length = 255)
	private String content;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "reply_zan", joinColumns = @JoinColumn(name = "reply_id"), inverseJoinColumns = @JoinColumn(name = "zan_user_id"))
	private Set<User> zanUsers = new HashSet<User>(0);
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "reply_cai", joinColumns = @JoinColumn(name = "reply_id"), inverseJoinColumns = @JoinColumn(name = "cai_user_id"))
	private Set<User> caiUsers = new HashSet<User>(0);

	/** default constructor */
	public Reply() {
	}

	/** minimal constructor */
	public Reply(User user, User replyTo, Comment comment, String content, Timestamp createTime) {
		this.user = user;
		this.replyTo = replyTo;
		this.comment = comment;
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