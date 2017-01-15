package com.wuspace.controller;

import com.wuspace.domain.Blog;
import com.wuspace.domain.BlogRepository;
import com.wuspace.domain.Comment;
import com.wuspace.domain.CommentRepository;
import com.wuspace.domain.security.User;
import com.wuspace.domain.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;

	@RequestMapping(value = "/comments/publish", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> publish(
			@RequestParam("userId") Integer userId,
			@RequestParam("blogId") Integer blogId,
			@RequestParam("content") String content) {

		Map<String, Object> map = new HashMap<String, Object>();
		User user = userRepository.findOne(userId);
		Blog blog = blogRepository.findOne(blogId);
		if (user == null || blog == null) {
			
		}

		Comment comment = commentRepository.save(new Comment(user, blog, content, new Timestamp(new Date().getTime())));
		map.put("id", comment.getId());
		map.put("avatar", user.getAvatar());
		map.put("createTime", comment.getCreatedAt());
		map.put("msg", "success");

		return map;
	}
	
	@RequestMapping(value = "/comments/{commentId}/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> delete(@PathVariable("commentId") Integer commentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		commentRepository.delete(commentId);
		if (commentRepository.exists(commentId)) {
			map.put("msg", "fail");
		} else {
			map.put("msg", "success");
		}
		return map;
	}
	
	/**
	 * 赞评论
	 * @param commentId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/comments/{commentId}/zan", method = RequestMethod.POST)
	@ResponseBody
	public String zan(
			@PathVariable("commentId") Integer commentId, 
			@RequestParam("userId") Integer userId) {
		
		Comment comment = commentRepository.findOne(commentId);
		User user = userRepository.findOne(userId);
		if (comment == null || user == null) {
			
		}
		
		if (!comment.existsZanUser(user)) {
			comment.addZanUser(user);
			commentRepository.save(comment);
			return "success";
		} else {
			return "fail";
		}
	}
	
	/**
	 * 踩评论
	 * @param commentId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/comments/{commentId}/cai", method = RequestMethod.POST)
	@ResponseBody
	public String cai(
			@PathVariable("commentId") Integer commentId, 
			@RequestParam("userId") Integer userId) {
		
		Comment comment = commentRepository.findOne(commentId);
		User user = userRepository.findOne(userId);
		if (comment == null || user == null) {
			
		}
		
		if (!comment.existsCaiUser(user)) {
			comment.addCaiUser(user);
			commentRepository.save(comment);
			return "success";
		} else {
			return "fail";
		}
	}
}
