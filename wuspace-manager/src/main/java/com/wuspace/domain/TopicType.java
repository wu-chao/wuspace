package com.wuspace.domain;

import java.util.ArrayList;
import java.util.List;

public enum TopicType {

	/**
	 * 电影
	 */
	Movie(0, "电影"),

	/**
	 * 电视剧
	 */
	TVshow(1, "电视剧"),

	/**
	 * 综艺
	 */
	Variety(2, "综艺"),

	/**
	 * 明星
	 */
	Star(3, "明星"),

	/**
	 * 动漫
	 */
	Cartoon(4, "动漫"),

	/**
	 * 音乐
	 */
	Music(5, "音乐"),

	/**
	 * 阅读
	 */
	Read(6, "阅读"),

	/**
	 * 自然
	 */
	Nature(7, "自然"),

	/**
	 * 动物
	 */
	Animal(8, "动物"),

	/**
	 * 植物
	 */
	Plant(9, "植物"),

	/**
	 * 旅游
	 */
	Travel(10, "旅游"),

	/**
	 * 美食
	 */
	Food(11, "美食"),

	/**
	 * 星座
	 */
	Constellation(12, "星座"),

	/**
	 * 娱乐
	 */
	Entertainment(13, "娱乐"),

	/**
	 * 摄影
	 */
	Photography(14, "摄影"),

	/**
	 * 时尚
	 */
	Fashion(15, "时尚"),

	/**
	 * 艺术
	 */
	Art(16, "艺术"),

	/**
	 * 服饰
	 */
	Clothing(17, "服饰"),

	/**
	 * DIY
	 */
	DIY(18, "DIY"),

	/**
	 * 舞蹈
	 */
	Dance(19, "舞蹈"),

	/**
	 * 运动
	 */
	Sport(20, "运动"),

	/**
	 * 竞赛
	 */
	Competition(21, "竞赛"),

	/**
	 * 游戏
	 */
	Game(22, "游戏"),

	/**
	 * 生活
	 */
	Life(23, "生活"),

	/**
	 * 校园生活
	 */
	School(24, "校园生活"),

	/**
	 * 情感
	 */
	Emotion(25, "情感"),

	/**
	 * 故事
	 */
	Story(26, "故事"),

	/**
	 * 历史
	 */
	History(27, "历史"),

	/**
	 * 文化
	 */
	Culture(28, "文化"),

	/**
	 * 语言
	 */
	Language(29, "语言"),

	/**
	 * 教育
	 */
	Education(30, "教育"),

	/**
	 * 留学
	 */
	StudyAbroad(31, "留学"),

	/**
	 * 汽车
	 */
	Car(32, "汽车"),

	/**
	 * 科技
	 */
	Technology(33, "科技"),

	/**
	 * 互联网
	 */
	Internet(34, "互联网"),

	/**
	 * 编程开发
	 */
	Programming(35, "编程开发"),

	/**
	 * 社会
	 */
	Society(36, "社会"),

	/**
	 * 军事
	 */
	Military(37, "军事"),

	/**
	 * 国际
	 */
	International(38, "国际"),
	
	
	
	/**
	 * 绘画
	 */
	Draw(39, "绘画"),
	
	/**
	 * 城市
	 */
	City(40, "城市风光"),
	
	/**
	 * 农村风光
	 */
	Village(41, "农村风光"),
	
	/**
	 * 数码电子
	 */
	Digital(42, "数码电子");

	private Integer key;
	private String value;

	private TopicType(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

	public static String getValueByKey(Integer key) {
		for (TopicType t : TopicType.values()) {
			if (t.getKey() == key) {
				return t.getValue();
			}
		}
		return "";
	}

	public static List<TopicType> getTopicTypes() {
		List<TopicType> topicTypes = new ArrayList<TopicType>();
		for (TopicType t : TopicType.values()) {
			topicTypes.add(t);
		}
		return topicTypes;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
