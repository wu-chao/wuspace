package com.wuspace.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BlogSearch {
	private Integer id;
	private Integer topicType;
	private String title;
	private String content;
	private String publishTime;
	private Integer viewedTimes;
	private String author;
	private Integer authorId;
	
	public BlogSearch() {}
	
	public BlogSearch(Integer id) {
		this.id = id;
	}

	public BlogSearch(Integer id, Integer topicType, String title, String content,
			String publishTime, Integer viewedTimes, String author,
			Integer authorId) {
		super();
		this.id = id;
		this.topicType = topicType;
		this.title = title;
		this.content = content;
		this.publishTime = publishTime;
		this.viewedTimes = viewedTimes;
		this.author = author;
		this.authorId = authorId;
	}
	
	public String getTopicTypeByKey() {
		return TopicType.getValueByKey(this.topicType);
	}
}
