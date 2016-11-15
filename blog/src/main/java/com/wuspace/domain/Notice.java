package com.wuspace.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "notice")
@Setter
@Getter
public class Notice implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "publish_user_id", nullable = false)
	private User publishBy;
	
	@ManyToOne
	@JoinColumn(name = "delete_user_id")
	private User deleteBy;
	
	@Column(name = "title", nullable = false, length = 500)
	private String title;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "content", nullable = false)
	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", columnDefinition = "TIMESTAMP")
	private Date createTime;
	
	//不能与上面的代码同时存在？？？？？？？？？？？？？？？？？？？
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "create_time", columnDefinition = "TIMESTAMP")
//	private Date deleteTime;

	@Column(name = "is_delete", nullable = false)
	private Integer isDelete = 0;
	
	public Notice() {};
	
	public Notice(User admin, String title, String content, Timestamp timestamp) {
		this.publishBy = admin;
		this.title = title;
		this.content = content;
		this.createTime = timestamp;
	}

}
