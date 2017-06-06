package com.wuspace.domain;

import com.wuspace.domain.security.User;
import com.wuspace.domain.shared.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Blog extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private User user;

	private String category;

	private String title;

	private String content;

	private String tags;

	private Long viewedTimes;
}