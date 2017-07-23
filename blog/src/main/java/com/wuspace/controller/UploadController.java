package com.wuspace.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuspace.application.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UploadController {
	
	@Autowired
	private UploadService uploadServiceImpl;
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/upload/uploadImage")
	@ResponseBody
	public String uploadImage(HttpServletRequest request, HttpServletResponse response) {
		return uploadServiceImpl.upload(request, response);
	}
	
	//转至UserConstroller
//	@RequestMapping(value = "/upload/uploadAvatar")
//	@ResponseBody
//	public String uploadAvatar(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		return uploadServiceImpl.uploadAvatar(request, response);
//	}
	
}
