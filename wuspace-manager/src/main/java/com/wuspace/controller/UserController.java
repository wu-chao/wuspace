package com.wuspace.controller;

import com.wuspace.service.UploadService;
import com.wuspace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class UserController {
	
	@Autowired
	private UserService userServiceImpl;
	
	@Autowired
	private UploadService uploadServiceImpl;

	@RequestMapping(value = "/users/register", method = RequestMethod.GET)
	public String register() {
		return "users/register";
	}
	
	@RequestMapping(value = "/users/register", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> register(@RequestParam("account") String account,
										@RequestParam("password") String password) {
		
		return userServiceImpl.register(account, password);
	}

	@RequestMapping(value = "/users/login", method = RequestMethod.GET)
	public String login() {
		return "users/login";
	}
	
	@RequestMapping(value = "/users/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(@RequestParam("account") String account, 
			@RequestParam("password") String password, 
			@RequestParam("cookieMark") Integer cookieMark, 
			HttpServletRequest request, HttpServletResponse response) {

		return userServiceImpl.login(account, password, cookieMark, request, response);
	}
	
	/**
	 * 退出登录
	 * @param userId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/users/{userId}/logout", method = RequestMethod.GET)
	public String logout(@PathVariable("userId") Integer userId, 
			HttpServletRequest request, HttpServletResponse response){
		
		userServiceImpl.logout(request, response);
		return "redirect:/blogs";
	}

	/*@RequestMapping(value = "/users/{userId}/uploadAvatar")
	@ResponseBody
	public String uploadAvatar(
			@PathVariable("userId") Integer userId, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		User user = userRepository.findOne(userId);
		if (user == null) {
			
		}

		String avatarfileName = uploadServiceImpl.uploadAvatar(request, response);
		user.setAvatar(avatarfileName);
		userRepository.save(user);
		
		return "http://localhost:8080/blog/resources/images/avatars/" + avatarfileName;
	}
*/


}
