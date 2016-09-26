package com.wuspace.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuspace.model.Comment;
import com.wuspace.model.CommentRepository;
import com.wuspace.model.Reply;
import com.wuspace.model.ReplyRepository;
import com.wuspace.model.User;
import com.wuspace.model.UserRepository;

@Controller
public class ReplyController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ReplyRepository replyRepository;

	@RequestMapping(value = "/replies/publish")
	@ResponseBody
	public Map<String, Object> publish(@RequestParam("commentId") Integer commentId,
			@RequestParam("userId") Integer userId,
			@RequestParam("replyToId") Integer replyToId,
			@RequestParam("recHtml") String recHtml) {

		Comment comment = commentRepository.findById(commentId);
		User user = userRepository.findOne(userId);
		User replyTo = userRepository.findOne(replyToId);
		if (comment == null || user == null || replyTo == null) {
			
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		Reply reply = replyRepository.save(new Reply(user, replyTo, comment, recHtml, new Timestamp(new Date().getTime())));
		map.put("id", reply.getId());
		map.put("avatar", user.getAvatar());
		map.put("createTime", reply.getCreateTime());
		map.put("msg", "success");
		
		return map;
	}

	@RequestMapping(value = "/replies/{replyId}/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> delete(@PathVariable("replyId") Integer replyId) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		/*
		 * replyRepository.delete(replyId)???????????????????
		 * */
		replyRepository.deleteById(replyId);
		if (replyRepository.exists(replyId)) {
			map.put("msg", "fail");
		} else {
			map.put("msg", "success");
		}
		return map;
	}
	
	@RequestMapping(value = "/replies/{replyId}/zan", method = RequestMethod.POST)
	@ResponseBody
	public String zan(
			@PathVariable("replyId") Integer replyId, 
			@RequestParam("userId") Integer userId) {
		
		Reply reply = replyRepository.findOne(replyId);
		User user = userRepository.findOne(userId);
		if (reply == null || user == null) {

		}
		
		if (!reply.existsZanUser(user)) {
			reply.addZanUser(user);
			replyRepository.save(reply);
			return "success";
		} else {
			return "fail";
		}
	}
	
	@RequestMapping(value = "/replies/{replyId}/cai", method = RequestMethod.POST)
	@ResponseBody
	public String cai(
			@PathVariable("replyId") Integer replyId, 
			@RequestParam("userId") Integer userId) {
		
		Reply reply = replyRepository.findOne(replyId);
		User user = userRepository.findOne(userId);
		if (reply == null || user == null) {
			
		}
		
		if (!reply.existsCaiUser(user)) {
			reply.addCaiUser(user);
			replyRepository.save(reply);
			return "success";
		} else {
			return "fail";
		}
	}

}
