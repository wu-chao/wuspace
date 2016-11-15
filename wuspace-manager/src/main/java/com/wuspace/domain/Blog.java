package com.wuspace.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "blog")
@Getter
@Setter
public class Blog implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "title", nullable = false, length = 500)
	private String title;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "content", nullable = false)
	private String content;
	
	@Column(name = "image", length = 200, nullable = true)
	private String image;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", columnDefinition = "TIMESTAMP")
	private Date createTime;

	@Column(name = "is_delete", nullable = false)
	private Integer isDelete = 0;

	@Column(name = "viewed_times", nullable = false)
	private Integer viewedTimes = 0;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "blog")
	@OrderBy("createTime desc")
	private Set<Comment> comments;

	@ManyToMany
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "blog_zan", joinColumns = @JoinColumn(name = "blog_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> zanUsers = new HashSet<User>(0);

	@ManyToMany(cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "blog_collect", joinColumns = @JoinColumn(name = "blog_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> collectUsers = new HashSet<User>(0);

	public Blog() {}

	public Blog(User user, String title, String content,
			Timestamp createTime) {
		this.user = user;
		this.title = title;
		this.content = content;
		this.createTime = createTime;
	}
	
	public Blog(User user, String title, String content,
			String image, Timestamp createTime) {
		this.user = user;
		this.title = title;
		this.content = content;
		this.image = image;
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

	public boolean existsCollectUser(User user) {
		if (this.collectUsers.contains(user)) {
			return true;
		} else {
			return false;
		}
	}

	public void addCollectUser(User user) {
		this.collectUsers.add(user);
	}

	public void removeCollectUser(User user) {
		this.collectUsers.remove(user);
	}

	public void visited() {
		this.viewedTimes++;
	}

	public void updateBlog(String title, String content) {
		this.title = title;
		this.content = content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Blog other = (Blog) obj;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}