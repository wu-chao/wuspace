package com.wuspace.domain;

import com.wuspace.domain.shared.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "blogs")
@Getter
@Setter
public class Blog extends BaseEntity {

	private static final long serialVersionUID = 1L;

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

	@Column(name = "viewed_times", nullable = false)
	private Integer viewedTimes = 0;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "blog")
	@OrderBy("createTime desc")
	private Set<Comment> comments;

	@ManyToMany
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "blog_zan", joinColumns = @JoinColumn(name = "blog_id")
			, inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> zanUsers = new HashSet<User>(0);

	@ManyToMany(cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "blog_collect", joinColumns = @JoinColumn(name = "blog_id")
			, inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> collectUsers = new HashSet<User>(0);


	public Blog() {}

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
}