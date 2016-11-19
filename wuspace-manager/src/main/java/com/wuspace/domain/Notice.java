package com.wuspace.domain;

import com.wuspace.domain.shared.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "notice")
@Setter
@Getter
public class Notice extends BaseEntity {

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
	
	public Notice() {};

	public Notice(User admin, String title, String content, Timestamp timestamp) {

	}

}
