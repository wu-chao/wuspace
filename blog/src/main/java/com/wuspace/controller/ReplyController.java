package com.wuspace.controller;

import com.wuspace.domain.CommentRepository;
import com.wuspace.domain.Reply;
import com.wuspace.domain.ReplyRepository;
import com.wuspace.domain.security.User;
import com.wuspace.domain.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
	public Map<String, Object> publish() {

		return null;
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
